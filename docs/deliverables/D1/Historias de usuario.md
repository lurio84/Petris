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

Dado un jugador que intente rechazar una invitación que no esté en la base de datos, se mostrará un error indicando que la invitación no existe.

### H-XX-E4 – Jugador/es inexistente/s

Dado un jugador que intente rechazar una invitación, no estando el jugador que la envía y/o el que la recibe en la base de datos, se mostrará un error indicando que el/los usuario/s no existe/n.


## H-XX – Abandonar partida

Como jugador, quiero abandonar una partida que esté jugando para poder dejarla si no quiero continuar.

### H-XX+E1 – Éxito al abandonar partida

Dado un jugador que intente abandonar una partida que esté jugando, el jugador saldrá de la partida, la cual se eliminará.

### H-XX-E2 – Partida inexistente

Dado un jugador que intente retomar o abandonar una partida que no esté en la base de datos, se mostrará un error indicando que la partida no existe.

### H-XX-E3 – Jugador/es inexistente/s

Dado un jugador que intente retomar o abandonar una partida, no estando el jugador que lo intenta y/o el otro jugador de la partida en la base de datos, se mostrará un error indicando que el/los usuario/s no existe/n.