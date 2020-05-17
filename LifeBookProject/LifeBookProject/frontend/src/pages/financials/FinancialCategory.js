import React, { Component } from 'react';
import {get, modify, post, remove} from '../../utilities/HTTPRequests';
import FinancialElement from './FinancialElement';
import './FinancialCategory.css';

class FinancialCategory extends Component{

  constructor(props) {
    super(props);
    this.state = {
      isLoading: true,
      financials: [],
      categoryCost: 0
    };
    this.remove = this.remove.bind(this);
  }


  async componentDidMount() {
    const category = this.props.category;

    const response = await get('http://localhost:8080/financial/category/' + category);
    const body = await response.json();

    const response2 = await get('http://localhost:8080/financial/costs/' + category.toUpperCase());
    //const categoryCost = await response2.json();
    //console.log(categoryCost);

    if(body){
      this.setState({ financials: body, isLoading: false , categoryCost: response2});
    }
  }

  async remove(id) {
    await remove(`http://localhost:8080/financial/delete/${id}`).then(() => {
      let updatedFinancials = [...this.state.financials].filter(i => i.id !== id);
      this.setState({financials: updatedFinancials});
    });
  }


  render() {
    const {financials, isLoading} = this.state;

    if (isLoading) {
      return <p>Loading...</p>;
    }

    console.log(financials);
    const financialList = financials.map(financial => (
      <FinancialElement
          stat = { financial }
          onDeleteTodo = {this.remove}>
      </FinancialElement>
    ))

    return(
        <div>{(financialList.length !== 0) ? (
        <div className="allFinancial">
          <div className="elementFinancial">
          <h3 className="financialName">{this.props.category}</h3>
          <div className="infos"> { financialList } </div>
        </div>
        
          <div className="percent" id="stats">
            <p>Category costs:</p>
            <p>{this.categoryCost}</p>
            <p>{this.categoryCost / this.props.costs} %</p>
          </div>
          
        </div>
        ) : null}</div>
    );

  }

}

export default FinancialCategory;