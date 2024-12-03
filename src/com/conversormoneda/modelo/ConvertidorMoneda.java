package com.conversormoneda.modelo;

public class ConvertidorMoneda {
    private String monedaBase;
    private String monedaDestino;
    private double tasaConversion;

    private double valor;

    public ConvertidorMoneda(ConvertidorMonedaApi miConvertidorMonedaApi, double valor) {
        this.monedaBase = miConvertidorMonedaApi.base_code();
        this.monedaDestino = miConvertidorMonedaApi.target_code();
        this.tasaConversion = miConvertidorMonedaApi.conversion_rate();
        this.valor = valor;
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

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("ConvertidorMoneda {");
        sb.append("monedaBase = '").append(monedaBase).append('\'');
        sb.append(", monedaDestino = '").append(monedaDestino).append('\'');
        sb.append(", tasaConversion = ").append(tasaConversion);
        sb.append(", valor = ").append(valor);
        sb.append('}');
        return sb.toString();
    }
}
