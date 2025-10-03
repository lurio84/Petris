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

Como jugador/administrador, quiero cerrar mi sesión para poder dejar de usar el sistema y/o permitir a otro jugador/administrador iniciar sesión.

### H-XX+E1 – Éxito al cerrar sesión

Dado un usuario con una sesión iniciada que intente cerrarla, la sesión se cerrará de forma segura y el sistema volverá a la pantalla de inicio.


## H-XX – Ver perfil

Como jugador, quiero ver mi perfil para poder comprobar qué información muestro sobre mí.

### H-XX+E1 – Éxito al ver perfil

Dado un jugador que intente ver su perfil, se le mostrará junto con toda su información: logros, estadísticas y datos personales.


## H-XX – Editar perfil

Como jugador, quiero editar mi perfil para poder elegir qué información muestro sobre mí.

### H-XX+E1 – Éxito al editar perfil

Dado un jugador que introduzca campos de perfil con formato correcto al intentar editar su perfil, los valores de su perfil serán reemplazados correctamente con los que ha introducido.

### H-XX-E2 – Intentar editar perfil con datos no válidos

Dado un jugador que introduzca campos de perfil con formato incorrecto al intentar editar su perfil, se mostrará un error en pantalla indicando que los datos no son válidos y los valores de su perfil no cambiarán.


## H-XX – Ver logros

Como jugador, quiero ver todos los logros que tenga (obtenidos y no obtenidos) para poder saber qué logros he obtenido hasta el momento y cómo conseguir los que aún no tengo.

### H-XX+E1 – Éxito al ver logros

Dado un jugador que intente ver sus logros, se mostrará una lista de todos los logros del juego, mostrando para cada uno si se ha obtenido o no y cómo obtenerlo.


## H-XX – Eliminar jugador

Como jugador, quiero eliminar mi perfil para poder deshacerme de los datos de mí que estén guardados en la base de datos del sistema.

### H-XX+E1 – Éxito al eliminar jugador

Dado un jugador que intente eliminar su perfil, dicho jugador será completamente eliminado de la base de datos del sistema.


## H-XX Ver solicitudes de amistad

Como jugador, quiero ver las solicitudes de amistad que haya recibido de otros jugadores para poder aceptarlas o ignorarlas.

### H-XX-E1 – Éxito al ver solicitudes de amistad

Dado un jugador que intente ver sus solicitudes de amistad, se mostrará una lista con todas las solicitudes de amistad que ha recibido y aún no haya aceptado o ignorado.


## H-XX – Enviar solicitud de amistad

Como jugador, quiero enviar solicitudes de amistad a otros jugadores para poder ser amigos y jugar partidas juntos.

### H-XX+E1 – Éxito al enviar solicitud de amistad

Dado un jugador que intente enviar una petición de amistad desde su lista de amigos mediante el id de otro jugador, la petición será enviada al jugador correctamente.


## H-XX – Aceptar solicitud de amistad

Como jugador, quiero aceptar las solicitudes de amistad que reciba de otros jugadores para poder ser amigos y jugar partidas juntos.

### H-XX+E1 – Éxito al aceptar solicitud de amistad

Dado un jugador que intente aceptar una petición de amistad que reciba de otro jugador, la petición será aceptada y los dos jugadores se convertirán en amigos.


## H-XX – Ignorar solicitud de amistad

Como jugador, quiero ignorar las solicitudes de amistad que reciba de otros jugadores para poder rechazar ser amigos con jugadores con los que no quiera serlo.

### H-XX+E1 – Éxito al ignorar solicitud de amistad

Dado un jugador que intente ignorar una petición de amistad que reciba de otro jugador, la petición será ignorarada, desapareciendo de la lista de peticiones, y los dos jugadores no serán amigos.


## H-XX – Eliminar amigo

Como jugador, quiero eliminar a un jugador de mi lista de amigos para poder controlar qué jugadores son mis amigos.

### H-XX+E1 – Éxito al eliminar amigo

Dado un jugador que intente eliminar a un jugador de su lista de amigos desde el perfil de dicho jugador, ambos jugadores ya no serán amigos.


## H-XX – Ver amigos

Como jugador, quiero ver los amigos que tengo en la aplicación para poder comprobar qué usuarios son mis amigos y ver cuáles están en línea.

### H-XX+E1 – Lista de amigos

Dado un jugador que intente ver sus amigos, se mostrará una lista con todos los que tenga, indicando si cada uno está en línea o no.

### H-XX+E2 – Lista vacía de amigos

Dado un jugador que intente ver sus amigos sin tener ninguno, se mostrará una lista vacía con un mensaje que indique que el usuario aún no tiene amigos.


## H-XX – Enviar invitación de jugar partida privada

