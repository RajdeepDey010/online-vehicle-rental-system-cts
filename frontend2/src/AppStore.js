import { createStore } from "redux";
const initialState = {
  isLoggedIn: false,
};

const reducer = (state, action) => {
  console.log(state, action);
  switch (action.type) {
    case "LOGGED_IN":
      console.log("Logged In");
      const loginState = { ...state, isLoggedIn: true };
      return loginState;
      break;
    case "LOGOUT":
      console.log("Logged Out");
      const logoutState = { ...state, isLoggedIn: false };
      return logoutState;
  }
  return state;
};

const AppStore = createStore(reducer, initialState);
export default AppStore;
