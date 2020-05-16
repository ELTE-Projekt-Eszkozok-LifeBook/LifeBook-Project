import React, { Component } from 'react';
import { NavLink, Link } from 'react-router-dom';
import './Menu.css';

class Menu extends Component{
    
  render() {
      return (
        <>
            <div className ="menu">
                    <NavLink exact to="/diary">My Diary</NavLink>
                    <NavLink exact to="/todos">ToDo's</NavLink>
                    <NavLink exact to="/timetable">Timetable</NavLink>
                    <NavLink exact to="/sport">Sport</NavLink>
                    <NavLink exact to="/eating">Eating</NavLink>
                    <NavLink exact to="/financials">Money</NavLink>
                </div>

        </>
      );
    
  }
}

export default Menu;