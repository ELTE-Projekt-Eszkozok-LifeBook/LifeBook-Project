import React, { Component } from 'react';
import {get, modify, post, remove, db} from '../../utilities/HTTPRequests';
import FinancialElement from './FinancialElement';
import './FinancialCategory.css';

class FinancialCategory extends Component{

  constructor(props) {
    super(props);
    this.state = {
      isLoading: true,
      financials: [],
      categoryCost: "0",
    };
    this.remove = this.remove.bind(this);
  }


  async componentDidMount() {
    const category = this.props.category;

    const response = await get(db + '/financial/category/' + category);
    const body = await response.json();

    const response2 = await get(db + '/financial/costs/' + category.toUpperCase());
    const categoryCost = await response2.text();
    console.log("cost: "+this.props.costs);
    console.log(this.props.category+" cost: "+categoryCost);

    if(body){
      this.setState({ financials: body});
    }
    if(categoryCost){
      this.setState({categoryCost: categoryCost});
      console.log(this.state.categoryCost);
    }
    if(this.state.financials && this.state.categoryCost){
      this.setState({isLoading: false});
    }
  }

  async remove(id) {
    await remove(db + `/financial/delete/${id}`).then(() => {
      let updatedFinancials = [...this.state.financials].filter(i => i.id !== id);
      this.setState({financials: updatedFinancials});
    });
  }


  render() {
    const {financials, isLoading} = this.state;

    if (isLoading) {
      return <p>Loading...</p>;
    }

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
            <p>Sum:</p>
            <p>{this.state.categoryCost}</p>

            {(this.props.category != 'INCOME') ? (
              <>
              <p>Category percentage:</p>
              <p>{Math.round(this.state.categoryCost / this.props.costs * 10000)/100} %</p>
              </>
            ) : null}
          </div>

        </div>

        ) : null}
        
      </div>
    );

  }

}

export default FinancialCategory;