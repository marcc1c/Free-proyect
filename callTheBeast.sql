CREATE DATABASE IF NOT EXISTS bbdd_call_the_best

USE bbdd_call_the_best;

DROP TABLE IF EXISTS LOOT_CALIDAD;
DROP TABLE IF EXISTS LOGRO_USUARIO;
DROP TABLE IF EXISTS LOGRO_META;
DROP TABLE IF EXISTS ITEM_USUARIO;
DROP TABLE IF EXISTS PROGRESO_COMBATE;
DROP TABLE IF EXISTS INVOCACION;
DROP TABLE IF EXISTS LOGRO;
DROP TABLE IF EXISTS ITEM;
DROP TABLE IF EXISTS USUARIO;

CREATE TABLE USUARIO (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    contrasena_hash VARCHAR(255) NOT NULL
);

CREATE TABLE ITEM (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    descripcion VARCHAR(255) NOT NULL,
    rareza VARCHAR(50) NOT NULL
);

CREATE TABLE INVOCACION (
    id INT AUTO_INCREMENT PRIMARY KEY,
    id_usuario INT NOT NULL,
    id_en_partida INT NOT NULL,

    nivel INT NOT NULL,
    ascension INT NOT NULL,

    raza VARCHAR(50) NOT NULL,
    rareza VARCHAR(50) NOT NULL,

    experiencia DOUBLE NOT NULL,
    experiencia_maxima DOUBLE NOT NULL,

    vida DOUBLE NOT NULL,
    vida_maxima DOUBLE NOT NULL,
    ataque DOUBLE NOT NULL,
    defensa DOUBLE NOT NULL,

    prob_critico DOUBLE NOT NULL,
    dano_critico DOUBLE NOT NULL,

    multi_vida DOUBLE NOT NULL,
    multi_ataque DOUBLE NOT NULL,
    multi_defensa DOUBLE NOT NULL,
    multi_prob_critico DOUBLE NOT NULL,
    multi_dano_critico DOUBLE NOT NULL,
    multi_experiencia DOUBLE NOT NULL,

    FOREIGN KEY (id_usuario) REFERENCES USUARIO(id)
        ON DELETE CASCADE
        ON UPDATE CASCADE
);

CREATE TABLE ITEM_USUARIO (
    id_usuario INT NOT NULL,
    id_item INT NOT NULL,
    cantidad INT NOT NULL,

    PRIMARY KEY (id_usuario, id_item),

    FOREIGN KEY (id_usuario) REFERENCES USUARIO(id)
        ON DELETE CASCADE
        ON UPDATE CASCADE,

    FOREIGN KEY (id_item) REFERENCES ITEM(id)
        ON DELETE CASCADE
        ON UPDATE CASCADE
);

CREATE TABLE LOGRO (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    descripcion VARCHAR(255) NOT NULL
);

CREATE TABLE LOGRO_META (
    id INT AUTO_INCREMENT PRIMARY KEY,
    id_logro INT NOT NULL,
    valor_meta INT NOT NULL,
    id_item_recompensa INT NULL,
    cantidad_recompensa INT NOT NULL,

    FOREIGN KEY (id_logro) REFERENCES LOGRO(id)
        ON DELETE CASCADE
        ON UPDATE CASCADE,

    FOREIGN KEY (id_item_recompensa) REFERENCES ITEM(id)
        ON DELETE SET NULL
        ON UPDATE CASCADE
);

CREATE TABLE LOGRO_USUARIO (
    id_usuario INT NOT NULL,
    id_logro INT NOT NULL,
    progreso INT NOT NULL,

    PRIMARY KEY (id_usuario, id_logro),

    FOREIGN KEY (id_usuario) REFERENCES USUARIO(id)
        ON DELETE CASCADE
        ON UPDATE CASCADE,

    FOREIGN KEY (id_logro) REFERENCES LOGRO(id)
        ON DELETE CASCADE
        ON UPDATE CASCADE
);

CREATE TABLE PROGRESO_COMBATE (
    id_usuario INT PRIMARY KEY,
    piso_torre_infinita INT NOT NULL,
    nivel_campana INT NOT NULL,
    piso_campana INT NOT NULL,

    FOREIGN KEY (id_usuario) REFERENCES USUARIO(id)
        ON DELETE CASCADE
        ON UPDATE CASCADE
);

