import React, { Component } from 'react';
import SportElement from './SportElement';
import {emptySport} from '../../domain/EmptyElems'
import './Sport.css';
import {get, modify, post, remove} from '../../utilities/HTTPRequests';
import { sportRegularity } from '../../domain/Enums';


class SportPage extends Component{


    constructor(props){
        super(props);
        this.state = {
            isLoading: true,
            sports: []
        };
        this.regularity = sportRegularity;
        this.postSport = this.postSport.bind(this);
        this.remove = this.remove.bind(this);
      }
    
      async componentDidMount() {
        const response = await get('http://localhost:8080/sportactivity');
        const body = await response.json();
        console.log(body);
        if(body){
          this.setState({ sports: body, isLoading: false });
        }
      }
    
      async postSport(){
        let sport = emptySport;
        sport.name = document.getElementById("nameInput").value;
        sport.regularity = document.getElementById("regularity").value;
        sport.duration = document.getElementById("durationInput").value;
        sport.startTime = document.getElementById("startInput").value;
        sport.isOfficial = document.getElementById("isOfficial").value;
    
        await post(`http://localhost:8080/sportactivity`, sport)
        .then(() => {
          this.componentDidMount();
        });
      }
    
      async remove(name) {
        await remove(`http://localhost:8080/sportactivity/delete/${name}`)
        .then(() => {
          let updatedSports = [...this.state.sports].filter(i => i.name !== name);
          this.setState({sports: updatedSports});
        });
      }
    
    
      render() {
        const {sports, isLoading} = this.state;
    
        if (isLoading) {
          return <p>Loading...</p>;
        }
    
        const sportList = sports.map(sport => (
          <SportElement
              sport = { sport }
              onDeleteSport = {this.remove}>
          </SportElement>
        ))

        return(
                <>
                <div className="background-4">
                    <div className="title">
                        <span> Find your own inner peace by doing sport daily.</span>
                    </div>
                </div>
                <div className='sportFormTitle'>
                    <h2>My sport habits</h2>
                    <p>Now you can manage your sport habits.</p>
                <form>
                    <div>
                        <input type='text' id='nameInput' name='name' placeholder='What sport do you do...'></input>
                    </div>
                    <div className='check'>
                        <label className ="officialLabel" htmlFor='isOfficial'>Is it official?
                            <input type="checkbox" id="isOfficial" value='false'></input>
                            <span className="checkmark"></span>
                        </label>
                    </div>
                    <div>
                        <input type='text' id='durationInput' name='duration' placeholder='Duration'></input>
                    </div>
                    <div>
                        <input type='text' id='startInput' name='startTime' placeholder='Start Time'></input>
                    </div>
                    <div className="regularityselect">
                        <label htmlFor="regularity">Regularity:</label>
                        <select className="select-css-sport" id="regularity" name="regularity">
                            {this.regularity.map((value, index) => {
                                return <option value={value} key={index}>{value.toUpperCase()}</option>
                                })}
                        </select>
                    </div>
                    <div className="submit">
                        <button className = "submitbut" onClick={() => this.postSport()}>Save</button>
                    </div>
                </form>
                </div>
                
                <div className="background-sportlist">
                    <div className="text">
                        <span>My sporting habits</span>
                    </div>
                </div>
                <div className="sportList" >{ sportList }</div>
                </>
                );
    }

}

export default SportPage;