import React, { Component } from 'react';
import DiaryElement from './DiaryElement';
import './Diary.css';

class Diary extends Component{

  emptyItem = {
    text: '',
    image: '',
    video: '',
    currentMood: '',
    date: ''
  };


  constructor(props) {
    super(props);
    this.state = {
      isLoading: true,
      diaries: []
    };
    this.postDiary = this.postDiary.bind(this);
  }



  async componentDidMount() {
    const response = await fetch('http://localhost:8080/diary');
    const body = await response.json();
    if(body){
      this.setState({ diaries: body, isLoading: false });
    }
  }

  async postDiary(){
    let d = new Date();
    let diary = this.emptyItem;
    diary.text = document.getElementById("textInput").value;
    diary.image = document.getElementById("imgInput").value;
    diary.video = document.getElementById("videoInput").value;
    diary.currentMood = document.getElementById("moodInput").value;
    diary.date = d.getFullYear() + "-" + (d.getMonth()+1) + "-" + d.getDate() ;

    await fetch(`http://localhost:8080/diary`, {
      method: 'POST',
      headers: {
        'Accept': 'application/json',
        'Content-Type': 'application/json'
      },
      body:diary
    }).then(() => {
      this.componentDidMount();
    });
  }

  async searchDiary(){
    var date = document.getElementById("dateInput").value;
    if(date){
      const response = await fetch('http://localhost:8080/diary/date/' + date);
      const body = await response.json();
      if(body){
        this.setState({ diaries: body, isLoading: false });
      }
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
                    <div class="background-1">
                        <div class="title">
                            <span>Your thoughts are the most important thing in yor life... Precise them!</span>
                        </div>
                    </div>
                    <div>
                        <div class="upload">
                            <h2>Dear Diary...</h2>
                            <p>Do you feel bad? Or rather delightful? Today was your best day ever in your life? Or your head is just filled with too much thoughts?
                                The solution is LIFEBook app. It functions as your own online diary that you can bring with yourself wherever you go. An easy-using, long-lasting, helpful assistant
                                that can help organize your feelings and experiences. Feel free to give it a try.<br></br> We promise you won't regret it ;D</p>
                            
                        <form>
                            <textarea id='textInput' name='text' placeholder="Write your day..."></textarea>
                            <div class="right-side">
                            <label htmlFor="mood">Mood</label>
                            <input type='text' id='moodInput' name='mood' placeholder="How are you feeling..."></input><br></br>
                            <label htmlFor="image">Image</label>
                            <input type='text' id='imgInput' name='image' placeholder="Add picture by url..."></input><br></br>
                            <label htmlFor="video">Video</label>
                            <input type='text' id='videoInput' name='video' placeholder="Add video by url..."></input><br></br>
                            <button type="submit" onClick={() => this.postDiary()}>Save</button>
                            </div>
                        </form>
                        </div>
                    </div>
                                    
                    <div class="background-2">
                        <div class="text">
                            <span>Memories</span>
                        </div>
                    </div>
                                    
                    <div class="search">
                        <h2>Searching through your entries</h2>
                        <p>If you want to modify an entry or just reread it you can find it by its date.</p>
                        
                        <input type='text' id='dateInput' placeholder="Search..."></input><br></br>
                        
                        <button onClick={() => this.searchDiary()}>Search</button>
                        <button onClick={() => this.diaryList }>Show all entries</button>
                    </div>
                </div>
               
      </>
    );
  }

}

export default Diary;