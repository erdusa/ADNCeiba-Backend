select *
from reserva re
inner join carro ca on re.carrid = ca.id
inner join cliente cl on re.clieid = cl.id
where re.id = :id;