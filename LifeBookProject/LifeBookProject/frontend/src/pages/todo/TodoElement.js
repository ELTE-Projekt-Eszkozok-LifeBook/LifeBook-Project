import React, { Component } from 'react';
import {get, modify, post, remove} from '../../utilities/HTTPRequests';

class TodoElement extends Component{

    constructor(props) {
        super(props);
        this.handleChange = this.handleChange.bind(this);
    }

    handleChange(target) {
        this.setState({
          [target.name]: target.value
        });
        this.props.todo.checked = target.checked;
        modify('http://localhost:8080/todo/update/' + this.props.todo.id, this.props.todo);  
    }

    render() {
        const todo = this.props.todo;

        return(
            <div key={ todo.id }>
                <p>{todo.todoText}</p>

                <div>
                    <input type="checkbox" id="done" value={todo.checked} onChange={this.handleChange}></input>
                    {(todo.important === true) ? (
                    <p>&#10082;</p>) : null}
                    <button onClick={() => this.props.onDeleteTodo(todo.id)}>&#10008;</button>
                </div>

            </div>
        );
    }

    
}

export default TodoElement;