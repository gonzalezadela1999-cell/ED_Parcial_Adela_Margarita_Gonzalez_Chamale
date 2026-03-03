package benchmark; // Ahora pertenece al paquete benchmark

public class Main {
    public static void main(String[] args) {
        System.out.println("=== UNIVERSIDAD DA VINCI DE GUATEMALA ===");
        System.out.println("Iniciando pruebas de rendimiento...");
        
        Medidor m = new Medidor();
        m.ejecutarPruebas();
        
        System.out.println("Proceso finalizado con éxito.");
    }
}