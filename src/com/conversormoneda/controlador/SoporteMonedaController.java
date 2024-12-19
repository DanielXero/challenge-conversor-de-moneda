package com.conversormoneda.controlador;

import com.conversormoneda.modelo.SoporteMonedaApi;
import com.google.gson.Gson;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class SoporteMonedaController {

    public SoporteMonedaApi obtenerTiposCodigo(String apiKey) {
        HttpClient client = HttpClient.newHttpClient();
        URI direccion = URI.create("https://v6.exchangerate-api.com/v6/"+apiKey+"/codes");

        HttpRequest request = HttpRequest.newBuilder()
                .uri(direccion)
                .build();


        try {
            HttpResponse<String> response = client
                    .send(request, HttpResponse.BodyHandlers.ofString());
            return new Gson().fromJson(response.body(), SoporteMonedaApi.class);
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException("No se pudo obtener los tipos de codigos soportados!");
        }

    }
}
