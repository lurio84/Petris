import { React } from "react";
import { useState } from "react";

export function Microbe({ color, type, selectable, onSelectionChange }) {

    const [isSelected, setIsSelected] = useState(false);

    const handleClick = () => {
        if (!selectable) {return;}

        const newSelectedState = !isSelected;
        setIsSelected(newSelectedState);
        onSelectionChange(newSelectedState);
    };

    const getImageSrc = () => {
        const baseType = type === "BACTERIA" ? "" : "_SARCINE";
        const stateIndicator = isSelected ? "_SELECTED" : "";
        return `${process.env.PUBLIC_URL}/board/microbe_${color}${baseType}${stateIndicator}.png`;
    };

    return (
        <img 
            src={getImageSrc()} 
            className="petriplate-microbe" 
            onClick={handleClick}
            alt={`${color} ${type}`}
            style={{ cursor: !selectable ? 'default' : 'pointer',
                pointerEvents: 'auto'
             }}
        />
    );
}