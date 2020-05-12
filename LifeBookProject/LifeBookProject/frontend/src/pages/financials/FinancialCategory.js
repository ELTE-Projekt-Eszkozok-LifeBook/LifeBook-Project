import React, { Component } from 'react';
import FinancialElement from './FinancialElement';

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

    const response = await fetch('http://localhost:8080/financial/category/' + category);
    const body = await response.json();

    const response2 = await fetch('http://localhost:8080/financial/costs/' + category.toUpperCase());
    //const categoryCost = await response2.json();
    //console.log(categoryCost);

    if(body){
      this.setState({ financials: body, isLoading: false , categoryCost: response2});
    }
  }

  async remove(id) {
    await fetch(`http://localhost:8080/financial/delete/${id}`, {
      method: 'DELETE',
      headers: {
        'Accept': 'application/json',
        'Content-Type': 'application/json'
      }
    }).then(() => {
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
      <>
        {(financialList.length !== 0) ? (
        <>
          <h3>{this.props.category}</h3>
          <div> { financialList } </div>

          <div id="stats">
            <p>Category costs:</p>
            <p>{this.categoryCost}</p>
            <p>{this.categoryCost / this.props.costs} %</p>
          </div>

        </>
        ) : null}
      </>
    );

  }

}

export default FinancialCategory;