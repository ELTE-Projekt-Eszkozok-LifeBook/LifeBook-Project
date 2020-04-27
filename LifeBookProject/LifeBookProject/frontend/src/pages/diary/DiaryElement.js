import React, { Component } from 'react';

class DiaryElement extends Component{

    constructor(props) {
        super(props);
    }

    render() {
        const diary = this.props.diary;

        return(
            <div key={ diary.id }>
                <p>{diary.text}</p>

                {diary.image ? (
                <p>van kép</p>) : null}

                {diary.video ? (
                <video width="320" height="240" controls>
                    <source src={diary.video} type="video/mp4"></source>
                    <source src={diary.video} type="video/ogg"></source>
                    <source src={diary.video} type="video/avi"></source>
                    Your browser does not support the video tag.
                </video>) : null}

                <div>
                    <p>{diary.currentMood}</p>
                    <p>{diary.date}</p>
                </div>

            </div>
        );
    }

    

}

export default DiaryElement;