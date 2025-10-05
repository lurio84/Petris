# Historias de usuario


## H-XX – Registrar jugador

Como usuario, quiero registrarme como jugador en el sistema para poder identificarme y acceder a las funcionalidades de jugador.

### H-XX+E1 – Éxito al registrar jugador

Dado un usuario que introduzca un nombre, contraseña y campos de perfil con formato correcto al intentar registrar un jugador, se creará un jugador en la base de datos con dichas credenciales, y el usuario iniciará sesión con dicho jugador.

### H-XX-E2 – Intentar registrar jugador con datos no válidos

Dado un usuario que introduzca un nombre, contraseña y/o campo/s de perfil con formato incorrecto al intentar registrar un jugador, se mostrará un error en pantalla indicando que los datos no son válidos y el jugador no será creado en la base de datos.


## H-XX – Iniciar sesión

Como usuario, quiero iniciar sesión como un jugador/administrador existente en la base de datos del sistema para poder identificarme y acceder a las funcionalidades de jugador.

### H-XX+E1 – Éxito al iniciar sesión

Dado un usuario que introduzca un nombre y/o contraseña asociados a un jugador/administrador existente en la base de datos del sistema al intentar iniciar sesión, el usuario iniciará sesión con dicho jugador.

### H-XX-E2 – Intentar iniciar sesión con nombre de usuario inexistente

Dado un usuario que introduzca un nombre no asociado a ningún jugador/administrador de la base de datos del sistema al intentar iniciar sesión, se mostrará un error en pantalla indicando que ningún jugador de la base de datos tiene dicho nombre y el usuario no iniciará sesión.

### H-XX-E3 – Intentar iniciar sesión con contraseña incorrecta

Dado un usuario que introduzca un nombre y contraseña donde la contraseña no esté asociada al jugador/administrador con dicho nombre en la base de datos al intentar iniciar sesión, se mostrará un error en pantalla indicando el jugador/administrador con dicho nombre no tiene dicha contraseña y el usuario no iniciará sesión.


## H-XX – Cerrar sesión

Como usuario, quiero cerrar mi sesión para poder dejar de usar el sistema y permitir a otro usuario iniciar sesión.

### H-XX+E1 – Éxito al cerrar sesión

Dado un usuario con una sesión iniciada que intente cerrarla, la sesión se cerrará de forma segura y la aplicación volverá a la pantalla de inicio.

### H-XX-E2 – Jugador inexistente

Dado un usuario que no esté en la base de datos con una sesión iniciada que intente cerrarla, se mostrará un error indicando que el usuario no existe.


## H-XX – Ver perfil

Como jugador, quiero ver mi perfil para poder comprobar qué información muestro sobre mí.

### H-XX+E1 – Éxito al ver perfil

Dado un jugador que intente ver su perfil, se le mostrará junto con toda su información: últimos logros obtenidos, estadísticas y datos personales.

### H-XX+E2 – Éxito al ver todos los logros

Dado un jugador que intente ver todos sus logros desde su perfil, se mostrará una lista de todos los logros del juego, mostrando para cada uno si se ha obtenido o no y cómo obtenerlo.

### H-XX-E3 – Jugador inexistente

Dado un jugador que no esté en la base de datos que intente ver su perfil, se mostrará un error indicando que el usuario no existe.


## H-XX – Editar perfil

Como jugador, quiero editar mi perfil para poder elegir qué información muestro sobre mí.

### H-XX+E1 – Éxito al editar perfil

Dado un jugador que introduzca campos de perfil con formato correcto al intentar editar su perfil, los valores de su perfil serán reemplazados correctamente con los que ha introducido.

### H-XX-E2 – Intentar editar perfil con datos no válidos

Dado un jugador que introduzca campos de perfil con formato incorrecto al intentar editar su perfil, se mostrará un error en pantalla indicando que los datos no son válidos y los valores de su perfil no cambiarán.

### H-XX-E3 – Jugador inexistente

Dado un jugador que no esté en la base de datos que intente editar su perfil, se mostrará un error indicando que el usuario no existe.


