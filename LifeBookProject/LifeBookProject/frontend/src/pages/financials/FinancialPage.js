import React, { Component } from 'react';
import FinancialElement from './FinancialElement';

class FinancialPage extends Component{

  constructor(props){
    super(props);
    this.state = {
        isLoading: true,
        financials: null
    };
    this.categories = [    
        "HOUSEHOLD",
        "FOOD",
        "UTILITIES",
        "TAX",
        "LOAN",
        "CLOTHES",
        "OTHER" ]
    this.componentDidMount = this.componentDidMount.bind(this);
    this.postData = this.postData.bind(this);
  }

  async componentDidMount() {
    const response = await fetch('http://localhost:8080/sportactivity');
    const body = await response.json();
    console.log(body);
    if(body){
      this.setState({ financials: body, isLoading: false });
    }
  }

  async postData(){
    let d = new Date();
    let data;

    await fetch(`http://localhost:8080/financial`, {
      method: 'POST',
      headers: {
        'Accept': 'application/json',
        'Content-Type': 'application/json'
      },
      body:data
    }).then(() => {
      this.componentDidMount();
    });
  }


  render() {
    const {financials, isLoading} = this.state;

    if (isLoading) {
      return <p>Loading...</p>;
    }

    const financialList = financials.map(elem => (
      <FinancialElement
          stat = { elem }>
      </FinancialElement>
    ))


    return(
      <>
        <h2>Financial Stats</h2>

        <form>
          <h3>Upload</h3>
          <label htmlFor="name">Name</label>
          <input type='text' id='nameInput' name='name'></input>

          <label htmlFor="regularity">Regularity</label>
          <select id="regularity" name="regularity">
            <option value="HOUSEHOLD">Household</option> {/* megcsinálni! */}
            <option value="SHOPPING">Shopping</option>
            <option value="SCHOOL">School</option>
            <option value="UNIVERSITY">University</option>
            <option value="SOMEDAY_STUFF">Someday stuff</option>
          </select>

          <label htmlFor="duration">Duration</label>
          <input type='text' id='durationInput' name='duration'></input>
          <label htmlFor="startTime">Start Time</label>
          <input type='text' id='startInput' name='startTime'></input>

          <label htmlFor='isOfficial'>Is it official?</label>
          <input type="checkbox" id="isOfficial" value='false'></input>

          <button type="submit" onClick={() => this.postData()}>Save</button>
        </form>

        <div> { financialList } </div>
      </>
    );
  }

}

export default FinancialPage;