Como jugador, quiero invitar a un amigo a jugar una partida privada para poder jugar a Petris con él.

### H-XX+E1 – Éxito al enviar invitación de jugar partida privada

Dado un jugador que intente enviar una invitación de jugar partida privada a uno de sus amigos, se abrirá una pantalla para configurar las normas de la partida. Si la partida se crea, dicho amigo recibirá la invitación y la partida comenzará cuando la acepte.

### H-XX+E2 – Cancelación al enviar invitación de jugar partida privada

Dado un jugador que intente enviar una invitación de jugar partida privada a uno de sus amigos, se abrirá una pantalla para configurar las normas de la partida. Si el jugador decide cancelar la creación de la partida, se volverá a la lista de amigos y la invitación no será enviada.


## H-XX – Eliminar invitación de jugar partida privada

Como jugador, quiero eliminar la invitación de jugar partida privada que he enviado a un amigo (y que aún no ha sido aceptada) para poder cancelar el comienzo de la partida.

### H-XX+E1 – Éxito al eliminar invitación de jugar partida privada

Dado un jugador que intente eliminar una invitación de jugar partida privada aún no aceptada que envió a uno de sus amigos, la invitación se eliminará correctamente, por lo que dicho amigo ya no podrá aceptarla, y la partida no comenzará.


## H-XX – Aceptar invitación de jugar partida privada

Como jugador, quiero aceptar la invitación de jugar partida privada que me envíe un amigo para poder jugar a Petris con él.

### H-XX+E1 – Éxito al aceptar invitación de jugar partida privada

Dado un jugador que intente aceptar una invitación de jugar partida privada de uno de sus amigos, se abrirá una pantalla para mostrar las normas de la partida. Si se acepta la invitación, la partida comenzará.


## H-XX – Rechazar invitación de jugar partida privada

Como jugador, quiero rechazar la invitación de jugar partida privada que me envíe un amigo para poder cancelarla y que no me aparezca en la lista de amigos.

### H-XX+E1 – Éxito al rechazar invitación de jugar partida privada

Dado un jugador que intente aceptar una invitación de jugar partida privada de uno de sus amigos, se abrirá una pantalla para mostrar las normas de la partida. Si se rechaza la invitación, la invitación será deshechada y la partida no comenzará.


## H-XX – Enviar invitación de espectar partida privada

Como jugador, quiero invitar a un amigo a espectar una partida privada en la que esté actualmente para que pueda ver mi partida.

### H-XX+E1 – Éxito al enviar invitación de espectar partida privada

Dado un jugador que intente enviar una invitación de espectar partida privada, se abrirá una pantalla para que el jugador decida a qué amigo se la mandará (solo aparecerán los usuarios que sean amigos con ambos participantes de la partida). Si se escoge a un amigo, recibirá la invitación.

### H-XX+E2 – Cancelación al enviar invitación de espectar partida privada

Dado un jugador que intente enviar una invitación de espectar partida privada, se abrirá una pantalla para que el jugador decida a qué amigo se la mandará (solo aparecerán los usuarios que sean amigos con ambos participantes de la partida). Si el jugador decide cancelar el envío, se volverá a la partida y la invitación no será enviada.


## H-XX – Eliminar invitación de espectar partida privada

Como jugador, quiero eliminar la invitación de espectar partida privada que he enviado a un amigo (y que aún no ha sido aceptada) para poder cancelar que pueda ver la partida.

### H-XX+E1 – Éxito al eliminar invitación de espectar partida privada

Dado un jugador que intente eliminar una invitación de espectar partida privada aún no aceptada que envió a uno de sus amigos, la invitación se eliminará correctamente, por lo que dicho amigo ya no podrá aceptarla.


## H-XX – Aceptar invitación de espectar partida privada

Como jugador, quiero aceptar la invitación de espectar partida privada que me envíe un amigo para poder ver su partida.

### H-XX+E1 – Éxito al aceptar invitación de espectar partida privada

Dado un jugador que intente aceptar una invitación de espectar partida privada de uno de sus amigos, se abrirá una pantalla para mostrar las normas de la partida y quiénes la están jugando. Si se rechaza la invitación, la invitación será deshechada y la partida no se espectará.


## H-XX – Rechazar invitación de espectar partida privada

Como jugador, quiero rechazar la invitación de espectar partida privada que me envíe un amigo para poder cancelarla y que no me aparezca en la lista de amigos.

### H-XX+E1 – Éxito al rechazar invitación de espectar partida privada

Dado un jugador que intente aceptar una invitación de espectar partida privada de uno de sus amigos, se abrirá una pantalla para mostrar las normas de la partida y quiénes la están jugando. Si se rechaza la invitación, la invitación será deshechada y la partida no se espectará.