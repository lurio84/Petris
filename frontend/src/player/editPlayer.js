import tokenService from "../services/token.service";
import "../static/css/player/profile.css";
import getErrorModal from "../util/getErrorModal";
import useFetchState from "../util/useFetchState";
import getIdFromUrl from "../util/getIdFromUrl";
import { useState, useEffect } from "react";
import { PlayerNotFoundErrorScreen, UnauthorizedEditErrorScreen } from "../components/errorScreen/errorScreens";

const jwt = tokenService.getLocalAccessToken();

export default function EditPlayer() {

    // Validamos si el usuario tiene permiso para editar este perfil
    const [message, setMessage] = useState(null);
    const [visible, setVisible] = useState(false);
    const modal = getErrorModal(setVisible, visible, message);
    const id = getIdFromUrl(3);
    const loggedUserId = tokenService.getUser().id;
    
    const [editSection, setEditSection] = useState(0); // 0: Editar perfil, 1: Modificar contraseña, 2: Eliminar jugador
    let section = 0;

    const [player, setPlayer] = useFetchState(
        [],
        `/api/v1/player/${id}`,
        jwt,
        setMessage,
        setVisible
    );

    const errorShouldNotEdit = UnauthorizedEditErrorScreen;
    const errorPlayerNotFound = PlayerNotFoundErrorScreen;

    const playerUser = player.user;
    if (!player || !playerUser) {
        return ( <PlayerNotFoundErrorScreen/>);
    }

    if (parseInt(id) !== parseInt(loggedUserId)) {
        return ( <UnauthorizedEditErrorScreen/>);
    }

   

    function editProfile() {
        return (
            <div>
                <h3>Editar perfil</h3>
            </div>
        );
    }

    function modifyPassword() {
        return (
            <div>
                <h3>Modificar contraseña</h3>
            </div>
        );
    }

    function removePlayer() {
        return (
            <div>
                <h3>Eliminar jugador</h3>
            </div>
        );
    }

    

    function editArea() {
        section = null;
        if (editSection == 0) {
            section = editProfile();
        } else if (editSection == 1) {
            section = modifyPassword();
        } else if (editSection == 2) {
            section = removePlayer();
        }
        return (
            <div className="edit-area">
                {section}
            </div>
        );
    }

    return (
        <div className="user-page-container">
            <div className="smaller-user-page-container">
                <table style={{ width: '100%', marginBottom: '3vh' }}>
                    <tbody>
                        <td className="profile-avatar">
                            <img src={`${process.env.PUBLIC_URL}/avatar/${player.avatar}`} alt={playerUser.username} />
                        </td>
                        <td style={{ width: '80%', height: '80%', verticalAlign: 'top' }}>
                            <h2 style={{fontSize: '4vh',marginLeft: '2vh'}}>{playerUser.username}</h2>
                            <div class="profileInfo">
                                {player.profileInfo}
                            </div>
                        </td>
                    </tbody>
                </table>
                {/* Parte de edición de usuario */}
                <div className="edit-player-container">
                  <table style={{ width: '120vh', margin: '2vh', height: '40vh' }}>
                    <tbody>
                        <td style={{width: '20%', verticalAlign: 'middle'}}>
                            <button className="profile-edit-section-button" onClick={() => setEditSection(0)}>Editar perfil</button>
                            <button className="profile-edit-section-button" onClick={() => setEditSection(1)}>Modificar contraseña</button>
                            <button className="profile-edit-section-button" onClick={() => setEditSection(2)}>Eliminar jugador</button>
                        </td>
                        <td style={{height: '100%', verticalAlign: 'middle'}}>
                            {editArea()}
                        </td>
                    </tbody> 
                  </table>  
                </div>
            </div>
        </div>
    );
    
    
    

    
}