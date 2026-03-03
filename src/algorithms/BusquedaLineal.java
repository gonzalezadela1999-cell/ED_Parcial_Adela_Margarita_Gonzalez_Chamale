package algorithms;

public class BusquedaLineal {
    // Versión Iterativa O(n)
    public int buscarIterativo(int[] arr, int x) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == x) return i;
        }
        return -1;
    }

    // Versión Recursiva O(n)
    public int buscarRecursivo(int[] arr, int x, int i) {
        if (i >= arr.length) return -1;
        if (arr[i] == x) return i;
        return buscarRecursivo(arr, x, i + 1);
    }
}