## H-XX – Eliminar jugador

Como jugador, quiero eliminar mi cuenta para poder deshacerme de los datos de mí que estén guardados en la base de datos del sistema.

### H-XX+E1 – Éxito al eliminar jugador

Dado un jugador que intente eliminar su cuenta, al introducir su contraseña dicho jugador será completamente eliminado de la base de datos del sistema, junto con todos sus datos asociados.

### H-XX+E2 - Fallo al eliminar jugador

Dado un jugador que intente eliminar su cuenta, al fallar al introducir su contraseña dicho jugador no se eliminará de la base de datos y se dará un mensaje de error.

### H-XX+E3 – Jugador inexistente

Dado un jugador que no esté en la base de datos que intente eliminar su cuenta, se mostrará un error indicando que el usuario no existe.


## H-XX – Enviar solicitud de amistad

Como jugador, quiero enviar solicitudes de amistad a otros jugadores para poder ser amigos y jugar partidas juntos.

### H-XX+E1 – Éxito al enviar solicitud de amistad

Dado un jugador que intente enviar una petición de amistad mediante el código de amigo de otro jugador, la petición será enviada al jugador correctamente.

### H-XX-E2 – Jugador/es inexistente/s

Dado un jugador que intente enviar una petición de amistad mediante el código de amigo de otro jugador, no estando el jugador que la envía y/o el que la recibe en la base de datos, se mostrará un error indicando que el/los usuario/s no existe/n.

### H-XX-E3 – Solicitud de amistad a sí mismo

Dado un jugador que intente enviar una petición de amistad mediante su mismo código de amigo, se mostrará un error indicando que no se puede enviar una solicitud de amistad a uno mismo.

### H-XX-E4 – Solicitud de amistad a amigo

Dado un jugador que intente enviar una petición de amistad mediante el código de amigo de un jugador que ya es su amigo, se mostrará un error indicando que no se puede enviar una solicitud de amistad a un jugador que ya sea tu amigo.

### H-XX-E5 – Solicitud de amistad a jugador con solicitud pendiente

Dado un jugador que intente enviar una petición de amistad mediante el código de amigo de otro jugador, cuando ya existe una solicitud pendiente de un jugador al otro o viceversa, se mostrará un error indicando que aún hay una solicitud pendiente de gestionar.

### H-XX-E6 – Solicitud de amistad a administrador

Dado un jugador que intente enviar una petición de amistad mediante el código de amigo de un administrador, se mostrará un error indicando que no se puede enviar una solicitud de amistad a un administrador.


## H-XX Gestionar solicitudes de amistad

Como jugador, quiero gestionar las solicitudes de amistad que haya recibido de otros jugadores para poder aceptarlas o ignorarlas.

### H-XX-E1 – Éxito al ver solicitudes de amistad

Dado un jugador que intente ver sus solicitudes de amistad, se mostrará una lista con todas las solicitudes de amistad que ha recibido y aún no haya aceptado o ignorado.

### H-XX+E2 – Éxito al aceptar solicitud de amistad

Dado un jugador que intente aceptar una solicitud de amistad que reciba de otro jugador, la solicitud será aceptada y los dos jugadores se convertirán en amigos.

### H-XX+E3 – Éxito al ignorar solicitud de amistad

Dado un jugador que intente ignorar una petición de amistad que reciba de otro jugador, la petición será ignorarada, desapareciendo de la lista de peticiones, y los dos jugadores no serán amigos.

### H-XX-E4 – Jugador/es inexistente/s

Dado un jugador que intente aceptar o ignorar una petición de amistad, no estando el jugador que la envía y/o el que la recibe en la base de datos, se mostrará un error indicando que el/los usuario/s no existe/n.

### H-XX-E5 – Solicitud inexistente

Dado un jugador que intente aceptar o ignorar una petición de amistad que no esté en la base de datos, se mostrará un error indicando que la solicitud no existe.

### H-XX-E5 – Jugadores amigos

Dado un jugador que intente aceptar o ignorar una petición de amistad de un jugador que es su amigo, se mostrará un error indicando que los dos ya son amigos.


