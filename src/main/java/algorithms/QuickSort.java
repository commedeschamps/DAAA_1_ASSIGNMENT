package algorithms;

import java.util.Random;

public class QuickSort {
    private static final Random RAND = new Random();
    private static final int CUTOFF = 16;

    public static void quickSort(int[] a) {
        quickSort(a, 0, a.length - 1, null);
    }

    public static void quickSort(int[] a, Metrics metrics) {
        quickSort(a, 0, a.length - 1, metrics);
    }

    public static void quickSort(int[] a, int left, int right, Metrics metrics) {
        while (left < right) {
            int n = right - left + 1;
            if (n <= CUTOFF) {
                insertionSort(a, left, right, metrics);
                return;
            }

            int pivotIndex = left + RAND.nextInt(n);
            int p = partition(a, left, right, pivotIndex, metrics);

            if (p - left < right - p) {
                if (metrics != null) metrics.enter();
                quickSort(a, left, p - 1, metrics);
                if (metrics != null) metrics.exit();
                left = p + 1; // iterate on larger side
            } else {
                if (metrics != null) metrics.enter();
                quickSort(a, p + 1, right, metrics);
                if (metrics != null) metrics.exit();
                right = p - 1;
            }
        }
    }

    private static int partition(int[] a, int left, int right, int pivotIndex, Metrics metrics) {
        int pivot = a[pivotIndex];
        swap(a, pivotIndex, right);
        int store = left;
        for (int i = left; i < right; i++) {
            if (metrics != null) metrics.comp();
            if (a[i] < pivot) {
                swap(a, i, store);
                store++;
            }
        }
        swap(a, store, right);
        return store;
    }

    private static void insertionSort(int[] a, int left, int right, Metrics metrics) {
        for (int i = left + 1; i <= right; i++) {
            int key = a[i];
            int j = i - 1;
            while (j >= left && (metrics == null ? a[j] > key : (++metrics.comparisons >= 0 && a[j] > key))) {
                a[j + 1] = a[j];
                j--;
            }
            a[j + 1] = key;
        }
    }

    private static void swap(int[] a, int i, int j) {
        if (i == j) return;
        int t = a[i];
        a[i] = a[j];
        a[j] = t;
    }
}
