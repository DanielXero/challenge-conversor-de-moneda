package com.conversormoneda.modelo;

import java.util.Map;

public class SoporteMoneda {
    private Map<String, String> codigos;

    public SoporteMoneda(SoporteMonedaApi miSoporteMonedaApi) {
        this.codigos = miSoporteMonedaApi.supported_codes();
    }

    public Map<String, String> getCodigos() {
        return this.codigos;
    }

    public boolean existeCodigo(String codigo) {
        return this.codigos.containsKey(codigo);
    }

    public void mostrarCodigos() {
        int contador = 0;

        for (String codigo : this.codigos.keySet()) {
            System.out.println(++contador + ") Código Moneda: [" + codigo + "] - " + this.codigos.get(codigo));
        }
    }
}
