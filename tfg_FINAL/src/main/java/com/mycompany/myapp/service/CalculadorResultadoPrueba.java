package com.mycompany.myapp.service;

import com.mycompany.myapp.domain.ResultadoPrueba;

public interface CalculadorResultadoPrueba {

	public ResultadoPrueba calcular(ResultadoPrueba resultadoPrueba) throws CalculadorResultadoPruebaException;

}