select re.id, re.fechainicial, re.fechafinal, ca.marca, ca.modelo, ca.placa, ca.gama, re.valor
from reserva re
inner join carro ca on ca.id = re.carrid
inner join cliente cl on cl.id = re.clieid
where cl.id = :idCliente
and re.estado = 'VIGENTE'
and re.fechafinal >= now()