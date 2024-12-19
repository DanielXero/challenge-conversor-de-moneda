package com.conversormoneda.modelo;

import java.util.Map;

public record ConvertidorMonedaApi(String base_code, Map<String, Double> conversion_rates) {
}
