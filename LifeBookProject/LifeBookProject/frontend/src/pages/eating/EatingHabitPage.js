import React, { Component } from 'react';
import EatingHabitElement from './EatingHabitElement';
import {emptyEatingHabit} from '../../domain/EmptyElems';
import {eatingFrequency} from '../../domain/Enums';
import './Eating.css';
import {get, modify, post, remove} from '../../utilities/HTTPRequests';

class EatingHabitPage extends Component{

  constructor(props){
    super(props);
    this.state = {
        isLoading: true,
        eatingHabits: []
    };
    this.frequency = eatingFrequency;
    this.componentDidMount = this.componentDidMount.bind(this);
    this.postEatingHabit = this.postEatingHabit.bind(this);
    this.remove = this.remove.bind(this);
  }

  async componentDidMount() {
    const response = await get('http://localhost:8080/eatinghabits');
    const body = await response.json();
    if(body){
      this.setState({ eatingHabits: body, isLoading: false });
    }
  }

  async postEatingHabit(){
    let d = new Date();
    let eatingHabit = emptyEatingHabit;
    eatingHabit.name = document.getElementById("nameInput").value;
    eatingHabit.type = document.getElementById("typeInput").value.toLowerCase();
    eatingHabit.isFood = document.getElementById("isFood").value;
    eatingHabit.frequency = document.getElementById("frequencyInput").value;
    eatingHabit.portion = document.getElementById("portionInput").value;

    await post(`http://localhost:8080/eatinghabits`, eatingHabit)
    .then(() => {
      this.componentDidMount();
    });
  }

  async remove(name) {
    await remove(`http://localhost:8080/eatinghabits/delete/${name}`)
    .then(() => {
      let updatedEatingHabits = [...this.state.eatingHabits].filter(i => i.name !== name);
      this.setState({eatingHabits: updatedEatingHabits});
    });
  }


  async searchEatingHabit(){
    const type = document.getElementById('searchInput').value;
    if(type){
      const response = await get('http://localhost:8080/eatinghabits/type/' + type);
      const body = await response.json();
      if(body){
        this.setState({ eatingHabits: body, isLoading: false });
      }
    }
  }



  render() {
    const {eatingHabits, isLoading} = this.state;

    if (isLoading) {
      return <p>Loading...</p>;
    }

    const eatingHabitList = eatingHabits.map(eatingHabit => (
      <EatingHabitElement
          eatingHabit = { eatingHabit }
          onDeleteTodo = {this.remove}>
      </EatingHabitElement>
    ))

    return(
      <>
		<div className="background-5">
			<div className="title">
				<span> You can't control everything in your life, but you can control what you eat.</span>
			</div>
		</div>
		<div className='eatingFormTitle'>
			<h2>My eating habits</h2>
			<p>Here you can manage your eating habits.</p>
		</div>
	<div className="eatingForm">
    <form>
			<div className='check'>
				<label className="foodLabel" htmlFor='isFood'>Is it food?
					<input type="checkbox" id="isFood" value='false'></input>
					<span className="checkmark"></span>
				</label>
			</div>
			<input type='text' id='nameInput' name='name' placeholder='Name of the food'></input>
			<div className="frequencyselect">
				<label htmlFor="frequency">Frequency</label>
				<select className="select-css-eating" id="frequencyInput" name="frequency">
				{this.frequency.map((value, index) => {
				  return <option value={value} key={index}>{value.toUpperCase()}</option>
				})}
				</select>
			</div>
			<input type='text' id='typeInput' name='type' placeholder="Food type"></input>
			<input type='text' id='portionInput' name='portion' placeholder="Portion"></input>
          <button type="submit" onClick={() => this.postEatingHabit()}>Save</button>
    </form>
	</div>
		<div className="foodlistBackground">
			<div className="text">
				<span>Food list</span>
			</div>
		</div>
		<div className="searchfood">
        <form id='searchform'>
          <h3>Search</h3>
          <label htmlFor="type">If you want to modify an entry or just reread it you can find it by its type.</label><br></br>
          <input type='text' id='searchInput' name='type' placeholder="I'm searching for..."></input><br></br>
          <button className="searchButton" type="submit" onClick={() => this.searchEatingHabit()}>Search</button>
        </form>
		</div>

        {(eatingHabitList.length == 0) ? 
          (<p>There are no recorded foods.</p>) : (<div> { eatingHabitList } </div>)}
        
      </>
    );
  }

}

export default EatingHabitPage;
