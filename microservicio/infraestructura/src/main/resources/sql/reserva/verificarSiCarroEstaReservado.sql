select count(1)
from reserva re
where re.carrid = :idCarro
and re.estado = 'VIGENTE'
and (
    :fechainicial between date_trunc('day', re.fechainicial) and date_trunc('day', re.fechafinal)
    or  :fechafinal between date_trunc('day', re.fechainicial) and date_trunc('day', re.fechafinal)
    or  date_trunc('day', re.fechainicial)  between :fechainicial and :fechafinal
    or  date_trunc('day', re.fechafinal) between :fechainicial and :fechafinal
)
