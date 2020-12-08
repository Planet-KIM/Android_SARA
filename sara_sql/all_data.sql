select fmu.id, fmu.fmuname, input.parameter,  input.unit, input.value, output.parameter, output.unit, output.value
from fmu, input, output
where  fmu.id  = input.fmuid = output.fmuid