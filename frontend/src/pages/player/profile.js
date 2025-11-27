import tokenService from "../../util/services/token.service";
import "./player.css";
import getErrorModal from "../../util/getErrorModal";
import useFetchState from "../../util/useFetchState";
import getIdFromUrl from "../../util/getIdFromUrl";
import { useState } from "react";
import { useNavigate } from "react-router-dom";
import { PlayerNotFoundErrorScreen } from "../errorScreen/errorScreens";



const jwt = tokenService.getLocalAccessToken();
export default function Profile() {
    const [message, setMessage] = useState(null);
    const [visible, setVisible] = useState(false);
    const modal = getErrorModal(setVisible, visible, message);
    const username = getIdFromUrl(2);
    const loggedUser = tokenService.getUser();
    const navigate = useNavigate();

    

    const [player, setPlayer] = useFetchState(
        [],
        `/api/v1/players/username/${username}`,
        jwt,
        setMessage,
        setVisible
    );

    const [stats, setStats] = useFetchState(
        [],
        `/api/v1/player-statistics/${username}`,
        jwt,
        setMessage,
        setVisible
    );

    if (!player || player.username === undefined) {
        return ( <PlayerNotFoundErrorScreen/>);
    }

    const playerAchievements = player.achievements;


    console.log(player);
    
    //#region  Parte de estadisticas y logros
    function statsList() {
        if (!stats) {
            return <p>Loading statistics...</p>;
        }

        return (
            <div className="profile-small-container">
                <p>
                    Partidas jugadas: {stats.gamesPlayed}<br />
                    Partidas ganadas: {stats.gamesWon}<br />
                    Partidas como Bacilo: {stats.gamesAsGreen}<br />
                    Partidas ganadas como Bacilo: {stats.victoriesAsGreen}<br />
                    Partidas como Coco: {stats.gamesAsPurple}<br />
                    Partidas ganadas como Coco: {stats.victoriesAsPurple}<br />
                    Bacteria favorita: {stats.favouriteTeam === 'PURPLE' ? 'Coco' : 'Bacilo'}<br />
                    Máxima cantidad de bacterias en partida: {stats.maxBacteryPlayedAsGreen + stats.maxBacteryPlayedAsPurple}
                </p>
            </div>
        );
    }

    function recentAchievementList() {
        if (!playerAchievements||playerAchievements.length === 0) {
            return (<div className="profile-small-container">
                        <p>Este jugador no ha obtenido ningún logro aún.</p>
                    </div>);
        }
        const achievementCapsules =
        playerAchievements.map((a) => {
            return (
                <div key={a.id} className="achievement-badge-obtained">
                    <div className="achievement-image-obtained">
                        <img src={`${process.env.PUBLIC_URL}/achievement/${a.badgeImage}`} alt={a.name} className="achievement-image-obtained" />
                    </div>
                    <div className="achievement-content-obtained">
                        <h4>{a.name}</h4>
                        <p>{a.description}</p>
                    </div>
                </div>
            );
        });

        return (
            <div className="profile-small-container">
                {achievementCapsules}
            </div>
        )
    }
    //#endregion

    function editButton() {
        if (username === loggedUser.username) {
            return (
                <div style={{ marginTop: '20px' }}>
                    <button className="profile-edit-button" onClick={() => navigate(`/player/edit/${username}`)}>Editar perfil</button>
                </div>
            );
        }
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
                        <div>
                            <center><h4>Últimos logros de {player.username}</h4></center>
                                {recentAchievementList()}
                            <div style={{marginTop: '10px'}}><a href={`/achievements/${username}`} ><center>Haz click aqui para ver el resto de logros de {player.username}</center></a></div>
                        </div>
                        <div style={{marginTop: '30px'}}>
                            <center><h4>Estadísticas de {player.username}</h4></center>
                                {statsList()}
                            <center>{editButton()}</center>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    );
}