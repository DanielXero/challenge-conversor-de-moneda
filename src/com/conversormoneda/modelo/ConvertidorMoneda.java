package com.conversormoneda.modelo;

public class ConvertidorMoneda {
    private String monedaBase;
    private String monedaDestino;
    private double tasaConversion;

    public ConvertidorMoneda(ConvertidorMonedaApi miConvertidorMonedaApi) {
        this.monedaBase = miConvertidorMonedaApi.base_code();
        this.monedaDestino = miConvertidorMonedaApi.target_code();
        this.tasaConversion = miConvertidorMonedaApi.conversion_rate();
    }

    public String getMonedaBase() {
        return monedaBase;
    }

    public String getMonedaDestino() {
        return monedaDestino;
    }

    public double getTasaConversion() {
        return tasaConversion;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("ConvertidorMoneda{");
        sb.append("monedaBase = '").append(monedaBase).append('\'');
        sb.append(", monedaDestino = '").append(monedaDestino).append('\'');
        sb.append(", tasaConversion = ").append(tasaConversion);
        sb.append('}');
        return sb.toString();
    }
}
