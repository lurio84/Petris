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
                return(
            <div key={num} className="turn-image-empty">
                <img src={`${process.env.PUBLIC_URL}/board/empty.png`} />
            </div>);
            }
        });

        return(
            <div className="turn-manager">
                {turnImages}
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