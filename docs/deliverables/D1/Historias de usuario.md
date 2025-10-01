# Historias de usuario


## H-01 – Registrar jugador

Como usuario, quiero registrarme como jugador en el sistema para poder identificarme y acceder a las funcionalidades de jugador.

**H-01+E1 – Éxito al registrar jugador**

Dado un usuario que introduzca un nombre, contraseña y campos de perfil con formato correcto al intentar registrar un jugador, se creará un jugador en la base de datos con dichas credenciales, y el usuario iniciará sesión con dicho jugador.

**H-01-E2 – Intentar registrar jugador con datos no válidos**

Dado un usuario que introduzca un nombre, contraseña y/o campo/s de perfil con formato incorrecto al intentar registrar un jugador, se mostrará un error en pantalla indicando que los datos no son válidos y el jugador no será creado en la base de datos.


## H-02 – Iniciar sesión

Como usuario, quiero iniciar sesión como un jugador/administrador existente en la base de datos del sistema para poder identificarme y acceder a las funcionalidades de jugador.

**H-02+E1 – Éxito al iniciar sesión**

Dado un usuario que introduzca un nombre y/o contraseña asociados a un jugador/administrador existente en la base de datos del sistema al intentar iniciar sesión, el usuario iniciará sesión con dicho jugador.

**H-02-E2 – Intentar iniciar sesión con nombre de usuario inexistente**

Dado un usuario que introduzca un nombre no asociado a ningún jugador/administrador de la base de datos del sistema al intentar iniciar sesión, se mostrará un error en pantalla indicando que ningún jugador de la base de datos tiene dicho nombre y el usuario no iniciará sesión.

**H-02-E3 – Intentar iniciar sesión con contraseña incorrecta**

Dado un usuario que introduzca un nombre y contraseña donde la contraseña no esté asociada al jugador/administrador con dicho nombre en la base de datos al intentar iniciar sesión, se mostrará un error en pantalla indicando el jugador/administrador con dicho nombre no tiene dicha contraseña y el usuario no iniciará sesión.


## H-03 – Cerrar sesión

Como jugador/administrador, quiero cerrar mi sesión para poder dejar de usar el sistema y/o permitir a otro jugador/administrador iniciar sesión.

**H-03+E1 – Éxito al cerrar sesión**

Dado un usuario con una sesión iniciada que intente cerrarla, la sesión se cerrará de forma segura y el sistema volverá a la pantalla de inicio.


## H-04 – Editar perfil

Como jugador, quiero editar mi perfil para poder mostrar información sobre mí.

**H-04+E1 – Éxito al editar perfil**

Dado un jugador que introduzca campos de perfil con formato correcto al intentar editar su perfil, los valores de su perfil serán reemplazados correctamente con los que ha introducido.

**H-04-E2 – Intentar editar perfil con datos no válidos**

Dado un jugador que introduzca campos de perfil con formato incorrecto al intentar editar su perfil, se mostrará un error en pantalla indicando que los datos no son válidos y los valores de su perfil no cambiarán.


# H-05 – Eliminar jugador

Como jugador, quiero eliminar mi perfil para poder deshacerme de mis datos que estén guardados en la base de datos del sistema.

**H-05+E1 – Éxito al eliminar jugador**

Dado un jugador que intente eliminar su perfil, dicho jugador será completamente eliminado de la base de datos del sistema.