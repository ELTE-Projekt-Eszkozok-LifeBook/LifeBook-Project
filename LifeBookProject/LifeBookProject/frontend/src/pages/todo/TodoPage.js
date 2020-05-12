import React, { Component } from 'react';
import TodoCategory from './TodoCategory';

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
        <h2>Todo</h2>


        <form>
          <h3>Upload</h3>
          <label htmlFor="text">Todo</label>
          <input type='text' id='textInput' name='text'></input>

          <label htmlFor="categories">Choose category:</label>
          <select id="categories" name="categories"> {/* megcsinálni! */}
            {this.categories.map((value, index) => {
              return <option value={value} key={index}>{value.toLowerCase()}</option>
            })}
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