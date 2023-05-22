import React from "react";
import ReactDOM from 'react-dom';
import { Route, Switch, Redirect, historyApiFallback } from "react-router-dom";
import { BrowserRouter, useHistory } from "react-router-dom/cjs/react-router-dom.min.js";
import Hello from "./pages/Hello.js";
import Survey from './pages/Survey';
import Main from './pages/Main';
import User from './pages/User';
import Delete from './pages/Delete';
import GoalMain from './pages/GoalMain';
import CreateGoal from './pages/CreateGoal';
import Goal from "./pages/Goal.js";
import Bfr from "./pages/Bfr.js";
import Mypage from "./pages/Mypage.js";
import ManageAccount from "./pages/ManageAccount.js";

function App() {
    return (
        <BrowserRouter>
            <Switch>
                <Route exact path="/" component={Hello} />
                <Route exact path="/survey" component={Survey} />
                <Route exact path="/main" component={Main} />
                <Route exact path="/user" component={User} />
                <Route exact path="/delete" component={Delete} />
                <Route exact path="/goalMain" component={GoalMain} />
                <Route exact path="/createGoal" component={CreateGoal} />
                <Route exact path="/goal" component={Goal} />
                <Route exact path="/bfr" component={Bfr} />
                <Route exact path="/mypage" component={Mypage} />
                <Route exact path="/manageaccount" component={ManageAccount} />
                <Redirect to="/" />
            </Switch>
        </BrowserRouter>
    );
}

export default App;