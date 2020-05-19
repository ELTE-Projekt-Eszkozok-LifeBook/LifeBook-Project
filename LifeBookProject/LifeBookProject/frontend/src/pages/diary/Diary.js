import React, { Component } from 'react';
import DiaryElement from './DiaryElement';
import {emptyDiary, db} from '../../domain/EmptyElems';
import {get, modify, post, remove} from '../../utilities/HTTPRequests';
import './Diary.css';

class Diary extends Component{

  constructor(props) {
    super(props);
    this.state = {
      isLoading: true,
      diaries: []
    };
    this.postDiary = this.postDiary.bind(this);
    this.searchDiary = this.searchDiary.bind(this);
    this.listDiaries = this.listDiaries.bind(this);
  }

  async componentDidMount() {
      this.setState({ isLoading: false });
  }

  async postDiary(){
    let d = new Date();
    let diary = emptyDiary;
    diary.text = document.getElementById("textInput").value;
    diary.image = document.getElementById("imgInput").value;
    diary.video = document.getElementById("videoInput").value;
    diary.currentMood = document.getElementById("moodInput").value;
    if(d.getMonth()+1 / 10  < 10){
      diary.date = d.getFullYear() + "-0" + (d.getMonth()+1) + "-" + d.getDate() ;
    } else {
      diary.date = d.getFullYear() + "-" + (d.getMonth()+1) + "-" + d.getDate() ;
    }

    await post(db +'/diary', diary)
  }

  async searchDiary(){
    var date = document.getElementById("dateInput").value;
    if(date){
      const response = await get(db +'/diary/date/' + date);
      const body = await response.json();
      if(body){
        this.setState({ diaries: body, isLoading: false });
      }
    }
  }


  async listDiaries(){
    const form = document.getElementById("showHide").style;
    if(form.display == "block"){
        form.display = "none";
    } else {
        const response = await get(db + '/diary');
        const body = await response.json();
        if(body){
          this.setState({ diaries: body, isLoading: false });
        }
        form.display = "block";
    }
  }


  render() {
    const {diaries, isLoading} = this.state;

    if (isLoading) {
      return <p>Loading...</p>;
    }

    const diaryList = diaries.map(diary => (
        <DiaryElement
            diary = { diary }>
        </DiaryElement>
    ))
    
    return(
      <>
		    <div>
                    <div className="background-1">
                        <div className="title">
                            <span>Your thoughts are the most important thing in your life... Precise them!</span>
                        </div>
                    </div>
                    <div>
                        <div className="upload">
                            <h2>Dear Diary...</h2>
                            <p>Do you feel bad? Or rather delightful? Today was your best day ever in your life? Or your head is just filled with too much thoughts?
                                The solution is LIFEBook app. It functions as your own online diary that you can bring with yourself wherever you go. An easy-using, long-lasting, helpful assistant
                                that can help organize your feelings and experiences. Feel free to give it a try.<br></br> We promise you won't regret it ;D</p>
                            
                        <form>
                            <textarea id='textInput' name='text' placeholder="Write your day!"></textarea>
                           
                            <input type='text' id='moodInput' name='mood' placeholder="How are you feeling?"></input><br></br>
                            <input type='text' id='imgInput' name='image' placeholder="Add picture by url"></input><br></br>
                            <input type='text' id='videoInput' name='video' placeholder="Add video by url"></input><br></br>
                            <button className="diaryBut" onClick={() => this.postDiary()}>Save</button>
                            
                        </form>
                        </div>
                    </div>
                                    
                    <div className="background-2">
                        <div className="text">
                            <span>Memories</span>
                        </div>
                    </div>
                                    
                    <div className="search">
                        <h2>Searching through your entries</h2>
                        <p>If you want to modify an entry or just reread it you can find it by its date.</p>
                        
                        <input type='text' id='dateInput' placeholder="E.g.: 2020-05-10"></input><br></br>
                        
                        <button onClick={() => this.searchDiary()}>Search</button>
                        <button onClick={() => this.listDiaries()}>Show all entries</button>
                    
                    </div>
                    
                     {(!this.isLoading) ? (
                      <div id={"showHide"}>
                        { diaryList }
                      </div>
                      ) : (
                        <p>Loading...</p>
                      )}
                     

        </div>
               
      </>
    );
  }

}

export default Diary;