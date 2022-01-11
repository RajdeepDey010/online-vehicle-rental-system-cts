import React, { Component } from 'react';
import './SearchVehicle.css'

export default class SearchVehicle extends Component {

    constructor(props){
        super(props);
        this.state = {
            pickUPdate : new Date(),
            dropdate : new Date(),
            pickupLocation : '',
            dropLocation : ''
        }

        this.changepickUpdate = this.changepickUpdate.bind(this);
        this.changedropdate = this.changedropdate.bind(this);
        this.changepickLocation = this.changepickLocation.bind(this);
        this.changedropLocation = this.changedropLocation.bind(this);
        this.saveB = this.saveB.bind(this);
    }

    changepickLocation = (event) => {
        this.setState({pickupLocation : event.target.value});
    }

    changedropLocation = (event) => {
        this.setState({dropLocation : event.target.value});
    }

    changepickUpdate = (event) => {
        this.setState({pickUPdate : event.target.value});
    }
    changedropdate = (event) => {
        this.setState({dropdate : event.target.value});
    }

    saveB = () => {
        alert(this.state.pickupLocation+" to "+this.state.dropLocation+"\nFrom "+this.state.pickUPdate+" to "+this.state.dropdate);
    }
        
    
    render(){


        return (
            <div className='searchvehicle'>
                <h1>Search For Vehicle </h1>
                <br/>
                <form>
                    
                    <label>PickUp Date - </label> &nbsp;
                    <input type="date" value={this.pickUPdate} onChange={this.changepickUpdate}/>
                    <br/>
                    <br/>
           
                    <label>Drop Date - </label> &nbsp;
                    <input type="date" value={this.dropdate} onChange={this.changedropdate}/>
                    <br/>
                    <br/>

                    <label>PickUp Location - </label> &nbsp;
                    <input type="text" value={this.pickupLocation} onChange={this.changepickLocation}/>
                    <br/>
                    <br/>

                    <label>Drop Location - </label> &nbsp;
                    <input type="text" value={this.dropLocation} onChange={this.changedropLocation}/>
                    <br/>
                    <br/>

                    <input className='button' type="submit" onClick={this.saveB}/>
                    <br/>
                </form>
            </div>
        )
    }
}