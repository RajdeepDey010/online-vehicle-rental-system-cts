import { useEffect } from "react";
import { Redirect } from "react-router-dom";
import AuthenticationService from "./AuthenticationService";

function Logout() {
  const authService = new AuthenticationService();

  useEffect(() => {
    authService.logout();
  });

  return (
    <div className="container">
      <Redirect to="/log-in" />
      <h1 className="ui-message">Logged Out Successfully</h1>
    </div>
  );
}

export default Logout;
