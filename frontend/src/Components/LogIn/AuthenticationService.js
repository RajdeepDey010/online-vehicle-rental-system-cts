class AuthenticationService {
  constructor() {
    this.isloggedin = false;
  }

  registerSuccessfulLogin(username) {
    sessionStorage.setItem("Authenticated User", username);
    this.isloggedin = true;
  }
  logout() {
    sessionStorage.removeItem("Authenticated User");
    this.isloggedin = false;
  }

  isLoggedIn() {
    const status = sessionStorage.getItem("Authenticated User");
    if (status === null) return false;
    return true;
  }
}

export default AuthenticationService;
