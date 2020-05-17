import React, { Component }
from 'react';
import FinancialCategory from './FinancialCategory';
import {emptyFinancial}
from '../../domain/EmptyElems';
import {financialCategories}
from '../../domain/Enums';
import {get, modify, post, remove}
from '../../utilities/HTTPRequests';
import './Financial.css';

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
        if (body){
this.setState({ costs: body, isLoading: false });
        const financialLists = this.categories.map(category => (
                <FinancialCategory
    category = { category }
    costs = {this.costs}>
</FinancialCategory>

                ));
        if (financialLists){
this.setState({ financialLists: financialLists, isLoading: false });
        }
}
}

async postData(){
let d = new Date();
        let data = emptyFinancial;
        data.amount = document.getElementById("amountInput").value;
        data.date = d.getFullYear() + "-" + (d.getMonth() + 1) + "-" + d.getDate();
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
                <div className="background-6">
                    <div className="title">
                        <span> Never spend your money before you have earned it.</span>
                    </div>
                </div>
                <div className='financialFormTitle'>
                    <h2>Financials</h2>
                    <p>Here you can manage your financials.</p>


                    <form>
                        <label htmlFor="amount">
                            <input type='text' id='amountInput' name='amount' placeholder='How much money?'></input></label>
                        <label htmlFor="description">
                            <input type='text' id='descriptionInput' name='description' placeholder='Write here the description'></input></label>
                        <div className="moneycategoryselect">
                            <label htmlFor="categories">Category:</label>
                            <select className="select-css-money" id="categoriesInput" name="categories">
                                {this.categories.map((value, index) => {
                        return <option value={value} key={"category" + index}>{value.toLowerCase()}</option>
                        })}
                            </select>
                        </div>
                        <button className="submitbut" type="submit" onClick={() => this.postData()} >Save</button>
                    </form>
                </div>

                <div className="background-financiallist">
                    <div className="text">
                        <span>My financial list</span>
                    </div>
                </div>

                <div className="financials"> { financialLists } </div>
                </>
                );
}

}

export default FinancialPage;