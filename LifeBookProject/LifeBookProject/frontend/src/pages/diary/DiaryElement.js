import React, { Component } from 'react';
import {emptyDiary} from '../../domain/EmptyElems';
import {get, modify, post, remove} from '../../utilities/HTTPRequests';

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
        const form = document.getElementById("modifyForm");
        if(form.style.display == "none"){
            form.style.display = "block";
        } else {
            form.style.display = "none";
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
        
        const form = document.getElementById("modifyForm");
        form.style.display = "none";
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

                <button onClick={() => this.modifyDiaryButton()}>Modify</button>

                <form id={diary.id + "modifyForm"}>
                    <textarea id={diary.id + 'textInput'} name='text' data-state="textValue" value={this.state.textValue} onChange={e => this.handleChange(e.target)}></textarea>
                    <div className="right-side">
                    <label htmlFor="mood">Mood</label>
                    <input type='text' id={diary.id +'moodInput'} name='mood' data-state="moodValue" value={this.state.moodValue} onChange={e => this.handleChange(e.target)}></input><br></br>
                    <label htmlFor="image">Image</label>
                    <input type='text' id={diary.id + 'imgInput'} name='image' data-state="imgValue" value={this.state.imgValue} onChange={e => this.handleChange(e.target)}></input><br></br>
                    <label htmlFor="video">Video</label>
                    <input type='text' id={diary.id + 'videoInput'} name='video' data-state="videoValue" value={diary.video} onChange={e => this.handleChange(e.target)}></input><br></br>
                    <button type="submit" onClick={() => this. modifyDiary(diary.id)}>Save</button>
                    </div>
                </form>

            </div>
        );
    }

    

}

export default DiaryElement;