import React, { Component } from 'react';
import TodoCategory from './TodoCategory';
import './Todo.css';

class TodoPage extends Component{

  emptyItem = {
    todoText: '',
    checked: '',
    important: '',
    category: ''
  };

  constructor(props){
    super(props);
    this.state = {
        isLoading: true,
        todoLists: null
    };
    this.categories = 
      ["HOUSEHOLD", 
      "SHOPPING", 
      "SCHOOL", 
      "UNIVERSITY", 
      "SOMEDAY_STUFF"];

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
    console.log("elmentem");
    let todo = this.emptyItem;
    todo.todoText = document.getElementById("textInput").value;
    todo.checked = false;
    todo.important = document.getElementById("important").value;
    todo.category = document.getElementById("categories").value;

    console.log(todo);

    await fetch(`http://localhost:8080/todo`, {
      method: 'POST',
      headers: {
        'Accept': 'application/json',
        'Content-Type': 'application/json'
      },
      body: JSON.stringify(todo)
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
		<div>
                    <div class="background-3">
                        <div class="title">
                            <span> Don't forget to drink some water during your everyday routine.</span>
                        </div>
                    </div>
                    <div>
                        <div class="upload">
								<h2>My To-do list</h2>
								<p>Do you feel like you are not so good at time management? Always late for the meetings, because you forgot them again? Try to manage a to-do list, so you will never forget to water your plants, or go to a meeting.</p>
								
							<form>
								<label class= "container" htmlFor='important'>Important
									<input type="checkbox" id="important" value='false'></input>
									<span class="checkmark"></span>
								</label>
									<textarea id='toDoTextInput' name='text' placeholder="Write here what you have to do"></textarea>
								<div class="categoryselect">
									<label htmlFor="categories">Choose category:</label>
									<select class="select-css" id="categories" name="categories"> 
										{this.categories.map((value, index) => {
										  return <option value={value} key={index}>{value.toUpperCase()}</option>
										})}
									</select>
								</div>
								<div class="submit">
									<button class="submitbut" type="submit" onClick={() => this.putTodo()}>Save</button>
								</div>
							</form>
                        </div>
                    </div>
                                    
                    <div class="background-2">
                        <div class="text">
                            <span>Memories</span>
                        </div>
                    </div>
                                    
                    <div class="search">
                        <h2>Searching through your entries</h2>
                        <p>If you want to modify an entry or just reread it you can find it by its date.</p>
                        
                        <input type='text' id='dateInput' placeholder="Search..."></input><br></br>
                        
                        <button onClick={() => this.searchDiary()}>Search</button>
                        <button onClick={() => this.diaryList }>Show all entries</button>
                    </div>
					<div class="todolist"> { todoLists } </div>
                </div>
               
      </>      

    );
  }

}

export default TodoPage;