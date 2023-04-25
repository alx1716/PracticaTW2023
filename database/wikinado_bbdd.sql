-- Tabla usuarios
CREATE TABLE usuario (
    id_usuario INT NOT NULL PRIMARY KEY,
    nombre_usuario VARCHAR(255) NOT NULL,
    contraseña VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL,
    fecha_registro TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Tabla para las Wikis
CREATE TABLE wiki (
    id_wiki INT NOT NULL PRIMARY KEY,
    nombre_wiki VARCHAR(255) NOT NULL,
    descripcion_wiki TEXT,
    id_creador INT NOT NULL,
    fecha_creacion TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (id_creador) REFERENCES usuario(id_usuario)
);

-- Tabla para articulos
CREATE TABLE articulo (
    id_articulo INT NOT NULL PRIMARY KEY,
    titulo_articulo VARCHAR(255) NOT NULL,
    contenido_articulo TEXT,
    id_wiki INT NOT NULL,
    fecha_creacion TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (id_wiki) REFERENCES wiki(id_wiki)
);

-- Tabla de Roles
CREATE TABLE roles (
    id_rol INT NOT NULL PRIMARY KEY,
    nombre_rol VARCHAR(255) NOT NULL,
    descripcion_rol TEXT
);

-- Tabla relacion usuarios y roles
CREATE TABLE usuario_rol (
    id_usuario_rol INT NOT NULL PRIMARY KEY,
    id_usuario INT NOT NULL,
    id_rol INT NOT NULL,
    id_wiki INT,
    fecha_asignacion TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (id_usuario) REFERENCES usuario(id_usuario),
    FOREIGN KEY (id_rol) REFERENCES roles(id_rol),
    FOREIGN KEY (id_wiki) REFERENCES wiki(id_wiki)
);

-- Tabla para revisiones de artículos
CREATE TABLE revision (
    id_revision INT NOT NULL PRIMARY KEY,
    id_articulo INT NOT NULL,
    id_usuario INT NOT NULL,
    contenido_articulo TEXT,
    estado_revision ENUM('PENDIENTE', 'ACEPTADA', 'RECHAZADA') DEFAULT 'PENDIENTE',
    fecha_creacion TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (id_articulo) REFERENCES articulo(id_articulo),
    FOREIGN KEY (id_usuario) REFERENCES usuario(id_usuario)
);

-- Tabla solicitudes de rol
CREATE TABLE solicitud_rol (
    id_solicitud_rol INT NOT NULL PRIMARY KEY,
    id_usuario INT NOT NULL,
    id_rol INT NOT NULL,
    motivo TEXT,
    fecha_solicitud TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (id_usuario) REFERENCES usuario(id_usuario),
    FOREIGN KEY (id_rol) REFERENCES roles(id_rol)
);