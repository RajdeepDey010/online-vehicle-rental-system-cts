import React from "react";
import Navbar from "./Components/Navbar/Navbar";
import Faqs from "./Components/FAQ/Faqs";
import Login from "./Components/LogIn/Login";
import SearchVehicle from "./Components/SearchVehicle/SearchVehicle";
import Aboutus from "./Components/AboutUs/Aboutus";
import Support from "./Components/Support/Support";
import { BrowserRouter as Router, Switch, Route } from "react-router-dom";
import "./App.css";
import Register from "./Components/LogIn/Register.js";
import Logout from "./Components/LogIn/Logout";

function App() {
  return (
    <>
      <Router>
        <Navbar />
        <Switch>
          <Route path="/" exact component={SearchVehicle} />
          <Route path="/Faqs" component={Faqs} />
          <Route path="/log-in" component={Login} />
          <Route path="/logout" component={Logout} />
          <Route path="/about-us" component={Aboutus} />
          <Route path="/support" component={Support} />
          <Route path="/register" component={Register} />
        </Switch>
      </Router>
    </>
  );
}

export default App;
