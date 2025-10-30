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
    const mockProfile = {
        id: id,
        avatar: imgnotfound,
        profileInfo: "Esta es mi descripción falsa de perfil falso!",
        friends: [],
        achievements: [],
        playerstatistics: playerStatistics
    }

    function statsList() {
        const stats = mockProfile.playerstatistics;
        return(
        <div className="player-statistics-container">
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
        //TODO
    }

    return (
        <div className="user-page-container">
            <div className="smaller-user-page-container">
                <table>
                    <tbody>
                            <td>{statsList()}</td>
                            <td>{statsList()}</td>
                    </tbody>
                </table>
                
            </div>
        </div>
    );
}