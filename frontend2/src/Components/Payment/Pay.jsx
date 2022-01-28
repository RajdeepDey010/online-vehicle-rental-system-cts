import React, { Component } from 'react';

export default class Pay extends Component {
  render() {
    return <div>
        <form><script src="https://checkout.razorpay.com/v1/payment-button.js" data-payment_button_id="pl_InJSp9WvVmSMzq" async> </script> </form>
    </div>;
  }
}
