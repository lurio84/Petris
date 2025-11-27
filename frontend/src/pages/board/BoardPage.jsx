import React from 'react';
import './board.css';
import { TurnManager } from './TurnManager';
import { ContaminationBar } from './ContaminationBar';
import { Petriplate } from './Petriplate';
import useFetchState from "../../util/useFetchState";
import tokenService from "../../util/services/token.service";
import { useState } from "react";


const jwt = tokenService.getLocalAccessToken();
// Current turn tiene un valor de ejemplo ahora mismo, cambiar con estado de la partida
export default function BoardPage() {
    const loggedUser = tokenService.getUser();
    const [message, setMessage] = useState(null);
    const [visible, setVisible] = useState(false);
    const [activePetriplate, setActivePetriplate] = useState(null);
    
    
    

    const [player, setPlayer] = useFetchState(
            [],
            `/api/v1/players/username/${loggedUser.username}`,
            jwt,
            setMessage,
            setVisible
    );

    /* Mock de partida para ver la interfaz */
    const mockGame = {
        currentTurn: 1,
        contaminationOpponent: 0,
        contaminationPlayer: 0,
        greenPlayerId: player.id,
        purplePlayerId: 2,
        turnTeam: "PURPLE"
    }

    const [opponent, setOpponent] = useFetchState(
            [],
            `/api/v1/players/id/${mockGame.purplePlayerId}`,
            jwt,
            setMessage,
            setVisible
    );

    const handlePetriplateActivation = (position) => {
        setActivePetriplate(position);
    }

    // Determinar el color del jugador
    let playerColor = "PURPLE";
    if (mockGame.greenPlayerId == player.id) {
        playerColor = "GREEN";
    } 

    let opponentColor = "PURPLE";
    if (mockGame.greenPlayerId == opponent.id) {
        opponentColor = "GREEN";
    } 
    
    //#region Barras de contaminación

    function ContaminationOpponent() {
        return <ContaminationBar color={opponentColor} contamination={mockGame.contaminationOpponent} />;
    }

    function ContaminationPlayer() {
        
        return <ContaminationBar color={playerColor} contamination={mockGame.contaminationPlayer} />;
    }
    //#endregion



    //#region Renderizado de las petriplates
    // El jugador verde ve las petriplates al revés que el jugador púrpura
    function Petriplates() {
        if (mockGame.purplePlayerId == player.id) { 
            return (
                <div className="petriplatesArea">
                    <table>
                        <tbody>
                            <td>
                                <Petriplate position={1}  playerColor={playerColor} activePetriplate={handlePetriplateActivation} currentActivePetriplate={activePetriplate} />
                                <Petriplate position={4}  playerColor={playerColor} activePetriplate={handlePetriplateActivation} currentActivePetriplate={activePetriplate} />
                            </td>
                            <td>
                                <Petriplate position={2}  playerColor={playerColor} activePetriplate={handlePetriplateActivation} currentActivePetriplate={activePetriplate} />
                                <Petriplate position={5}  playerColor={playerColor} activePetriplate={handlePetriplateActivation} currentActivePetriplate={activePetriplate} />
                                <Petriplate position={7}  playerColor={playerColor} activePetriplate={handlePetriplateActivation} currentActivePetriplate={activePetriplate} />
                            </td>
                            <td>
                                <Petriplate position={3}  playerColor={playerColor} activePetriplate={handlePetriplateActivation} currentActivePetriplate={activePetriplate} />
                                <Petriplate position={6}  playerColor={playerColor} activePetriplate={handlePetriplateActivation} currentActivePetriplate={activePetriplate} />
                            </td>
                        </tbody>
                    </table>
                </div>
            )
        } else {
            return (
                <div className="petriplatesArea">
                    <table>
                        <tbody>
                            <td>
                                <Petriplate position={4}  playerColor={playerColor} activePetriplate={handlePetriplateActivation} currentActivePetriplate={activePetriplate} />
                                <Petriplate position={1}  playerColor={playerColor} activePetriplate={handlePetriplateActivation} currentActivePetriplate={activePetriplate} />
                            </td>
                            <td>
                                <Petriplate position={7}  playerColor={playerColor} activePetriplate={handlePetriplateActivation} currentActivePetriplate={activePetriplate} />
                                <Petriplate position={5}  playerColor={playerColor} activePetriplate={handlePetriplateActivation} currentActivePetriplate={activePetriplate} />
                                <Petriplate position={2}  playerColor={playerColor} activePetriplate={handlePetriplateActivation} currentActivePetriplate={activePetriplate} />
                            </td>
                            <td>
                                <Petriplate position={6}  playerColor={playerColor} activePetriplate={handlePetriplateActivation} currentActivePetriplate={activePetriplate} />
                                <Petriplate position={3}  playerColor={playerColor} activePetriplate={handlePetriplateActivation} currentActivePetriplate={activePetriplate} />
                            </td>
                        </tbody>
                    </table>
                </div>
            )
        }
    }
    //#endregion

    return(
        <div className="page-container">
            <TurnManager currentTurn={1} />
            <div className="game-area">
                <ContaminationOpponent />
                <Petriplates />
                <ContaminationPlayer />
            </div>
        </div>
    );
}