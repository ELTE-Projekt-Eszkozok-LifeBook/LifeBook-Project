import React, { Component } from 'react';

class TodoElement extends Component{

    constructor(props) {
        super(props);
    }

    render() {
        const todo = this.props.todo;

        return(
            <div key={ todo.id }>   {/*ha rákattintunk lehessen törölni, illetve módosítni*/}
                <p>{todo.todoText}</p>

                <div>
                    <input type="checkbox" id="done" value={todo.checked}></input>
                    {(todo.important === true) ? (
                    <p>&#10082;</p>) : null}
                    <button onClick={() => this.props.onDeleteTodo(todo.id)}>&#10008;</button>
                </div>

            </div>
        );
    }

    
}

export default TodoElement;