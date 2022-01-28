import React, { Component, useState } from 'react';
import axios from 'axios';
import './BookedVehiclesByUser.css'

export default class BookedVehiclesByUser extends Component {
    constructor(){
        super()
        this.state = {
            bookedList : [],
            costPerKM : 0,
            distance : 0
        }
        //this.getBookedVehicles = this.getBookedVehicles.bind(this)
        this.componentDidMount = this.componentDidMount.bind(this)
        this.proceedToPay = this.proceedToPay.bind(this)
        this.totalDistance = this.totalDistance.bind(this)
    }

    totalDistance(event){
        this.setState({distance : event.target.value})
        
    }

    proceedToPay = (vehicle) =>{
        //alert(this.state.distance)
        let cpkm = 0;
        axios.get("http://localhost:8080/api/client/vehiclesearch",
        {
            params : {
                city : vehicle.city
            }
        }).then(res => {
            const list = res.data.vehicleInformationList;
            for (const iterator of list) {
                if(iterator.vehicleRegistrationNumber === vehicle.vehicleRegistrationNumber){
                    cpkm = iterator.costPerKilometer;
                    window.sessionStorage.setItem("vhicleName",vehicle.vehicleName)
                    window.sessionStorage.setItem("vehicleNumber", vehicle.vehicleRegistrationNumber)
                    window.sessionStorage.setItem("costPerKM",iterator.costPerKilometer)
                    window.sessionStorage.setItem("bookingID",vehicle.bookingId)
                    window.sessionStorage.setItem("totalDistance",this.state.distance)
                    this.props.history.push('/payment')
                }
            }

        })
        //this.setState({bookedList : this.state.bookedList.filter(i => i.rideCompleted===false)})
        //alert(cpkm)
       
        
    }
    componentDidMount(){
        if(window.sessionStorage.getItem("user") === null){
            alert("Please Log-in")
        }
        else{
            axios.interceptors.request.use(
            config => {
            config.headers.authorization = "Bearer "+ window.sessionStorage.getItem("jwtToken");
            return config;
            },
            (error) => {
            return Promise.reject(error);
            },
            );


            axios.get("http://localhost:8080/api/client/booking/all",
            {
                params : {
                    email : window.sessionStorage.getItem("user")
                }
            }).then(res => {
                this.setState({bookedList : res.data})
                //alert(res.data[0].bookingId)
            })
        } 
    }




  render() {
    return (<div>
        <h2>Booked Vehicles</h2>
        <br/>
        <div className='full_container'>
               <table className="table">
                    <thead>
                        <tr>
                            <th scope="col">Vehicle name</th>
                            <th scope="col">Vehicle Registeration Number</th>
                            <th scope="col">City</th>
                            <th scope="col">Distance Covered</th>
                            <th scope="col">Proceed to Pay</th>
                        </tr>
                    </thead>
                    <tbody>
                        {this.state.bookedList.map(veh => <tr>
                            <td>{veh.vehicleName}</td>
                            <td>{veh.vehicleRegistrationNumber}</td>
                            <td>{veh.city}</td>
                            <td><input onChange={this.totalDistance} value={this.distance}/></td>
                            <td><button className='button' onClick={() => this.proceedToPay(veh)}>Pay</button></td>
                            </tr>)}
                    </tbody>
                </table>
            </div>
    </div>
    );
  }
}

/*
{this.state.bookedList.map(item => {
                    <tr key={item.vehicleRegistrationNumber}>
                        <td>{item.vehicleName}</td>
                        <td>{item.city}</td>
                        <td>{item.vehicleRegistrationNumber}</td>
                    </tr>
                })}
*/
