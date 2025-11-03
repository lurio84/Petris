import tokenService from "../services/token.service";
import "../static/css/player/profile.css";
import getErrorModal from "../util/getErrorModal";
import useFetchState from "../util/useFetchState";
import getIdFromUrl from "../util/getIdFromUrl";
import { useState, useEffect } from "react";
import { useNavigate } from "react-router-dom";
import jwt_decode from "jwt-decode";


const imgnotfound = "https://cdn-icons-png.flaticon.com/512/5778/5778223.png";
const jwt = tokenService.getLocalAccessToken();
export default function EditPlayer() {
    const [message, setMessage] = useState(null);
    const [visible, setVisible] = useState(false);
    const [alerts, setAlerts] = useState([]);
    const modal = getErrorModal(setVisible, visible, message);
    const id = getIdFromUrl(3);
    const navigate = useNavigate();


    const [player, setPlayer] = useFetchState(
        [],
        `/api/v1/player/${id}`,
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

    const playerUser = player.user;
    

    console.log(player);

    function saveButton() {
        return (
            <div style={{ marginTop: '10px' }}>
                <button className="profile-edit-button-2" onClick={() => {
                    try {
                        console.log(document.getElementById("username").value)
                        console.log(document.getElementById("profileInfo").value)
                        navigate(`/player/${playerUser.id}`)
                    } catch (error) {
                        console.log(error)
                    }
                }}>Guardar cambios</button>
            </div>
        );
    }
    
    function deleteButton() {
        return (
            <div style={{ marginTop: '10px' }}>
                <button className="profile-edit-button" onClick={() => console.log("Eliminado")}>Eliminar cuenta</button>
            </div>
        );
    }


    return (
        <div className="user-page-container">
            <div className="smaller-user-page-container">
                <table style={{ width: '100%' }}>
                    <td className="profile-avatar">
                        <img src={`${process.env.PUBLIC_URL}/avatar/${player.avatar}`} alt={playerUser.username} />
                    </td>
                    <td style={{ width: '80%', height: '80%', verticalAlign: 'top' }}>
                        <h4>Nombre de usuario:</h4>
                        <input id="username" defaultValue={playerUser.username} required maxlength="60" size="20" />
                        <h4></h4>
                        <h4>Descripción:</h4>
                        <input id="profileInfo" defaultValue={player.profileInfo} required maxlength="500" size="35" />
                    </td>
                </table>
                <table style={{ width: '100%', marginTop: '20px' }}>
                    <tbody>
                            <td>
                                {saveButton()}
                            </td>
                            <td>
                                {deleteButton()}
                            </td>
                    </tbody>
                </table>
            </div>
        </div>
    );
}