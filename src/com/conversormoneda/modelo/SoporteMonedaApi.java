package com.conversormoneda.modelo;

import java.util.Map;

public record SoporteMonedaApi(Map<String, String> supported_codes) {
}
