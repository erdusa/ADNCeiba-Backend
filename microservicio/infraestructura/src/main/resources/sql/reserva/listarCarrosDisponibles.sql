with carros_reservados
as (
	select ca.carrid
	from reserva re
	inner join carro ca using (carrid)
	where carrgama = :gama
	and reseestado = 'VIGENTE'
	and (
		:fechainicial between date_trunc('day', resefechainicial) and date_trunc('day', resefechafinal)
		or  :fechafinal between date_trunc('day', resefechainicial) and date_trunc('day', resefechafinal)
		or  date_trunc('day', resefechainicial)  between :fechainicial and :fechafinal
		or  date_trunc('day', resefechafinal) between :fechainicial and :fechafinal
		)
	)
select *
from carro c
left join carros_reservados care using (carrid)
where c.carrgama = :gama
and care.carrid is null