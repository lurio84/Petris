import "../../static/css/player/profile.css";
import { useNavigate } from "react-router-dom";

export default function GenericErrorScreen(text, imageName) {
    const navigate = useNavigate();
    return (
        <div className="user-page-container">
            <div className="smaller-user-page-container">
                <img src={`${process.env.PUBLIC_URL}/error/${imageName || "default"}.png`} style ={ {width: '50%', height: 'auto'} }></img>
                <h2>{text || "Algo ha salido mal"}</h2>
                <p onClick={() => navigate(-1)}>Volver a la página anterior</p>
            </div>
        </div>
    );
}