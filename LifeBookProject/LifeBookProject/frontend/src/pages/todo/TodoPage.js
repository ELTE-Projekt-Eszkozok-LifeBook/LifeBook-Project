import React, { Component } from 'react';
import TodoCategory from './TodoCategory';
import './Todo.css';
import { emptyTodo } from '../../domain/EmptyElems';
import { todoCategories } from '../../domain/Enums';
import {get, modify, post, remove, db} from '../../utilities/HTTPRequests';

class TodoPage extends Component{

    constructor(props){
        super(props);
        this.state = {
            isLoading: true,
            todoLists: null
        };
        this.categories = todoCategories;
    
        this.componentDidMount = this.componentDidMount.bind(this);
        this.postTodo = this.postTodo.bind(this);
      }
    
      async componentDidMount() {
        const todoLists = this.categories.map(category => (
            <TodoCategory
                category = { category }>
            </TodoCategory>
          ));
    
        if(todoLists){
          this.setState({ todoLists: todoLists, isLoading: false });
        }
      }
    
    
      async postTodo(){
        let todo = emptyTodo;
        todo.todoText = document.getElementById("toDoTextInput").value;
        todo.checked = false;
        todo.important = document.getElementById("important").value;
        todo.category = document.getElementById("categories").value;
    
        await post(db + `/todo`, todo)
        .then(() => {
          this.componentDidMount();
        });
      }
    

      render() {
    
        const {todoLists, isLoading} = this.state;
    
        if (isLoading) {
          return <p>Loading...</p>;
        }
        
        return(
            <>
                    <div>
                        <div className="background-3">
                            <div className="title">
                                <span> Don't forget to drink some water during your everyday routine.</span>
                            </div>
                        </div>
                        <div>
                            <div className="upload">
                                <h2>My To-do list</h2>
                                <p>Do you feel like you are not so good at time management? Always late for the meetings, because you forgot them again? Try to manage a to-do list, so you will never forget to water your plants, or go to a meeting.</p>

                                <form>
                                    <label className= "container" htmlFor='important'>Important
                                        <input type="checkbox" id="important" value='false'></input>
                                        <span className="checkmark"></span>
                                    </label>
                                    <textarea id='toDoTextInput' name='text' placeholder="Write here what you have to do"></textarea>
                                    <div className="categoryselect">
                                        <label htmlFor="categories">Category:</label>
                                        <select className="select-css" id="categories" name="categories"> 
                                            {this.categories.map((value, index) => {
                            return <option value={value} key={index}>{value.toUpperCase()}</option>
                            })}
                                        </select>
                                    </div>
                                    <div className="submit">
                                        <button className="submitbut" onClick={() => this.postTodo()}>Save</button>
                                    </div>
                                </form>
                            </div>
                        </div>

                        <div className="todolistbackground">
                            <div className="text">
                                <span>My tasks</span>
                            </div>
                        </div>

                        <div className="todolist"> { todoLists } </div>
                    </div>

                    </>      

                    );
}

}

export default TodoPage;