## H-XX – Gestionar amigos

Como jugador, quiero gestionar los amigos que tengo en la aplicación para poder comprobar qué usuarios son mis amigos y ver cuáles están en línea.

### H-XX+E1 – Lista de amigos

Dado un jugador que intente ver sus amigos, se mostrará una lista con todos los que tenga, indicando si cada uno está en línea o no.

### H-XX+E2 – Lista vacía de amigos

Dado un jugador que intente ver sus amigos sin tener ninguno, se mostrará una lista vacía con un mensaje que indique que el usuario aún no tiene amigos.

### H-XX+E3 – Éxito al eliminar amigo

Dado un jugador que intente eliminar a un jugador de su lista de amigos desde el perfil de dicho jugador, ambos jugadores ya no serán amigos.

### H-XX-E4 – Jugador inexistente al eliminar amigo

Dado un jugador que intente eliminar a un jugador de su lista de amigos, no estando el jugador que la envía y/o el que la recibe en la base de datos, se mostrará un error indicando que el/los usuario/s no existe/n.

### H-XX-E5 – Jugador no amigo al eliminar amigo

Dado un jugador que intente eliminar a un jugador que no es su amigo de su lista de amigos, se mostrará un error indicando que el usuario ya no es su amigo.


## H-XX – Ver partidas

Como jugador, quiero ver las partidas que existan para poder decidir si me interesa unirme o espectar alguna.

### H-XX+E1 – Éxito al ver partidas públicas

Dado un jugador que intente ver las partidas públicas abiertas, se mostrará una lista con todas las partidas públicas con un solo jugador esperando que aún no hayan empezado.

### H-XX+E2 – Éxito al ver invitaciones de jugar o espectar partida privada

Dado un jugador que intente ver las invitaciones de jugar o espectar partida privada que haya recibido, se mostrará una lista con todas las invitaciones que no haya aceptado o rechazado.


## H-XX – Unirse a partida

Como jugador, quiero unirme a alguna partida para poder jugar a Petris.

### H-XX+E1 – Éxito al unirse a partida pública

Dado un jugador que intente unirse a una partida pública con un solo jugador esperando que aún no haya empezado, se le llevará a esa partida, la cual comenzará.

### H-XX+E2 – Éxito al aceptar invitación de jugar partida privada

Dado un jugador que intente aceptar una invitación de jugar partida privada de uno de sus amigos, se abrirá una pantalla para mostrar las normas de la partida. Si se acepta la invitación, la partida comenzará.

### H-XX-E3 – Partida inexistente

Dado un jugador que intente unirse a una partida pública que no esté en la base de datos, se mostrará un error indicando que la partida no existe.

### H-XX-E4 – Invitación inexistente

Dado un jugador que intente aceptar una invitación de jugar partida privada que no esté en la base de datos, se mostrará un error indicando que la invitación no existe.

### H-XX-E5 – Jugador/es inexistente/s

Dado un jugador que intente unirse a una partida pública o privada, no estando el jugador que la envía y/o el que está esperando a que comience en la base de datos, se mostrará un error indicando que el/los usuario/s no existe/n.


## H-XX – Crear partida

Como jugador, quiero crear una partida pública para poder jugar a Petris con otro usuario.

### H-XX+E1 – Éxito al crear partida pública

Dado un jugador que intente crear una partida pública, se abrirá una pantalla para configurar las normas de la partida. Si la partida se crea, aparecerá en la lista de partidas públicas y comenzará cuando otro usuario se una a ella.

### H-XX+E2 – Éxito al enviar invitación de jugar partida privada

Dado un jugador que intente enviar una invitación de jugar partida privada a uno de sus amigos, se abrirá una pantalla para configurar las normas de la partida. Si la partida se crea, dicho amigo recibirá la invitación y la partida comenzará cuando la acepte.

### H-XX-E3 – Jugador/es inexistente/s

Dado un jugador que intente crear una partida pública o enviar una invitación de jugar partida privada a uno de sus amigos, no estando el jugador que la envía y/o el amigo en la base de datos, se mostrará un error indicando que el/los usuario/s no existe/n.

