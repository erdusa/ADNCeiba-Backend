with carro_reservado as (
    select re.carrid
    from reserva re
    where re.carrid = :idCarro
    and re.estado = 'VIGENTE'
    and (
        :fechainicial between date_trunc('day', re.fechainicial) and date_trunc('day', re.fechafinal)
        or  :fechafinal between date_trunc('day', re.fechainicial) and date_trunc('day', re.fechafinal)
        or  date_trunc('day', re.fechainicial)  between :fechainicial and :fechafinal
        or  date_trunc('day', re.fechafinal) between :fechainicial and :fechafinal
    )
)
select ca.*
from carro ca
left join carro_reservado cr on cr.carrid = ca.id
where ca.id = :idCarro
and cr.carrid is null