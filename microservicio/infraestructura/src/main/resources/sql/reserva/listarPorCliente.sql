select *
from reserva re
inner join carro ca using (carrid)
inner join cliente cl using (clieid)
where cl.clieid = :idCliente
and reseestado = 'VIGENTE';