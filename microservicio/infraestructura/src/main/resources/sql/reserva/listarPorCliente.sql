select *
from reserva re
inner join carro ca on ca.id = re.carrid
inner join cliente cl on cl.id = re.clieid
where cl.id = :idCliente
and re.estado = 'VIGENTE';