import React, { Component } from 'react';

class SportElement extends Component{

    constructor(props) {
        super(props);
    }

    render() {
        const sport = this.props.sport;
        console.log(sport);

        return(
            <div key={ sport.id }>
                <h4>{sport.name}</h4>

                <p>{sport.regularity}</p>
                <p>{sport.duration}</p>
                <p>{sport.startTime}</p>

                {(sport.isOfficial === true) ? 
                (<p>Official</p>) : (<p>Not official</p>)}

                <button onClick={() => this.props.onDeleteTodo(sport.id)}>&#10008;</button>

            </div>
        );
    }

    
}

export default SportElement;