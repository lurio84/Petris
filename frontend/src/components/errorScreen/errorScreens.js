import GenericErrorScreen from "./genericErrorScreen";

export function PlayerNotFoundErrorScreen() {
    return GenericErrorScreen("Jugador no encontrado");
}

export function UnauthorizedEditErrorScreen() {
    return GenericErrorScreen("No tienes permiso para editar este perfil");
}