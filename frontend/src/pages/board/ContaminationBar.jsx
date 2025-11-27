import './board.css';

export function ContaminationBar({ color, contamination }) {
    const counters = Array.from({ length: 9 }, (_, i) => i + 1).map((num => {
        if (num - 1 === contamination) {
            return (
            <div key={num} className={`contamination-${color}-counter`}>
                    <img src={`${process.env.PUBLIC_URL}/board/contamination_indicator_${color}.png`} />    
            </div>)
        } else {
            return (
            <div key={num} className={`contamination-${color}-counter`}>
                    {-(num-1)}
            </div>)
        }
    }))
    return (
        <div className={`contamination-${color}`}>
            {counters}
        </div>
    );
}