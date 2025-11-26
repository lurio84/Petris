import React from 'react';
import './home.css';
import logo from '../../static/images/logo-petris.png';
import tokenService from '../../util/services/token.service';
import { useState } from "react";
import { useNavigate } from "react-router-dom";
import getErrorModal from "../../util/getErrorModal";
import useFetchState from '../../util/useFetchState';





const jwt = tokenService.getLocalAccessToken();

export default function Dashboard(){
    const [message, setMessage] = useState(null);
    const [visible, setVisible] = useState(false);
    const [alerts, setAlerts] = useState([]);
    const modal = getErrorModal(setVisible, visible, message);
    const loggedUserId = tokenService.getUser().id;
    const navigate = useNavigate();

    const [player, setPlayer] = useFetchState(
            [],
            `/api/v1/player/${loggedUserId}`,
            jwt,
            setMessage,
            setVisible
        );
    if (!player || !player.user) {
        return (
            <div className="user-page-container">
                <h2>Player not found</h2>
            </div>
        );
    }
    const loggedUser = player.user;
    return(
        <div className="home-page-container">
            <div className="hero-div">
                <img src={logo} style={{ height: '100px' }} />
                <p>¡Bienvenido {loggedUser.username}! ¡Ponte la bata de laboratorioy empieza a jugar!</p>
                <div style={{ marginTop: '10px' }}>
                    <button className="login-button" onClick={() => navigate(`/play`)}>Partida pública</button>
                </div>
                <div style={{ marginTop: '10px' }}>
                    <button className="register-button" onClick={() => navigate(`/play`)}>Partida privada</button>
                </div>
                
            </div>
        </div>
    );
    
}