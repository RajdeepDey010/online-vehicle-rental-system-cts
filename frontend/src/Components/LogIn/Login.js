import React from "react";
import "./Login.css";
import axios from "axios";
import { useState, useEffect } from "react";
import AuthenticationService from "./AuthenticationService.js";

import { Link } from "react-router-dom";
import history from "./History";

function Login(props) {
  const authService = new AuthenticationService();
  let x = "";
  const initialValues = { email: "", password: "" };
  const [formValues, setFormValues] = useState(initialValues);
  const [formErrors, setFormErrors] = useState({});
  const [isSubmit, setIsSubmit] = useState(false);
  const [resp, setResp] = useState([]);

  const handleChange = (e) => {
    const { name, value } = e.target;
    setFormValues({ ...formValues, [name]: value });
  };

  const handleSubmit = (e) => {
    e.preventDefault();
    setFormErrors(validate(formValues));
    setIsSubmit(true);
  };

  //   useEffect(() => {
  //     console.log(formErrors);
  //     if (Object.keys(formErrors).length === 0 && isSubmit) {
  //       console.log(formValues);
  //     }
  //   }, [formErrors]);

  const validate = (values) => {
    const errors = {};
    const regex = /^[^\s@]+@[^\s@]+\.[^\s@]{2,}$/i;
    if (!values.email) {
      errors.email = "Email is required!";
    } else if (!regex.test(values.email)) {
      errors.email = "This is not a valid email format!";
    }
    if (!values.password) {
      errors.password = "Password is required";
    } else if (values.password.length < 4) {
      errors.password = "Password must be more than 4 characters";
    } else if (values.password.length > 10) {
      errors.password = "Password cannot exceed more than 10 characters";
    }
    return errors;
  };

  useEffect(() => {
    console.log(formErrors);
    if (Object.keys(formErrors).length === 0 && isSubmit) {
      axios
        .post("http://localhost:8080/api/validate", formValues)
        .then((respo) => {
          //console.log(response.data)
          x = respo.data.response;
          console.log(x);
          setResp(x);
        })
        .catch((error) => console.log("Error", error));
    }
  }, [formErrors]);

  function Msg() {
    if (Object.keys(formErrors).length === 0 && isSubmit && resp !== "Failed") {
      authService.registerSuccessfulLogin(resp);
      // props.history.push("/");

      return (
        <div className="ui-message">
          Logged in Successfully as{" "}
          <i>
            <b>{resp}</b>
          </i>
        </div>
      );
    } else {
      return (
        <div className="ui-message">
          {" "}
          {resp}
          <h5 className="ui-message">
            Not registered ?{" "}
            <Link className="ui-message" to="/register">
              click here
            </Link>
          </h5>
        </div>
      );
    }
  }

  return (
    <div className="container">
      <Msg />
      <form onSubmit={handleSubmit}>
        <div className="ui-form">
          <h1>Login </h1>
          <div className="field">
            <label>Email - </label> &nbsp;
            <input
              type="text"
              name="email"
              placeholder="Email"
              value={formValues.email}
              onChange={handleChange}
            />
          </div>
          <p className="ui-message">{formErrors.email}</p>
          <div className="field">
            <label>Password - </label> &nbsp;
            <input
              type="password"
              name="password"
              placeholder="Password"
              value={formValues.password}
              onChange={handleChange}
            />
          </div>
          <p className="ui-message">{formErrors.password}</p>
          <button className="fluid ui button blue">Submit</button>
        </div>
      </form>
    </div>
  );
}

export default Login;
