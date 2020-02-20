CREATE TABLE public.casas (
    id serial NOT NULL,
    nombre character varying NOT NULL,
    animal character varying,
    fantasma character varying,
    PRIMARY KEY (id)
);

CREATE TABLE public.rol (
    id serial NOT NULL,
    nombre character varying,
    PRIMARY KEY (id)
);

CREATE TABLE public.personajes (
    id serial NOT NULL,
    nombre character varying NOT NULL,
    varita character varying,
    id_casa integer REFERENCES public.casas (id),
    PRIMARY KEY (id)
);

CREATE TABLE poseer (
	id_personaje integer REFERENCES public.personajes(id),
	id_rol integer REFERENCES public.rol(id),
	PRIMARY KEY (id_personaje, id_rol)
);

CREATE TABLE public.mascotas (
    id serial NOT NULL,
    nombre character varying NOT NULL,
    dueno integer REFERENCES public.personajes (id),
    PRIMARY KEY (id),
    CONSTRAINT uk UNIQUE (dueno)
);