import React, { Component } from 'react';
import TimetableElement from './TimetableElement';
import './Timetable.css'
import {get, modify, post, remove, db} from '../../utilities/HTTPRequests';

class TimetablePage extends Component {

    emptyItem = {
        event: '',
        frequency: '',
        date: '',
        time: '',
        note: ''
    };

    constructor(props) {
        super(props);
        this.state = {
            isLoading: true,
            events: []
        };
        this.postEvent = this.postEvent.bind(this);
    }

    async componentDidMount() {
        const response = await fetch(db + '/timetable');
        const body = await response.json();
        if(body){
            this.setState({ events: body, isLoading: false});
        }
    }

    async postEvent() {
        let event = this.emptyItem;
        let d = event.date = Date.parse(document.getElementById("dateInput").value + document.getElementById("timeInput").value);
        event.event = document.getElementById("eventInput").value;
        event.frequency = document.getElementById("frequencyInput").value;
        event.date = Date.parse(document.getElementById("dateInput").value);
        event.time = d.toTimeString();
        event.note = document.getElementById("noteInput").value;
        
        await fetch(db + '/timetable', {
            method: 'POST',
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            },
            body:event
        }).then(() => {
            this.componentDidMount();
        });
    }

    render() {
        const{events, isLoading} = this.state;

        if (isLoading) {
            return <p>Loading</p>;
        }
        
        const eventList = events.map( event => (
            <TimetableElement 
                event = {event}>
            </TimetableElement>
        ))

        return(
                <div>
                    <div className="background-timetable1">
                        <div className="title-timetable">
                            <span>Life is easier with time management</span>
                        </div>
                    </div>
                    <div>
                        <div className="upload-timetable">
                            <h2>New Events</h2>
                            <p>blablabla</p>
                        <form className="timetable">
                            <input className="timetableEvent" type="text" id='eventInput' name='event' placeholder="Event name"></input><br></br>
                            <input className="timetableFrequency" type='text' id='frequencyInput' name='frequency' placeholder="Frequency of it"></input><br></br>
                            <input className="timetableDate" type='text' id='dateInput' name='date' placeholder="Date of it in format 'yyyy-mm-dd'"></input><br></br>
                            <input className="timetableTime" type='text' id='timeInput' name='time' placeholder="Time of it in format 'hh:mm:ss'"></input><br></br>
                            <textarea className="timetableNote" id='noteInput' name='note' placeholder="Event description..."></textarea>
                            <button className="timetableButton" type="submit" onClick={() => this.postEvent()}>Save</button>
                        </form>
                        </div>
                    </div>
                    
                    <div className="background-timetable2">
                        <div className="text-timetable">
                            <span>Schedule</span>
                        </div>
                    </div>
                    
                    <div className="events">
                        <div> { eventList } </div>
                    </div>
                </div>
                );
    }
}

export default TimetablePage;