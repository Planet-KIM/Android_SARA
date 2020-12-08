SELECT fmu.id, fmu.fmuname, input.parameter, input.value, input.unit
from fmu, input
where fmu.id LIKE input.fmuid