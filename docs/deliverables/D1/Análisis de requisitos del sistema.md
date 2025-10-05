# Documento de análisis de requisitos del sistema

**Asignatura:** Diseño y Pruebas (Grado en Ingeniería del Software, Universidad de Sevilla)  
**Curso académico:** <!-- p.ej., 2025/2026 -->  
**Grupo/Equipo:** <!-- p.ej., L4-03 Equipo 33 -->  
**Nombre del proyecto:** <!-- p. ej. Petris -->  
**Repositorio:** <!-- URL del repo -->  
**Integrantes (máx. 6):** <!-- Nombre Apellidos (US-Id / correo @us.es) -->

_Esta es una plantilla que sirve como guía para realizar este entregable. Por favor, mantén las mismas secciones y los contenidos que se indican para poder hacer su revisión más ágil._

## Introducción

_En esta sección debes describir de manera general cual es la funcionalidad del proyecto a rasgos generales. ¿Qué valor puede aportar? ¿Qué objetivos pretendemos alcanzar con su implementación? ¿Cuántos jugadores pueden intervenir en una partida como máximo y como mínimo? ¿Cómo se desarrolla normalmente una partida?¿Cuánto suelen durar?¿Cuando termina la partida?¿Cuantos puntos gana cada jugador o cual es el criterio para elegir al vencedor?_

