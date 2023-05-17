import React from 'react';
import {Routes, Route, Link} from 'react-router-dom';
import './App.css';
import Main from './pages/Main';
import User from './pages/User';
import GoalMain from "./pages/GoalMain";
import CreateGoal from "./pages/CreateGoal";
import 'react-datepicker/dist/react-datepicker.css';

function App() {

    return (
        <div className="app">
            <nav>
                <Link to="/goalMain">목표 현황</Link>
            </nav>
            <Routes>
                <Route path="/main" element={<Main />} />
                <Route path="/user" element={<User />} />
                <Route path="/goalMain" element={<GoalMain />} />
                <Route path="/createGoal" element={<CreateGoal/>}/>
            </Routes>
        </div>

    );
}

export default App;