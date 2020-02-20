CREATE TABLE public.equipos
(
    codigo integer NOT NULL,
    nombre character varying,
    ubicacion character varying,
    PRIMARY KEY (codigo)
);

CREATE TABLE public.personas
(
    codigo integer NOT NULL,
    nombre character varying NOT NULL,
    password character varying NOT NULL,
    email character varying,
    PRIMARY KEY (codigo)
);

CREATE TABLE public.avisos
(
    fechayhora character varying NOT NULL,
    descripcion character varying NOT NULL,
    cod_persona integer NOT NULL,
    cod_equipo integer NOT NULL,
    fechayhora_resolucion character varying,
    detalles_resolucion character varying,
    PRIMARY KEY (cod_persona, cod_equipo, fechayhora),
    CONSTRAINT fk_personas FOREIGN KEY (cod_persona)
        REFERENCES public.personas (codigo) MATCH SIMPLE
        ON UPDATE CASCADE
        ON DELETE CASCADE,
    CONSTRAINT fk_equipos FOREIGN KEY (cod_equipo)
        REFERENCES public.equipos (codigo) MATCH SIMPLE
        ON UPDATE CASCADE
        ON DELETE CASCADE
);

insert into personas values(1, 'admin', '7890'), (2, 'maria', '1234'),
(3, 'lydia', '1234'), (4, 'gonza', '1234');
insert into equipos values(1, 'Lenovo Legion Y530', 'San Vicente'), 
(2, 'OnePlus 3T', 'Alicante'), (3, 'Xiaomi Redmi Note 5', 'Monforte Del Cid');
insert into avisos values('28/01/2020 00:35', 'Fallo de conexión del puerto de carga', 2, 1),
('28/01/2020 00:36', 'No enciende después de haberse mojado', 3, 3),
('28/01/2020 00:37', 'Rotura de pantalla', 4, 2);