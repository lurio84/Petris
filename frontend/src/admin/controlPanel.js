import { useState } from "react";
import getErrorModal from "../util/getErrorModal";
import getIdFromUrl from "../util/getIdFromUrl";
import useFetchState from "../util/useFetchState";
import { useNavigate } from "react-router-dom";
import tokenService from "../services/token.service";

const jwt = tokenService.getLocalAccessToken();

export default function ControlPanel() {

    const [message, setMessage] = useState(null);
    const [visible, setVisible] = useState(false);
    const modal = getErrorModal(setVisible, visible, message);
    const navigate = useNavigate();
    
    
    return (
        <div className="auth-page-container">
            <h2>Admin Control Panel</h2>
        </div>
    );
}