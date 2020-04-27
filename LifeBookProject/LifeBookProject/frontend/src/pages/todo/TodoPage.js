import React, { Component } from 'react';
import TodoCategory from './TodoCategory';

class TodoPage extends Component{

  constructor(props){
    super(props);
    this.state = {
        isLoading: true,
        todoLists: null
    };
    this.categories = ["HOUSEHOLD", "SHOPPING", "SCHOOL", "UNIVERSITY", "SOMEDAY_STUFF"];

    this.componentDidMount = this.componentDidMount.bind(this);
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


  async putTodo(){
    let todo;
    todo.todoText = document.getElementById("textInput").value;
    todo.checked = false;
    todo.important = document.getElementById("important").value;
    todo.category = document.getElementById("categories").value;

    await fetch(`http://localhost:8080/todo`, {
      method: 'POST',
      headers: {
        'Accept': 'application/json',
        'Content-Type': 'application/json'
      },
      body: todo
    }).then(() => {
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
        <h2>Todo</h2>


        <form>
          <label htmlFor="text">Todo</label>
          <input type='text' id='textInput' name='text'></input>

          <label htmlFor="categories">Choose category:</label>
          <select id="categories" name="categories"> {/* megcsinálni! */}
            <option value="HOUSEHOLD">Household</option>
            <option value="SHOPPING">Shopping</option>
            <option value="SCHOOL">School</option>
            <option value="UNIVERSITY">University</option>
            <option value="SOMEDAY_STUFF">Someday stuff</option>
          </select>

          <label htmlFor='important'>Important</label>
          <input type="checkbox" id="important" value='false'></input>

          <button type="submit" onClick={() => this.putTodo()} >Save</button>
        </form>


        <div> { todoLists } </div>
      </>
    );
  }

}

export default TodoPage;