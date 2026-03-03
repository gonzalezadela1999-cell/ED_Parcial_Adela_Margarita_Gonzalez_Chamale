package algorithms;

public class Fibonacci {
    // Versión Iterativa O(n)
    public long calcularIterativo(int n) {
        if (n <= 1) return n;
        long a = 0, b = 1;
        for (int i = 2; i <= n; i++) {
            long temp = a + b;
            a = b;
            b = temp;
        }
        return b;
    }

    // Versión Recursiva O(2^n)
    public long calcularRecursivo(int n) {
        if (n <= 1) return n;
        return calcularRecursivo(n - 1) + calcularRecursivo(n - 2);
    }
}