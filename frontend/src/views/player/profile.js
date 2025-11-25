import tokenService from "../../services/token.service";
import "./player.css";
import getErrorModal from "../../util/getErrorModal";
import useFetchState from "../../util/useFetchState";
import getIdFromUrl from "../../util/getIdFromUrl";
import { useState } from "react";
import { useNavigate } from "react-router-dom";
import { PlayerNotFoundErrorScreen } from "../../components/errorScreen/errorScreens";


const imgnotfound = "https://cdn-icons-png.flaticon.com/512/5778/5778223.png";
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

    const [achievements, setAchievements] = useFetchState(
        [],
        `/api/v1/achievements/`,
        jwt,
        setMessage,
        setVisible
    );

    if (!player) {
        return ( <PlayerNotFoundErrorScreen/>);
    }

    const playerAchievements = player.achievements;


    console.log(player);
    
    function statsList() {
        if (!player.stats) {
            return <p>Loading statistics...</p>;
        }

        const stats = player.stats;
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

    function editButton() {
        if (username === loggedUser.username) {
            return (
                <div style={{ marginTop: '20px' }}>
                    <button className="profile-edit-button" onClick={() => navigate(`/player/edit/${player.id}`)}>Editar perfil</button>
                </div>
            );
        }
    }

    
   

    return (
        <div className="user-page-container">
            <div className="smaller-user-page-container">
                <table style={{ width: '100%' }}>
                    <td className="profile-avatar">
                        <img src={`${process.env.PUBLIC_URL}/avatar/${player.avatar}.png`} alt={player.username} />
                    </td>
                    <td style={{ width: '80%', height: '80%', verticalAlign: 'top' }}>
                        <h2 style={{fontSize: '4vh',marginLeft: '2vh'}}>{player.username}</h2>
                        <div class="profileInfo">
                            {player.profileInfo}
                        </div>
                    </td>
                </table>
                <table style={{ width: '100%', marginTop: '20px' }}>
                    <tbody>
                            <td> 
                                <center><h4>Últimos logros de {player.username}</h4></center>
                                {recentAchievementList()}
                                <div style={{marginTop: '10px'}}><a href={`/achievements/${username}`} ><center>Haz click aqui para ver el resto de logros de {player.username}</center></a></div>
                            </td>
                            <td>
                                <center><h4>Estadísticas de {player.username}</h4></center>
                                {statsList()}
                                <center>{editButton()}</center>
                            </td>
                            
                    </tbody>
                </table>
            </div>
        </div>
    );
}