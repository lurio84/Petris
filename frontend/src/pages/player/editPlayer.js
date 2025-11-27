import tokenService from "../../util/services/token.service";
import "./player.css";
import getErrorModal from "../../util/getErrorModal";
import useFetchState from "../../util/useFetchState";
import getIdFromUrl from "../../util/getIdFromUrl";
import { useState, useEffect } from "react";
import { PlayerNotFoundErrorScreen, UnauthorizedEditErrorScreen } from "../errorScreen/errorScreens";
import { useNavigate } from "react-router-dom";
import useFetchData from "../../util/useFetchData";

const jwt = tokenService.getLocalAccessToken();

export default function EditPlayer() {
    
    
    const [message, setMessage] = useState(null);
    const [visible, setVisible] = useState(false);
    const modal = getErrorModal(setVisible, visible, message);
    const username = getIdFromUrl(3);
    const loggedUsername = tokenService.getUser().username;
    const loggedId = tokenService.getUser().id;
    const navigate = useNavigate();
    const [newDescription, setNewDescription] = useState("");
    const [editSection, setEditSection] = useState(0); 
    const auths = useFetchData(`/api/v1/users/authorities`, jwt);
    let section = 0;

    

    const [player, setPlayer] = useFetchState(
        [],
        `/api/v1/players/username/${username}`,
        jwt,
        setMessage,
        setVisible
    );

    const [user, setUser] = useFetchState(
        [],
        `/api/v1/users/${loggedId}`,
        jwt,
        setMessage,
        setVisible
    );

    const id = player.id;

    if (!player || !id) {
        return ( <PlayerNotFoundErrorScreen/>);
    }

    if (loggedUsername != username) {
        return ( <UnauthorizedEditErrorScreen/>);
    }

    //#region Edit profile info

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
    
    function handleSubmitPlayer(event) {
        event.preventDefault();
        updatePlayerData(player);
    }

    function handleChangePlayer(event) {
        const target = event.target;
        const value = target.value;
        const name = target.name;
        setPlayer({ ...player, [name]: value });
    }

    function editProfile() {
        return (
            <div className="edit-area-inner">
                <h3>Editar perfil</h3>
                <h5>Descripción de perfil:</h5>
                <textarea rows="4" cols="50"
                name="profileInfo"
                value={player.profileInfo || ""}
                onChange={handleChangePlayer}
                placeholder="¡Escribe aquí tu nueva descripción!"/>
                <button className="profile-save-buttonA" onClick={handleSubmitPlayer} >Guardar descripción</button> 
                <button className="profile-save-buttonB" onClick={() => setEditSection(3)}>Elegir avatar</button>
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

    
    function handleChangeUser(event) {
    const target = event.target;
    const value = target.value;
    const name = target.name;
    if (name === "authority") {
      const auth = auths.find((a) => a.id === Number(value));
      setUser({ ...user, authority: auth });
    } else setUser({ ...user, [name]: value });
  }

  function handleSubmitUser(event) {
    event.preventDefault();

    fetch("/api/v1/users" + (user.id ? "/" + user.id : ""), {
      method: user.id ? "PUT" : "POST",
      headers: {
        Authorization: `Bearer ${jwt}`,
        Accept: "application/json",
        "Content-Type": "application/json",
      },
      body: JSON.stringify(user),
    })
      .then((response) => response.json())
      .then((json) => {
        if (json.message) {
          setMessage(json.message);
          setVisible(true);
        } else window.location.href = "/controlPanel/users";
      })
      .catch((message) => alert(message));
  }

    function modifyPassword() {
        return (
            <div className="edit-area-inner">
                <h3>Modificar contraseña</h3>
                <input type="password" name="password" value={""} onChange={handleChangeUser} placeholder="Nueva contraseña" />
                <button className="profile-save-buttonA" onClick={handleSubmitUser} >Guardar contraseña</button> 
            </div>
        );
    }
    //#endregion

    //#region Remove player

    function DeletePlayer() {
        if (!window.confirm("¿Estás seguro de que quieres eliminar tu cuenta? Esta acción no se puede deshacer.")) {
            return;
        }

        const currentJwt = tokenService.getLocalAccessToken();
        
        if (!currentJwt) {
            setMessage("No estás autenticado. Por favor, inicia sesión.");
            setVisible(true);
            return;
        }


        fetch(`/api/v1/player-statistics/${player.username}`, {
            method: "DELETE",
            headers: {
                Authorization: `Bearer ${currentJwt}`,
                Accept: "application/json",
            },
        }).then((response) => {
            if (response.status === 204 || response.status === 200) {
                console.log("Estadísticas del jugador eliminadas correctamente.");
                fetch(`/api/v1/players/${player.id}`, {
            method: "DELETE",
            headers: {
                Authorization: `Bearer ${currentJwt}`,
                Accept: "application/json",
            },
        })
        .then((response) => {
            if (response.status === 204 || response.status === 200) {
                setMessage("Tu cuenta ha sido eliminada exitosamente.");
                setVisible(true);
                
                
                tokenService.removeUser();
                
                setTimeout(() => {
                    window.location.href = "/";
                }, 2000);
            } else if (response.status === 401) {
                setMessage("No autorizado. Tu sesión puede haber expirado.");
                setVisible(true);
                setTimeout(() => {
                    window.location.href = "/";
                }, 2000);
            } else {
                return response.json();
            }
        })
        .then((data) => {
            if (data && data.message) {
                setMessage(data.message);
                setVisible(true);
            }
        })
        .catch((error) => {
            console.error("Error deleting player:", error);
            setMessage("Error al eliminar el jugador. Por favor, inténtalo de nuevo.");
            setVisible(true);
        });
            }
        });

        
    }

    function removePlayer() {
        return (
            <div className="edit-area-inner">
                <h3>Eliminar jugador</h3>
                <p>Esta acción es irreversible. Se eliminarán todos tus datos y estadísticas.</p>
                <button className="profile-save-buttonB"
                onClick={DeletePlayer}>
                    Eliminar jugador
                </button>
            </div>
        );
    }

    //#endregion

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

    
    //#region RETURN

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
    
    //#endregion
    
}