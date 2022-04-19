INSERT INTO cliente
(documento, nombre)
VALUES('8000', 'EUDIS RENE DUARTE'),
('8001', 'YAIRA QUINTERO');

INSERT INTO carro
(marca, modelo, placa, gama)
VALUES
('RENAULT', 2020, '123', 'BAJA'),
('FORD', 2022, '234', 'MEDIA'),
('CHEVROLET', 2022, '235', 'MEDIA'),
('MERCEDES', 2021, '345', 'ALTA'),
('FERRARI', 2022, '346', 'ALTA'),
('BENTLEY', 2020, '347', 'ALTA');

INSERT INTO reserva
(clieid, carrid, fechainicial, fechafinal, valor, estado)
VALUES(1, 2, '2022-04-20 07:00:00', '2022-04-21 07:00:00', 1000, 'VIGENTE'),
(1, 3, '2022-04-22 07:00:00', '2022-04-23 07:00:000', 1000, 'VIGENTE'),
(2, 4, '2022-04-22 07:00:00', '2022-04-25 07:00:00', 1000, 'VIGENTE');


