package com.conversormoneda.persistencia;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class GeneradorArchivo {
    private static final String NOMBRE_ARCHIVO = "conversiones.txt";

    public static void registrarArchivo(String detalleConversion) {
        try {
            FileWriter escritura = new FileWriter(NOMBRE_ARCHIVO, true);
            escritura.write(detalleConversion);
            escritura.close();
        } catch (IOException e) {
            System.out.println("Ocurrio un error al registrar la conversion: " + e.getMessage());
        }
    }

    public static boolean esArchivoVacio() {
        try {
            FileReader lector = new FileReader(NOMBRE_ARCHIVO);
            BufferedReader lectura = new BufferedReader(lector);

            return lectura.readLine() == null;
        }catch (IOException e) {
            return true;
        }
    }

    public static void leerArchivo() {

        try {
            FileReader lector = new FileReader(NOMBRE_ARCHIVO);
            BufferedReader lectura = new BufferedReader(lector);
            String linea;
            linea = lectura.readLine();
            while (linea != null) {
                System.out.println(linea);
                linea = lectura.readLine();
            }
            lector.close();
            lectura.close();
        }catch (IOException e) {
            System.out.println("Error al leer el archivo: " + e.getMessage());
        }
    }
}
