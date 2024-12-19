package com.conversormoneda.modelo;

import java.util.Map;

public class ConvertidorMoneda {
    private String monedaBase;
    private Map<String, Double> tasasConversion;


    public ConvertidorMoneda(ConvertidorMonedaApi miConvertidorMonedaApi) {
        this.monedaBase = miConvertidorMonedaApi.base_code();
        this.tasasConversion = miConvertidorMonedaApi.conversion_rates();

    }

    public String getMonedaBase() {
        return monedaBase;
    }

    public Map<String, Double> getTasasConversion() {
        return tasasConversion;
    }

    public double calcularTasaCambio(String codigoMoneda, double valor) {
        return this.tasasConversion.get(codigoMoneda) * valor;
    }
}
