import "./../../static/css/player/profile.css"
import { Link } from "react-router-dom";
import tokenService from "./../../services/token.service";
import useFetchState from "./../../util/useFetchState";
import deleteFromList from "./../../util/deleteFromList";
import { useState } from "react";
import getErrorModal from "./../../util/getErrorModal";

const imgnotfound = "https://cdn-icons-png.flaticon.com/512/5778/5778223.png";
const jwt = tokenService.getLocalAccessToken();

export default function AchievementList() {
    const [message, setMessage] = useState(null);
    const [visible, setVisible] = useState(false);
    const [alerts, setAlerts] = useState([]);
    const modal = getErrorModal(setVisible, visible, message);

    const [achievements, setAchievements] = useFetchState(
        [],
        `/api/v1/achievements`,
        jwt
    );

    const logrosAleatorios = [2,3,5]
    const mockPlayerAchievements = achievements.filter(achievement => logrosAleatorios.includes(achievement.id));

    const achievementList =
        achievements.map((a) => {
            if (mockPlayerAchievements.includes(a)) {
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
            }
            
            return (
                <div key={a.id} className="achievement-badge">
                    <div className="achievement-image">
                        <img src={a.badgeImage ? a.badgeImage : imgnotfound} alt={a.name} className="achievement-image" />
                    </div>
                    <div className="achievement-content">
                        <h4>{a.name}</h4>
                        <p>{a.description}</p>
                    </div>
                </div>
            );
        });

    // De momento con una lista falsa, player esta por implementar
    const playerAchievementList =
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
            <div className="user-page-container">
                <div className="smaller-user-page-container">
                    <h1 style={{color:'#704ABA'}}>Logros de mockPlayer:</h1>
                    <div className="all-achievements">
                        <div className="achievement-grid">
                            {playerAchievementList}
                        </div>
                    </div>
                    <h1 style={{color:'#704ABA'}}>Todos los logros:</h1>
                    <div className="all-achievements">
                        <div className="achievement-grid">
                            {achievementList}
                        </div>
                    </div>
                </div>
            </div>
    );
}

