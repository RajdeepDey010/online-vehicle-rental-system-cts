import React from "react";
import ReactDOM from "react-dom";
import App from "./App";
import history from "./Components/LogIn/History";
import { BrowserRouter as Router } from "react-router-dom";
import { Provider } from "react-redux";
import store from "./AppStore";
ReactDOM.render(
  <Provider store={store}>
    <Router history={history}>
      <App />
    </Router>
  </Provider>,
  document.getElementById("root")
);
