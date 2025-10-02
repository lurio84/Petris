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

Dado un jugador que intente enviar una petición de amistad desde el perfil de otro jugador o desde su lista de amigos, la petición será enviada al jugador correctamente.


## H-XX – Aceptar solicitud de amistad

Como jugador, quiero aceptar las solicitudes de amistad que reciba de otros jugadores para poder ser amigos y jugar partidas juntos.

### H-XX+E1 – Éxito al aceptar solicitud de amistad

Dado un jugador que intente aceptar una petición de amistad que reciba de otro jugador, la petición será aceptada, se enviará una notificación al otro jugador, y los dos jugadores se convertirán en amigos.


## H-XX – Ignorar solicitud de amistad

Como jugador, quiero ignorar las solicitudes de amistad que reciba de otros jugadores para poder rechazar ser amigos con jugadores con los que no quiera serlo.

### H-XX+E1 – Éxito al ignorar solicitud de amistad

Dado un jugador que intente ignorar una petición de amistad que reciba de otro jugador, la petición será ignorarada, desapareciendo de la lista de peticiones, y los dos jugadores no serán amigos.


## H-XX – Eliminar amigo

Como jugador, quiero eliminar a un jugador de mi lista de amigos para poder controlar qué jugadores son mis amigos.

### H-XX+E1 – Éxito al eliminar amigo

Dado un jugador que intente eliminar a un jugador de su lista de amigos desde el perfil de dicho jugador, ambos jugadores ya no serán amigos.


## H-XX – Ver amigos

Como jugador, quiero ver los amigos que tengo en la aplicación para poder comprobar qué usuarios son mis amigos y poder comenzar partidas con ellos.

### H-XX+E1 – Lista de amigos

Dado un jugador que intente ver sus amigos, se mostrará una lista con todos los que tenga, con opciones para invitar o unirse a una partida.

### H-XX+E2 – Lista vacía de amigos

Dado un jugador que intente ver sus amigos sin tener ninguno, se mostrará una lista vacía con un mensaje que indique que el usuario aún no tiene amigos.


## H-XX – Enviar invitación

Como jugador, quiero invitar a un amigo a comenzar una partida para poder jugar a Petris con él.

### H-XX+E1 – Éxito al enviar invitación

Dado un jugador que intente enviar una invitación de partida a uno de sus amigos, se abrirá una pantalla para configurar las normas de la partida. Si la partida se crea, dicho amigo recibirá la invitación y la partida comenzará cuando la acepte.

### H-XX+E2 – Cancelación al enviar invitación

Dado un jugador que intente enviar una invitación de partida a uno de sus amigos, se abrirá una pantalla para configurar las normas de la partida. Si el jugador decide cancelar la creación de la partida, se volverá a la lista de amigos y la invitación no será enviada.


## H-XX – Eliminar invitación

Como jugador, quiero eliminar la invitación que he enviado a un amigo (y que aún no ha sido aceptada) para poder cancelar el comienzo de la partida.

### H-XX+E1 – Éxito al eliminar invitación

Dado un jugador que intente eliminar una invitación de partida aún no aceptada que envió a uno de sus amigos, la invitación se eliminará correctamente, por lo que dicho amigo ya no podrá aceptarla, y la partida no comenzará.


## H-XX – Aceptar invitación

Como jugador, quiero aceptar la invitación de partida que me envíe un amigo para poder jugar a Petris con él.

### H-XX+E1 – Éxito al aceptar invitación

Dado un jugador que intente aceptar una invitación de partida de uno de sus amigos, se abrirá una pantalla para mostrar las normas de la partida. Si se acepta la invitación, dicho amigo será notificado y la partida comenzará.


## H-XX – Rechazar invitación

Como jugador, quiero rechazar la invitación de partida que me envíe un amigo para poder cancelarla y que no me aparezca en la lista de amigos.

### H-XX+E1 – Éxito al rechazar invitación

Dado un jugador que intente aceptar una invitación de partida de uno de sus amigos, se abrirá una pantalla para mostrar las normas de la partida. Si se rechaza la invitación, dicho amigo será notificado, la invitación será deshechada y la partida no comenzará.


## H-XX – Ver logros

Como jugador, quiero ver todos los logros que tenga (obtenidos y no obtenidos) para poder saber qué logros he obtenido hasta el momento y cómo conseguir los que aún no tengo.

### H-XX+E1 – Éxito al ver logros

Dado un jugador que intente ver sus logros, se mostrará una lista de todos los logros del juego, mostrando para cada uno si se ha obtenido o no y cómo obtenerlo.