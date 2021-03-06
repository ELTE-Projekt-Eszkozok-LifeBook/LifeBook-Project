import React, { Component } from 'react';
import {db} from '../../domain/Enums';
import {get, modify, post, remove} from '../../utilities/HTTPRequests';

class TodoElement extends Component{

    constructor(props) {
        super(props);
        this.state = {
            checked: this.props.todo.checked
        };
        this.handleChange = this.handleChange.bind(this);
    }

    handleChange(target) {
        console.log(target.checked);
        this.setState({
          checked: target.checked
        });
        let todo = this.props.todo;
        todo.checked = this.state.checked;
        modify(db + '/todo/update/' + this.props.todo.id, todo);  
    }

    render() {
        const todo = this.props.todo;

        return(
            <div key={ todo.id }>
                <p>{todo.todoText}</p>

                <div>
                    <input type="checkbox" id="done" checked={this.state.checked} onChange={(e) => this.handleChange(e.target)}></input>
                    {(todo.important === true) ? (
                    <p>&#10082;</p>) : null}
                    <button onClick={() => this.props.onDeleteTodo(todo.id)}>&#10008;</button>
                </div>

            </div>
        );
    }

    
}

export default TodoElement;