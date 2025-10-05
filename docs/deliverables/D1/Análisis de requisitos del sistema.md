# Documento de análisis de requisitos del sistema

**Asignatura:** Diseño y Pruebas (Grado en Ingeniería del Software, Universidad de Sevilla)  
**Curso académico:** 2025/2026
**Grupo/Equipo:** Grupo 2 L4-01
**Nombre del proyecto:** Petris
**Repositorio:** https://github.com/gii-is-DP1/dp1-2025-2026-l4-1
**Integrantes (máx. 6):**
Jesús Cárdenas Conejo (jescarcon / jescarcon@alum.us.es)
Guillermo Ciria González (NQP9307 / guicirgon@alum.us.es, gciria@us.es)
Jaime González de Herrera Soriano (JMQ0481 / jaigonsor@us.es)
Conor López Chivite (LCY2956 / conlopchi@alum.us.es)
Lucas Ronquillo Bernáldez (HGK2646 / lucronber@alum.us.es)
Roberto Serrano Villalba (XDW2012 / robservil@alum.us.es)

## Introducción

La aplicación que vamos a diseñar se trata de una adaptación virtual interactiva del juego de mesa **Petris**. El juego consiste en encarnar el rol de unos científicos que tratan de impedir que una plaga de bacterias se expanda. El sistema no consiste solamente en permitir que se juegue a Petris; también tendrá funcionalidades que aprovechen el entorno digital de la aplicación para dar una experiencia más amplia que el juego normal.

Las funcionalidades que implementaremos son las siguientes:

- **Registro de jugadores:** Los usuarios podrán registrarse en la aplicación con nombre y contraseña, al igual que iniciar y cerrar sesión cuando deseen.
- **Personalización de perfil:** Cada jugador tendrá un perfil que puede personalizar a su gusto (foto de perfil, descripción...).
- **Gestionar amigos:** Se podrán mandar y recibir solicitudes de amistad para ser amigo de otros jugadores, permitiendo jugar partidas con ellos.
- **Partidas públicas y privadas:** Se podrán crear tanto partidas públicas en las que cualquier persona pueda unirse, y partidas privadas para jugar únicamente con amigos, y las partidas tendrán normas personalizables por el creador.
- **Chat en tiempo real:** Los jugadores y espectadores de una partida podrán comunicarse por chat en tiempo real.
- **Estadísticas:** Cada jugador tendrá estadísticas sobre las partidas que han jugado (número de victorias, duración media de partida...).
- **Logros:** Habrá logros que los jugadores podrán obtener si cumplen ciertos objetivos.
- **Administradores:** Existirán administradores que controlen el uso de la aplicación para asegurar que no haya problemas.

En cuanto a la implementación del juego Petris en sí, nos aseguraremos de incluir las reglas del juego, que son las siguientes:

(Algunas de estas reglas podrán ser personalizadas por el creador de la partida, pero aquí se muestran las reglas por defecto)

### El juego de un vistazo

Petris es un juego de control bacteriano para 2 jugadores, +10 años y 10’ de duración.

Cada jugador encarna un científico y deberá controlar la propagación de las bacterias bajo su supervisión. Dichas bacterias están hambrientas y siempre están buscando alimento en los discos de Petri colindantes. ¡Cuidado! Las bacterias que estén solas en un disco de Petri comerán mucho y se reproducirán... ¡haciendo más difícil la tarea de controlarlas!

Durante la partida, deberás gestionar la propagación de las bacterias a tu cargo, para que se reproduzcan menos y así no poner en riesgo los cultivos de los discos de Petri. En cada fase contaminación, recibirás puntos de contaminación por cada disco de Petri en el que haya más bacterias tuyas que del oponente. Al final de la partida, ¡el jugador con menos puntos de contaminación será el ganador!

### Componentes

- 7 Losetas de disco de Petri a doble cara (discos, de ahora en adelante)
- 1 Ficha de marcador de turno
- 1 Ficha de contaminación por jugador
- 1 Tablero

### Preparación de la partida

1. Se muestra el área de juego con los 7 discos. Esto representa el cultivo comunitario.
2. Se muestra el tablero al lado del área de juego, con la cara de 2 jugadores hacia arriba, y coloca la ficha de marcador de turno en el espacio inicial del contador de turnos.
3. Cada jugador tiene su ficha de contaminación en el espacio inicial del contador de contaminación.
4. Cada jugador tiene una bacteria de su reserva en el disco de su color.

### Cómo se juega

Tal y como se indica en el tablero, se jugarán 4 rondas. En cada ronda hay 3 fases de propagación por jugador, 3 fases de fisión binaria y 1 fase de contaminación.

**Fase de propagación**

En cada fase de propagación, el jugador activo deberá realizar una propagación válida. Para ello:

El jugador seleccionará un disco que contenga al menos una de sus bacterias. Desde dicho disco podrá mover cualquier número de sus bacterias a uno o más discos adyacentes, repartiendo dichas bacterias como considere oportuno, pero siempre respetando las dos reglas siguientes:

1. Un disco nunca puede albergar exactamente el mismo número de bacterias de cada jugador.
2. Siempre se deberá mover al menos una bacteria.

Si el jugador no puede realizar una propagación válida, dicho jugador pierde la partida automáticamente.

Si después de una propagación (o una fisión binaria, ver más adelante) un disco contiene 5 bacterias de un jugador, se sustituyen inmediatamente dichas 5 bacterias por una sarcina. Las sarcinas nunca pueden ser retiradas ni movidas y equivalen a 5 bacterias. Adicionalmente, un jugador nunca puede mover bacterias a un disco que contenga una de sus sarcinas.

Una vez que el jugador realiza su propagación, se moverá el marcador de turno.

**Fase de fisión binaria**

Cada 2 propagaciones hay una fase de fisión binaria. Por cada disco:

- Si contiene bacterias de un único jugador, estas se reproducen y dicho jugador añade una bacteria adicional. Esto puede resultar en la aparición de una sarcina. 
- Si contiene bacterias de ambos jugadores, no ocurre nada. Una vez realizada la fisión binaria, se moverá el marcador de turno, dando paso a la siguiente fase de propagación o de contaminación.

**Fase de contaminación**

Cada 3 propagaciones hay una fase de contaminación, que se registrará en el contador de contaminación. Por cada disco:

- El jugador con más bacterias en dicho disco anota 1 punto de contaminación. Para ello, desciende 1 espacio su ficha de contaminación en el contador de contaminación, pudiendo provocar el final de la partida (ver más adelante).

Una vez realizada la contaminación, se moverá el marcador de turno, dando paso a la siguiente fase de propagación. Durante la partida hay 4 fases de contaminación, y después de la cuarta se procede al final de partida.

### Final de la partida

El final de partida se puede producir de tres formas:

