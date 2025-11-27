import React from 'react';
import './board.css';

export default function BoardPage() {

    const TurnManager = () => {
        const turnImages = Array.from({ length: 40 }, (_, i) => i + 1).map((num) => {
            if (num % 10 !== 3 && num % 10 != 6 && num % 10 != 9 && num % 10 !== 0) {
                if ((num + Math.floor(num/10)) % 2 === 0) {
                    return(
                        <div key={num} className="turn-image">
                            <img src={`${process.env.PUBLIC_URL}/board/turnGreen.png`} />
                        </div>);
                } else {
                    return(
                        <div key={num} className="turn-image">
                            <img src={`${process.env.PUBLIC_URL}/board/turnPurple.png`} />
                        </div>);
                }
           } else {
                if(num % 10 === 3 || num % 10 === 6 || num % 10 === 9) {
                    if ((num + Math.floor(num/10)) % 2 === 0) {
                        return(
                        <div key={num} className="turn-propagation">
                            <img src={`${process.env.PUBLIC_URL}/board/propagation_icon_PURPLE.png`} />   
                        </div>);
                    } else {
                        return(
                        <div key={num} className="turn-propagation">
                            <img src={`${process.env.PUBLIC_URL}/board/propagation_icon_GREEN.png`} />   
                        </div>);
                    }
                } else {
                    if ((num + Math.floor(num/10)) % 2 === 0) {
                        return(
                        <div key={num} className="turn-propagation">
                            <img src={`${process.env.PUBLIC_URL}/board/biohazard_PURPLE.png`} />   
                        </div>);
                    } else {
                        return(
                        <div key={num} className="turn-propagation">
                            <img src={`${process.env.PUBLIC_URL}/board/biohazard_GREEN.png`} />   
                        </div>);
                    }
                }
                
            }
        });

        
        const turnIndicator = Array.from({ length: 40 }, (_, i) => i + 1).map((num) => {
            if (num == 1) { // Aquí estaría el turno actual
                return(
                    <div key={num} className="turn-indicator">
                        <img src={`${process.env.PUBLIC_URL}/board/turn_indicator.png`} />   
                    </div>
                );
            } else {
                return(
                    <div key={num} className="turn-image-empty">
                        <img src={`${process.env.PUBLIC_URL}/board/empty.png`}></img>
                    </div>
                );
            }
        });
        
        
        
        return(
            <div className="turns">
                <div className="turn-manager-movement">
                    {turnIndicator}
                </div>
                <div className="turn-manager-visual">
                    {turnImages}
                </div>
            </div>
        );
    }


    return(
        <div className="page-container">
            <TurnManager />
            <div className="game-area">
                Game Area
            </div>
        </div>
    );
}