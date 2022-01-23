import React, { useState } from "react";
import "./Payment.css";
import { Button } from "../NavBar/Button";
import { Link } from "react-router-dom";

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

	async function displayRazorpay() {
		const res = await loadScript('https://checkout.razorpay.com/v1/checkout.js')

		if (!res) {
			alert('Razorpay SDK failed to load. Are you online?')
			return
		}

		const data = await fetch('http://localhost:1337/razorpay', { method: 'POST' }).then((t) =>
			t.json()
		)

		console.log(data)

		const options = {
			key: __DEV__ ? 'rzp_test_sW4nVmeQMQFwcW' : 'PRODUCTION_KEY',
			currency: data.currency,
			amount: data.amount.toString(),
			order_id: data.id,
			name: 'CarRentalz',
			description: 'Payment is in Progress. Please do not press back button or refresh the page.',
			image: 'http://localhost:1337/logo.svg',
			handler: function (response) {
				alert(response.razorpay_payment_id)
				alert(response.razorpay_order_id)
				alert (response.razorpay_signature)
				alert("Payment Successful")
			},
			prefill: {
				name,
				email: 'saloni@gmail.com',
				phone_number: '8888888888'
			}
		}
		const paymentObject = new window.Razorpay(options)
		paymentObject.open()
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
                placeholder="J1111-2222-3333"
              />
              <br />
              <br />
              <label for="ccnum">Vehicle Registration Number - &nbsp;</label>
              <input
                type="text"
                id="reg_no"
                name="register_number"
                placeholder="MH27 CN 7043"
              />
              <br />
              <br />
              <label for="expmonth">Vehicle Name - &nbsp;</label>
              <input
                type="text"
                id="vehicle_name"
                name="vehicle_name"
                placeholder="Maruti Suzuki"
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
        <h4>Amount to Paid - 1rs</h4>
        <br/>
      <Button class="button btn-success" onClick={displayRazorpay}>Pay</Button>
      </div>
    </div>
  );
}
export default Payment;