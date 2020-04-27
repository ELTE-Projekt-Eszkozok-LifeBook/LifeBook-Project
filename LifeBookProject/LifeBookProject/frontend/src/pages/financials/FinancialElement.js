import React, { Component } from 'react';

class FinancialElement extends Component{

    constructor(props) {
        super(props);
    }

    render() {
        const stat = this.props.stat;
        console.log(stat);

        return(
            <div key={ stat.id }>
                <h4>{sport.name}</h4>

                <p>{sport.regularity}</p>
                <p>{sport.duration}</p>
                <p>{sport.startTime}</p>

                {(sport.isOfficial === true) ? 
                (<p>Official</p>) : (<p>Not official</p>)}

            </div>
        );
    }

}

export default FinancialElement;