import React, { Component } from 'react'
import carImage from '../car2.jpg';
import './comp.css'
import axios from 'axios';

export default class VehicleList extends Component {
    constructor(){
        super()
        this.componentDidMount = this.componentDidMount.bind(this);
        this.state = {
            vehicles : []
        }
        this.bookVehicle = this.bookVehicle.bind(this);
        //this.getVehicleList = this.getVehicleList.bind(this); 
    }

    bookVehicle = (v)=>{
        const user = window.sessionStorage.getItem("user");
        if(user === null){
            alert("Please log-in before booking")
        }
        else{
            const accessToken = window.sessionStorage.getItem("jwtToken")
       
            const vehicleBook = {
                vehicleRegistrationNumber:v.vehicleRegistrationNumber,
                vehicleName:v.vehicleName,
                city:v.city,
                startDuration:"2024-02-17T12:40:00.000+05:30",
                endDuration: "2024-02-17T12:40:10.000+05:30",
                bookedByUser: window.sessionStorage.getItem("user")
            }
            axios.interceptors.request.use(
                config => {
                    config.headers.authorization = "Bearer "+accessToken;
                    return config;
                },
                (error) => {
                    return Promise.reject(error);
                }
            )
            axios.post("http://localhost:8080/api/client/book",vehicleBook).then(
                (res) => {
                    if(res.data.success === false){
                        alert("tryagain")
                    }
                    else{
                        alert("Successfully booked by : "+window.sessionStorage.getItem("user"))
                    }
                }).catch(err => alert('Login: '+ err))
            this.setState({
            vehicles : this.state.vehicles.filter(veh => veh.vehicleRegistrationNumber!==v.vehicleRegistrationNumber)
            })

        }
       

        
        
    }

    

    componentDidMount = () => {

        if(this.props.location.state.city === null){
            alert("Please enter city")
        }
       
        axios.get("http://localhost:8080/api/client/vehiclesearch",
        {
            params : {
                city : this.props.location.state.city
            }
        }).then(res =>
            {
                this.setState({vehicles : res.data.vehicleInformationList})
                //alert(res.data.totalVehicleAvailable)
            })
        
        
    }


    render() {
        return (
            <a href={carImage} target="_blank" rel="noopener noreferrer">
            <div className="searchvehicle" style={{ backgroundImage: `url(${carImage})` }}>
            <div>
                <h2>Vehicle List</h2>
                <br/>
            <div className='full_container'>
                <table className="table">
                    <thead>
                        <tr>
                            <th scope="col">Vehicle name</th>
                            <th scope="col">Vehicle Registeration Number</th>
                            <th scope="col">City</th>
                            <th scope="col">Cost/KM</th>
                            <th scope="col">Book</th>
                        </tr>
                    </thead>
                    <tbody>
                        {this.state.vehicles.map(veh => <tr key={veh.vehicleRegistrationNumber}>
                            <td>{veh.vehicleName}</td>
                            <td>{veh.vehicleRegistrationNumber}</td>
                            <td>{veh.city}</td>
                            <td>{veh.costPerKilometer}</td>
                            <td><button className='button' onClick={() => this.bookVehicle(veh)} >Book</button></td>
                        </tr>)}
                    </tbody>
                </table>
            </div>
            </div>
            </div>
            </a>
        )
    }
}
