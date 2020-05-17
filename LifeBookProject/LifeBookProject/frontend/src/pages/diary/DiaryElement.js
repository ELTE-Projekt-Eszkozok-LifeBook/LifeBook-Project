import React, { Component } from 'react';
import {emptyDiary} from '../../domain/EmptyElems';
import {get, modify, post, remove} from '../../utilities/HTTPRequests';
import './DiaryElement.css';

class DiaryElement extends Component{

    constructor(props) {
        super(props);
        this.state = {
            textValue: props.diary.text,
            moodValue: props.diary.currentMood,
            imgValue: props.diary.image,
            videoValue: props.diary.video
        };
        this.handleChange = this.handleChange.bind(this);
        this.modifyDiary = this.modifyDiary.bind(this);
    }


    handleChange(target) {
        this.setState({
            [target.getAttribute("data-state")]: target.value}
        );
    }

    async modifyDiaryButton(id){
        const form = document.getElementById(id + "modifyForm").style;
        if(form.display == "block"){
            form.display = "none";
        } else {
            form.display = "block";
        }
    }

    async modifyDiary(id){
        let d = new Date();
        let diary = emptyDiary;
        diary.text = this.state.textValue;
        diary.image = this.state.imgValue;
        diary.video = this.state.videoValue;
        diary.currentMood = this.state.moodValue;
        diary.date = diary.date;

        await modify(`http://localhost:8080/diary/update/` + this.props.diary.id, diary)
        .then(() => {
          diary.id = this.props.diary;
          this.props.diary = diary;
        });
        
        const form = document.getElementById(id + "modifyForm").style;
        form.display = "none";
    }
    
    
    render() {
        const diary = this.props.diary;

        return(
            
            <div className="elementEntryList" key={ diary.id }>
                <label className="dateLabel">Date: {diary.date}</label>
                <div className="elementEntry">
                
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
                    <p>My mood was {diary.currentMood}</p>
                </div>
                </div>

                <button className="modifyBut" onClick={() => this.modifyDiaryButton(diary.id)}>Modify</button>
                
                <form className="elementForm" id={diary.id + "modifyForm"}>
                    <textarea className="elementTextInput" id={diary.id + 'textInput'} name='text' data-state="textValue" value={this.state.textValue} placeholder="Write your day!" onChange={e => this.handleChange(e.target)}></textarea>
                   
                    <input className="elementMoodInput" type='text' id={diary.id +'moodInput'} name='mood' data-state="moodValue" value={this.state.moodValue} placeholder="How are you feeling?" onChange={e => this.handleChange(e.target)}></input><br></br>
                    <input className="elementImgInput" type='text' id={diary.id + 'imgInput'} name='image' data-state="imgValue" value={this.state.imgValue} placeholder="Add image by url" onChange={e => this.handleChange(e.target)}></input><br></br>
                    <input className="elementVideoInput" type='text' id={diary.id + 'videoInput'} name='video' data-state="videoValue" value={diary.video} placeholder="Add video by url" onChange={e => this.handleChange(e.target)}></input><br></br>
                    <button className="elementBut" type="submit" onClick={() => this. modifyDiary(diary.id)}>Save</button>
                    
                </form>

            </div>
        );
    }

    

}

export default DiaryElement;