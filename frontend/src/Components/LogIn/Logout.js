import { Redirect } from "react-router-dom";
import AuthenticationService from "./AuthenticationService";

function Logout() {
  AuthenticationService.logout();

  return (
    <div className="container">
      <Redirect to="/log-in" />
      <h1 className="ui-message">Logged Out Successfully</h1>
    </div>
  );
}

export default Logout;
