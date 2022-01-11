import React from 'react';
import './Support.css';
import { Button } from '../Navbar/Button';
import { Link } from 'react-router-dom';

function Support() {
    return (
      <div className='support-container'>
        <section className='support-subscription'>
          <p className='support-subscription-heading'>
          "Car Rentalz Is Always There To Help You"
          </p>
          <p><emp><h3>How can we help you?</h3></emp></p>
          <br/>
          <p className='support-subscription-text'>
            You can also write your query here.
          </p>
          <div className='input-areas'>
            <form>
              <input
                className='support-input'
                name='support'
                type='text'
                placeholder='Write Your Query'
              />
              <Button buttonStyle='btn--outline'>Help Me</Button>
            </form>
          </div>
        </section>
        <div class='support-links'>
            <div class='support-link-items'>
              <h2>Support</h2>
                <Link to='/'><p><i>Contact Number - 9865439871 (Chennai),  9453265798 (Bangalore),  9065432887 (Pune)</i></p></Link>
                <Link to='/'><p><i>Emergency Contact - 8830152476</i></p></Link>
                <Link to='/'><p><i>E-mail id - carrentalz@gmail.com</i></p></Link><br/>
                <Link to='/'>Office Address - 4, Kaveri Building, 41, Nr Oshiwara Police Station, Oshiwara, Andheri West, Mumbai, Maharashtra, India - 400053 </Link>
            </div>
        </div>
        <section class='social-media'>
          <div class='social-media-wrap'>
            <div class='support-logo'>
              <Link to='/' className='social-logo'>
                CarRentalz
                <i class='fas fa-map-marked-alt' />
              </Link>
            </div>
            <small class='website-rights'>CarRentalz © 2022</small>
            <div class='social-icons'>
              <Link
                class='social-icon-link facebook'
                to='/'
                target='_blank'
                aria-label='Facebook'
              >
                <i class='fab fa-facebook-f' />
              </Link>
              <Link
                class='social-icon-link instagram'
                to='/'
                target='_blank'
                aria-label='Instagram'
              >
                <i class='fab fa-instagram' />
              </Link>
              <Link
                class='social-icon-link youtube'
                to='/'
                target='_blank'
                aria-label='Youtube'
              >
                <i class='fab fa-youtube' />
              </Link>
              <Link
                class='social-icon-link twitter'
                to='/'
                target='_blank'
                aria-label='Twitter'
              >
                <i class='fab fa-twitter' />
              </Link>
              <Link
                class='social-icon-link twitter'
                to='/'
                target='_blank'
                aria-label='LinkedIn'
              >
                <i class='fab fa-linkedin' />
              </Link>
            </div>
          </div>
        </section>
      </div>
    );
  }
  

  export default Support;