CREATE TABLE LOOT_CALIDAD (
    id INT AUTO_INCREMENT PRIMARY KEY,
    id_item INT NOT NULL,
    calidad_enemigo VARCHAR(50) NOT NULL,
    porcentaje DOUBLE NOT NULL,
    cantidad_minima INT NOT NULL,
    cantidad_maxima INT NOT NULL,

    FOREIGN KEY (id_item) REFERENCES ITEM(id)
        ON DELETE CASCADE
        ON UPDATE CASCADE
);

INSERT INTO ITEM (nombre, descripcion, rareza) VALUES
('Oro', 'Moneda basica del juego; sirve para compras, mejoras, costes de menu y progresion general.', 'Comun'),

('Polvo Comun', 'Material basico usado para mejoras iniciales y fabricacion sencilla.', 'Comun'),
('Polvo Natural', 'Polvo de energia natural, util para mejoras equilibradas y evolucion temprana.', 'Natural'),
('Polvo Raro', 'Recurso poco frecuente con energia concentrada para mejoras avanzadas.', 'Raro'),
('Polvo Unico', 'Polvo especial de gran valor, reservado para objetos o invocaciones exclusivas.', 'Unico'),
('Polvo Extinto', 'Fragmento antiguo con poder olvidado, usado en mejoras de alto nivel.', 'Extinto'),
('Polvo Primordial', 'Esencia original extremadamente poderosa, destinada a mejoras legendarias.', 'Primordial'),

('Orbe Comun', 'Orbe basico que canaliza energia comun para invocaciones o mejoras simples.', 'Comun'),
('Orbe Natural', 'Orbe vinculado a la esencia natural, ideal para progresion estable.', 'Natural'),
('Orbe Raro', 'Orbe de energia rara, aumenta el potencial de mejoras superiores.', 'Raro'),
('Orbe Unico', 'Orbe singular con poder especial para desbloqueos exclusivos.', 'Unico'),
('Orbe Extinto', 'Orbe ancestral que contiene energia perdida de epocas antiguas.', 'Extinto'),
('Orbe Primordial', 'Orbe supremo con energia primordial, reservado para el contenido mas poderoso.', 'Primordial'),

('Frasco de Experiencia', 'Consumible basico que concede una pequena cantidad de experiencia.', 'Comun'),
('Cristal de Experiencia', 'Consumible valioso que concede una cantidad notable de experiencia.', 'Raro'),

('Fragmento de Ascendencia', 'Material inicial para realizar ascensiones y desbloquear nuevos limites de nivel.', 'Raro'),
('Piedra de Ascendencia', 'Material avanzado para ascensiones de mayor nivel o coste superior.', 'Unico'),
('Nucleo de Ascendencia', 'Material muy escaso para ascensiones altas y desbloqueos avanzados.', 'Extinto'),

('Nucleo de Trascendencia', 'Objeto final de rareza primordial usado para iniciar o completar la trascendencia.', 'Primordial');

INSERT INTO LOOT_CALIDAD (
    id_item,
    calidad_enemigo,
    porcentaje,
    cantidad_minima,
    cantidad_maxima
) VALUES

((SELECT id FROM ITEM WHERE nombre = 'Oro'), 'Comun', 100, 20, 30),
((SELECT id FROM ITEM WHERE nombre = 'Oro'), 'Natural', 100, 30, 40),
((SELECT id FROM ITEM WHERE nombre = 'Oro'), 'Raro', 100, 40, 50),
((SELECT id FROM ITEM WHERE nombre = 'Oro'), 'Unico', 100, 50, 60),
((SELECT id FROM ITEM WHERE nombre = 'Oro'), 'Extinto', 100, 60, 70),
((SELECT id FROM ITEM WHERE nombre = 'Oro'), 'Primordial', 100, 70, 80),

((SELECT id FROM ITEM WHERE nombre = 'Polvo Comun'), 'Comun', 100, 20, 40),
((SELECT id FROM ITEM WHERE nombre = 'Polvo Natural'), 'Natural', 100, 20, 40),
((SELECT id FROM ITEM WHERE nombre = 'Polvo Raro'), 'Raro', 100, 20, 40),
((SELECT id FROM ITEM WHERE nombre = 'Polvo Unico'), 'Unico', 100, 20, 40),
((SELECT id FROM ITEM WHERE nombre = 'Polvo Extinto'), 'Extinto', 100, 20, 40),
((SELECT id FROM ITEM WHERE nombre = 'Polvo Primordial'), 'Primordial', 100, 20, 40),

