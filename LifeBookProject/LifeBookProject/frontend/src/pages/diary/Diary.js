import React, { Component } from 'react';
import DiaryElement from './DiaryElement';

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
        <h2>Diary</h2>

        <div>
          <h3>Get registry by date</h3>
          <input type='text' id='dateInput'></input>
          <button onClick={() => this.searchDiary()}>Search</button>
        </div>

        <form>
          <h3>Upload</h3>
          <label htmlFor="text">Text</label>
          <input type='text' id='textInput' name='text'></input>
          <label htmlFor="mood">Mood</label>
          <input type='text' id='moodInput' name='mood'></input>
          <label htmlFor="image">Image</label>
          <input type='text' id='imgInput' name='image'></input>
          <label htmlFor="video">Video</label>
          <input type='text' id='videoInput' name='video'></input>
          <button type="submit" onClick={() => this.postDiary()}>Save</button>
        </form>

        <div> { diaryList } </div>
      </>
    );
  }

}

export default Diary;