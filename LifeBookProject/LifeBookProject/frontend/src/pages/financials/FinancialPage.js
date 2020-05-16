import React, { Component } from 'react';
import FinancialCategory from './FinancialCategory';
import {emptyFinancial} from '../../domain/EmptyElems';
import {financialCategories} from '../../domain/Enums';
import {get, modify, post, remove} from '../../utilities/HTTPRequests';

class FinancialPage extends Component{

  emptyItem = {
    amount: '',
    date: '',
    description: '',
    category: ''
  };

  constructor(props){
    super(props);
    this.state = {
        isLoading: true,
        financialLists: null,
        costs: 0
    };
    this.categories = financialCategories;
    this.componentDidMount = this.componentDidMount.bind(this);
    this.postData = this.postData.bind(this);
  }

  async componentDidMount() {
    const response = await get('http://localhost:8080/financial/costs');
    const body = await response.json();
    console.log(body);

    if(body){
      this.setState({ costs: body, isLoading: false });

      const financialLists = this.categories.map(category => (
        <FinancialCategory
            category = { category }
            costs = {this.costs}>
        </FinancialCategory>

      ));
  
      if(financialLists){
        this.setState({ financialLists: financialLists, isLoading: false });
      }
    }
  }

  async postData(){
    let d = new Date();
    let data = emptyFinancial;
    data.amount = document.getElementById("amountInput").value;
    data.date = d.getFullYear() + "-" + (d.getMonth()+1) + "-" + d.getDate();
    data.description = document.getElementById("descriptionInput").value;
    data.category = document.getElementById("categoriesInput").value;

    await post(`http://localhost:8080/financial`, data).then(() => {
      this.componentDidMount();
    });
  }


  render() {
    const {financialLists, isLoading} = this.state;

    if (isLoading) {
      return <p>Loading...</p>;
    }

    return(
      <>
        <h2>Financials</h2>


        <form>
          <h3>Upload</h3>
          <label htmlFor="amount">amount</label>
          <input type='text' id='amountInput' name='amount'></input>
          <label htmlFor="description">description</label>
          <input type='text' id='descriptionInput' name='description'></input>

          <label htmlFor="categories">Choose category:</label>
          <select id="categoriesInput" name="categories">
            {this.categories.map((value, index) => {
              return <option value={value} key={"category"+index}>{value.toLowerCase()}</option>
            })}
          </select>

          <button type="submit" onClick={() => this.postData()} >Save</button>
        </form>


        <div> { financialLists } </div>
      </>
    );
  }

}

export default FinancialPage;