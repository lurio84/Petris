import React from 'react';
import './board.css';
import {TurnManager, ContaminationBar} from './BoardPresentation';
import { Petriplate } from './Petriplate';



function ContaminationOpponent() {
    // Valores de ejemplo, cambiar con estado de la partida
    return <ContaminationBar color="GREEN" contamination={1} />;
}

function ContaminationPlayer() {
    // Valores de ejemplo, cambiar con estado de la partida
    return <ContaminationBar color={"PURPLE"} contamination={0} />;
}

function Petriplates() {
    return (
        <div className="petriplatesArea">
            <table>
                <tbody>
                    <td><Petriplate/><Petriplate/></td>
                    <td><Petriplate/><Petriplate/><Petriplate/></td>
                    <td><Petriplate/><Petriplate/></td>
                </tbody>
            </table>
        </div>
    )
}


// Current turn tiene un valor de ejemplo ahora mismo, cambiar con estado de la partida
export default function BoardPage() {
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