import React, { Component } from 'react';

class EatingHabitElement extends Component{

    constructor(props) {
        super(props);
    }

    render() {
        const eatingHabit = this.props.eatingHabit;
        console.log(eatingHabit);

        return(
            <div key={ eatingHabit.id }>
                <h4>{eatingHabit.name}</h4>

                <p>{eatingHabit.type}</p>

                {(eatingHabit.isOfficial === true) ? 
                (<p>Food</p>) : (<p>Drink or something else</p>)}

                <p>{eatingHabit.frequency}</p>
                <p>{eatingHabit.portion}</p>

                <button onClick={() => this.props.onDeleteTodo(eatingHabit.id)}>&#10008;</button>

            </div>
        );
    }

    
}

export default EatingHabitElement;
