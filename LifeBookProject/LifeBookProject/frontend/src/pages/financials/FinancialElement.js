import React, { Component } from 'react';

class FinancialElement extends Component{

    constructor(props) {
        super(props);
    }

    render() {
        const stat = this.props.stat;
        console.log(stat);

        return(
            <div key={ "stat" + toString(stat.id) }>

                <p>{stat.date}</p>
                <p>{stat.amount}</p>
                <p>{stat.description}</p>
                <button onClick={() => this.props.onDeleteTodo(stat.id)}>&#10008;</button>

            </div>
        );
    }

}

export default FinancialElement;