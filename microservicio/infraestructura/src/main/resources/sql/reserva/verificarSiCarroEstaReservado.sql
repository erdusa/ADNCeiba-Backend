select distinct 1
from reserva re
where carrid = :idCarro
and reseestado = 'VIGENTE'
and (
:fechainicial between date_trunc('day', resefechainicial) and date_trunc('day', resefechafinal)
or  :fechafinal between date_trunc('day', resefechainicial) and date_trunc('day', resefechafinal)
or  date_trunc('day', resefechainicial)  between :fechainicial and :fechafinal
or  date_trunc('day', resefechafinal) between :fechainicial and :fechafinal
)