## SISTEMA DE CITAS DE CONSULTORIO MEDICO - EVIDENCIA
**INFORMACIÓN**

**Instalación y configuración:**
El programa neccesita ser descargado en el aparatdo de tags, donde se encuentra la version optima del proyecto (v1.0) y así implementarlo en su ordenador mediante un comprimido.
Una vez hecho esto, hay que abrir la carpeta del proyecto en su IDE de preferenica par ser compilado.

**Uso del programa:** 
El sistema de citas de un consultorio medico le dará 3 opciones de usuario para ser utilizado: Administrador, es el cual tiene privilegios para crear o eliminar doctores, pacientes
o citas, asi como visualizar la información registrada segun el apartado manipulado en ese momento de dichas opciones dentro del mismo menu desplegable. Los doctores y pacientes solo tendrán
sus respectivas credenciales para visualizar sus citas programadas. Cada usuario iniciará sesion con un ID y una contraseña; administrador teniendo por default su información y los restantes dependerá de
los datos que les asigne el administrador una vez que haga sus registros en el sistema. El usuario se identificará por medio de ID, nombre, especialidad/edad (segun el tipo de usuario) y las citas obtendrán
un folio de manera automatica, fecha, hora, los involucrados (paciente y doctor) estos asignados mediante su ID, y el motivo por el cual se lleva a cabo la consulta. Asimismo, se podrá eliminar usuarios
o citas mediante su ID o su folio respectivamente.

**NOTA:** todas las modificaciones de registro de usuarios y citas, así como la eliminación de algún dato de estos mismos, se verá reflejado en tres archivos de texto (CSV) según corresponda la división de información 
(usuario, cita) para tener un mejor control de todos los movimientos del sistema de citas. Si no se cuentan con los archivos donde se almacena dicha información, al momento de compilar le código fuente creará la 
carpeta db y posteriormente, conforme se vayan haciendo registros de usuarios o citas, se crearán los archivos de texto de manera automática, todo esto dentro de la carpeta del proyecto.

**Créditos:** Jesús Alejandro Alamo Acosta

**Licencia:** Publica.
