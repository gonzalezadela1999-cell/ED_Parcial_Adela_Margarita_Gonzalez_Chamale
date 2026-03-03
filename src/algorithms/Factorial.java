package algorithms;

public class Factorial {
    // Versión Iterativa O(n) 
    public long calcularIterativo(int n) {
        long res = 1;
        for (int i = 1; i <= n; i++) res *= i;
        return res;
    }

    // Versión Recursiva O(n) 
    public long calcularRecursivo(int n) {
        if (n <= 1) return 1;
        return n * calcularRecursivo(n - 1);
    }
}