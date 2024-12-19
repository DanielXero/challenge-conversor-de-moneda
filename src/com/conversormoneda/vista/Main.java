package com.conversormoneda.vista;

import com.conversormoneda.controlador.ConvertidorMonedaController;
import com.conversormoneda.controlador.SoporteMonedaController;
import com.conversormoneda.modelo.ConvertidorMoneda;
import com.conversormoneda.modelo.SoporteMoneda;
import com.conversormoneda.persistencia.GeneradorArchivo;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner teclado = new Scanner(System.in);
        ConvertidorMonedaController consultaConvertidor = new ConvertidorMonedaController();
        SoporteMonedaController consultaSoporteMoneda = new SoporteMonedaController();
        int opcion;
        boolean isFinalizado = false, esOpcionInvalida = false;
        double valor = 0.0, resultadoConversion = 0.0;
        String msgConversion = "";


        String apikey = "33fa19c78154d3e3748bf0a8";
        String monedaOrigen = "";
        String monedaDestino = "";


        ConvertidorMoneda convertidorMoneda;
        SoporteMoneda soporteTipoMoneda;


        while (!isFinalizado) {

            try {
                System.out.println("""
                                        
                    *******************************************************
                    Sea bienvenido/a al Conversor de moneda =]
                                        
                    1) Dólar =>> Peso argentino
                    2) Peso argentino =>> Dólar
                    3) Dólar =>> Real Brasileño
                    4) Real Brasileño =>> Dólar
                    5) Dólar =>> Peso colombiano
                    6) Peso colombiano =>> Dólar
                    7) Dólar =>> Boliviano boliviano
                    8) Peso chileno =>> Dólar
                    9) Dólar =>> Peso chileno
                    10) Peso chileno ==> Dólar
                    11) Mostrar Historial de Conversiones
                    12) Más Opciones de soporte de monedas
                    13) Salir        
                    """);
                System.out.print("Elija una opción válida: ");
                opcion = Integer.parseInt(teclado.nextLine());
                System.out.println("*******************************************************\n");

                switch (opcion) {
                    case 1 -> {
                        monedaOrigen = "USD";
                        monedaDestino = "ARS";
                    }
                    case 2 -> {
                        monedaOrigen = "ARS";
                        monedaDestino = "USD";
                    }
                    case 3 -> {
                        monedaOrigen = "USD";
                        monedaDestino = "BRL";
                    }
                    case 4 -> {
                        monedaOrigen = "BRL";
                        monedaDestino = "USD";
                    }
                    case 5 -> {
                        monedaOrigen = "USD";
                        monedaDestino = "COP";
                    }
                    case 6 -> {
                        monedaOrigen = "COP";
                        monedaDestino = "USD";
                    }
                    case 7 -> {
                        monedaOrigen = "USD";
                        monedaDestino = "BOB";
                    }
                    case 8 -> {
                        monedaOrigen = "BOB";
                        monedaDestino = "USD";
                    }
                    case 9 -> {
                        monedaOrigen = "USD";
                        monedaDestino = "CLP";
                    }
                    case 10 -> {
                        monedaOrigen = "CLP";
                        monedaDestino = "USD";
                    }
                    case 11 -> {
                        System.out.println("\t\tHistorial de Conversiones\n");
                        if (GeneradorArchivo.esArchivoVacio()) {
                            System.out.println("Historial de Conversiones Vacio!!!");
                        } else {
                            GeneradorArchivo.leerArchivo();
                        }

                    }
                    case 12 -> {
                        System.out.println("\t\t Todos los tipos de Soporte de Monedas\n");
                        soporteTipoMoneda = new SoporteMoneda(consultaSoporteMoneda.obtenerTiposCodigo(apikey));
                        soporteTipoMoneda.mostrarCodigos();

                        do {
                            System.out.print("\nIngrese el codigo correspondiente a la moneda de origen: ");
                            monedaOrigen = teclado.nextLine().toUpperCase();

                            System.out.print("\nIngrese el codigo correspondiente a la moneda de destino: ");
                            monedaDestino = teclado.nextLine().toUpperCase();

                            if (!soporteTipoMoneda.existeCodigo(monedaOrigen) || !soporteTipoMoneda.existeCodigo(monedaDestino)){
                                System.out.println("\nCódigo de Monedas Incorrectos. Por favor ingrese nuevamente los datos!!!");
                            }
                        }while (!soporteTipoMoneda.existeCodigo(monedaOrigen) || !soporteTipoMoneda.existeCodigo(monedaDestino));


                    }
                    case 13 -> {
                        isFinalizado = true;
                    }
                    default -> {
                        System.out.println("Opción Invalida!!!");
                        System.out.println("Por favor Ingrese una nueva Opción!!!");
                        esOpcionInvalida = true;
                    }
                }

                if (!isFinalizado && !esOpcionInvalida && opcion != 11) {
                    System.out.print("\nIngrese el valor que desea convertir: ");
                    valor = Double.parseDouble(teclado.nextLine());

                    convertidorMoneda = new ConvertidorMoneda(consultaConvertidor.obtenerConversionMoneda(apikey, monedaOrigen));
                    resultadoConversion = convertidorMoneda.calcularTasaCambio(monedaDestino, valor);
                    LocalDateTime fechaActual = LocalDateTime.now();
                    DateTimeFormatter formatoFecha = DateTimeFormatter.ofPattern("'Fecha: 'dd/MM/yyyy ' - Hora: 'HH:mm:ss");

                    String fechaFormateada = fechaActual.format(formatoFecha);

                    System.out.println("\nEl valor " + valor + " ["+monedaOrigen+"] corresponde al valor final " +
                            "de =>>> " + resultadoConversion + " ["+monedaDestino +"]");

                    msgConversion = """
                            Detalle Conversión - %s
                            Moneda Origen [%s] a Moneda Destino [%s]
                            El valor %.2f corresponde al valor final de =>>> %.2f
                            
                            """.formatted(fechaFormateada, monedaOrigen, monedaDestino, valor, resultadoConversion);

                    GeneradorArchivo.registrarArchivo(msgConversion);


                }
            } catch (NumberFormatException e) {
                System.out.println("Error dato invalido: " + e.getMessage());
                System.out.println("Ingrese nuevamente los datos");
            }
        }
        System.out.println("\nMuchas Gracias Por utilizar el conversor de Moneda!!!");
    }
}
