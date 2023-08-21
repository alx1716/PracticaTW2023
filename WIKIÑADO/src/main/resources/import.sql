INSERT INTO wikis (Nombre, Creador, Email, Fecha) VALUES ('Teorías de conspiración famosas:', 'Victoria', 'Alexander.victoria@vikingassistance.com', '2017-08-28');
INSERT INTO wikis (Nombre, Creador, Email, Fecha) VALUES ('Extraterrestres y avistamientos OVNI:', 'Gomez', 'Mamba@gmail.com', '2023-03-14');
INSERT INTO wikis (Nombre, Creador, Email, Fecha) VALUES('Pseudociencia y medicina alternativa:', 'Guzman', 'profesor@bolsadeideas.com', '2017-08-01');
INSERT INTO wikis (Nombre, Creador, Email, Fecha) VALUES('Grandes teorías de la historia:', 'Doe', 'john.doe@gmail.com', '2017-08-02');
INSERT INTO wikis (Nombre, Creador, Email, Fecha) VALUES('Conspiraciones en la industria de la tecnología:', 'Torvalds', 'linus.torvalds@gmail.com', '2017-08-03');
INSERT INTO wikis (Nombre, Creador, Email, Fecha) VALUES('Misterios en el mundo antiguo:', 'Doe', 'jane.doe@gmail.com', '2017-08-04');
INSERT INTO wikis (Nombre, Creador, Email, Fecha) VALUES('Proyectos secretos del gobierno:', 'Lerdorf', 'rasmus.lerdorf@gmail.com', '2017-08-05');
INSERT INTO wikis (Nombre, Creador, Email, Fecha) VALUES('Curiosidades históricas y eventos inexplicables:', 'Gamma', 'erich.gamma@gmail.com', '2017-08-06');
INSERT INTO wikis (Nombre, Creador, Email, Fecha) VALUES('Profecías y predicciones:', 'Helm', 'richard.helm@gmail.com', '2017-08-07');
INSERT INTO wikis (Nombre, Creador, Email, Fecha) VALUES('Enigmas de la mente humana:', 'Johnson', 'ralph.johnson@gmail.com', '2017-08-08');
INSERT INTO wikis (Nombre, Creador, Email, Fecha) VALUES('Fenómenos paranormales y casas embrujadas:', 'Vlissides', 'john.vlissides@gmail.com', '2017-08-09');
INSERT INTO wikis (Nombre, Creador, Email, Fecha) VALUES('Criptozoología y criaturas misteriosas:', 'Gosling', 'james.gosling@gmail.com', '2017-08-010');
INSERT INTO wikis (Nombre, Creador, Email, Fecha) VALUES('Mitos y leyendas urbanas contemporáneas:', 'Lee', 'bruce.lee@gmail.com', '2017-08-11');
INSERT INTO wikis (Nombre, Creador, Email, Fecha) VALUES('Reliquias y objetos sagrados:', 'Doe', 'johnny.doe@gmail.com', '2017-08-12');
INSERT INTO wikis (Nombre, Creador, Email, Fecha) VALUES('Poderes ocultos y habilidades sobrenaturales:', 'Roe', 'john.roe@gmail.com', '2017-08-13');
INSERT INTO wikis (Nombre, Creador, Email, Fecha) VALUES('Misterios en el espacio y la astronomía:', 'Roe', 'jane.roe@gmail.com', '2017-08-14');
INSERT INTO wikis (Nombre, Creador, Email, Fecha) VALUES('Experiencias y testimonios de encuentros paranormales:', 'Doe', 'richard.doe@gmail.com', '2017-08-15');
INSERT INTO wikis (Nombre, Creador, Email, Fecha) VALUES('Civilizaciones perdidas y tecnologías olvidadas:', 'Doe', 'janie.doe@gmail.com', '2017-08-16');
INSERT INTO wikis (Nombre, Creador, Email, Fecha) VALUES('Objetos históricos y reliquias controversiales:', 'Webb', 'phillip.webb@gmail.com', '2017-08-17');
INSERT INTO wikis (Nombre, Creador, Email, Fecha) VALUES('Fenómenos naturales inusuales y teorías alternativas:', 'Victoria', 'perritobuenos@gmail.com', '2016-06-12');










