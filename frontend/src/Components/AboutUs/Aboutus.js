import React from 'react';
import './Aboutus.css';
import { Button } from '../Navbar/Button';
import { Link } from 'react-router-dom';

function Aboutus() {
    return (
      <div className='aboutus-container'>
        <section className='aboutus-subscription'>
          <p className='aboutus-subscription-heading'>
          "A car for every occasion"
          </p>
          <p><emp><h3>Car Rentalz offers city taxis, inter-city cabs, and local cabs at hourly packages</h3></emp></p>
          <br/>
          <p className='aboutus-subscription-text'>
            You can unsubscribe at any time.
          </p>
          <div className='input-areas'>
            <form>
              <input
                className='aboutus-input'
                name='email'
                type='email'
                placeholder='Your Email'
              />
              <Button buttonStyle='btn--outline'>Subscribe</Button>
            </form>
          </div>
        </section>
        <div class='aboutus-links'>
          <div className='aboutus-link-wrapper'>
            <div class='aboutus-link-items'>
              <h2>About Us</h2>
              <Link to='/log-in'>How it works</Link>
              <Link to='/'>Testimonials</Link>
              <Link to='/'>Careers</Link>
              <Link to='/'>Investors</Link>
              <Link to='/'>Terms of Service</Link>
            </div>
          </div>
          <div className='aboutus-link-wrapper'>
            <div class='aboutus-link-items'>
              <h2>Videos</h2>
              <Link to='/'>Submit Video</Link>
              <Link to='/'>Ambassadors</Link>
              <Link to='/'>Agency</Link>
              <Link to='/'>Influencer</Link>
            </div>
            <div class='aboutus-link-items'>
              <h2>Social Media</h2>
              <Link to='/'>Instagram</Link>
              <Link to='/'>Facebook</Link>
              <Link to='/'>Youtube</Link>
              <Link to='/'>Twitter</Link>
            </div>
          </div>
        </div>
        <section class='social-media'>
          <div class='social-media-wrap'>
            <div class='aboutus-logo'>
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
  

  export default Aboutus;