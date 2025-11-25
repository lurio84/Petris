import tokenService from "../../services/token.service";
import "./player.css";
import getErrorModal from "../../util/getErrorModal";
import useFetchState from "../../util/useFetchState";
import getIdFromUrl from "../../util/getIdFromUrl";
import { useState, useEffect } from "react";
import { PlayerNotFoundErrorScreen, UnauthorizedEditErrorScreen } from "../../components/errorScreen/errorScreens";
import { useNavigate } from "react-router-dom";

const jwt = tokenService.getLocalAccessToken();

export default function EditPlayer() {
    
    // Validamos si el usuario tiene permiso para editar este perfil
    const [message, setMessage] = useState(null);
    const [visible, setVisible] = useState(false);
    const modal = getErrorModal(setVisible, visible, message);
    const id = getIdFromUrl(3);
    const loggedUserId = tokenService.getUser().id;
    const navigate = useNavigate();

    const [newDescription, setNewDescription] = useState("");
    const [editSection, setEditSection] = useState(0); // 0: Editar perfil, 1: Modificar contraseña, 2: Eliminar jugador, 3: Elegir avatar
    let section = 0;

    const [player, setPlayer] = useFetchState(
        [],
        `/api/v1/player/${id}`,
        jwt,
        setMessage,
        setVisible
    );

    function updatePlayerData() {
         fetch(
            "/api/v1/player/" + id,
            {
                method: "PUT",
                headers: {
                    Authorization: `Bearer ${jwt}`,
                    Accept: "application/json",
                    "Content-Type": "application/json",
                },
                body: JSON.stringify(player),
            }
        )
            .then((response) => response.text())
            .then((data) => {
                if (data === "")
                    navigate("/player/edit/" + id);
                else {
                    let json = JSON.parse(data);
                    if (json.message) {
                        setMessage(JSON.parse(data).message);
                        setVisible(true);
                    } else
                        navigate("/player/edit/" + id);
                }
            })
            .catch((message) => alert(message));
    }
    function handleSubmit(event) {
        event.preventDefault();
        updatePlayerData(player);
    }

    function handleChange(event) {
        const target = event.target;
        const value = target.value;
        const name = target.name;
        setPlayer({ ...player, [name]: value });
    }

    
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
                <h5>Descripción de perfil:</h5>
                <textarea rows="4" cols="50"
                name="profileInfo"
                value={player.profileInfo || ""}
                onChange={handleChange}
                placeholder="¡Escribe aquí tu nueva descripción!"/>
                <button className="profile-save-button" onClick={handleSubmit} >Guardar descripción</button> 
                <button className="profile-edit-section-button" onClick={() => setEditSection(3)}>Elegir avatar</button>
            </div>
            
        );
    }

    function avatarSelection() {
        const avatarNames = ["avatar1.png", "avatar2.png", "placeholder1.png", "placeholder2.png", "placeholder3.png", "placeholder1.png", "placeholder2.png", "placeholder3.png", "placeholder1.png", "placeholder2.png", "placeholder3.png"]; // Hay avatares repetidos para probar el scroll
        const avatarList = avatarNames.map((avatarName) => {
            return (
                <div key={avatarName} className="avatar-selection-image">
                    <img 
                        src={`${process.env.PUBLIC_URL}/avatar/${avatarName}`} 
                        alt={avatarName} 
                        onClick={() => {
                            const newPlayer = player;
                            newPlayer.avatar = avatarName;
                            setPlayer(newPlayer);
                            updatePlayerData();
                            setEditSection(0);
                        }}
                    />
                </div>
            );
        });
        return (
            <div>
            <h3>Selecciona un avatar</h3>
                <div className="avatar-selection-container">
                    {avatarList}
                </div>
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
        } else if (editSection == 3) {
            section = avatarSelection();
        }
        return (
            <div className="edit-area">
                {section}
            </div>
        );
    }

    return (
        <div className="user-page-container">
            {modal} 
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