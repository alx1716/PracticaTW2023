
Este Archivo sirve para dos cosas, comprobar que se ha sincronizado este repositorio con mio máquina local y servir de descripción/manual de la aplicación que vamos a crear.

Sigo Modificando el archivo para ver si me sale en el repositorio a la página web

He comprobado que se puede actualizar aquí si modifico en mi máquina local, ahora debo mirar si funciona al revés.


He comprobado que me guarda los cambios haciendo un pull desde mi máquina.


Hago un cambio más para ver si con el cmd sin ser admin me funcionan los pull

Compruebo que funcionan correctamente

@Fer: Pruebo a realizar modificación de fichero en local

@Fer:  he creado una rama para el proyecto con Thymeleaf, he subido el proyecto, me quedan detalles de login y registro, creo que voy a cambiar para usar el email como usuario para registro y acceso.
Para hacer pruebas con hsql yo lo tengo instalado en una carpeta de disco c:, primero me abro un cmd como admin y ejecuto esto:
java -cp C:\hsqldb-2.7.1\hsqldb\lib\hsqldb.jar org.hsqldb.Server -database.0 file:wikinado_db -dbname.0 wikdb

me abro otro cmd me meto en la carpeta bin de hsqldb y ejecuto el runserverSwing

me creo una conexion con estos datos:
Driver: org.hsqldb.jdbc.JDBCDriver
url: jdbc:hsqldb:hsql://localhost/wikdb
user: SA

pulsar OK

Por otro lado en las properties del proyecto tengo puesto el puerto 8090, asique para hacer pruebas en local --> http://localhost:8090/

