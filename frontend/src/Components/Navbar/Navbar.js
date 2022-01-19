import React, { useState, useEffect } from "react";
import { Button } from "./Button";
import { Button1 } from "./Button1.js";
import { Link } from "react-router-dom";
import "./Navbar.css";
import AuthenticationService from "../LogIn/AuthenticationService";

function Navbar() {
  const [click, setClick] = useState(false);
  const [button, setButton] = useState(true);

  const handleClick = () => setClick(!click);
  const closeMobileMenu = () => setClick(false);

  const authService = new AuthenticationService();

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
  }, [authService.isloggedin]);

  window.addEventListener("resize", showButton);

  return (
    <>
      <nav className="navbar">
        <div className="navbar-container">
          <Link to="/" className="navbar-logo" onClick={closeMobileMenu}>
            CarRentalz
            <i class="fas fa-map-marked-alt"></i>
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
              <Link
                to="/logout"
                className="nav-links-mobile"
                onClick={closeMobileMenu}
              >
                Log Out
              </Link>
            </li>
          </ul>
          {!authService.isloggedin && button && (
            <Button buttonStyle="btn--outline">LOG IN</Button>
          )}
          {authService.isloggedin && button && (
            <Button1 buttonStyle="btn--outline">LOG OUT</Button1>
          )}
        </div>
      </nav>
    </>
  );
}
export default Navbar;
