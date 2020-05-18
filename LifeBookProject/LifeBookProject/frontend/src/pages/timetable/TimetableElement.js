import React, { Component } from 'react';

class TimetableElement extends Component {

    constructor(props) {
        super(props);
    }

    render() {
        const event = this.props.event;
        
        return(
                <div key={event.id}>
                    <p> {event.event} </p>
                    <p> {event.time} </p>
                    <p> {event.note} </p>
                </div>
        );
    }
}

export default TimetableElement;