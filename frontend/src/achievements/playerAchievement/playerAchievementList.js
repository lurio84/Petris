import {
    Table, Button
} from "reactstrap";

import "./../../static/css/player/perfil.css"
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

    const achievementList =
        achievements.map((a) => {
            return (
                <div key={a.id} className="achievement-badge">
                    <div className="achievement-image">
                        <img src={a.badgeImage ? a.badgeImage : imgnotfound} alt={a.name} className="achievement-image" />
                    </div>
                    <div className="achievement-content">
                        <h3>{a.name}</h3>
                        <p>{a.description}</p>
                    </div>
                </div>
            );
        });


    return (
            <div className="user-page-container">
                <div className="smaller-user-page-container">
                    <h1>Todos los logros</h1>
                    <div className="all-achievements">
                        <div className="achievement-grid">
                            {achievementList}
                        </div>
                    </div>
                </div>
            </div>
    );
}

