create sequence IF NOT EXISTS cliente_seq;
CREATE TABLE IF NOT EXISTS cliente (
	clieid int4 NOT NULL default nextval ('cliente_seq'),
	cliedocumento varchar NOT NULL,
	clienombre varchar NOT NULL,
	PRIMARY KEY (clieid)
);

create sequence IF NOT EXISTS carro_id_seq;
CREATE TABLE IF NOT EXISTS carro (
	carrid int4 NOT NULL DEFAULT nextval('carro_id_seq'),
	carrmarca varchar NOT NULL,
	carrmodelo int2 NOT NULL,
	carrplaca varchar NOT NULL,
	carrgama varchar NOT NULL,
	CONSTRAINT carro_pk PRIMARY KEY (carrid)
);

create sequence IF NOT EXISTS reserva_id_seq;
CREATE TABLE IF NOT EXISTS reserva (
	reseid int4 NOT NULL DEFAULT nextval('reserva_id_seq'),
	clieid int4 NOT NULL,
	carrid int4 NOT NULL,
	resefechainicial timestamp NOT NULL,
	resefechafinal timestamp NOT NULL,
	resevalor numeric(12, 2) NOT NULL,
	reseestado varchar NOT NULL,
	CONSTRAINT reserva_pk PRIMARY KEY (reseid),
    FOREIGN KEY (carrid)
        REFERENCES carro (carrid),
    FOREIGN KEY (clieid)
        REFERENCES cliente (clieid)
);