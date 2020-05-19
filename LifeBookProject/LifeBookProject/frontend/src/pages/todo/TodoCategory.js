import React, { Component } from 'react';
import TodoElement from './TodoElement';
import {get, modify, post, remove, db} from '../../utilities/HTTPRequests';
import './TodoCategory.css'

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
    const response = await get(db + '/todo/category/' + category);
    const body = await response.json();
    if(body){
      this.setState({ todos: body, isLoading: false });
    }
  }

  async remove(id) {
    await remove(db + `/todo/delete/${id}`)
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
        <div className="todoList">
          <h3 className="todoName">{this.props.category}</h3>
          <div> { todoList } </div>
        </div>
        ) : null}
      </>
    );

  }

}

export default TodoCategory;