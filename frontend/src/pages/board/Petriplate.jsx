export function Petriplate() {
    return (
        <div className="petriplate">
            <div className="petriplate-bg">
                <img src={`${process.env.PUBLIC_URL}/board/petriplate.png`} />
                <div className="petriplate-microbes">
                    {/* Aquí irían los microbios dentro de la petriplate */}
                </div>
            </div>
        </div>
    );
}