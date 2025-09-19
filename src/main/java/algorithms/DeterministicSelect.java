package algorithms;

import java.util.Arrays;

public class DeterministicSelect {
    public static int select(int[] a, int k) {
        if (a == null || a.length == 0) throw new IllegalArgumentException("Empty array");
        if (k < 0 || k >= a.length) throw new IllegalArgumentException("k out of bounds");
        return select(a, 0, a.length - 1, k);
    }

    private static int select(int[] a, int left, int right, int k) {
        while (true) {
            if (left == right) return a[left];

            int n = right - left + 1;
            if (n <= 10) {
                Arrays.sort(a, left, right + 1);
                return a[left + k];
            }

            int pivotValue = medianOfMedians(a, left, right);
            int pivotIndex = partitionAroundValue(a, left, right, pivotValue);
            int rank = pivotIndex - left;

            if (k == rank) {
                return a[pivotIndex];
            } else if (k < rank) {
                right = pivotIndex - 1;
            } else {
                k = k - rank - 1;
                left = pivotIndex + 1;
            }
        }
    }

    private static int medianOfMedians(int[] a, int left, int right) {
        int n = right - left + 1;
        int numGroups = (n + 4) / 5;
        for (int i = 0; i < numGroups; i++) {
            int subLeft = left + i * 5;
            int subRight = Math.min(subLeft + 4, right);
            insertionSort(a, subLeft, subRight);
            int medianIndex = subLeft + (subRight - subLeft) / 2;
            swap(a, left + i, medianIndex);
        }

        int mid = numGroups / 2;
        return select(a, left, left + numGroups - 1, mid);
    }

    private static int partitionAroundValue(int[] a, int left, int right, int pivotValue) {
        int pivotIndex = left;
        while (pivotIndex <= right && a[pivotIndex] != pivotValue) pivotIndex++;
        if (pivotIndex > right) {
            pivotIndex = right;
        }
        swap(a, pivotIndex, right);
        int store = left;
        for (int i = left; i < right; i++) {
            if (a[i] < pivotValue) {
                swap(a, store, i);
                store++;
            }
        }
        swap(a, store, right);
        return store;
    }

    private static void insertionSort(int[] a, int left, int right) {
        for (int i = left + 1; i <= right; i++) {
            int key = a[i];
            int j = i - 1;
            while (j >= left && a[j] > key) {
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
