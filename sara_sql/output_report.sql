SELECT fmu.id, fmu.fmuname, output.parameter, output.value, output.unit
from fmu, output
where fmu.id LIKE output.fmuid