# Plan de Pruebas

**Asignatura:** Diseño y Pruebas (Grado en Ingeniería del Software, Universidad de Sevilla)  

**Curso académico:** 2025/2026  

**Grupo/Equipo:** L4-1  

**Nombre del proyecto:** Petris

**Repositorio:** https://github.com/gii-is-DP1/dp1-2025-2026-l4-1

**Integrantes (máx. 6):** 
Jesús Cárdenas Conejo (jescarcon / jescarcon@alum.us.es)

Guillermo Ciria González (NQP9307 / guicirgon@alum.us.es, gciria@us.es)

Jaime González de Herrera Soriano (JMQ0481 / jaigonsor@us.es)

Conor López Chivite (LCY2956 / conlopchi@alum.us.es)

Lucas Ronquillo Bernáldez (HGK2646 / lucronber@alum.us.es)

Roberto Serrano Villalba (XDW2012 / robservil@alum.us.es)

## 1. Introducción

Este documento describe el plan de pruebas para el proyecto **Petris** desarrollado en el marco de la asignatura **Diseño y Pruebas 1** por el grupo **L4-1**. El objetivo del plan de pruebas es garantizar que el software desarrollado cumple con los requisitos especificados en las historias de usuario y que se han realizado las pruebas necesarias para validar su funcionamiento.

## 2. Alcance

El alcance de este plan de pruebas incluye:

- Pruebas unitarias.
  - Pruebas unitarias de backend incluyendo pruebas servicios o repositorios
  - Pruebas unitarias de frontend: pruebas de las funciones javascript creadas en frontend.
  - Pruebas unitarias de interfaz de usuario. Usan la interfaz de  usuario de nuestros componentes frontend.
- Pruebas de integración.  En nuestro caso principalmente son pruebas de controladores que también se ejecutarán mediante JUnit.

## 3. Estrategia de Pruebas

### 3.1 Tipos de Pruebas

#### 3.1.1 Pruebas Unitarias
Las pruebas unitarias se realizarán para verificar el correcto funcionamiento de los componentes individuales del software. Se utilizarán herramientas de automatización de pruebas como **JUnit** en backend y jest en frontend.

Las pruebas unitarias engloban:

T-01 User

T-02 Person

T-03 Player

T-04 Game

T-05 PetriPlate

T-06 Microorganism

T-07 TurnManager

T-08 Notification

T-09 PlayerStatistics

T-10 Achievement

#### 3.1.2 Pruebas de Integración
Las pruebas de integración se enfocarán en evaluar la interacción entre los distintos módulos o componentes del sistema, nosotros las realizaremos a nivel de API, probando nuestros controladores Spring.

Dichas pruebas pueden encontrarse en la tabla en el apartado 5.2.

## 4. Herramientas y Entorno de Pruebas

### 4.1 Herramientas
- **Maven**: Gestión de dependencias y ejecución de las pruebas.
- **JUnit**: Framework de pruebas unitarias.
- **Jacoco**: Generación de informes de cobertura de código. Si se ejecuta el comando de maven install, se copiará el informe de cobertura a la subcarpeta del repositorio /docs/deliverables/D3/coverage (puede visualizarse pulsando en el fichero index.html de dicho directorio).
- **Allure**: Generación de informes de estado de las últimas ejecuciones de las pruebas. Permite agrupar las pruebas por módulo/épica y feature. Si se ejecuta el comando de maven install, se copiará el informe de estado a la subcarpeta del repositorio /docs/deliverables/D3/status (puede visualizarse pulsando en el fichero index.html de dicho directorio).
- **Jest**: Framework para pruebas unitarias en javascript.
- **React-test**: Librería para la creación de pruebas unitarias de componentes React.

### 4.2 Entorno de Pruebas
Las pruebas se ejecutarán en el entorno de desarrollo y, eventualmente, en el entorno de pruebas del servidor de integración continua.

## 5. Planificación de Pruebas
### 5.1 Estado y trazadibilidad de Pruebas por Módulo y Épica

