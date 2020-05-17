import React, { Component } from 'react';
import './FinancialElement.css';

class FinancialElement extends Component{

    constructor(props) {
        super(props);
    }

    render() {
        const stat = this.props.stat;
        console.log(stat);

        return(
            <div clasName="infos" key={ "stat" + toString(stat.id) }>

                <p>{stat.date}</p>
                <p>{stat.amount}</p>
                <p>{stat.description}</p>
                <button onClick={() => this.props.onDeleteTodo(stat.id)}>&#10008;</button>

            </div>
        );
    }

}

export default FinancialElement;