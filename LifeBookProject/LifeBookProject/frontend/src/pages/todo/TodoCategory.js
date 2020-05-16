import React, { Component } from 'react';
import TodoElement from './TodoElement';
import {get, modify, post, remove} from '../../utilities/HTTPRequests';

class TodoCategory extends Component{

  constructor(props) {
    super(props);
    this.state = {
      isLoading: true,
      todos: []
    };
    this.remove = this.remove.bind(this);
  }


  async componentDidMount() {
    const category = this.props.category;
    const response = await get('http://localhost:8080/todo/category/' + category);
    const body = await response.json();
    if(body){
      this.setState({ todos: body, isLoading: false });
    }
  }

  async remove(id) {
    await remove(`http://localhost:8080/todo/delete/${id}`)
    .then(() => {
      let updatedTodos = [...this.state.todos].filter(i => i.id !== id);
      this.setState({todos: updatedTodos});
    });
  }


  render() {
    const {todos, isLoading} = this.state;

    if (isLoading) {
      return <p>Loading...</p>;
    }

    const todoList = todos.map(todo => (
      <TodoElement
          todo = { todo }
          onDeleteTodo = {this.remove}>
      </TodoElement>
    ))
    
    return(
      <>
        {(todoList.length !== 0) ? (
        <>
          <h3>{this.props.category}</h3>
          <div> { todoList } </div>
        </>
        ) : null}
      </>
    );

  }

}

export default TodoCategory;