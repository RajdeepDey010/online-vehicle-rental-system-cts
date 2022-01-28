class AuthenticationService {
  registerSuccessfulLogin(username) {
    sessionStorage.setItem("Authenticated User", username);
  }
  logout() {
    sessionStorage.removeItem("Authenticated User");
  }

  isLoggedIn() {
    const status = sessionStorage.getItem("Authenticated User");
    if (status === null) return false;
    return true;
  }
}

export default AuthenticationService;
