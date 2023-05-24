import React, { useState } from "react";
import "./Payment.css";
import axios from "axios";
//import { Button } from "../Navbar/Button";




function loadScript(src) {
	return new Promise((resolve) => {
		const script = document.createElement('script')
		script.src = src
		script.onload = () => {
			resolve(true)
		}
		script.onerror = () => {
			resolve(false)
		}
		document.body.appendChild(script)
	})
}

const __DEV__ = document.domain === 'localhost'

function Payment() {

  const [name, setName] = useState('Saloni')

  //alert(this.props.location.state.bookingID)
  

  //const bookingID = this.props.location.state.bookingID;
  //const vehicleNumber = this.props.location.state.vehicleNumber;
  //const vehicleName = this.props.location.state.vehicleName;
  //const costPerKM = this.props.location.state.costPerKilomater;
  
 const bookingID = window.sessionStorage.getItem("bookingID")
  const vehicleNumber = window.sessionStorage.getItem("vehicleNumber")
  const vehicleName = window.sessionStorage.getItem("vhicleName")
  const costPerKM = window.sessionStorage.getItem("costPerKM")
  const totalDistance = window.sessionStorage.getItem("totalDistance")


	async function displayRazorpay() {
    const res = await loadScript(
      "https://checkout.razorpay.com/v1/checkout.js"
    );

    if (!res) {
      alert("Razorpay SDK failed to load. Are you online?");
      return;
    }

    const data = await fetch("http://localhost:1337/razorpay", {
      method: "POST",
    }).then((t) => t.json());

    console.log(data);

    const options = {
      key: __DEV__ ? "rzp_test_WMm9FXMqNScJkJ" : "PRODUCTION_KEY",
      currency: data.currency,
      amount: data.amount.toString(),
      order_id: data.id,
      name: "CarRentalz",
      description:
        "Payment is in Progress. Please do not press back button or refresh the page.",
      image: "http://localhost:1337/logo.svg",
      handler: function (response) {
        alert(response.razorpay_payment_id);
        alert(response.razorpay_order_id);
        alert(response.razorpay_signature);
        alert("Payment Successful");
      },
      prefill: {
        name,
        email: "saloni@gmail.com",
        phone_number: "8888888888",
      },
    };
    const paymentObject = new window.Razorpay(options);
    paymentObject.open();
	}

  return (
      <div>
    <h1>"Payment"</h1><br/><br/>
    <div className="payment-container">
      <div className="container1">
        <section className="payment-methods">
          <div className="payment-cards">
            <div className="payment-card-item">
              <h2><i class="fas fa-car"> &nbsp;</i>"Booking Details"</h2>
              <br />
            </div>
            <form>
              <label for="cname">Booking Id - &nbsp;</label>
              <input
                type="text"
                id="booking_id"
                name="booking_id"
                value={bookingID}
              />
              <br />
              <br />
              <label for="ccnum">Vehicle Registration Number - &nbsp;</label>
              <input
                type="text"
                id="reg_no"
                name="register_number"
                value={vehicleNumber}
              />
              <br />
              <br />
              <label for="expmonth">Vehicle Name - &nbsp;</label>
              <input
                type="text"
                id="vehicle_name"
                name="vehicle_name"
                value={vehicleName}
              />
              <br />
              <br />
            </form>
          </div>
        </section>
      </div>
    </div>
    <br/>
    <br/>
      <div className="pay-button">

        <h4>Amount to Paid {totalDistance * costPerKM}</h4>

        <br/>
        <button class="button btn-success" onClick={displayRazorpay}>Pay</button>
         </div>
    </div>
  );
  }
export default Payment;


/*import React, { useState } from "react";
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

export default Payment;*/