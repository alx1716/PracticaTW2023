INSERT INTO wikis (Nombre, Creador, Email, Fecha) VALUES ('Teorías de conspiración famosas', 'Victoria', 'Alexander.victoria@vikingassistance.com', '2017-08-28');
INSERT INTO wikis (Nombre, Creador, Email, Fecha) VALUES ('Extraterrestres y avistamientos OVNI', 'Gomez', 'Mamba@gmail.com', '2023-03-14');
INSERT INTO wikis (Nombre, Creador, Email, Fecha) VALUES('Pseudociencia y medicina alternativa', 'Guzman', 'profesor@bolsadeideas.com', '2017-08-01');
INSERT INTO wikis (Nombre, Creador, Email, Fecha) VALUES('Grandes teorías de la historia', 'Doe', 'john.doe@gmail.com', '2017-08-02');
INSERT INTO wikis (Nombre, Creador, Email, Fecha) VALUES('Conspiraciones en la industria de la tecnología', 'Torvalds', 'linus.torvalds@gmail.com', '2017-08-03');
INSERT INTO wikis (Nombre, Creador, Email, Fecha) VALUES('Misterios en el mundo antiguo', 'Doe', 'jane.doe@gmail.com', '2017-08-04');
INSERT INTO wikis (Nombre, Creador, Email, Fecha) VALUES('Proyectos secretos del gobierno', 'Lerdorf', 'rasmus.lerdorf@gmail.com', '2017-08-05');
INSERT INTO wikis (Nombre, Creador, Email, Fecha) VALUES('Curiosidades históricas y eventos inexplicables', 'Gamma', 'erich.gamma@gmail.com', '2017-08-06');
INSERT INTO wikis (Nombre, Creador, Email, Fecha) VALUES('Profecías y predicciones', 'Helm', 'richard.helm@gmail.com', '2017-08-07');
INSERT INTO wikis (Nombre, Creador, Email, Fecha) VALUES('Enigmas de la mente humana', 'Johnson', 'ralph.johnson@gmail.com', '2017-08-08');
INSERT INTO wikis (Nombre, Creador, Email, Fecha) VALUES('Fenómenos paranormales y casas embrujadas', 'Vlissides', 'john.vlissides@gmail.com', '2017-08-09');
INSERT INTO wikis (Nombre, Creador, Email, Fecha) VALUES('Criptozoología y criaturas misteriosas', 'Gosling', 'james.gosling@gmail.com', '2017-08-010');
INSERT INTO wikis (Nombre, Creador, Email, Fecha) VALUES('Mitos y leyendas urbanas contemporáneas', 'Lee', 'bruce.lee@gmail.com', '2017-08-11');
INSERT INTO wikis (Nombre, Creador, Email, Fecha) VALUES('Reliquias y objetos sagrados', 'Doe', 'johnny.doe@gmail.com', '2017-08-12');
INSERT INTO wikis (Nombre, Creador, Email, Fecha) VALUES('Poderes ocultos y habilidades sobrenaturales', 'Roe', 'john.roe@gmail.com', '2017-08-13');
INSERT INTO wikis (Nombre, Creador, Email, Fecha) VALUES('Misterios en el espacio y la astronomía', 'Roe', 'jane.roe@gmail.com', '2017-08-14');
INSERT INTO wikis (Nombre, Creador, Email, Fecha) VALUES('Experiencias y testimonios de encuentros paranormales', 'Doe', 'richard.doe@gmail.com', '2017-08-15');
INSERT INTO wikis (Nombre, Creador, Email, Fecha) VALUES('Civilizaciones perdidas y tecnologías olvidadas', 'Doe', 'janie.doe@gmail.com', '2017-08-16');
INSERT INTO wikis (Nombre, Creador, Email, Fecha) VALUES('Objetos históricos y reliquias controversiales', 'Webb', 'phillip.webb@gmail.com', '2017-08-17');
INSERT INTO wikis (Nombre, Creador, Email, Fecha) VALUES('Fenómenos naturales inusuales y teorías alternativas', 'Victoria', 'perritobuenos@gmail.com', '2016-06-12');










