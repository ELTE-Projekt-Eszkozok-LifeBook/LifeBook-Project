import React, { Component } from 'react';
import './EatingHabitElement.css';

class EatingHabitElement extends Component{

    constructor(props) {
        super(props);
    }

    render() {
        const eatingHabit = this.props.eatingHabit;

        return(
            <div className="elementEating" key={ eatingHabit.name }>
                <h3 className="eatingName">{eatingHabit.name}</h3>
                <p>Type: {eatingHabit.type}</p>

                {(eatingHabit.isFood === true) ? 
                (<div></div>) : (<div></div>)}

        <p>Frequency: {eatingHabit.frequency}</p>
        <p>Portion: {eatingHabit.portion}</p>

                <button onClick={() => this.props.onDeleteTodo(eatingHabit.name)}>&#10008;</button>

            </div>
        );
    }

    
}

export default EatingHabitElement;
