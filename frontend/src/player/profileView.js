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



const imgnotfound = "https://cdn-icons-png.flaticon.com/512/5778/5778223.png";
const jwt = tokenService.getLocalAccessToken();

export default function ProfileView() {
    const [message, setMessage] = useState(null);
    const [visible, setVisible] = useState(false);
    const [alerts, setAlerts] = useState([]);
    const modal = getErrorModal(setVisible, visible, message);
    const id = getIdFromUrl(2);

    
    const [playerStatistics, setPlayerStatistics] = useFetchState(
        [],
        `/api/v1/playerstatistics/${id}`,
        jwt,
        setMessage,
        setVisible
    );


    
    // Aun no está terminada la entidad Player

    const playerUser = {
        id: id,
        name: "mockPlayer"
    }
    
    const mockProfile = {
        id: id,
        avatar: imgnotfound,
        profileInfo: "Esta es mi descripción falsa de perfil falso!",
        friends: [],
        achievements: [],
        playerstatistics: playerStatistics,
        user: playerUser
    }

    const [achievements, setAchievements] = useFetchState(
            [],
            `/api/v1/achievements`,
            jwt
        );
    
        const logrosAleatorios = [2,3,5,1,4]
        const mockPlayerAchievements = achievements.filter(achievement => logrosAleatorios.includes(achievement.id));
    

    function statsList() {
        const stats = mockProfile.playerstatistics;
        return(
        <div className="profile-small-container">
            <p>
                Nº de amigos: {stats.friends}<br />
                Partidas jugadas: {stats.gamesPlayed}<br />
                Partidas ganadas: {stats.gamesWon}<br />
                Partidas como Bacilo: {stats.gamesAsGreen}<br />
                Partidas ganadas como Bacilo: {stats.victoriesAsGreen}<br />
                Partidas como Coco: {stats.gamesAsPurple}<br />
                Partidas ganadas como Coco: {stats.victoriesAsPurple}<br />
                Bacteria favorita: {stats.favouriteTeam == 'PURPLE' ? 'Coco' : 'Bacilo'}<br />
                Máxima cantidad de bacterias en partida: {stats.maxBacteryPlayedAsGreen + stats.maxBacteryPlayedAsPurple}
            </p>
        </div>
        );
    }

    function recentAchievementList() {
        const achievementCapsules =
        mockPlayerAchievements.map((a) => {
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


    return (
        <div className="user-page-container">
            <div className="smaller-user-page-container">
                <div>

                </div>
                <table style={{width:'100%'}}>
                    <tbody>
                            <td>
                                {recentAchievementList()} 
                                <a href={`/achievements/${id}`}><center>Haz click aqui para ver el resto de logros de {mockProfile.user.name}</center></a>
                            </td>
                            <td>
                                {statsList()}
                                {editButton()}
                            </td>

                    </tbody>
                </table>
                
            </div>
        </div>
    );
}