package benchmark;

import java.io.PrintWriter;
import java.io.FileWriter;
import java.io.File;
import algorithms.*;
import utils.GeneradorDatos;

public class Medidor {
    public void ejecutarPruebas() {
        File folder = new File("src/resultados");
        if (!folder.exists()) folder.mkdirs();

        try (PrintWriter pw = new PrintWriter(new FileWriter("src/resultados/tiempos.csv"))) {
            pw.println("Algoritmo,Version,n,Ej_1,Ej_2,Ej_3,Ej_4,Ej_5,PROMEDIO");

            Factorial fact = new Factorial();
            Fibonacci fib = new Fibonacci();
            BusquedaLineal busq = new BusquedaLineal();
            OrdenamientoBurbuja burb = new OrdenamientoBurbuja();

            // 1. Pruebas Factorial y Fibonacci (n=5 a n=30) 
            int[] nsPequenos = {5, 10, 15, 20, 25, 30};
            for (int n : nsPequenos) {
                registrarDetallado(pw, "A1 - Factorial", "Iterativo", n, () -> fact.calcularIterativo(n));
                registrarDetallado(pw, "A1 - Factorial", "Recursivo", n, () -> fact.calcularRecursivo(n));
                registrarDetallado(pw, "A2 - Fibonacci", "Iterativo", n, () -> fib.calcularIterativo(n));
                registrarDetallado(pw, "A2 - Fibonacci", "Recursivo", n, () -> fib.calcularRecursivo(n));
            }

            // 2. Pruebas Búsqueda y Burbuja (n=100 a n=10000) 
            int[] nsGrandes = {100, 500, 1000, 5000, 10000};
            for (int n : nsGrandes) {
                int[] data = GeneradorDatos.generarArreglo(n);
                
                registrarDetallado(pw, "A3 - Busqueda", "Iterativo", n, () -> busq.buscarIterativo(data, -1));
                registrarDetallado(pw, "A3 - Busqueda", "Recursivo", n, () -> {
                    try { busq.buscarRecursivo(data, -1, 0); } 
                    catch (StackOverflowError e) {} 
                });
                
                registrarDetallado(pw, "A4 - Burbuja", "Iterativo", n, () -> burb.ordenarIterativo(data.clone()));
                if (n <= 1000) { // Límite para evitar fallos por profundidad de recursión 
                    registrarDetallado(pw, "A4 - Burbuja", "Recursivo", n, () -> burb.ordenarRecursivo(data.clone(), n));
                }
            }
            System.out.println("Archivo tiempos.csv generado con éxito para llenar tu tabla.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void registrarDetallado(PrintWriter pw, String alg, String ver, int n, Runnable task) {
        double[] tiempos = new double[5];
        double suma = 0;

        for (int i = 0; i < 5; i++) {
            long inicio = System.nanoTime(); // Medición con nanoTime 
            task.run();
            long fin = System.nanoTime();
            double duracionMs = (fin - inicio) / 1_000_000.0; // Conversión a milisegundos 
            tiempos[i] = duracionMs;
            suma += duracionMs;
        }

        double promedio = suma / 5; // Cálculo del promedio 

        // Algoritmo, Version, n, E1, E2, E3, E4, E5, Promedio
        pw.printf("%s,%s,%d,%.6f,%.6f,%.6f,%.6f,%.6f,%.6f\n", 
            alg, ver, n, tiempos[0], tiempos[1], tiempos[2], tiempos[3], tiempos[4], promedio);
    }
}