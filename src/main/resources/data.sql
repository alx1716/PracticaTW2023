--Añadimos los Roles
INSERT INTO ROLES
( "ID", "TIPO_ROL" )
VALUES ( 1, 'gestor'),
( 2, 'coordinador'),
( 3, 'supervisor'),
( 4, 'colaborador');

--Añadimos algunos usuarios, contraseña wikinado
INSERT INTO USUARIOS
( "ID", "EMAIL", "NOMBRE", "PASSWORD" )
VALUES ( 1, 'gestor@wikinado.com', 'Gestor', '$2a$12$5fWxal38eg4ykGfOXgKUFev.0LwXySFk3/j2VunUa24O5wwPgy5pC'),
 ( 2, 'coordinador@wikinado.com', 'Coordinador','$2a$12$5fWxal38eg4ykGfOXgKUFev.0LwXySFk3/j2VunUa24O5wwPgy5pC'),
 ( 3, 'supervisor@wikinado.com', 'Supervisor','$2a$12$5fWxal38eg4ykGfOXgKUFev.0LwXySFk3/j2VunUa24O5wwPgy5pC');

 --Asignacion de rol a cada usuario creado
INSERT INTO USUARIO_ROL
( "USUARIO_ID", "ROL_ID" )
VALUES ( 1,2 ),
 ( 2,2 ),
 ( 3,3 );



INSERT INTO ARTICULOS (CONTENIDO, FECHA_CREACION, TITULO)
VALUES
    ('Contenido del artículo 1', '2023-07-01', 'Título del artículo 1'),
    ('Contenido del artículo 2', '2023-07-02', 'Título del artículo 2'),
    ('Contenido del artículo 3', '2023-07-03', 'Título del artículo 3'),
    ('Contenido del artículo 4', '2023-07-04', 'Título del artículo 4'),
    ('Contenido del artículo 5', '2023-07-05', 'Título del artículo 5'),
    ('Contenido del artículo 6', '2023-07-06', 'Título del artículo 6'),
    ('Contenido del artículo 7', '2023-07-07', 'Título del artículo 7'),
    ('Contenido del artículo 8', '2023-07-08', 'Título del artículo 8'),
    ('Contenido del artículo 9', '2023-07-09', 'Título del artículo 9'),
    ('Contenido del artículo 10', '2023-07-10', 'Título del artículo 10');
    
   
INSERT INTO WIKIS ("DESCRIPCION", "FECHA_CREACION", "NOMBRE" )
VALUES 
( 'Politica Española', '2023-01-01', 'Politica'),
( 'Deportes de Motor', '2023-02-04', 'Deportes'),
( 'Geofrafía de América', '2020-12-04', 'Geografía'),
( 'Historia de China', '2019-01-10', 'Historia'),
( 'Inteligencia Artificial', '2023-05-11', 'Informática'),
( 'Redes WAN', '2023-02-04', 'Informática'),
( 'Sistema Circulatorio', '2021-12-12', 'Medicina'),
( 'Teorema de Pitágoras', '2018-01-07', 'Matemáticas'),
( 'Teoría de Cuerdas', '2015-10-10', 'Física');
;