[Enlace al vídeo de explicación de las reglas del juego / partida jugada por el grupo](https://www.youtube.com/watch?v=ZcoIduJaJIo)

## Tipos de Usuarios / Roles

**Jugador:**

**Jugador en partida:**

**Espectador:**

**Administrador:**

## Historias de Usuario

A continuación se definen todas las historias de usuario a implementar:
_Os recomentamos usar la siguiente plantilla de contenidos que usa un formato tabular:_

### HU-(ISSUE#ID): Nombre ([Enlace a la Issue asociada a la historia de usuario]()

| Descripción de la historia siguiendo el esquema: "Como <rol> quiero que el sistema <funcionalidad> para poder <objetivo/beneficio>." |
| ------------------------------------------------------------------------------------------------------------------------------------ |
| Mockups (prototipos en formato imagen de baja fidelidad) de la interfaz de usuario del sistema                                       |
| Decripción de las interacciones concretas a realizar con la interfaz de usuario del sistema para lleva a cabo la historia.           |

## Diagrama conceptual del sistema

![Diagrama UML del sistema](./PetrisUML.png)

## Reglas de Negocio

### R1 – Inicio de partida

_Restricción: Una partida solo puede iniciarse si hay exactamente dos jugadores conectados._

_Ej:_ Si hay un jugador esperando y nadie más conectado, la partida no puede comenzar.

---

### R2 – Asignación automática de equipo

_Restricción: El sistema asigna automáticamente el color de bacterias (equipo) a cada jugador._

---

### R3 – Preparación del tablero

_Restricción: El tablero se prepara con 7 discos de Petri colocados en el área de juego._

---

### R4 – Recursos iniciales

_Restricción: Cada jugador recibe al inicio 20 bacterias y 4 sarcinas._

---

### R5 – Posición inicial

_Restricción: Cada jugador comienza con una bacteria en su disco de salida (definido por color)._

---

### R6 – Marcador de turnos

_Restricción: El marcador de turnos se coloca en la casilla inicial y avanza según la progresión de fases._

---

### R7 – Contador de contaminación

_Restricción: El contador de contaminación estará a cero al inicio de la partida para ambos jugadores._

---

### R8 – Duración de la partida

_Restricción: La partida se desarrolla en 4 rondas completas._

---

### R9 – Fases del juego

_Restricción: El juego se divide en tres fases: propagación, fisión binaria y contaminación._

_Ej:_ En la segunda fase cada jugador añade una bacteria por cada disco donde tiene desventaja numérica.

---

### R10 – Control de color

_Restricción: Cada jugador controla un color de bacterias, sin roles diferenciados._

---

### R11 – Acciones del jugador

_Restricción: Durante su turno el jugador puede colocar, mover bacterias o formar sarcinas._

---

### R12 – Unidades indivisibles

_Restricción: Las sarcinas son unidades indivisibles y afectan la dominación de discos._

---

### R13 – Inicio del turno

_Restricción: Se verifica que el jugador tenga bacterias disponibles._

---

### R14 – Acción principal

_Restricción: El jugador realiza su jugada colocando o moviendo bacterias._

---

### R15 – Control de discos

_Restricción: Si un disco está completamente cubierto se marca como dominado._

---

### R16 – Contaminación

_Restricción: Se actualizan los valores de contaminación de ambos jugadores._

---

### R17 – Final de turno

_Restricción: Se pasa el turno al oponente._

---

### R18 – Condiciones de victoria

_Restricción: El juego puede finalizar por dominación, contaminación o agotamiento._

---

### R19 – Chat en tiempo real

_Restricción: Los jugadores deben tener acceso a un chat de equipo en tiempo real._

---

### R20 – Orden de turnos

_Restricción: El sistema gestiona automáticamente el orden de turnos._

---

### R21 – Validación de movimientos

_Restricción: La aplicación valida todos los movimientos antes de aplicarlos._

---

### R22 – Sincronización de tablero

_Restricción: El tablero se sincroniza en tiempo real entre los jugadores._

---

### R23 – Información visual

_Restricción: La interfaz debe mostrar tablero, dominación, contaminación y reservas._

---

### R24 – Controles del jugador

_Restricción: Los jugadores disponen de botones para seleccionar, colocar, mover y confirmar._

---

### R25 – Retroalimentación visual y sonora

_Restricción: El sistema debe ofrecer feedback visual y sonoro en eventos importantes._

---

### R26 – Exclusividad de turno

_Restricción: Solo el jugador activo puede mover durante su turno._

---

### R27 – Jugadas inválidas

_Restricción: Los intentos de jugadas inválidas deben bloquearse._

---

### R28 – Registro de jugadas

_Restricción: Se debe registrar el historial de jugadas para detectar errores o trampas._

---

### R29 – Autenticación obligatoria

_Restricción: Un usuario sin cuenta no puede acceder al juego; iniciar sesión es obligatorio._

---

### R30 – Integridad de la partida

_Restricción: Las partidas en curso no pueden modificarse por jugadores no participantes._

---

### R31 – Gestión de perfiles

_Restricción: Los jugadores solo pueden editar o eliminar su propio perfil, salvo administradores._

---

### R32 – Creación de partidas

_Restricción: El sistema permite crear partidas públicas o privadas._

---

### R33 – Autenticación para unirse

_Restricción: Para unirse a una partida, el jugador debe estar autenticado._

---

### R34 – Registro de partidas

_Restricción: El sistema mantiene registro de partidas finalizadas y en curso._

---

### R35 – Visibilidad de partidas en curso

_Restricción: Las partidas en curso no aparecen en el listado público para no participantes._

---

### R36 – Invitaciones activas

_Restricción: Un jugador solo puede enviar una invitación activa por amigo a la vez._

---

### R37 – Expiración de invitaciones

_Restricción: Una invitación expira tras un minuto si no se acepta._

---

### R38 – Espectadores

_Restricción: Solo pueden espectar los amigos de todos los participantes de la partida._

---

### R39 – Estado “en línea”

_Restricción: Un jugador con sesión abierta aparece como “en línea”._

---

### R40 – Estado “desconectado”

_Restricción: Un jugador sin sesión activa aparece como “desconectado”._

---

### R41 – Actualización de estado

_Restricción: El estado de conexión se actualiza en tiempo real y afecta a las invitaciones._

### R42 – Gestión de logros

_Restricción: Solo los administradores podrán eliminar, editar o crear logros._

---

### R43 – Gestión de jugadores

_Restricción: Solo los administradores podrán ver, modificar o eliminar jugadores._

---

### R44 – Control de partidas activas

_Restricción: Solo los administradores podrán pausar o eliminar una partida que esté en curso._
