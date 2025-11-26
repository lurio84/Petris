import tokenService from "../../util/services/token.service";
import "./player.css";
import getErrorModal from "../../util/getErrorModal";
import useFetchState from "../../util/useFetchState";
import getIdFromUrl from "../../util/getIdFromUrl";
import { useState, useEffect } from "react";
import { PlayerNotFoundErrorScreen, UnauthorizedEditErrorScreen } from "../errorScreen/errorScreens";
import { useNavigate } from "react-router-dom";

const jwt = tokenService.getLocalAccessToken();

export default function EditPlayer() {
    
    
    const [message, setMessage] = useState(null);
    const [visible, setVisible] = useState(false);
    const modal = getErrorModal(setVisible, visible, message);
    const username = getIdFromUrl(3);
    const loggedUsername = tokenService.getUser().username;
    const navigate = useNavigate();
    const [newDescription, setNewDescription] = useState("");
    const [editSection, setEditSection] = useState(0); 
    let section = 0;

    

    const [player, setPlayer] = useFetchState(
        [],
        `/api/v1/players/username/${username}`,
        jwt,
        setMessage,
        setVisible
    );

    const id = player.id;

    if (!player) {
        return ( <PlayerNotFoundErrorScreen/>);
    }

    if (loggedUsername != username) {
        return ( <UnauthorizedEditErrorScreen/>);
    }

    function updatePlayerData() {
         fetch(
            "/api/v1/players/" + id,
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
                    navigate("/player/edit/" + username);
                else {
                    let json = JSON.parse(data);
                    if (json.message) {
                        setMessage(JSON.parse(data).message);
                        setVisible(true);
                    } else
                        navigate("/player/edit/" + username);
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


   
    //#region Edit profile info
    function editProfile() {
        return (
            <div className="edit-area-inner">
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
        const avatarNames = ["avatar1", "avatar2", "placeholder1", "placeholder2", "placeholder3", "placeholder1", "placeholder2", "placeholder3", "placeholder1", "placeholder2", "placeholder3"]; // Hay avatares repetidos para probar el scroll
        const avatarList = avatarNames.map((avatarName) => {
            return (
                <div key={avatarName} className="avatar-selection-image">
                    <img 
                        src={`${process.env.PUBLIC_URL}/avatar/${avatarName}.png`} 
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

    //#endregion

    //#region Modify password
    function modifyPassword() {
        return (
            <div className="edit-area-inner">
                <h3>Modificar contraseña</h3>
            </div>
        );
    }
    //#endregion

    function removePlayer() {
        return (
            <div className="edit-area-inner">
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
        <div className="page-container-A1">
            <div className="page-container-A2">
                <div className="profile-container">
                    <div className="profile-section">
                        <div className="profile-avatar">
                            <img src={`${process.env.PUBLIC_URL}/avatar/${player.avatar}.png`} alt={player.username} />
                        </div>
                        <div className="profile-section-inner">
                            <h2 style={{fontSize: '4vh',marginLeft: '2vh'}}>{player.username}</h2>
                            <div className="profile-info">
                                {player.profileInfo}
                            </div>
                        </div>
                    </div>

                    <div className="profile-section">
                        <div className="edit-player-container">
                            <div className="edit-sidebar">
                                <button className="profile-edit-section-button" onClick={() => setEditSection(0)}>Editar perfil</button>
                                <button className="profile-edit-section-button" onClick={() => setEditSection(1)}>Modificar contraseña</button>
                                <button className="profile-edit-section-button" onClick={() => setEditSection(2)}>Eliminar jugador</button>
                            </div>
                        {editArea()}
                        </div>
                    </div>
                </div>
            </div>
            {modal}
        </div>
    );
    

    
}