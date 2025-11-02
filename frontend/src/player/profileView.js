/*Pantalla creada para probar PlayerStatistics, pero será la vista de un
perfil de un usuario*/

import {Table} from "reactstrap";
import { Link } from "react-router-dom";
import tokenService from "../services/token.service";
import "../static/css/player/profile.css";
import getErrorModal from "../util/getErrorModal";
import useFetchState from "../util/useFetchState";
import getIdFromUrl from "../util/getIdFromUrl";
import { useState } from "react";
import jwt_decode from "jwt-decode";


const imgnotfound = "https://cdn-icons-png.flaticon.com/512/5778/5778223.png";
const jwt = tokenService.getLocalAccessToken();

export default function ProfileView() {
    const [message, setMessage] = useState(null);
    const [visible, setVisible] = useState(false);
    const [alerts, setAlerts] = useState([]);
    const modal = getErrorModal(setVisible, visible, message);
    const id = getIdFromUrl(2);
    const loggedUserId = tokenService.getUser().id;
    
    const [player, setPlayer] = useFetchState(
        [],
        `/api/v1/player/${id}`,
        jwt,
        setMessage,
        setVisible
    );

    
    const playerAchievements = player.achievements;

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
        const achievementCapsules =
        playerAchievements.map((a) => { 
            return (
                <div key={a.id} className="achievement-badge-obtained">
                    <div className="achievement-image-obtained">
                        <img src={a.badgeImage ? a.badgeImage : imgnotfound} alt={a.name} className="achievement-image-obtained" />
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
        //TODO
    }

    if (!player || !player.user) {
        return (
            <div className="user-page-container">
                <h2>Loading...</h2>
            </div>
        );
    }


    return (
        <div className="user-page-container">
            <div className="smaller-user-page-container">
                <table style={{width:'100%'}}>
                    <td className="profile-avatar">
                        <img src={player.avatar ? player.avatar : imgnotfound} alt={player.user.name}  />
                        
                    </td>
                    <td style={{ width: '80%',verticalAlign:'top' }}>
                        <h2>{player.user.name}</h2>
                        <div class="profileInfo">
                            {player.profileInfo}
                        </div>
                    </td>
                </table>
                <table style={{width:'100%', marginTop: '20px'}}>
                    <tbody>
                            <td> 
                                <center><h4>Últimos logros de {player.user.name}</h4></center>
                                {recentAchievementList()}
                                <div style={{marginTop: '10px'}}><a href={`/achievements/${id}`} ><center>Haz click aqui para ver el resto de logros de {player.user.name}</center></a></div>
                            </td>
                            <td>
                                <center><h4>Estadísticas de {player.user.name}</h4></center>
                                {statsList()}
                                {editButton()}
                            </td>
                    </tbody>
                </table>
            </div>
        </div>
    );
}