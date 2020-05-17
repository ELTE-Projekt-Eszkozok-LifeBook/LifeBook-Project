import React, { Component } from 'react';
import { Switch, Route, BrowserRouter as Router, Redirect } from 'react-router-dom';

import Menu from './pages/Menu';
import Diary from './pages/diary/Diary';
import TodoPage from './pages/todo/TodoPage';
import TimetablePage from './pages/timetable/TimetablePage';
import SportPage from './pages/sport/SportPage';
import FinancialPage from './pages/financials/FinancialPage';
import EatingHabitPage from './pages/eating/EatingHabitPage';
import './App.css';


class App extends Component {

    render() {
        return (
                <Router>
                    <Menu></Menu>
                    
                    <Switch>
                    <Route path="/diary">
                        <Diary></Diary>
                    </Route>
                    <Route path="/todos">
                        <TodoPage></TodoPage>
                    </Route>
                    <Route path="/timetable">
                        <TimetablePage></TimetablePage>
                    </Route>
                    <Route path="/sport">
                        <SportPage></SportPage>
                    </Route>
                    <Route path="/financials">
                        <FinancialPage></FinancialPage>
                    </Route>
                    <Route path="/eating">
                        <EatingHabitPage></EatingHabitPage>
                    </Route>
                    <Route path="/">        
                        <Diary></Diary>
                     </Route>
                    </Switch>
                
                </Router>
                );
    }
}

export default App;
