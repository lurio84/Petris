import React from "react";
import "./board.css";
import { Microbe } from "./Microbe";
import { useState } from "react";

export function Petriplate({ position, playerColor, activePetriplate, currentActivePetriplate }) {
    //#region Mock Data
    const petriplatesDatamock = {
        1: {
            greenBacteria: 0,
            purpleBacteria: 0,
            greenSarcines: 0,
            purpleSarcines: 0
        },
        2: {
            greenBacteria: 1,
            purpleBacteria: 0,
            greenSarcines: 0,
            purpleSarcines: 0
        },
        3: {
            greenBacteria: 2,
            purpleBacteria: 0,
            greenSarcines: 0,
            purpleSarcines: 0
        },
        4: {
            greenBacteria: 0,
            purpleBacteria: 0,
            greenSarcines: 0,
            purpleSarcines: 0
        },
        5: {
            greenBacteria: 3,
            purpleBacteria: 0,
            greenSarcines: 0,
            purpleSarcines: 0
        },
        6: {
            greenBacteria: 1,
            purpleBacteria: 0,
            greenSarcines: 0,
            purpleSarcines: 0
        },
        7: {
            greenBacteria: 0,
            purpleBacteria: 1,
            greenSarcines: 0,
            purpleSarcines: 0
        }
    }

    //#endregion

    const handleMicrobeSelect = (isSelected) => {
        if (isSelected) {
            activePetriplate(position);
        } else {
            activePetriplate(null);
        }
    }

    const petriplateData = petriplatesDatamock[position];
    const isThisActive = currentActivePetriplate === position;

    function checkIfSelectable(color) {
        return playerColor == color && (currentActivePetriplate === null || isThisActive);
    }

    //#region Render Microbes
    const greenBacteria = Array.from({ length: petriplateData.greenBacteria }, (_, i) => i + 1).map((num) => (
        <Microbe 
            key={`green-bacteria-${num}`}
            color="GREEN" 
            type="BACTERIA" 
                selectable={checkIfSelectable("GREEN")} 
            onSelectionChange={handleMicrobeSelect}
        />
    ));

    const purpleBacteria = Array.from({ length: petriplateData.purpleBacteria }, (_, i) => i + 1).map((num) => (
        <Microbe 
            key={`purple-bacteria-${num}`}
            color="PURPLE" 
            type="BACTERIA" 
            selectable={checkIfSelectable("PURPLE")} 
            onSelectionChange={handleMicrobeSelect}
        />
    ));

    const greenSarcines = Array.from({ length: petriplateData.greenSarcines }, (_, i) => i + 1).map((num) => (
        <Microbe 
            key={`green-sarcine-${num}`}
            color="GREEN" 
            type="SARCINE" 
            selectable={false}
        />
    ));

    const purpleSarcines = Array.from({ length: petriplateData.purpleSarcines }, (_, i) => i + 1).map((num) => (
        <Microbe 
            key={`purple-sarcine-${num}`}
            color="PURPLE" 
            type="SARCINE" 
            selectable={false}
        />
    ));

    //#endregion

    return (
        <div className={`petriplate ${isThisActive ? 'active' : ''}`}>
            <div className="petriplate-bg">
                <img src={`${process.env.PUBLIC_URL}/board/petriplate.png`} alt="petriplate" />
                <div className="petriplate-microbes">
                    {greenBacteria}
                    {purpleBacteria}
                    {greenSarcines}
                    {purpleSarcines}
                </div>
            </div>
        </div>
    );
}