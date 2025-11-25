import "./../player.css"
import tokenService from "../../../util/services/token.service";
import useFetchState from "../../../util/useFetchState";
import { useState } from "react";
import getErrorModal from "../../../util/getErrorModal";
import getIdFromUrl from "../../../util/getIdFromUrl";
import { PlayerNotFoundErrorScreen } from "../../errorScreen/errorScreens";
const imgnotfound = "https://cdn-icons-png.flaticon.com/512/5778/5778223.png";
const jwt = tokenService.getLocalAccessToken();

export default function AchievementsPage() {
    const [message, setMessage] = useState(null);
    const [visible, setVisible] = useState(false);
    const [alerts, setAlerts] = useState([]);
    const modal = getErrorModal(setVisible, visible, message);
    const username = getIdFromUrl(2);
    const loggedUserId = tokenService.getUser().id;


    const [achievements, setAchievements] = useFetchState(
        [],
        `/api/v1/achievements`,
        jwt
    );

     const [player, setPlayer] = useFetchState(
            [],
            `/api/v1/players/username/${username}`,
            jwt,
            setMessage,
            setVisible
        );
    
    if (!player || player.username === undefined) {
        return (
            <PlayerNotFoundErrorScreen/>
        );
    }

    const playerAchievements = player.achievements;

    const playerAchievementList =
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
    

    const achievementList =
        achievements.map((a) => {
            const isObtained = playerAchievements.some((pa) => pa.id === a.id);
            if (isObtained) {
                return (
                    <div key={a.id} className="achievement-badge-obtained">
                        <div className="achievement-image-obtained">
                            <img src={`${process.env.PUBLIC_URL}/achievement/${a.badgeImage}`} alt={a.name} className="achievement-image-obtained" />
                        </div>
                        <div>
                            <h4>{a.name}</h4>
                            <p>{a.description}</p>
                        </div>
                    </div>
                );
            }

            return (
                <div key={a.id} className="achievement-badge">
                    <div className="achievement-image">
                        <img src={`${process.env.PUBLIC_URL}/achievement/${a.badgeImage}`} alt={a.name} className="achievement-image" />
                    </div>
                    <div>
                        <h4>{a.name}</h4>
                        <p>{a.description}</p>
                    </div>
                </div>
            );
        });

        
   return (
        <div className="page-container-A1">
            <div className="page-container-A2">
                <div className="achievement-container">
                    <h1 style={{color:'#704ABA'}}>Logros de {player.username}:</h1>
                    <div className="all-achievements">
                        <div className="achievement-grid">{playerAchievementList}</div>
                        
                    </div>
                    <h1 style={{color:'#704ABA'}}>Todos los logros:</h1>
                    <div className="all-achievements">
                        <div className="achievement-grid">{achievementList}</div>
                    </div>
                </div>
            </div>
        </div>
   );
}

