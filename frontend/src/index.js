import React from "react";
import ReactDOM from "react-dom";
import App from "./App";
import history from "./Components/LogIn/History";
import { BrowserRouter as Router } from "react-router-dom";

ReactDOM.render(
  <Router history={history}>
    <App />
  </Router>,
  document.getElementById("root")
);
