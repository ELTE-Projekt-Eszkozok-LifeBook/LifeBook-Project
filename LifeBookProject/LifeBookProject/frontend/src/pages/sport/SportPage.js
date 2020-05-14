import React, { Component } from 'react';
import SportElement from './SportElement';
import './Sport.css';


class SportPage extends Component{

  emptyItem = {
    name: '',
    regularity: '',
    duration: '',
    startTime: '',
    isOfficial: '',
  };

  constructor(props){
    super(props);
    this.state = {
        isLoading: true,
        sports: null
    };
    this.regularity = ["DAILY", "WEAKLY", "MONTHLY", "EVERY_YEAR"];
    this.componentDidMount = this.componentDidMount.bind(this);
    this.postSport = this.postSport.bind(this);
  }

  async componentDidMount() {
    const response = await fetch('http://localhost:8080/sportactivity');
    const body = await response.json();
    console.log(body);
    if(body){
      this.setState({ sports: body, isLoading: false });
    }
  }

  async postSport(){
    let d = new Date();
    let sport = this.emptyItem;
    sport.name = document.getElementById("nameInput").value;
    sport.regularity = document.getElementById("regularity").value;
    sport.duration = document.getElementById("durationInput").value;
    sport.startTime = document.getElementById("startInput").value;
    sport.isOfficial = document.getElementById("isOfficial").value;

    await fetch(`http://localhost:8080/sport`, {
      method: 'POST',
      headers: {
        'Accept': 'application/json',
        'Content-Type': 'application/json'
      },
      body:sport
    }).then(() => {
      this.componentDidMount();
    });
  }

  async remove(id) {
    await fetch(`http://localhost:8080/sport/delete/${id}`, {
      method: 'DELETE',
      headers: {
        'Accept': 'application/json',
        'Content-Type': 'application/json'
      }
    }).then(() => {
      let updatedSports = [...this.state.sports].filter(i => i.id !== id);
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
          onDeleteTodo = {this.remove}>
      </SportElement>
    ))


    return(
     <>
		<div class="background-4">
			<div class="title">
				<span> Find your own inner peace by doing sport daily.</span>
			</div>
		</div>
		<div class='sportFormTitle'>
			<h2>My sport habits</h2>
			<p>Now you can manage your sport habits.</p>
		</div>
		<form>
			<div>
				<input type='text' id='nameInput' name='name' placeholder='What sport do you do...'></input>
			</div>
			<div class='check'>
				<label class ="officialLabel" htmlFor='isOfficial'>Is it official?
					<input type="checkbox" id="isOfficial" value='false'></input>
					<span class="checkmark"></span>
				</label>
			</div>
			<div>
				<input type='text' id='durationInput' name='duration' placeholder='Duration'></input>
			</div>
			<div>
				<input type='text' id='startInput' name='startTime' placeholder='Start Time'></input>
			</div>
			<div class="regularityselect">
				<label htmlFor="regularity">Regularity</label>
				<select class="select-css-sport" id="regularity" name="regularity">
					{this.regularity.map((value, index) => {
					return <option value={value} key={index}>{value.toUpperCase()}</option>
				})}
				</select>
			</div>
			<div class="submit">
				<button class = "submitbut" type="submit" onClick={() => this.postSport()}>Save</button>
			</div>
		</form>
		<div> { sportList } </div>
	 </>
    );
  }

}

export default SportPage;