### H-XX-E4 – Jugador no amigo al enviar invitación

Dado un jugador que intente enviar una invitación de jugar partida privada a un jugador que no es su amigo, se mostrará un error indicando que el usuario ya no es su amigo.


## H-XX Espectar partida

Como jugador, quiero espectar una partida que estén jugando mis amigos para poder ver el curso de su partida.

### H-XX+E1 – Éxito al enviar invitación de espectar partida privada

Dado un jugador que intente enviar una invitación de espectar partida privada, se abrirá una pantalla para que el jugador decida a qué amigo se la mandará (solo aparecerán los usuarios que sean amigos con ambos participantes de la partida). Si se escoge a un amigo, recibirá la invitación.

### H-XX+E2 – Éxito al aceptar invitación de espectar partida privada

Dado un jugador que intente aceptar una invitación de espectar partida privada de uno de sus amigos, se abrirá una pantalla para mostrar las normas de la partida y quiénes la están jugando. Si se acepta la invitación, se mostrará la partida que se está jugando en tiempo real.


## H-XX – Rechazar invitaciones recibidas

Como jugador, quiero rechazar las invitaciones que reciba de mis amigos para que no me aparezcan si no me interesan.

### H-XX+E1 – Éxito al rechazar invitación de jugar partida privada

Dado un jugador que intente aceptar una invitación de jugar partida privada de uno de sus amigos, se abrirá una pantalla para mostrar las normas de la partida. Si se rechaza la invitación, la invitación será deshechada y la partida no comenzará.

### H-XX+E2 – Éxito al rechazar invitación de espectar partida privada

Dado un jugador que intente aceptar una invitación de espectar partida privada de uno de sus amigos, se abrirá una pantalla para mostrar las normas de la partida y quiénes la están jugando. Si se rechaza la invitación, la invitación será deshechada y la partida no se espectará.

### H-XX-E3 – Invitación inexistente

Dado un jugador que intente rechazar una invitación que no esté en la base de datos (por haber expirado), se mostrará un error indicando que la invitación no existe.

### H-XX-E4 – Jugador/es inexistente/s

Dado un jugador que intente rechazar una invitación, no estando el jugador que la envía y/o el que la recibe en la base de datos, se mostrará un error indicando que el/los usuario/s no existe/n.


## H-XX – Ver información de partida

Como jugador, quiero ver la información de la partida que esté jugando o espectando para poder saber cómo va.

### H-XX+E1 – Éxito al ver información de partida

Dado un jugador que esté jugando o espectando una partida, se mostrarán los siguientes aspectos en tiempo real: posición de las bacterias y sarcinas, tablero de ayuda que indica cuál es la fase actual y las siguientes, y puntos de contaminación de cada jugador.


## H-XX – Funcionamiento de la partida

Como jugador, quiero que la partida que estoy jugando funcione correctamente para poder jugar a Petris con otros jugadores sin complicaciones.

### H-XX+E1 – Fases

Una partida avanzará de una fase a la siguiente, de la forma definida por el tablero de ayuda (los círculos indican fases de propagación, los rectángulos verdes indican fases de fisión y el rectángulo amarillo indica fase de contaminación) hasta que la partida termine. Las fases de propagación tienen asociadas un jugador, que es el que deberá mover sus bacterias para llegar a la siguiente fase; aparte de ésta, las demás fases avanzan automáticamente.

## H-XX-E2 – Acción de jugador en fase incorrecta

Dado un jugador que esté jugando una partida que intente mover sus bacterias en una fase que no sea de las suyas de propagación, la aplicación ignorará su acción.

## H-XX-E3 – Abandono de partida

Dado un jugador que esté jugando una partida que la abandone, la partida terminará sin ganador.

## H-XX-E4 – Jugador/es inexistente/s

Dado un usuario que esté jugando o espectando una partida, no estando el usuario y/o los jugadores de la partida en la base de datos, se mostrará un error indicando que el/los usuario/s no existe/n (si el usuario era uno de los jugadores de la partida, la partida terminará).

