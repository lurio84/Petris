import React from 'react';
import '../App.css';
import '../static/css/home/home.css';
import logo from '../static/images/logo-petris.png';
import { useNavigate } from "react-router-dom"; 

export default function Home(){
    const navigate = useNavigate();

    return(
        <div className="home-page-container">
            <div className="hero-div">
                <img src={logo} style={{ height: '100px' }} />
                <p>¡Bienvenido a Petris! El juego de mesa donde sacas tu cientifico interior y controlas la propagación de bacterias. Ponte la bata de laboratorio, inicia sesión y empieza a jugar o conviertete en un cientifico registrandote</p>
                <div style={{ marginTop: '10px' }}>
                    <button className="login-button" onClick={() => navigate(`/login`)}>Iniciar sesión</button>
                </div>
                <div style={{ marginTop: '10px' }}>
                    <button className="register-button" onClick={() => navigate(`/register`)}>Registrarse</button>
                </div>
            </div>
        </div>
    );
}