((SELECT id FROM ITEM WHERE nombre = 'Orbe Comun'), 'Comun', 10, 1, 1),
((SELECT id FROM ITEM WHERE nombre = 'Orbe Natural'), 'Natural', 10, 1, 1),
((SELECT id FROM ITEM WHERE nombre = 'Orbe Raro'), 'Raro', 10, 1, 1),
((SELECT id FROM ITEM WHERE nombre = 'Orbe Unico'), 'Unico', 10, 1, 1),
((SELECT id FROM ITEM WHERE nombre = 'Orbe Extinto'), 'Extinto', 10, 1, 1),
((SELECT id FROM ITEM WHERE nombre = 'Orbe Primordial'), 'Primordial', 10, 1, 1),

((SELECT id FROM ITEM WHERE nombre = 'Frasco de Experiencia'), 'Comun', 10, 1, 1),
((SELECT id FROM ITEM WHERE nombre = 'Frasco de Experiencia'), 'Natural', 10, 1, 1),

((SELECT id FROM ITEM WHERE nombre = 'Cristal de Experiencia'), 'Raro', 5, 1, 2),
((SELECT id FROM ITEM WHERE nombre = 'Cristal de Experiencia'), 'Unico', 5, 1, 2),

((SELECT id FROM ITEM WHERE nombre = 'Fragmento de Ascendencia'), 'Comun', 5, 1, 2),
((SELECT id FROM ITEM WHERE nombre = 'Fragmento de Ascendencia'), 'Natural', 5, 2, 3),
((SELECT id FROM ITEM WHERE nombre = 'Fragmento de Ascendencia'), 'Raro', 5, 3, 4),
((SELECT id FROM ITEM WHERE nombre = 'Fragmento de Ascendencia'), 'Unico', 5, 4, 5),
((SELECT id FROM ITEM WHERE nombre = 'Fragmento de Ascendencia'), 'Extinto', 5, 5, 6),
((SELECT id FROM ITEM WHERE nombre = 'Fragmento de Ascendencia'), 'Primordial', 5, 6, 7),

((SELECT id FROM ITEM WHERE nombre = 'Piedra de Ascendencia'), 'Unico', 5, 1, 2),
((SELECT id FROM ITEM WHERE nombre = 'Piedra de Ascendencia'), 'Extinto', 5, 2, 3),
((SELECT id FROM ITEM WHERE nombre = 'Piedra de Ascendencia'), 'Primordial', 5, 3, 4),

((SELECT id FROM ITEM WHERE nombre = 'Nucleo de Ascendencia'), 'Extinto', 5, 1, 2),
((SELECT id FROM ITEM WHERE nombre = 'Nucleo de Ascendencia'), 'Primordial', 5, 1, 3),

((SELECT id FROM ITEM WHERE nombre = 'Nucleo de Trascendencia'), 'Primordial', 0.5, 1, 1);

INSERT INTO USUARIO (nombre, contrasena_hash)
VALUES ('test', '1234');

INSERT INTO PROGRESO_COMBATE (
    id_usuario,
    piso_torre_infinita,
    nivel_campana,
    piso_campana
) VALUES (
    1,
    1,
    1,
    1
);

INSERT INTO ITEM_USUARIO (id_usuario, id_item, cantidad)
SELECT 1, ITEM.id, 0
FROM ITEM;

INSERT INTO INVOCACION (
    id_usuario,
    id_en_partida,
    nivel,
    ascension,
    raza,
    rareza,
    experiencia,
    experiencia_maxima,
    vida,
    vida_maxima,
    ataque,
    defensa,
    prob_critico,
    dano_critico,
    multi_vida,
    multi_ataque,
    multi_defensa,
    multi_prob_critico,
    multi_dano_critico,
    multi_experiencia
) VALUES (
    1,
    0,
    1,
    0,
    'Felino',
    'Primordial',
    0,
    10,
    120,
    120,
    35,
    15,
    12.5,
    150.0,
    1.2,
    1.5,
    1.0,
    1.3,
    1.4,
    1.0
);