El informe de estado de las pruebas (con trazabilidad de éstas hacia los módulos y las épicas/historias de usaurio) se encuentra [aquí](
https://gii-is-dp1.github.io/group-project-seed/deliverables/D3/status/#behaviors).

### 5.2 Cobertura de Pruebas

El informe de cobertura de pruebas se puede consultar [aquí](
https://gii-is-dp1.github.io/group-project-seed/deliverables/D3/coverage/).

*Nota importante para el alumno*: A la hora de entregar el proyecto, debes modificar la url para que esté asociada al respositorio concreto de tu proyecto. Date cuenta de que ahora mismo apunta al repositorio _gii-is-DP1/group-project-seed_.

| Historia de Usuario | Prueba | Descripción | Estado | Tipo |
|---------------------|--------|-------------|--------|------|
| HU-01: Registrar jugador | **T-11** | Verifica que el sistema permite registrar un usuario con datos válidos y que inicia sesión automáticamente. | Pendiente | Funcional (interfaz / backend) |
| HU-01: Registrar jugador | **T-12** | Comprueba que el sistema rechaza datos inválidos al registrarse. | Pendiente | Funcional (validación backend) |
| HU-02: Iniciar sesión | **T-13** | Verifica que un usuario puede iniciar sesión con credenciales válidas. | Pendiente | Funcional (backend de autenticación) |
| HU-02: Iniciar sesión | **T-14** | Comprueba que el sistema muestra error si la contraseña es incorrecta. | Pendiente | Funcional (controlador) |
| HU-03: Cerrar sesión | **T-13** | Verifica que un usuario autenticado puede cerrar su sesión correctamente. | Pendiente | Funcional (sesión / seguridad) |
| HU-04: Ver perfil | **T-15** | Comprueba que se muestra correctamente la información del perfil del jugador. | Pendiente | Funcional (interfaz / backend) |
| HU-05: Editar perfil | **T-15** | Verifica que el jugador puede modificar sus datos personales y se guardan correctamente. | Pendiente | Funcional (interfaz / backend) |
| HU-06: Eliminar jugador | **T-16** | Comprueba que el jugador puede eliminar su cuenta y todos sus datos. | Pendiente | Funcional (backend / base de datos) |
| HU-07: Enviar solicitud de amistad | **T-17** | Verifica que se puede enviar una solicitud de amistad a otro jugador existente. | Pendiente | Funcional (backend / relaciones) |
| HU-07: Enviar solicitud de amistad | **T-18** | Comprueba que el sistema impide enviarse una solicitud a uno mismo. | Pendiente | Funcional (validación backend) |
| HU-08: Gestionar solicitudes de amistad | **T-19** | Verifica que se pueden aceptar o rechazar solicitudes pendientes. | Pendiente | Funcional (backend / lógica de relaciones) |
| HU-09: Gestionar amigos | **T-20** | Comprueba que los amigos aparecen en la lista y se pueden eliminar. | Pendiente | Funcional (interfaz / backend) |
| HU-10: Ver partidas | **T-21** | Verifica que el sistema lista las partidas públicas disponibles. | Pendiente | Funcional (interfaz / API) |
| HU-11: Unirse a partida | **T-22** | Comprueba que un jugador puede unirse a una partida pública existente. | Pendiente | Funcional (backend / sincronización) |
| HU-12: Crear partida | **T-23** | Verifica que un jugador puede crear una partida privada e invitar amigos. | Pendiente | Funcional (backend / lógica de juego) |
| HU-13: Espectar partida | **T-25** | Comprueba que un usuario puede ver en tiempo real una partida de amigos. | Pendiente | Funcional (frontend / WebSocket) |
| HU-14: Rechazar invitaciones recibidas | **T-24** | Verifica que el sistema elimina invitaciones rechazadas. | Pendiente | Funcional (interfaz / backend) |
| HU-15: Funcionamiento de la partida | **T-26** | Comprueba que las fases del juego avanzan correctamente. | Pendiente | Funcional (motor de juego) |
| HU-15: Funcionamiento de la partida | **T-27** | Verifica que los movimientos inválidos son rechazados. | Pendiente | Funcional (validación de reglas) |
| HU-15: Funcionamiento de la partida | **T-28** | Comprueba que se crean sarcinas al acumular 5 bacterias. | Pendiente | Funcional (lógica de tablero) |
| HU-15: Funcionamiento de la partida | **T-29** | Verifica la creación de bacterias durante la fase de fisión. | Pendiente | Funcional (reglas de juego) |
| HU-15: Funcionamiento de la partida | **T-30** | Comprueba la actualización de puntos en fase de contaminación. | Pendiente | Funcional (reglas / puntuación) |
| HU-15: Funcionamiento de la partida | **T-31** | Verifica la correcta detección de condiciones de victoria. | Pendiente | Funcional (motor / control de estado) |
| HU-16: Ver información de partida | **T-26, T-30** | Comprueba que se muestra correctamente el estado de la partida (tablero, fases y puntuación). | Pendiente | Integración (frontend-backend en tiempo real) |
| HU-17: Condiciones de victoria | **T-31** | Verifica que se determina correctamente el ganador según las reglas. | Pendiente | Funcional (motor de juego) |
| HU-18: Fase de propagación | **T-26, T-27, T-28** | Comprueba que los movimientos válidos/ inválidos se gestionan correctamente. | Pendiente | Funcional (motor de fases) |
| HU-19: Fase de fisión | **T-29** | Verifica que se generan bacterias nuevas en las losetas correctas. | Pendiente | Funcional (motor de fases) |
| HU-20: Fase de contaminación | **T-30** | Comprueba que los puntos de contaminación se aplican correctamente. | Pendiente | Funcional (motor / reglas) |
| HU-21: Abandonar partida | **T-31** | Verifica que la partida se marca como terminada al abandonar. | Pendiente | Funcional (backend / control de estado) |
| HU-22: Chat de partida | **T-32** | Comprueba que los jugadores pueden enviar mensajes visibles entre ellos. | Pendiente | Integración (WebSocket) |
| HU-22: Chat de partida | **T-33** | Verifica que los mensajes de espectadores no se muestran a los jugadores. | Pendiente | Integración (WebSocket / permisos) |
| HU-23: Administrar jugadores | **T-35, T-36, T-37** | Comprueba que el administrador puede ver, editar y eliminar jugadores. | Pendiente | Funcional (backend / seguridad) |
| HU-24: Administrar logros | **T-38** | Verifica que se pueden crear, modificar y eliminar logros correctamente. | Pendiente | Funcional (backend / base de datos) |
| HU-25: Administrar partidas en curso | **T-39** | Comprueba que el administrador puede listar y eliminar partidas activas. | Pendiente | Funcional (backend / seguridad) |

## 6. Criterios de Aceptación

- Todas las pruebas unitarias deben pasar con éxito antes de la entrega final del proyecto.
- La cobertura de código debe ser al menos del 70%.
- No debe haber fallos críticos en las pruebas de integración y en la funcionalidad.

## 7. Conclusión

Este plan de pruebas establece la estructura y los criterios para asegurar la calidad del software desarrollado. Es responsabilidad del equipo de desarrollo y pruebas seguir este plan para garantizar la entrega de un producto funcional y libre de errores.
