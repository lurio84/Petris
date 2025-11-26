import GenericErrorScreen from "./genericErrorScreen";

export function PlayerNotFoundErrorScreen() {
    return GenericErrorScreen("Jugador no encontrado");
}

export function UnauthorizedEditErrorScreen() {
    return GenericErrorScreen("No tienes permiso para editar este perfil");
}
export function PlayerNotIdentifiedErrorScreen() {
    return GenericErrorScreen("No estas identificado. Por favor, inicia sesión.");
}