INSERT INTO articulos (titulo, contenido, wiki_id, fecha_creacion,enabled) VALUES('Falsa llegada a la Luna','/articulos/test.txt' , 1, NOW(),1);  
INSERT INTO articulos (titulo, contenido, wiki_id, fecha_creacion,enabled) VALUES('Reptilianos: ¿verdad o ficción?', '/articulos/test.txt', 1, NOW(),1);
INSERT INTO articulos (titulo, contenido, wiki_id, fecha_creacion,enabled) VALUES('Control del clima por el gobierno', '/articulos/test.txt', 1, NOW(),1);
INSERT INTO articulos (titulo, contenido, wiki_id, fecha_creacion,enabled) VALUES('Roswell: El incidente OVNI más famoso', '/articulos/test.txt', 2, NOW(),1);
INSERT INTO articulos (titulo, contenido, wiki_id, fecha_creacion,enabled) VALUES('Encuentros cercanos del tercer tipo', '/articulos/test.txt', 2, NOW(),1);
INSERT INTO articulos (titulo, contenido, wiki_id, fecha_creacion,enabled) VALUES('Abducciones extraterrestres', '/articulos/test.txt', 2, NOW(),1);
INSERT INTO articulos (titulo, contenido, wiki_id, fecha_creacion,enabled) VALUES('Homeopatía: curando con agua', '/articulos/test.txt', 3, NOW(),1);
INSERT INTO articulos (titulo, contenido, wiki_id, fecha_creacion,enabled) VALUES('Energía de cristales y sanación', '/articulos/test.txt', 3, NOW(),1);
INSERT INTO articulos (titulo, contenido, wiki_id, fecha_creacion,enabled) VALUES('Astrología y signos del zodíaco', '/articulos/test.txt', 3, NOW(),1);
INSERT INTO articulos (titulo, contenido, wiki_id, fecha_creacion,enabled) VALUES('El verdadero autor de las obras de Shakespeare', '/articulos/test.txt', 4, NOW(),1);
INSERT INTO articulos (titulo, contenido, wiki_id, fecha_creacion,enabled) VALUES('El Santo Grial y su búsqueda', '/articulos/test.txt', 4, NOW(),1);
INSERT INTO articulos (titulo, contenido, wiki_id, fecha_creacion,enabled) VALUES('Atlántida: ¿leyenda o realidad?', '/articulos/test.txt', 4, NOW(),1);
INSERT INTO articulos (titulo, contenido, wiki_id, fecha_creacion,enabled) VALUES('5G y teorías de conspiración sobre la salud', '/articulos/test.txt', 5, NOW(),1);
INSERT INTO articulos (titulo, contenido, wiki_id, fecha_creacion,enabled) VALUES('Control de la mente a través de smartphones', '/articulos/test.txt', 5, NOW(),1);

INSERT INTO `usuarios` (username, password, email, enabled) VALUES ('COLABORADOR','$2a$10$6RupV85kTTieYF1ybeilB.N43CSXTmH/FPJV.HsQ0wsApvP5WR/gC','colaborador@wikinado.com',1);
INSERT INTO `usuarios` (username, password, email, enabled) VALUES ('GESTOR','$2a$10$miSV48.apvsRhzMmO1YAz.PQi6aKaxDRY8qARFl67A.taLG5Uws1e','gestor@wikinado.com',1);
INSERT INTO `usuarios` (username, password, email, enabled) VALUES ('COORDINADOR','$2a$10$riL0iV3GSwsm4/HX0YePgOz1ys.wyj0mPhFWEB/N3vtkMvRRlyv2C','coordinador@wikinado.com',1);
INSERT INTO `usuarios` (username, password, email, enabled) VALUES ('SUPERVISOR','$2a$10$hMgaXSjfiWT6l1Btozp5M.xozobXE9upygUIVuMz/0GXkQ4Q2WuYm','supervisor@wikinado.com',1);

INSERT INTO `authorities` (user_id, authority) VALUES (1,'ROLE_COLABORADOR');
INSERT INTO `authorities` (user_id, authority) VALUES (2,'ROLE_GESTOR');


INSERT INTO `propuestas` (estado, propuesta, articulo_id, usuario_id) VALUES ('PENDIENTE','Esto es una prueba para cargar la propuesta desde el import sql', 1, 1 );
INSERT INTO `propuestas` (estado, propuesta, articulo_id, usuario_id) VALUES ('PENDIENTE','pRUEBAS PARA VER QUE SE LE HACE', 2, 1 );
INSERT INTO `propuestas` (estado, propuesta, articulo_id, usuario_id) VALUES ('PENDIENTE','Esto es una prueba para cargar la propuesta desde el import sql', 3, 1 );
INSERT INTO `propuestas` (estado, propuesta, articulo_id, usuario_id) VALUES ('PENDIENTE','Esto es una prueba para cargar la propuesta desde el import sql', 4, 1 );

INSERT INTO `authorities` (user_id, authority) VALUES (4,'ROLE_SUPERVISOR');
INSERT INTO `authorities` (user_id, authority) VALUES (4,'ROLE_COLABORADOR');
INSERT INTO `authorities` (user_id, authority) VALUES (3,'ROLE_COORDINADOR');
INSERT INTO `authorities` (user_id, authority) VALUES (3,'ROLE_COLABORADOR');












INSERT INTO peticiones_rol (requested_authority, status, user_id) VALUES ('ROLE_COORDINADOR', 'PENDIENTE', 1)
INSERT INTO peticiones_rol (requested_authority, status, user_id) VALUES ('ROLE_GESTOR', 'PENDIENTE', 4)
INSERT INTO peticiones_rol (requested_authority, status, user_id) VALUES ('ROLE_SUPERVISOR', 'PENDIENTE', 3)

