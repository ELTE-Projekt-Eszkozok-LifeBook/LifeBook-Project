import React, { Component } from 'react';
import './SportElement.css';

class SportElement extends Component{

    constructor(props) {
        super(props);
    }

    render() {
        const sport = this.props.sport;

        return(
            <div className="elementSport" key={ sport.name }>
                <h3 className="sportName">{sport.name}</h3>

                <p>{sport.duration} hours {sport.regularity}</p>
                <p>Started: {sport.startTime}</p>

                {(sport.isOfficial === true) ? 
                (<p>Doing officially</p>) : (<p>Doing by myself</p>)}

                <button onClick={() => this.props.onDeleteSport(sport.name)}>&#10008;</button>

            </div>
        );
    }

    
}

export default SportElement;