1. Si durante su turno un jugador no puede realizar una propagación válida (bien porque no tiene bacterias para mover, bien porque no puede desplazarlas a ningún disco libre), pierde automáticamente la partida.     
2. Si durante alguna fase de contaminación, un jugador mueve su ficha de contaminación al último espacio del contador de contaminación, su oponente gana automáticamente la partida. Esto refleja que las bacterias del primer jugador se han descontrolado totalmente, y han arruinado el cultivo comunitario. En el caso que ambos jugadores llegaran al último espacio del contador de contaminación en la misma fase de contaminación, ganará el jugador con menos fichas (bacterias y sarcinas) en los discos. Si el empate persiste, ganará el jugador con menos sarcinas en los discos.
3. Si después de la cuarta fase de contaminación ningún jugador ha ganado la partida, la gana el jugador cuya ficha de contaminación esté menos avanzada en el contador de contaminación. Si estuvieran empatados, se rompe el empate de la forma anteriormente descrita.         

[Enlace al vídeo de explicación de las reglas del juego y partida jugada por el grupo](https://www.youtube.com/watch?v=ZcoIduJaJIo)

## Tipos de Usuarios / Roles

**Jugador:** Un usuario registrado que no es administrador. Puede modificar su perfil, enviar y aceptar/rechazar solicitudes de amistad, gestionar su lista de amigos, crear y unirse a partidas públicas y privadas y eliminar su cuenta.

**Jugador en partida:** Un jugador que está en medio de una partida. Puede mover sus bacterias en sus fases de propagación, escribir en el chat, invitar espectadores y abandonar la partida.

**Espectador:** Un espectador ve la partida en tiempo real de otros dos jugadores. Puede escribir en el chat, aunque sus mensajes sólo pueden ser vistos por otros espectadores, pero no puede interactuar de ninguna forma con la partida.

**Administrador:** Un administrador tiene control casi total sobre el sistema. Puede modificar y eliminar jugadores; crear, modificar y eliminar logros; y eliminar partidas públicas y privadas.

## Historias de Usuario

A continuación se definen todas las historias de usuario a implementar:

### **Gestión de perfiles**

### H-01: Registrar jugador

| Como usuario, quiero registrarme como jugador en el sistema para poder identificarme y acceder a las funcionalidades de jugador. |
| ------------------------------------------------------------------------------------------------------------------------------------ |
| <img width="1290" height="916" alt="H-01" src="https://github.com/user-attachments/assets/9e01a1fd-1950-4bcf-9753-d2b185ec647d" />
**H-01+E1: Éxito al registrar jugador**
Dado un usuario que introduzca un nombre, contraseña y campos de perfil con formato correcto al intentar registrar un jugador, se creará un jugador en la base de datos con dichas credenciales, y el usuario iniciará sesión como dicho jugador.
**H-01-E2: Intentar registrar jugador con datos no válidos**
Dado un usuario que introduzca un nombre, contraseña y/o campo/s de perfil con formato incorrecto al intentar registrar un jugador, se mostrará un error en pantalla indicando que los datos no son válidos y el jugador no será creado en la base de datos.<img width="1290" height="916" alt="H-01-E2" src="https://github.com/user-attachments/assets/f53c2030-2f59-4b72-88a4-66ab6a2c8a7d" />



### H-02: Iniciar sesión

| Como usuario, quiero iniciar sesión como un jugador/administrador existente en la base de datos del sistema para poder identificarme y acceder a las funcionalidades de jugador. |
| ------------------------------------------------------------------------------------------------------------------------------------ |
| *Mockups (si hay)*                                      |
**H-02+E1: Éxito al iniciar sesión**
Dado un usuario que introduzca un nombre y/o contraseña asociados a un jugador/administrador existente en la base de datos del sistema al intentar iniciar sesión, el usuario iniciará sesión como dicho jugador.<img width="1290" height="916" alt="H-02-E1" src="https://github.com/user-attachments/assets/14c8077d-29ec-41c0-8795-8c6aea7a95ab" />

**H-02-E2: Intentar iniciar sesión con nombre de usuario inexistente**
Dado un usuario que introduzca un nombre no asociado a ningún jugador/administrador de la base de datos del sistema al intentar iniciar sesión, se mostrará un error en pantalla indicando que ningún jugador de la base de datos tiene dicho nombre y el usuario no iniciará sesión.<img width="1290" height="916" alt="H-02-E2" src="https://github.com/user-attachments/assets/edeb5963-d40a-4fea-8902-4573c08d25f2" />

**H-02-E3: Intentar iniciar sesión con contraseña incorrecta**
Dado un usuario que introduzca un nombre y contraseña donde la contraseña no esté asociada al jugador/administrador con dicho nombre en la base de datos al intentar iniciar sesión, se mostrará un error en pantalla indicando el jugador/administrador con dicho nombre no tiene dicha contraseña y el usuario no iniciará sesión.

### H-03: Cerrar sesión

| Como jugador, quiero cerrar mi sesión para poder dejar de usar el sistema y permitir a otro usuario iniciar sesión. |
| ------------------------------------------------------------------------------------------------------------------------------------ |
|
                             |
**H-03+E1: Éxito al cerrar sesión**
Dado un jugador con una sesión iniciada que intente cerrarla, la sesión se cerrará de forma segura y la aplicación volverá a la pantalla de inicio.
**H-03-E2: Jugador inexistente**
Dado un jugador que no esté en la base de datos con una sesión iniciada que intente cerrarla, se mostrará un error indicando que el usuario no existe.
<img width="1290" height="916" alt="H-03-E2" src="https://github.com/user-attachments/assets/01e5690a-3292-43b4-9623-34edd0f93d80" />
### H-04: Ver perfil

| Como jugador, quiero ver mi perfil para poder comprobar qué información muestro sobre mí. |
| ------------------------------------------------------------------------------------------------------------------------------------ |
|                                       |
**H-04+E1: Éxito al ver perfil**
Dado un jugador que intente ver su perfil, se le mostrará junto con toda su información: últimos logros obtenidos, estadísticas y datos personales.<img width="1288" height="916" alt="H-04+E1" src="https://github.com/user-attachments/assets/063672c3-3918-4a2a-96f7-8fe589a43131" />

**H-04+E2: Éxito al ver todos los logros**
Dado un jugador que intente ver todos sus logros desde su perfil, se mostrará una lista de todos los logros del juego, mostrando para cada uno si se ha obtenido o no y cómo obtenerlo.<img width="1288" height="916" alt="H-04+E2" src="https://github.com/user-attachments/assets/2d7c024f-15ea-4993-9b4a-ca16dc6a4175" />

**H-04-E3: Jugador inexistente**
Dado un jugador que no esté en la base de datos que intente ver su perfil, se mostrará un error indicando que el usuario no existe.<img width="1290" height="916" alt="H-04-E3" src="https://github.com/user-attachments/assets/4d45dbce-959a-42d9-a360-5ed3d43fe63b" />


### H-05: Editar perfil

| Como jugador, quiero editar mi perfil para poder elegir qué información muestro sobre mí. |
| ------------------------------------------------------------------------------------------------------------------------------------ |
|                                      
**H-05+E1: Éxito al editar perfil**
Dado un jugador que introduzca campos de perfil con formato correcto al intentar editar su perfil, los valores de su perfil serán reemplazados correctamente con los que ha introducido.<img width="1288" height="916" alt="H-05+E1" src="https://github.com/user-attachments/assets/1855281c-2ccb-4fef-911d-82f0e5580c45" />

**H-05-E2: Editar perfil con datos no válidos**
Dado un jugador que introduzca campos de perfil con formato incorrecto al intentar editar su perfil, se mostrará un error en pantalla indicando que los datos no son válidos y los valores de su perfil no cambiarán.<img width="1288" height="916" alt="H-05-E2" src="https://github.com/user-attachments/assets/990dd44a-c385-4eb1-bf4a-9ae9ff7cfacb" />

**H-05-E3: Jugador inexistente**
Dado un jugador que no esté en la base de datos que intente editar su perfil, se mostrará un error indicando que el usuario no existe.<img width="1290" height="916" alt="H-05-E3" src="https://github.com/user-attachments/assets/81a7d09f-64ad-40df-b26d-6cbe37d306fd" />


### H-06: Eliminar jugador

| Como jugador, quiero eliminar mi cuenta para poder deshacerme de los datos de mí que estén guardados en la base de datos del sistema. |
| ------------------------------------------------------------------------------------------------------------------------------------ |
| *Mockups (si hay)*                                      |
**H-06+E1: Éxito al eliminar jugador**
Dado un jugador que intente eliminar su cuenta, al introducir su contraseña dicho jugador será completamente eliminado de la base de datos del sistema, junto con todos sus datos asociados.<img width="1288" height="916" alt="H-06+E1" src="https://github.com/user-attachments/assets/76101273-4add-431c-8320-25cab7480c86" />

**H-06-E2 - Contraseña incorrecta**
Dado un jugador que intente eliminar su cuenta, al introducir una contraseña incorrecta, dicho jugador no se eliminará de la base de datos y se dará un mensaje de error.<img width="1288" height="916" alt="H-06-E2" src="https://github.com/user-attachments/assets/1251580f-daf8-40a6-8a97-78adc040f697" />

**H-06+E3: Jugador inexistente**
Dado un jugador que no esté en la base de datos que intente eliminar su cuenta, se mostrará un error indicando que el usuario no existe.<img width="1290" height="916" alt="H-06-E3" src="https://github.com/user-attachments/assets/4a996b8e-ac98-4dfd-a274-63c67723272e" />


### **Gestión de amigos**

### H-07: Enviar solicitud de amistad

| Como jugador, quiero enviar solicitudes de amistad a otros jugadores para poder ser amigos y jugar partidas juntos. |
| ------------------------------------------------------------------------------------------------------------------------------------ |
|           <img width="1288" height="916" alt="H-07" src="https://github.com/user-attachments/assets/9e60d063-c5f6-4bcd-aec2-8f2f429c4628" />
                         
**H-07+E1: Éxito al enviar solicitud de amistad**
Dado un jugador que intente enviar una petición de amistad mediante el código de amigo de otro jugador, la petición será enviada al jugador correctamente.
**H-07-E2: Jugador/es inexistente/s**
Dado un jugador que intente enviar una petición de amistad mediante el código de amigo de otro jugador, no estando el jugador que la envía y/o el que la recibe en la base de datos, se mostrará un error indicando que el/los usuario/s no existe/n.<img width="1290" height="916" alt="H-07-E2" src="https://github.com/user-attachments/assets/c56eaf68-0eaf-4f8e-87bd-ab6b6b5c4533" />

**H-07-E3: Solicitud de amistad a sí mismo**
Dado un jugador que intente enviar una petición de amistad mediante su mismo código de amigo, se mostrará un error indicando que no se puede enviar una solicitud de amistad a uno mismo.<img width="1288" height="916" alt="H-07-E3" src="https://github.com/user-attachments/assets/dcc779dd-b50f-4143-84c5-55abac392a82" />

**H-07-E4: Solicitud de amistad a amigo**
Dado un jugador que intente enviar una petición de amistad mediante el código de amigo de un jugador que ya es su amigo, se mostrará un error indicando que no se puede enviar una solicitud de amistad a un jugador que ya sea tu amigo.<img width="1288" height="916" alt="H-07-E4" src="https://github.com/user-attachments/assets/257bd80d-ef37-4b98-b4a9-39cdeb646ca9" />

**H-07-E5: Solicitud de amistad a jugador con solicitud pendiente**
Dado un jugador que intente enviar una petición de amistad mediante el código de amigo de otro jugador, cuando ya existe una solicitud pendiente de un jugador al otro o viceversa, se mostrará un error indicando que aún hay una solicitud pendiente de gestionar.<img width="1288" height="916" alt="H-07-E5" src="https://github.com/user-attachments/assets/bab0f3b1-00fc-4995-a4b3-f98e2787e567" />

**H-07-E6: Solicitud de amistad a administrador**
Dado un jugador que intente enviar una petición de amistad mediante el código de amigo de un administrador, se mostrará un error indicando que no se puede enviar una solicitud de amistad a un administrador.<img width="1288" height="916" alt="H-07-E6" src="https://github.com/user-attachments/assets/6273fdb8-4a31-4d87-ad06-d0fcc04754f2" />


### H-08: Gestionar solicitudes de amistad

| Como jugador, quiero gestionar las solicitudes de amistad que haya recibido de otros jugadores para poder aceptarlas o ignorarlas. |
| ------------------------------------------------------------------------------------------------------------------------------------ |
|           <img width="1288" height="916" alt="H-08" src="https://github.com/user-attachments/assets/af4ba9d2-e3a4-4817-bd0c-457d4b527038" />
             
**H-08+E1: Éxito al ver solicitudes de amistad**
Dado un jugador que intente ver sus solicitudes de amistad, se mostrará una lista con todas las solicitudes de amistad que ha recibido y aún no haya aceptado o ignorado.
**H-08+E2: Éxito al aceptar solicitud de amistad**
Dado un jugador que intente aceptar una solicitud de amistad que reciba de otro jugador, la solicitud será aceptada y los dos jugadores se convertirán en amigos.
**H-08+E3: Éxito al ignorar solicitud de amistad**
Dado un jugador que intente ignorar una petición de amistad que reciba de otro jugador, la petición será ignorarada, desapareciendo de la lista de peticiones, y los dos jugadores no serán amigos.
**H-08-E4: Jugador/es inexistente/s**
Dado un jugador que intente aceptar o ignorar una petición de amistad, no estando el jugador que la envía y/o el que la recibe en la base de datos, se mostrará un error indicando que el/los usuario/s no existe/n.<img width="1288" height="916" alt="H-08-E4" src="https://github.com/user-attachments/assets/f0fafb43-aac6-4991-8a6a-21fb35cf8ac5" />

**H-08-E5: Solicitud inexistente**
Dado un jugador que intente aceptar o ignorar una petición de amistad que no esté en la base de datos, se mostrará un error indicando que la solicitud no existe.<img width="1288" height="916" alt="H-08-E5" src="https://github.com/user-attachments/assets/b7f1efdc-c791-474d-94f9-7ba10a9b532c" />

**H-08-E6: Jugadores amigos**
Dado un jugador que intente aceptar o ignorar una petición de amistad de un jugador que es su amigo, se mostrará un error indicando que los dos ya son amigos.<img width="1288" height="916" alt="H-08-E6" src="https://github.com/user-attachments/assets/7e029d8f-3445-49d2-a919-f24b18370d93" />


### H-09: Gestionar amigos

| Como jugador, quiero gestionar los amigos que tengo en la aplicación para poder comprobar qué usuarios son mis amigos y ver cuáles están en línea. |
| ------------------------------------------------------------------------------------------------------------------------------------ |
|                                     
**H-09+E1: Lista de amigos**
Dado un jugador que intente ver sus amigos, se mostrará una lista con todos los que tenga, indicando si cada uno está en línea o no.<img width="1288" height="916" alt="H-09+E1" src="https://github.com/user-attachments/assets/98c3dbbd-f084-4f20-af96-8c279dce80b1" />

**H-09+E2: Lista vacía de amigos**
Dado un jugador que intente ver sus amigos sin tener ninguno, se mostrará una lista vacía con un mensaje que indique que el usuario aún no tiene amigos.<img width="1288" height="916" alt="H-09+E2" src="https://github.com/user-attachments/assets/371d587e-8856-404f-8a12-f91d87ac9abf" />

**H-09+E3: Éxito al eliminar amigo**
Dado un jugador que intente eliminar un jugador de su lista de amigos, ambos jugadores ya no serán amigos.<img width="1288" height="916" alt="H-09+E3" src="https://github.com/user-attachments/assets/9992c791-de8c-4a93-82b4-f8fba9f74e14" />

**H-09-E4: Jugador inexistente al eliminar amigo**
Dado un jugador que intente eliminar un jugador de su lista de amigos, no estando el jugador que la envía y/o el que la recibe en la base de datos, se mostrará un error indicando que el/los usuario/s no existe/n.<img width="1290" height="916" alt="H-09-E4" src="https://github.com/user-attachments/assets/3bcd50aa-d682-4b85-b1ef-85bdb0d0cf1b" />

**H-09-E5: Jugador no amigo al eliminar amigo**
Dado un jugador que intente eliminar un jugador que no es su amigo de su lista de amigos, se mostrará un error indicando que el usuario no es su amigo.<img width="1288" height="916" alt="H-09-E5" src="https://github.com/user-attachments/assets/c00571da-c382-4788-bb55-e60d642c2f22" />


### **Gestión de partidas**

### H-10: Ver partidas

| Como jugador, quiero ver las partidas que existan para poder decidir si me interesa unirme o espectar alguna. |
| ------------------------------------------------------------------------------------------------------------------------------------ |
|
**H-10+E1: Éxito al ver partidas públicas**
Dado un jugador que intente ver las partidas públicas abiertas, se mostrará una lista con todas las partidas públicas con un solo jugador esperando que aún no hayan empezado.<img width="1288" height="916" alt="H-10+E1" src="https://github.com/user-attachments/assets/f8a33286-ee6e-4a19-9ce2-91fa6ee624ce" />

**H-10+E2: Éxito al ver invitaciones de jugar o espectar partida privada**
Dado un jugador que intente ver las invitaciones de jugar o espectar partida privada que haya recibido, se mostrará una lista con todas las invitaciones que no haya aceptado o rechazado.<img width="1288" height="916" alt="H-10+E2" src="https://github.com/user-attachments/assets/21339f89-682b-4bdd-8b42-34238c293def" />


### H-11: Unirse a partida

| Como jugador, quiero unirme a alguna partida para poder jugar a Petris. |
| ------------------------------------------------------------------------------------------------------------------------------------ |
| 
**H-11+E1: Éxito al unirse a partida pública**
Dado un jugador que intente unirse a una partida pública con un solo jugador esperando que aún no haya empezado, se le llevará a esa partida, la cual comenzará.
**H-11+E2: Éxito al aceptar invitación de jugar partida privada**
Dado un jugador que intente aceptar una invitación de jugar partida privada de uno de sus amigos, se abrirá una pantalla para mostrar las normas de la partida. Si se acepta la invitación, la partida comenzará.<img width="1288" height="916" alt="H-11+E2" src="https://github.com/user-attachments/assets/324d834d-f35c-46f9-b94d-ef796b44fde5" />

**H-11-E3: Partida inexistente**
Dado un jugador que intente unirse a una partida pública que no esté en la base de datos, se mostrará un error indicando que la partida no existe.<img width="1288" height="916" alt="H-11-E3" src="https://github.com/user-attachments/assets/a72eec33-5fd6-4c58-8c87-ec0914798358" />

**H-11-E4: Invitación inexistente**
Dado un jugador que intente aceptar una invitación de jugar partida privada que no esté en la base de datos, se mostrará un error indicando que la invitación no existe.<img width="1288" height="916" alt="H-11-E4" src="https://github.com/user-attachments/assets/013d859c-c0bf-4d4b-a3b9-31b6c9f44b01" />

**H-11-E5: Jugador/es inexistente/s**
Dado un jugador que intente unirse a una partida pública o privada, no estando el jugador que la envía y/o el que está esperando a que comience en la base de datos, se mostrará un error indicando que el/los usuario/s no existe/n.<img width="1288" height="916" alt="H-11-E5" src="https://github.com/user-attachments/assets/993a8ebf-6645-4bd5-b794-7d0aea008b35" />


### H-12: Crear partida

| Como jugador, quiero crear una partida pública para poder jugar a Petris con otro usuario. |
| ------------------------------------------------------------------------------------------------------------------------------------ |
| 
**H-12+E1: Éxito al crear partida pública**
Dado un jugador que intente crear una partida pública, se abrirá una pantalla para configurar las normas de la partida. Si la partida se crea, aparecerá en la lista de partidas públicas y comenzará cuando otro usuario se una a ella.<img width="1288" height="916" alt="H-12+E1" src="https://github.com/user-attachments/assets/78ffabc4-f7bf-4dfb-b930-3b9b3d68e07f" />

**H-12+E2: Éxito al enviar invitación de jugar partida privada**
Dado un jugador que intente enviar una invitación de jugar partida privada a uno de sus amigos, se abrirá una pantalla para configurar las normas de la partida. Si la partida se crea, dicho amigo recibirá la invitación y la partida comenzará cuando la acepte.
**H-12-E3: Jugador/es inexistente/s**
Dado un jugador que intente crear una partida pública o enviar una invitación de jugar partida privada a uno de sus amigos, no estando el jugador que la envía y/o el amigo en la base de datos, se mostrará un error indicando que el/los usuario/s no existe/n.<img width="1288" height="916" alt="H-12-E3" src="https://github.com/user-attachments/assets/3b6e5cd2-d2c5-4eff-88bc-8399df35ddea" />

**H-12-E4: Jugador no amigo al enviar invitación**
Dado un jugador que intente enviar una invitación de jugar partida privada a un jugador que no es ssu amigo, se mostrará un error indicando que el usuario ya no es su amigo.<img width="1288" height="916" alt="H-12-E4" src="https://github.com/user-attachments/assets/a14c98d8-1d07-440c-808f-c73f28b66688" />


### H-13: Espectar partida

| Como jugador, quiero espectar una partida que estén jugando mis amigos para poder ver el curso de su partida. |
| ------------------------------------------------------------------------------------------------------------------------------------ |
| 
**H-13+E1: Éxito al enviar invitación de espectar partida privada**
Dado un jugador que intente enviar una invitación de espectar partida privada, se abrirá una pantalla para que el jugador decida a qué amigo se la mandará (solo aparecerán los usuarios que sean amigos con ambos participantes de la partida). Si se escoge a un amigo, recibirá la invitación.<img width="1288" height="916" alt="H-13+E1" src="https://github.com/user-attachments/assets/9a56a349-5f25-4abe-962e-2cc3f9e842a0" />

**H-13+E2: Éxito al aceptar invitación de espectar partida privada**
Dado un jugador que intente aceptar una invitación de espectar partida privada de uno de sus amigos, se abrirá una pantalla para mostrar las normas de la partida y quiénes la están jugando. Si se acepta la invitación, se mostrará la partida que se está jugando en tiempo real.

### H-14: Rechazar invitaciones recibidas

| Como jugador, quiero rechazar las invitaciones que reciba de mis amigos para que no me aparezcan si no me interesan. |
| ------------------------------------------------------------------------------------------------------------------------------------ |
| 
**H-14+E1: Éxito al rechazar invitación de jugar partida privada**
Dado un jugador que intente aceptar una invitación de jugar partida privada de uno de sus amigos, se abrirá una pantalla para mostrar las normas de la partida. Si se rechaza la invitación, la invitación será deshechada y la partida no comenzará.
**H-14+E2: Éxito al rechazar invitación de espectar partida privada**
Dado un jugador que intente aceptar una invitación de espectar partida privada de uno de sus amigos, se abrirá una pantalla para mostrar las normas de la partida y quiénes la están jugando. Si se rechaza la invitación, la invitación será deshechada y la partida no se espectará.
**H-14-E3: Invitación inexistente**
Dado un jugador que intente rechazar una invitación que no esté en la base de datos (por haber expirado), se mostrará un error indicando que la invitación no existe.<img width="1288" height="916" alt="H-14-E3" src="https://github.com/user-attachments/assets/d1daded7-1bec-496b-b582-cea3edfebe93" />

**H-14-E4: Jugador/es inexistente/s**
Dado un jugador que intente rechazar una invitación, no estando el jugador que la envía y/o el que la recibe en la base de datos, se mostrará un error indicando que el/los usuario/s no existe/n.<img width="1288" height="916" alt="H-14-E4" src="https://github.com/user-attachments/assets/ad2ac769-c980-4525-b9cc-8fcbe0dac9b2" />


### **Desarrollo de partida**

### H-15: Funcionamiento de la partida

| Como jugador en partida, quiero que la partida que estoy jugando funcione correctamente para poder jugar a Petris con otros jugadores sin complicaciones. |
| ------------------------------------------------------------------------------------------------------------------------------------ |
|
**H-15+E1: Fases**
Una partida avanzará de una fase a la siguiente, de la forma definida por el tablero de ayuda (los círculos indican fases de propagación, los rectángulos verdes indican fases de fisión y el rectángulo amarillo indica fase de contaminación) hasta que la partida termine. Las fases de propagación tienen asociadas un jugador, que es el que deberá mover sus bacterias para llegar a la siguiente fase; aparte de ésta, las demás fases avanzan automáticamente.
**H-15-E2: Acción de jugador en fase incorrecta**
Dado un jugador en partida que intente mover sus bacterias en una fase que no sea de las suyas de propagación, la aplicación ignorará su acción.
**H-15-E3: Abandono de partida**
Dado un jugador en partida que abandone la partida, la partida terminará sin ganador.<img width="1288" height="916" alt="H-15-E3" src="https://github.com/user-attachments/assets/0dae7594-44c7-4a0e-9722-76d0be3fc810" />

**H-15-E4: Jugador/es inexistente/s**
Dado un jugador en partida o espectador, no estando el usuario y/o los jugadores de la partida en la base de datos, se mostrará un error indicando que el/los usuario/s no existe/n (si el usuario era uno de los jugadores de la partida, la partida terminará).<img width="1288" height="916" alt="H-15-E4" src="https://github.com/user-attachments/assets/2c4d8898-d404-4260-bceb-ae4076531e0e" />

**H-15-E5: Jugadores no amigos en partida privada**
Dado un jugador en partida o espectador en una partida privada, no siendo amigo de el/los jugador/es, se mostrará un error indicando que es necesario ser amigo de todos los jugadores de la partida (si el usuario era uno de los jugadores de la partida, la partida terminará).<img width="1288" height="916" alt="H-15-E5" src="https://github.com/user-attachments/assets/3bb442ba-7643-46a4-9c32-23d186063f43" />

**H-15-E6: Espectador no amigo de jugadores en partida pública**
Dado un espectador en una partida pública, no siendo amigo de alguno de los jugadores, se mostrará un error indicando que es necesario ser amigo de todos los jugadores de la partida.<img width="1288" height="916" alt="H-15-E6" src="https://github.com/user-attachments/assets/a33fd9d3-244c-4c0b-a9d3-8bb912c9d53a" />

**H-15-E7: Partida inexistente**
Dado un jugador en partida o espectador, no estando la partida en la base de datos, se mostrará un error indicando que la partida no existe.<img width="1288" height="916" alt="H-15-E7" src="https://github.com/user-attachments/assets/f6bb7f5f-7d7e-4741-8018-10a50000465a" />


### H-16: Ver información de partida

| Como jugador en partida o espectador, quiero ver la información de la partida que esté jugando o espectando para poder saber cómo va. |
| ------------------------------------------------------------------------------------------------------------------------------------ |
| 
**H-16+E1: Éxito al ver información de partida**
Dado un jugador en partida o espectador, se mostrarán los siguientes aspectos en tiempo real: posición de las bacterias y sarcinas, tablero de ayuda que indica cuál es la fase actual y las siguientes, y puntos de contaminación de cada jugador.
<img width="1288" height="916" alt="H-16+E1" src="https://github.com/user-attachments/assets/c457fcab-22ab-42d2-9bff-1975d56da849" />

### H-17: Condiciones de victoria

| Como jugador en partida, quiero que la partida que estoy jugando tenga condiciones de victoria para poder intentar ganar, sabiendo que la aplicación detectará mi victoria o derrota correctamente. |
| ------------------------------------------------------------------------------------------------------------------------------------ |
| 
**H-17+E1: Sin movimientos disponibles**
Dado un jugador en partida cuando sea una de sus fases de propagación, si no puede hacer ningún movimiento válido, la partida terminará con su derrota y la victoria del otro jugador.<img width="1288" height="916" alt="H-17+E1" src="https://github.com/user-attachments/assets/e6132337-519b-4237-a7ef-d8b04440b420" />

**H-17+E2: Máximo de puntos de contaminación**
Dado un jugador en partida cuando sea una fase de contaminación, si al final de la fase ha alcanzado C puntos de contaminación (siendo C un número que el creador de la partida escogerá), la partida terminará con su derrota y la victoria del otro jugador.<img width="1288" height="916" alt="H-17+E2" src="https://github.com/user-attachments/assets/d7d000bd-c36c-4465-8ae2-cc55dfeaf5df" />

**H-17+E3: Fase de contaminación final**
Dado un jugador en partida cuando sea la última fase de contaminación, si al final de la fase aún no se ha decidido el ganador, se observarán los puntos de contaminación de cada jugador. Si dicho jugador es el que tiene más puntos, la partida terminarácon su derrota y la victoria del otro jugador.<img width="1288" height="916" alt="H-17+E3" src="https://github.com/user-attachments/assets/0028d0d7-6a59-473a-99ea-e4a9db704c82" />

**H-17+E4: Desempate**
Si ambos jugadores de partida han llegado a una condición de derrota en la misma fase, el ganador se decidirá por quién tiene menos bacterias en el tablero (contando también las sarcinas, que valen S bacterias cada una, siendo S un número que el creador de la partida escogerá). Si no se llega a desempatar de ese modo, el ganador será el jugador con menos sarcinas en el tablero. Si se ha decidido el ganador, la partida terminará con su victoria y la derrota del otro jugador.
**H-17-E5: Imposible desempatar**
Si se ha intentado desempatar y no se ha decidido ningún ganador, la partida terminará en empate, y ninguno de los jugadores ganará o perderá.<img width="1288" height="916" alt="H-17-E5" src="https://github.com/user-attachments/assets/d9e8f13a-47a5-4f74-8ec2-740851d4d0e0" />


### H-18: Fase de propagación

| Como jugador en partida, quiero que la partida que estoy jugando tenga fases de progapación para poder mover mis bacterias por el tablero. |
| ------------------------------------------------------------------------------------------------------------------------------------ |
| 
**H-18+E1: Mover bacterias**
Dado un jugador en partida cuando sea una fase suya de propagación, puede mover sus bacterias de una loseta a otra si el movimiento es válido (el movimiento es válido si no incumple los escenarios negativos de esta historia de usuario).
**H-18+E2: Fin de fase**
Dado un jugador en partida cuando sea una fase suya de propagación, la fase terminará si el jugador mueve todas las bacterias de una loseta de forma válida, o si no mueve todas las bacterias de una loseta pero sí termina la fase manualmente.<img width="1288" height="916" alt="H-18+E2" src="https://github.com/user-attachments/assets/b3002992-87bd-4b4d-8026-5cc1e4232dfe" />

**H-18+E3: Reiniciar fase**
Dado un jugador en partida cuando sea una fase suya de propagación, si ha movido una o más bacterias pero aún no ha terminado la fase, puede reiniciarla para deshacer los movimientos que ha hecho.<img width="1288" height="916" alt="H-18+E3" src="https://github.com/user-attachments/assets/5451f6f5-0cdc-49bc-9731-5d6bee7b3e00" />

**H-18+E4: Creación de sarcinas**
Dado un jugador en partida cuando sea una fase suya de propagación, al final de la fase se observarán las losetas y, para cada una, se reemplazarán cada S bacterias del jugador por una sarcina (siendo S un número que el creador de la partida escogerá).<img width="1288" height="916" alt="H-18+E4" src="https://github.com/user-attachments/assets/ce449ef8-e908-45c3-afea-eb74f895288a" />

**H-18-E5: Bacterias desde varias losetas**
Dado un jugador en partida cuando sea una fase suya de propagación, si ha movido una bacteria de una loseta a otra, y después intenta mover una bacteria de una loseta distinta, la aplicación deshará el movimiento e indicará que todas las bacterias que se muevan deben pertenecer a la misma loseta.
**H-18-E6: Loseta/s no adyacente/s**
Dado un jugador en partida cuando sea una fase suya de propagación, si intenta mover una bacteria a una loseta no adyacente, la aplicación deshará el movimiento e indicará que todas las bacterias que se muevan deben moverse a losetas adyacentes.<img width="1288" height="916" alt="H-18-E6" src="https://github.com/user-attachments/assets/a782ace5-3b1d-47e5-bd16-5e431f2dab4d" />

**H-18-E7: Loseta/s con mismas bacterias de ambos jugadores**
Dado un jugador en partida cuando sea una fase suya de propagación, si al terminar la fase existe/n una o más losetas con el mismo número de bacterias de ambos jugadores, la aplicación deshará todos los movimientos e indicará que no pueden haber losetas con el mismo número de bacterias de ambos jugadores.
**H-18-E8: Intentar mover hacia sarcina**
Dado un jugador en partida cuando sea una fase suya de propagación, si intenta mover una de sus bacterias a una loseta que tenga una de sus sarcinas, la aplicación deshará el movimiento e indicará que no se puede mover una bacteria propia a una loseta con una sarcina propia.
**H-18-E9: Mover al menos una bacteria**
Dado un jugador en partida cuando sea una fase suya de propagación, si intenta terminar la fase manualmente sin haber movido ninguna bacteria, la aplicación no lo permitirá.<img width="1288" height="916" alt="H-18-E9" src="https://github.com/user-attachments/assets/81f2531d-cc5d-465a-9ce5-aec28f275431" />


### H-19: Fase de fisión

| Como jugador en partida, quiero que la partida que estoy jugando tenga fases de fisión para poder crear nuevas bacterias en el tablero. |
| ------------------------------------------------------------------------------------------------------------------------------------ |
| 
**H-19+E1: Creación de bacterias**
En una fase de fisión, se observará cada loseta y, por cada una en la que haya bacterias de un solo jugador, se añadirán B a esa loseta (siendo B un número que el creador de la partida escogerá).
**H-19+E2: Creación de sarcinas**
En una fase de fisión, tras crear las bacterias por loseta, se observarán las loseta y, para cada una, se reemplazarán cada S bacterias del jugador por una sarcina (siendo S un número que el creador de la partida escogerá). Tras ello, la fase terminará.

### H-20: Fase de contaminación

| Como jugador en partida, quiero que la partida que estoy jugando tenga fases de contaminación para poder añadir puntos de contaminación a ambos jugadores. |
| ------------------------------------------------------------------------------------------------------------------------------------ |
| 
**H-20+E1: Añadir puntos de contaminación**
En una fase de contaminación, se observará cada loseta y, por cada una, si hay más bacterias de un jugador que de otro (contando también las sarcinas, que valen S bacterias cada una, siendo S un número que el creador de la partida escogerá), se añadirá un punto de contaminación a dicho jugador. Si se hace esto para todas las losetas y ningún jugador ha perdido, la fase terminará.
<img width="1288" height="916" alt="H-20+E1" src="https://github.com/user-attachments/assets/0771b1cc-01d1-4329-8729-d16af919f7d0" />

### H-21: Abandonar partida

| Como jugador en partida, quiero abandonar una partida que esté jugando para poder dejarla si no quiero continuar. |
| ------------------------------------------------------------------------------------------------------------------------------------ |
| 
**H-21+E1: Éxito al abandonar partida**
Dado un jugador en partida que intente abandonar su partida, el jugador saldrá de la partida, la cual se eliminará.
**H-21-E2: Partida inexistente**
Dado un jugador en partida que intente abandonar su partida que no esté en la base de datos, se mostrará un error indicando que la partida no existe.<img width="1288" height="916" alt="H-21-E2" src="https://github.com/user-attachments/assets/493fce8d-0f4a-4aa8-a867-b0020c6ee62d" />

**H-21-E3: Jugador/es inexistente/s**
Dado un jugador en partida que intente abandonar su partida, no estando el jugador que lo intenta y/o el otro jugador de la partida en la base de datos, se mostrará un error indicando que el/los usuario/s no existe/n.<img width="1288" height="916" alt="H-21-E3" src="https://github.com/user-attachments/assets/b096d70d-9378-468b-8362-0791defef5f0" />


### H-22: Chat de partida

| Como jugador en partida o espectador, quiero ver y mandar mensajes en el chat de la partida que esté jugando o espectando para comunicarme con los otros usuarios. |
| ------------------------------------------------------------------------------------------------------------------------------------ |
| 
**H-22+E1: Ver mensajes**
Dado un jugador en partida o espectador, se mostrarán los mensajes enviados en el chat en tiempo real, al igual que quién ha enviado cada uno.
**H-22+E2: Enviar mensajes**
Dado un jugador en partida o espectador que intente enviar un mensaje en el chat, el mensaje se guardará en el chat y los otros usuarios podrán verlo.
**H-22-E3: Mensajes de espectadores**
Dado un jugador en partida, no se mostrarán los mensajes enviados en el chat por espectadores; solamente los que han sido enviados por los jugadores.

### **Administradores**

### H-23: Administrar jugadores

| Como administrador, quiero administrar los jugadores existentes en la base de datos para poder verlos, modificarlos o eliminarlos. |
| ------------------------------------------------------------------------------------------------------------------------------------ |
| 
**H-23+E1: Ver jugadores**
Dado un administrador que intente ver todos los jugadores de la base de datos, se mostrará una lista con todos, junto con opciones para modificarlos o eliminarlos.<img width="1288" height="916" alt="H-23+E1" src="https://github.com/user-attachments/assets/9a845ba7-481b-4547-b48d-4db21e8562a3" />

**H-23+E2: Modificar jugador**
Dado un administrador que introduzca campos de perfil con formato correcto al intentar editar el perfil de un jugador de la base de datos, los valores de su perfil serán reemplazados correctamente con los que ha introducido.<img width="1288" height="916" alt="H-23+E2" src="https://github.com/user-attachments/assets/b66c4dec-2536-4da2-8cb8-0100f9706966" />

**H-23+E3: Eliminar amigos**
Dado un administrador que intente eliminar un jugador de la lista de amigos de otro jugador desde la pantalla de modificar jugador, ambos jugadores ya no serán amigos.
**H-23+E4: Eliminar jugador**
Dado un administrador que intente eliminar un jugador de la base de datos, dicho jugador será completamente eliminado de la base de datos del sistema, junto con todos sus datos asociados.
**H-23-E5: Editar perfil con datos no válidos**
Dado un jugador que introduzca campos de perfil con formato incorrecto al intentar editar el perfil de un jugador de la base de datos, se mostrará un error en pantalla indicando que los datos no son válidos y los valores de su perfil no cambiarán.<img width="1288" height="916" alt="H-23-E5" src="https://github.com/user-attachments/assets/66d080a6-d99c-4636-ac8e-2e6a399136a7" />

**H-23-E6: Jugador inexistente**
Dado un administrador que intente modificar o eliminar un jugador que no esté en la base de datos, se mostrará un error indicando que el usuario no existe.<img width="1288" height="916" alt="H-23-E6" src="https://github.com/user-attachments/assets/37e9cb6f-5fd9-43d6-95ca-8ce1ed037e7f" />

**H-23-E7: Jugador no amigo**
Dado un administrador que intente eliminar un jugador que no es su amigo de la lista de amigos de otro jugador, se mostrará un error indicando que el usuario no es su amigo.
**H-23-E8: Amigo inexistente**
Dado un administrador que intente eliminar un jugador que no esté en la base de datos de la lista de amigos de otro jugador, se mostrará un error indicando que el usuario no existe.<img width="1288" height="916" alt="H-23-E8" src="https://github.com/user-attachments/assets/85730441-f072-435f-af23-e68517b3a98b" />


### H-24: Administrar logros

| Como administrador, quiero administrar los logros existentes en la base de datos para poder verlos, modificarlos, eliminarlos o crear nuevos. |
| ------------------------------------------------------------------------------------------------------------------------------------ |
| 
**H-24+E1: Ver logros**
Dado un administrador que intente ver todos los logros de la base de datos, se mostrará una lista con todos, junto con opciones para modificarlos o eliminarlos.<img width="1288" height="916" alt="H-24+E1" src="https://github.com/user-attachments/assets/1db113af-e9ea-4777-a5c1-f1778e28294f" />

**H-24+E2: Crear logro**
Dado un administrador que introduzca campos de logro con formato correcto al intentar crear un logro, se creará un logro en la base de datos con dichos valores.<img width="1288" height="916" alt="H-24+E2" src="https://github.com/user-attachments/assets/a8efda22-dbd3-489b-8152-cb6743c450b8" />

**H-24+E3: Modificar logro**
Dado un administrador que introduzca campos de logro con formato correcto al intentar modificar un logro de la base de datos, los valores del logro serán reemplazados correctamente con los que ha introducido.<img width="1288" height="916" alt="H-24+E3" src="https://github.com/user-attachments/assets/d87c9df1-f742-4175-82b0-06a3eb7af062" />

**H-24+E4: Eliminar logro**
Dado un administrador que intente eliminar un logro de la base de datos, dicho logro será completamente eliminado de la base de datos del sistema, junto con todos las instancias de logros obtenidos de todos los jugadores de la aplicación.
**H-24-E5: Crear o modificar logro con datos no válidos**
Dado un jugador que introduzca campos de logro con formato incorrecto al intentar crear o modificar un logro de la base de datos, se mostrará un error en pantalla indicando que los datos no son válidos y el logro no se creará/los valores del logro no cambiarán.<img width="1288" height="916" alt="H-24-E5" src="https://github.com/user-attachments/assets/7ee1a057-c044-4e76-954c-791f462448fa" />

**H-24-E6: Logro inexistente**
Dado un administrador que intente modificar o eliminar un logro que no esté en la base de datos, se mostrará un error indicando que el logro no existe.<img width="1288" height="916" alt="H-24-E6" src="https://github.com/user-attachments/assets/b76687c1-25b3-441e-9468-57dad39afaf8" />


### H-25: Administrar partidas en curso

| Como administrador, quiero administrar las partidas en curso existentes en la base de datos para poder verlas o eliminarlas. |
| ------------------------------------------------------------------------------------------------------------------------------------ |
| 
**H-25+E1: Ver partidas en curso**
Dado un administrador que intente ver todas las partidas en curso existentes en la base de datos, se mostrará una lista con todas, junto con opciones para eliminarlas.<img width="1288" height="916" alt="H-25+E1" src="https://github.com/user-attachments/assets/0724429b-ae21-4934-9f55-b64850d10219" />

**H-25+E2: Eliminar partida en curso**
Dado un administrador que intente eliminar una partida en curso de la base de datos, dicha partida será completamente eliminada de la base de datos del sistema.
**H-25-E3: Partida inexistente**
Dado un administrador que intente eliminar una partida que no esté en la base de datos, se mostrará un error indicando que la partida no existe.<img width="1288" height="916" alt="H-25-E3" src="https://github.com/user-attachments/assets/7a1870b5-648e-4068-86b2-068934f4fbea" />
**Diagrama conceptual del sistema**

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

### R4 – Posición inicial

_Restricción: Cada jugador comienza con una bacteria en su disco de salida (definido por color)._

---

### R5 – Marcador de turnos

_Restricción: El marcador de turnos se coloca en la casilla inicial y avanza según la progresión de fases._

---

### R6 – Contador de contaminación

_Restricción: El contador de contaminación estará a cero al inicio de la partida para ambos jugadores._

---

### R7 – Duración de la partida

_Restricción: La partida se desarrolla en 4 rondas completas._

---

### R8 – Fases del juego

_Restricción: El juego se divide en tres fases: propagación, fisión binaria y contaminación._

_Ej:_ En la segunda fase cada jugador añade una bacteria por cada disco donde tiene desventaja numérica.

---

### R9 – Control de color

_Restricción: Cada jugador controla un color de bacterias, sin roles diferenciados._

---

### R10 – Acciones del jugador

_Restricción: Durante su turno el jugador puede colocar, mover bacterias o formar sarcinas._

---

### R11 – Unidades indivisibles

_Restricción: Las sarcinas son unidades indivisibles y afectan la dominación de discos._

---

### R12 – Inicio del turno

_Restricción: Se verifica que el jugador tenga bacterias disponibles._

---

### R13 – Acción principal

_Restricción: El jugador realiza su jugada colocando o moviendo bacterias._

---

### R14 – Control de discos

_Restricción: Si un disco está completamente cubierto se marca como dominado._

---

### R15 – Contaminación

_Restricción: Se actualizan los valores de contaminación de ambos jugadores._

---

### R16 – Final de turno

_Restricción: Se pasa el turno al oponente._

---

### R17 – Condiciones de victoria

_Restricción: El juego puede finalizar por dominación, contaminación o agotamiento._

---

### R18 – Chat en tiempo real

_Restricción: Los jugadores deben tener acceso a un chat de equipo en tiempo real._

---

### R19 – Orden de turnos

_Restricción: El sistema gestiona automáticamente el orden de turnos._

---

### R20 – Validación de movimientos

_Restricción: La aplicación valida todos los movimientos antes de aplicarlos._

---

### R21 – Sincronización de tablero

_Restricción: El tablero se sincroniza en tiempo real entre los jugadores._

---

### R22 – Información visual

_Restricción: La interfaz debe mostrar tablero, dominación, contaminación y reservas._

---

### R23 – Controles del jugador

_Restricción: Los jugadores disponen de botones para seleccionar, colocar, mover y confirmar._

---

### R24 – Retroalimentación visual y sonora

_Restricción: El sistema debe ofrecer feedback visual y sonoro en eventos importantes._

---

### R25 – Exclusividad de turno

_Restricción: Solo el jugador activo puede mover durante su turno._

---

### R26 – Jugadas inválidas

_Restricción: Los intentos de jugadas inválidas deben bloquearse._

---

### R27 – Registro de jugadas

_Restricción: Se debe registrar el historial de jugadas para detectar errores o trampas._

---

### R28 – Autenticación obligatoria

_Restricción: Un usuario sin cuenta no puede acceder al juego; iniciar sesión es obligatorio._

---

### R29 – Integridad de la partida

_Restricción: Las partidas en curso no pueden modificarse por jugadores no participantes._

---

### R30 – Gestión de perfiles

_Restricción: Los jugadores solo pueden editar o eliminar su propio perfil, salvo administradores._

---

### R31 – Creación de partidas

_Restricción: El sistema permite crear partidas públicas o privadas._

---

### R32 – Autenticación para unirse

_Restricción: Para unirse a una partida, el jugador debe estar autenticado._

---

### R33 – Registro de partidas

_Restricción: El sistema mantiene registro de partidas finalizadas y en curso._

---

### R34 – Visibilidad de partidas en curso

_Restricción: Las partidas en curso no aparecen en el listado público para no participantes._

---

### R35 – Invitaciones activas

_Restricción: Un jugador solo puede enviar una invitación activa por amigo a la vez._

---

### R36 – Expiración de invitaciones

_Restricción: Una invitación expira tras un minuto si no se acepta._

---

### R37 – Espectadores

_Restricción: Solo pueden espectar los amigos de todos los participantes de la partida._

---

### R38 – Estado “en línea”

_Restricción: Un jugador con sesión abierta aparece como “en línea”._

---

### R39 – Estado “desconectado”

_Restricción: Un jugador sin sesión activa aparece como “desconectado”._

---

### R40 – Actualización de estado

_Restricción: El estado de conexión se actualiza en tiempo real y afecta a las invitaciones._

---

### R41 – Gestión de logros

_Restricción: Solo los administradores podrán eliminar, editar o crear logros._

---

### R42 – Gestión de jugadores

_Restricción: Solo los administradores podrán ver, modificar o eliminar jugadores._

---

### R43 – Control de partidas activas

_Restricción: Solo los administradores podrán pausar o eliminar una partida que esté en curso._

---

### R44 – Personalizar normas de partida

_Restricción: Solo los creadores de una partida pueden personalizar sus normas._

=======
## Orden para abordar en el desarrollo
Para este proyecto vamos a seguir el siguiente orden de desarrollo e integración, en orden natural:
### 1-Registrar jugador
### 2-Iniciar sesión
### 3-Cerrar sesión
### 4-Crear partida
### 5-Ver lista de partidas
### 6-Unirse a partida
### 7-Funcionamiento de la partida
### 8-Condiciones de victoria
### 9-Fase de propagación
### 10-Fase de fisión
### 11-Fase de contaminación
### 12-Abandonar partida
### 13-Administrar jugadores
### 14-Administrar partidas en curso
### 15-Administrar logros
### 16-Chat de partida
### 17-Ver perfil
### 18-Editar perfil
### 19-Eliminar jugador
### 20-Enviar solicitud de amistad
### 21-Gestionar solicitudes de amistad
### 22-Solicitud inexistente
### 23-Espectar partida
### 24-Rechazar invitaciones recibidas
### 25-Ver información de la partida

En el caso de las entidades uml y de los objetos de la base de datos (que coinciden con las mismas), seguiremos este orden:
### 1-User
### 2-Game
### 3-Invitation
### 4-PlayerGame
### 5-PetriPlate
### 6-Sarcine
### 7-Bactery
### 8-Turn
### 9-Action
### 10-GameStatistics
### 11-Profile
### 12-AchievementList
### 13-Achievement
### 14-FriendRequest

Los servicios y controladores de la API tendrán el mismo orden de implementación que las entidades. Dentro de esto
se intentará crear los servicios y los controladores correspondentes a cada entidad tras la creación de ésta.
Posteriormente junto con la integración en React se implementarán las pruebas.