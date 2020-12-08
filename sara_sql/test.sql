INSERT INTO fmu(fmuname)
values (
	'DN8_Evaporator_Hanon_45_4_Umode'				
);

INSERT INTO input(parameter, unit, value, fmuid)
values(
	'[input_depth], [input_height], [input_n_tube1], [input_n_tube2], [input_n_tube3], [input_n_tube4], [input_h_flattube],[input_z_flattubes], [input_n_channels_internal], [input_dp],
     [input_fins],	[input_m_flow_air], [input_T_air], [input_h_in], [input_p_out],	[input_dT_sat]',
     'm, m, ea, ea, ea, ea, m, m, ea, m, FPDM, kg/s, K, J/kg, Pa, K',
     '0.045, 0.215, 18, 19, 19, 18, 0.0018, 0.0074, 1, 0.0001, 60, 0.1, 300.15, 277690, 297458, 5',
     1
);

INSERT INTO output(parameter, unit, value, fmuid)
values (
	'[output_Q] [output_mdot], [output_dP_ref], [output_dP_air], [output_hout], [output_SH]',
    'kW, kg/h, kPa, kPa, kJ/kg, K',
    '100, 200, 300, 400, 500, 600',
    1
);
