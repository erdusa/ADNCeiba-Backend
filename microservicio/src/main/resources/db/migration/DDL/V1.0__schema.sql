create sequence IF NOT EXISTS cliente_id_seq;
CREATE TABLE IF NOT EXISTS cliente (
	id int4 NOT NULL default nextval ('cliente_id_seq'),
	documento varchar NOT NULL,
	nombre varchar NOT NULL,
	PRIMARY KEY (id)
);

create sequence IF NOT EXISTS carro_id_seq;
CREATE TABLE IF NOT EXISTS carro (
	id int4 NOT NULL DEFAULT nextval('carro_id_seq'),
	marca varchar NOT NULL,
	modelo int2 NOT NULL,
	placa varchar NOT NULL,
	gama varchar NOT NULL,
	CONSTRAINT carro_pk PRIMARY KEY (id)
);

create sequence IF NOT EXISTS reserva_id_seq;
CREATE TABLE IF NOT EXISTS reserva (
	id int4 NOT NULL DEFAULT nextval('reserva_id_seq'),
	clieid int4 NOT NULL,
	carrid int4 NOT NULL,
	fechainicial timestamp NOT NULL,
	fechafinal timestamp NOT NULL,
	valor numeric(12, 2) NOT NULL,
	estado varchar NOT NULL,
	CONSTRAINT reserva_pk PRIMARY KEY (id),
    FOREIGN KEY (carrid)
        REFERENCES carro (id),
    FOREIGN KEY (clieid)
        REFERENCES cliente (id)
);