## H-XX-E5 – Jugadores no amigos en partida privada

Dado un usuario que esté jugando o espectando una partida privada, no siendo amigo de el/los jugador/es, se mostrará un error indicando que es necesario ser amigo de todos los jugadores de la partida (si el usuario era uno de los jugadores de la partida, la partida terminará).

## H-XX-E6 – Espectador no amigo de jugadores en partida pública

Dado un espectador que esté espectando una partida pública, no siendo amigo de alguno de los jugadores, se mostrará un error indicando que es necesario ser amigo de todos los jugadores de la partida.

## H-XX-E7 – Partida inexistente

Dado un usuario que esté jugando o espectando una partida, no estando la partida en la base de datos, se mostrará un error indicando que la partida no existe.


## H-XX – Condiciones de victoria

Como jugador, quiero que la partida que estoy jugando tenga condiciones de victoria para poder intentar ganar, sabiendo que la aplicación detectará mi victoria o derrota correctamente.

### H-XX+E1 – Sin movimientos disponibles

Dado un jugador que esté jugando una partida y sea una de sus fases de propagación, si no puede hacer ningún movimiento válido, la partida terminará con su derrota y la victoria del otro jugador.

### H-XX+E2 – Máximo de puntos de contaminación

Dado un jugador que esté jugando una partida y sea una fase de contaminación, si al final de la fase ha alcanzado C puntos de contaminación (siendo C un número que el creador de la partida escogerá), la partida terminará con su derrota y la victoria del otro jugador.

### H-XX+E3 – Fase de contaminación final

Dado un jugador que esté jugando una partida y sea la última fase de contaminación, si al final de la fase aún no se ha decidido el ganador, se observarán los puntos de contaminación de cada jugador. Si dicho jugador es el que tiene más puntos, la partida terminarácon su derrota y la victoria del otro jugador.

### H-XX+E4 – Desempate

Si ambos jugadores han llegado a una condición de derrota en la misma fase, el ganador se decidirá por quién tiene menos bacterias en el tablero (contando también las sarcinas, que valen S bacterias cada una, siendo S un número que el creador de la partida escogerá). Si no se llega a desempatar de ese modo, el ganador será el jugador con menos sarcinas en el tablero. Si se ha decidido el ganador, la partida terminará con su victoria y la derrota del otro jugador.

### H-XX-E5 – Imposible desempatar

Si se ha intentado desempatar y no se ha decidido ningún ganador, la partida terminará en empate, y ninguno de los jugadores ganará o perderá.


## H-XX – Fase de propagación

Como jugador, quiero que la partida que estoy jugando tenga fases de progapación para poder mover mis bacterias por el tablero.

### H-XX+E1 – Mover bacterias

Dado un jugador que esté jugando una partida y sea una fase suya de propagación, puede mover sus bacterias de una loseta a otra si el movimiento es válido (el movimiento es válido si no incumple los escenarios negativos de esta historia de usuario).

### H-XX+E2 – Fin de fase

Dado un jugador que esté jugando una partida y sea una fase suya de propagación, la fase terminará si el jugador mueve todas las bacterias de una loseta de forma válida, o si no mueve todas las bacterias de una loseta pero sí termina la fase manualmente.

### H-XX+E3 – Reiniciar fase

Dado un jugador que esté jugando una partida y sea una fase suya de propagación, si ha movido una o más bacterias pero aún no ha terminado la fase, puede reiniciarla para deshacer los movimientos que ha hecho.

### H-XX+E4 – Creación de sarcinas

Dado un jugador que esté jugando una partida y sea una fase suya de propagación, al final de la fase se observarán las losetas y, para cada una, se reemplazarán cada S bacterias del jugador por una sarcina (siendo S un número que el creador de la partida escogerá).

### H-XX-E5 – Bacterias desde varias losetas

Dado un jugador que esté jugando una partida y sea una fase suya de propagación, si ha movido una bacteria de una loseta a otra, y después intenta mover una bacteria de una loseta distinta, la aplicación deshará el movimiento e indicará que todas las bacterias que se muevan deben pertenecer a la misma loseta.

