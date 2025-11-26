import React from 'react';
import './home.css';
import logo from '../../static/images/logo-petris.png';
import tokenService from '../../util/services/token.service.js';
import { useState } from "react";
import { useNavigate } from "react-router-dom";
import getErrorModal from "../../util/getErrorModal.js";
import useFetchState from '../../util/useFetchState.js';
import { PlayerNotIdentifiedErrorScreen } from '../errorScreen/errorScreens.js';




const jwt = tokenService.getLocalAccessToken();

export default function Dashboard(){
    const [message, setMessage] = useState(null);
    const [visible, setVisible] = useState(false);
    const [alerts, setAlerts] = useState([]);
    const modal = getErrorModal(setVisible, visible, message);
    const username = tokenService.getUser().username;
    const navigate = useNavigate();

    const [player, setPlayer] = useFetchState(
            [],
            `/api/v1/players/username/${username}`,
            jwt,
            setMessage,
            setVisible
        );
    if (!player) {
        return (
            <PlayerNotIdentifiedErrorScreen />
        );
    }
    return(
        <div className="page-container-A1">
            <div className="hero-div">
                <img src={logo} style={{ height: '100px' }} />
                <p>¡Bienvenido {player.username}! ¡Ponte la bata de laboratorio y empieza a jugar!</p>
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