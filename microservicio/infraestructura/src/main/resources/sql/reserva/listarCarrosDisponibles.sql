with carros_reservados
as (
	select ca.id
	from reserva re
	inner join carro ca on re.carrid = ca.id
	where ca.gama = :gama
	and re.estado = 'VIGENTE'
	and (
		:fechainicial between date_trunc('day', re.fechainicial) and date_trunc('day', re.fechafinal)
		or  :fechafinal between date_trunc('day', re.fechainicial) and date_trunc('day', re.fechafinal)
		or  date_trunc('day', re.fechainicial)  between :fechainicial and :fechafinal
		or  date_trunc('day', re.fechafinal) between :fechainicial and :fechafinal
		)
	)
select c.*, :fechainicial fechainicial, :fechafinal fechafinal
from carro c
left join carros_reservados care using(id)
where c.gama = :gama
and care.id is null