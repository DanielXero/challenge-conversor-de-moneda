package com.conversormoneda.modelo;

public record ConvertidorMonedaApi(String base_code, String target_code, double conversion_rate) {
}
