import "./admin.css";

import { useState } from "react";
import getErrorModal from "../../util/getErrorModal";
import getIdFromUrl from "../../util/getIdFromUrl";
import useFetchState from "../../util/useFetchState";
import { useNavigate } from "react-router-dom";
import tokenService from "../../util/services/token.service";

const jwt = tokenService.getLocalAccessToken();

export default function ControlPanel() {

    const [message, setMessage] = useState(null);
    const [visible, setVisible] = useState(false);
    const modal = getErrorModal(setVisible, visible, message);
    const navigate = useNavigate();
    
    
    return (
        <div className="admin-page-container" style={{ minHeight: '100vh', display: 'flex', flexDirection: 'column', justifyContent: 'center', alignItems: 'center' }}>
            <h2>Admin Control Panel</h2>
            <button className="control-panel-button" style={{ margin: '10px' }} onClick={() => navigate("/controlPanel/achievements")}>
                Achievements
            </button>
            <button className="control-panel-button" style={{ margin: '10px' }} onClick={() => navigate("/controlPanel/users")}>
                Users
            </button>   
            {modal}
        </div>
    );
}