### H-XX-E6 – Loseta/s no adyacente/s

Dado un jugador que esté jugando una partida y sea una fase suya de propagación, si intenta mover una bacteria a una loseta no adyacente, la aplicación deshará el movimiento e indicará que todas las bacterias que se muevan deben moverse a losetas adyacentes.

### H-XX-E7 – Loseta/s con mismas bacterias de ambos jugadores

Dado un jugador que esté jugando una partida y sea una fase suya de propagación, si al terminar la fase existe/n una o más losetas con el mismo número de bacterias de ambos jugadores, la aplicación deshará todos los movimientos e indicará que no pueden haber losetas con el mismo número de bacterias de ambos jugadores.

### H-XX-E8 – Intentar mover hacia sarcina

Dado un jugador que esté jugando una partida y sea una fase suya de propagación, si intenta mover una de sus bacterias a una loseta que tenga una de sus sarcinas, la aplicación deshará el movimiento e indicará que no se puede mover una bacteria propia a una loseta con una sarcina propia.

### H-XX-E9 – Mover al menos una bacteria

Dado un jugador que esté jugando una partida y sea una fase suya de propagación, si intenta terminar la fase manualmente sin haber movido ninguna bacteria, la aplicación no lo permitirá.


## H-XX – Fase de fisión

Como jugador, quiero que la partida que estoy jugando tenga fases de fisión para poder crear nuevas bacterias en el tablero.

### H-XX+E1 – Creación de bacterias

En una fase de fisión, se observará cada loseta y, por cada una en la que haya bacterias de un solo jugador, se añadirán B a esa loseta (siendo B un número que el creador de la partida escogerá).

### H-XX+E2 – Creación de sarcinas

En una fase de fisión, tras crear las bacterias por loseta, se observarán las loseta y, para cada una, se reemplazarán cada S bacterias del jugador por una sarcina (siendo S un número que el creador de la partida escogerá). Tras ello, la fase terminará.


## H-XX – Fase de contaminación

Como jugador, quiero que la partida que estoy jugando tenga fases de contaminación para poder añadir puntos de contaminación a ambos jugadores.

### H-XX+E1 – Añadir puntos de contaminación

En una fase de contaminación, se observará cada loseta y, por cada una, si hay más bacterias de un jugador que de otro (contando también las sarcinas, que valen S bacterias cada una, siendo S un número que el creador de la partida escogerá), se añadirá un punto de contaminación a dicho jugador. Si se hace esto para todas las losetas y ningún jugador ha perdido, la fase terminará.


## H-XX – Abandonar partida

Como jugador, quiero abandonar una partida que esté jugando para poder dejarla si no quiero continuar.

### H-XX+E1 – Éxito al abandonar partida

Dado un jugador que intente abandonar una partida que esté jugando, el jugador saldrá de la partida, la cual se eliminará.

### H-XX-E2 – Partida inexistente

Dado un jugador que intente retomar o abandonar una partida que no esté en la base de datos, se mostrará un error indicando que la partida no existe.

### H-XX-E3 – Jugador/es inexistente/s

Dado un jugador que intente abandonar una partida, no estando el jugador que lo intenta y/o el otro jugador de la partida en la base de datos, se mostrará un error indicando que el/los usuario/s no existe/n.


## H-XX – Chat de partida

Como jugador o espectador, quiero ver y mandar mensajes en el chat de la partida que esté jugando o espectando para comunicarme con los otros usuarios.

### H-XX+E1 – Ver mensajes

Dado un jugador o espectador que esté jugando o espectando una partida, se mostrarán los mensajes enviados en el chat en tiempo real, al igual que quién ha enviado cada uno.

### H-XX+E2 – Enviar mensajes

Dado un jugador o espectador que esté jugando o espectando una partida que intente enviar un mensaje en el chat, el mensaje se guardará en el chat y los otros usuarios podrán verlo.

### H-XX-E3 – Mensajes de espectadores

Dado un jugador o espectador que esté jugando una partida, no se mostrarán los mensajes enviados en el chat por espectadores; solamente los que han sido enviados por los jugadores.