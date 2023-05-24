
import React, { useState } from "react";
import "./Payment.css";
import axios from "axios";

function loadScript(src) {
  // Code for loadScript function...
}

const _DEV_ = document.domain === 'localhost'

function Payment() {
  const [name, setName] = useState('Saloni')

  const bookingID = window.sessionStorage.getItem("bookingID")
  const vehicleNumber = window.sessionStorage.getItem("vehicleNumber")
  const vehicleName = window.sessionStorage.getItem("vhicleName")
  const costPerKM = window.sessionStorage.getItem("costPerKM")
  const totalDistance = window.sessionStorage.getItem("totalDistance")

  async function displayRazorpay() {
    // Code for displayRazorpay function...
  }

  // Function to detect default currency symbol
function getCurrencySymbol() {
  const currencySymbol = new Intl.NumberFormat().format(0).replace(/\d/g, "").trim();
  return currencySymbol;
}



  return (
    <div>
      <h1>"Payment"</h1><br/><br/>
      <div className="payment-container">
        
      </div>
      <br/>
      <br/>
      <div className="pay-button">
        <h4>Amount to Paid {getCurrencySymbol()}{totalDistance * costPerKM}</h4>
        <br/>
        <button className="button btn-success" onClick={displayRazorpay}>Pay</button>
      </div>
    </div>
  );
}

export default Payment;