import React, { Component } from 'react';
import { NavLink, Link } from 'react-router-dom';


class Menu extends Component{
    
  render() {
      return (
        <>
            <NavLink exact to="/diary">
                <button>Diary</button>
            </NavLink>
            <NavLink exact to="/todos">
                <button>To Do List</button>
            </NavLink>
            <NavLink exact to="/timetable">
                <button>Timetable</button>
            </NavLink>
            <NavLink exact to="/sport">
                <button>Sport activity</button>
            </NavLink>
            <NavLink exact to="/eating">
                <button>Eating habits</button>
            </NavLink>
            <NavLink exact to="/financials">
                <button>Financial Stats</button>
            </NavLink>

        </>
      );
    
  }
}

export default Menu;