INSERT INTO articulos (titulo, contenido, wiki_id, fecha_creacion) VALUES('Falsa llegada a la Luna','/articulos/test.txt' , 1, NOW());  
INSERT INTO articulos (titulo, contenido, wiki_id, fecha_creacion) VALUES('Reptilianos: ¿verdad o ficción?', 'Alguna observación importante!!', 1, NOW());
INSERT INTO articulos (titulo, contenido, wiki_id, fecha_creacion) VALUES('Control del clima por el gobierno', 'Alguna observación importante!!', 1, NOW());
INSERT INTO articulos (titulo, contenido, wiki_id, fecha_creacion) VALUES('Roswell: El incidente OVNI más famoso', 'Alguna observación importante!!', 2, NOW());
INSERT INTO articulos (titulo, contenido, wiki_id, fecha_creacion) VALUES('Encuentros cercanos del tercer tipo', 'Alguna observación importante!!', 2, NOW());
INSERT INTO articulos (titulo, contenido, wiki_id, fecha_creacion) VALUES('Abducciones extraterrestres', 'Alguna observación importante!!', 2, NOW());
INSERT INTO articulos (titulo, contenido, wiki_id, fecha_creacion) VALUES('Homeopatía: curando con agua', 'Alguna observación importante!!', 3, NOW());
INSERT INTO articulos (titulo, contenido, wiki_id, fecha_creacion) VALUES('Energía de cristales y sanación', 'Alguna observación importante!!', 3, NOW());
INSERT INTO articulos (titulo, contenido, wiki_id, fecha_creacion) VALUES('Astrología y signos del zodíaco', 'Alguna observación importante!!', 3, NOW());
INSERT INTO articulos (titulo, contenido, wiki_id, fecha_creacion) VALUES('El verdadero autor de las obras de Shakespeare', 'Alguna observación importante!!', 4, NOW());
INSERT INTO articulos (titulo, contenido, wiki_id, fecha_creacion) VALUES('El Santo Grial y su búsqueda', 'Alguna observación importante!!', 4, NOW());
INSERT INTO articulos (titulo, contenido, wiki_id, fecha_creacion) VALUES('Atlántida: ¿leyenda o realidad?', 'Alguna observación importante!!', 4, NOW());
INSERT INTO articulos (titulo, contenido, wiki_id, fecha_creacion) VALUES('5G y teorías de conspiración sobre la salud', 'Alguna observación importante!!', 5, NOW());
INSERT INTO articulos (titulo, contenido, wiki_id, fecha_creacion) VALUES('Control de la mente a través de smartphones', 'Alguna observación importante!!', 5, NOW());

INSERT INTO `usuarios` (username, password, email, enabled) VALUES ('angel','$2a$10$6RupV85kTTieYF1ybeilB.N43CSXTmH/FPJV.HsQ0wsApvP5WR/gC','angel@wikinado.com',1);
INSERT INTO `usuarios` (username, password, email, enabled) VALUES ('admin','$2a$10$miSV48.apvsRhzMmO1YAz.PQi6aKaxDRY8qARFl67A.taLG5Uws1e','admin@wikinado.com',1);
INSERT INTO `usuarios` (username, password, email, enabled) VALUES ('COORDINADOR','$2a$10$riL0iV3GSwsm4/HX0YePgOz1ys.wyj0mPhFWEB/N3vtkMvRRlyv2C','coordinador@wikinado.com',1);
INSERT INTO `usuarios` (username, password, email, enabled) VALUES ('SUPERVISOR','$2a$10$hMgaXSjfiWT6l1Btozp5M.xozobXE9upygUIVuMz/0GXkQ4Q2WuYm','supervisor@wikinado.com',1);

INSERT INTO `authorities` (user_id, authority, new_role, status) VALUES (1,'ROLE_COLABORADOR', 'ROLE_COORDINADOR', 'PENDIENTE' );
INSERT INTO `authorities` (user_id, authority) VALUES (2,'ROLE_GESTOR');
/*INSERT INTO `authorities` (user_id, authority) VALUES (2,'ROLE_COLABORADOR');*/
INSERT INTO `authorities` (user_id, authority) VALUES (4,'ROLE_SUPERVISOR');
INSERT INTO `authorities` (user_id, authority) VALUES (4,'ROLE_COLABORADOR');
INSERT INTO `authorities` (user_id, authority) VALUES (3,'ROLE_COORDINADOR');
INSERT INTO `authorities` (user_id, authority) VALUES (3,'ROLE_COLABORADOR');

INSERT INTO `propuestas` (estado, propuesta, articulo_id, usuario_id) VALUES ('EVALUACION','Esto es una prueba para cargar la propuesta desde el import sql', 1, 1 );
INSERT INTO `propuestas` (estado, propuesta, articulo_id, usuario_id) VALUES ('EVALUACION','Este es la única propuesta pendiente de revisión que le debería de salir al Supervisor', 2, 1 );
INSERT INTO `propuestas` (estado, propuesta, articulo_id, usuario_id) VALUES ('EVALUACION','Esto es una prueba para cargar la propuesta desde el import sql', 3, 1 );
INSERT INTO `propuestas` (estado, propuesta, articulo_id, usuario_id) VALUES ('EVALUACION','Esto es una prueba para cargar la propuesta desde el import sql', 4, 1 );
