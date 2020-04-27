import React, { Component } from 'react';

class TodoElement extends Component{

    constructor(props) {
        super(props);
    }

    render() {
        const todo = this.props.todo;

        console.log(todo.id);
        console.log(todo.todoText);
        return(
            <div key={ todo.id }>   {/*ha rákattintunk lehessen törölni, illetve módosítni*/}
                <p>{todo.todoText}</p>

                <div>
                    <input type="checkbox" id="done" value={todo.checked}></input>
                    {(todo.important === true) ? (
                    <p>!</p>) : null}
                    <button onClick={() => this.props.onDeleteTodo(todo.id)}>X</button>
                </div>

            </div>
        );
    }

    
}

export default TodoElement;