import React, { Component } from 'react';
import EatingHabitElement from './EatingHabitElement';

class EatingHabitPage extends Component{

  emptyItem = {
    name: '',
    type: '',
    isFood: '',
    frequency: '',
    portion: '',
  };

  constructor(props){
    super(props);
    this.state = {
        isLoading: true,
        eatingHabits: []
    };
    this.frequency = ["DAILY", "WEAKLY", "MONTHLY", "EVERY_YEAR"];
    this.componentDidMount = this.componentDidMount.bind(this);
    this.postEatingHabit = this.postEatingHabit.bind(this);
  }

  async componentDidMount() {
    const response = await fetch('http://localhost:8080/eatinghabits');
    const body = await response.json();
    console.log(body);
    if(body){
      this.setState({ eatingHabits: body, isLoading: false });
    }
  }

  async postEatingHabit(){
    let d = new Date();
    let eatingHabit = this.emptyItem;
    eatingHabit.name = document.getElementById("nameInput").value;
    eatingHabit.type = document.getElementById("typeInput").value.toLowerCase();
    eatingHabit.isFood = document.getElementById("isFood").value;
    eatingHabit.frequency = document.getElementById("frequencyInput").value;
    eatingHabit.portion = document.getElementById("portionInput").value;

    await fetch(`http://localhost:8080/eatinghabits`, {
      method: 'POST',
      headers: {
        'Accept': 'application/json',
        'Content-Type': 'application/json'
      },
      body:eatingHabit
    }).then(() => {
      this.componentDidMount();
    });
  }

  async remove(id) {
    await fetch(`http://localhost:8080/eatinghabits/delete/${id}`, {
      method: 'DELETE',
      headers: {
        'Accept': 'application/json',
        'Content-Type': 'application/json'
      }
    }).then(() => {
      let updatedEatingHabits = [...this.state.eatingHabits].filter(i => i.id !== id);
      this.setState({eatingHabits: updatedEatingHabits});
    });
  }


  async searchEatingHabit(){
    const type = document.getElementById('searchInput').value;
    if(type){
      const response = await fetch('http://localhost:8080/eatinghabits/type/' + type);
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
        <h2>EatingHabit Activities</h2>

        <form>
          <h3>Upload</h3>
          <label htmlFor="name">Name of the food</label>
          <input type='text' id='nameInput' name='name'></input>

          <label htmlFor="frequency">Frequency</label>
          <select id="frequencyInput" name="frequency">
            {this.frequency.map((value, index) => {
              return <option value={value} key={index}>{value.toLowerCase()}</option>
            })}
          </select>

          <label htmlFor="type">Type</label>
          <input type='text' id='typeInput' name='type'></input>
          <label htmlFor="portion">Portion</label>
          <input type='text' id='portionInput' name='portion'></input>

          <label htmlFor='isFood'>Is it food or other?</label>
          <input type="checkbox" id="isFood" value='false'></input>

          <button type="submit" onClick={() => this.postEatingHabit()}>Save</button>
        </form>


        <form>
          <h3>Search</h3>
          <label htmlFor="type">Food type</label>
          <input type='text' id='searchInput' name='type'></input>
          <button type="submit" onClick={() => this.searchEatingHabit()}>Search</button>
        </form>

        {(eatingHabitList.length == 0) ? 
          (<p>There are no recorded foods.</p>) : (<div> { eatingHabitList } </div>)}
        
      </>
    );
  }

}

export default EatingHabitPage;
