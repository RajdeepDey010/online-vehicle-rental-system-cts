import React, { useState, useEffect } from "react";
import { Button } from "./Button";
import { Button1 } from "./Button1.js";
import { Link } from "react-router-dom";
import { connect } from "react-redux";
import "./Navbar.css";

function Navbar({ isLoggedIn, dispatch }) {
  const [click, setClick] = useState(false);
  const [button, setButton] = useState(true);

  const handleClick = () => setClick(!click);

  const closeMobileMenu = () => {
    setClick(false);
  };

  const logout = () => {
    setClick(false);
    console.log("Logging out");
    dispatch({ type: "LOGOUT" });
  };

  const showButton = () => {
    if (window.innerWidth <= 960) {
      setButton(false);
      console.log("reached here");
    } else {
      setButton(true);
      console.log("reached here");
    }
  };

  useEffect(() => {
    showButton();
  });

  window.addEventListener("resize", showButton);

  return (
    <>
      <nav className="navbar">
        <div className="navbar-container">
          <Link to="/" className="navbar-logo" onClick={closeMobileMenu}>
            CarRentalz
            <i className="fas fa-map-marked-alt"></i>
          </Link>
          <div className="menu-icon" onClick={handleClick}>
            <i className={click ? "fas fa-times" : "fas fa-bars"} />
          </div>
          <ul className={click ? "nav-menu active" : "nav-menu"}>
            <li className="nav-item">
              <Link to="/" className="nav-links" onClick={closeMobileMenu}>
                Home
              </Link>
            </li>
            <li className="nav-item">
              <Link to="/faqs" className="nav-links" onClick={closeMobileMenu}>
                FAQ's
              </Link>
            </li>
            <li className="nav-item">
              <Link
                to="/about-us"
                className="nav-links"
                onClick={closeMobileMenu}
              >
                About Us
              </Link>
            </li>
            <li className="nav-item">
              <Link
                to="/support"
                className="nav-links"
                onClick={closeMobileMenu}
              >
                Support
              </Link>
            </li>
            <li>
              <Link
                to="/log-in"
                className="nav-links-mobile"
                onClick={closeMobileMenu}
              >
                Log In
              </Link>
            </li>
            <li>
              <Link to="/logout" className="nav-links-mobile">
                Log Out
              </Link>
            </li>
          </ul>
          {!isLoggedIn && button && (
            <Button buttonStyle="btn--outline">LOG IN</Button>
          )}
          {isLoggedIn && (
            <Button1 buttonStyle="btn--outline" onClick={logout}>
              LOG OUT
            </Button1>
          )}
        </div>
      </nav>
    </>
  );
}

const mapStateToProps = (state) => {
  return { isLoggedIn: state.isLoggedIn };
};
export default connect(mapStateToProps)(Navbar);
