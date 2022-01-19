import React from "react";
import axios from "axios";
import "./Login.css";
import { useState, useEffect } from "react";

function Register() {
  let x = "";
  let m = "";
  const [formValues, setFormValues] = useState({
    name: "",
    phno: "",
    licenseno: "",
    address: "",
    email: "",
    password: "",
  });
  const [formErrors, setFormErrors] = useState({});
  const [isSubmit, setIsSubmit] = useState(false);
  const [resp, setResp] = useState([]);
  const [message, setMessage] = useState([]);

  const handleSubmit = (e) => {
    e.preventDefault();
    setFormErrors(validate(formValues));
    setIsSubmit(true);
  };
  const handleChange = (e) => {
    setFormValues({ ...formValues, [e.target.name]: e.target.value });
  };

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
        .post("http://localhost:8080/api/register", formValues)
        .then((respo) => {
          x = respo.data.success;
          m = respo.data.message;
          console.log(x);
          console.log(m);
          setResp(x);
          setMessage(m);
        })
        .catch((error) => console.log("Error", error));
    }
  }, [formErrors]);

  return (
    <div className="container">
      {Object.keys(formErrors).length === 0 && isSubmit && resp === true ? (
        <div className="ui-message">{message}</div>
      ) : (
        <div className="ui-message"> {message}</div>
      )}
      <form onSubmit={handleSubmit}>
        <div className="ui-form">
          <h1>Register </h1>
          <div className="field">
            <label>Name- </label> &nbsp;
            <input
              type="text"
              name="name"
              placeholder="Name"
              value={formValues.name}
              onChange={handleChange}
            />
          </div>
          <p className="ui-message"></p>
          <div className="field">
            <label>Phone No.: </label> &nbsp;
            <input
              type="number"
              name="phno"
              placeholder="Phone Number"
              value={formValues.phno}
              onChange={handleChange}
            />
          </div>
          <p className="ui-message"></p>
          <div className="field">
            <label>License No.: </label> &nbsp;
            <input
              type="number"
              name="licenseno"
              placeholder="License"
              value={formValues.licenseno}
              onChange={handleChange}
            />
          </div>
          <p className="ui-message"></p>
          <div className="field">
            <label>Address : </label> &nbsp;
            <input
              type="text"
              name="address"
              placeholder="Address"
              onChange={handleChange}
              value={formValues.address}
            />
          </div>
          <p className="ui-message"></p>
          <div className="field">
            <label>Email Id: </label> &nbsp;
            <input
              type="text"
              name="email"
              placeholder="Email"
              onChange={handleChange}
              value={formValues.email}
            />
          </div>
          <p className="ui-message">{formErrors.email}</p>
          <div className="field">
            <label>Password </label> &nbsp;
            <input
              type="password"
              name="password"
              placeholder="password"
              onChange={handleChange}
              value={formValues.password}
            />
          </div>
          <p className="ui-message">{formErrors.password}</p>
          <button className="fluid ui button blue">Submit</button>
        </div>
      </form>
    </div>
  );
}

export default Register;
