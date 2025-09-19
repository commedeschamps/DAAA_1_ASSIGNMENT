package org.example;

import algorithms.QuickSort;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;

public class QuickSortTest {

    @Test
    void testRandomArray() {
        int[] arr = {10, 7, 8, 9, 1, 5};
        int[] expected = {1, 5, 7, 8, 9, 10};
        QuickSort.quickSort(arr);
        assertArrayEquals(expected, arr);
    }

    @Test
    void testWithDuplicatesAndSortedPortions() {
        int[] arr = {2, 3, 3, 1, 5, 2, 7};
        int[] sorted = arr.clone();
        java.util.Arrays.sort(sorted);
        QuickSort.quickSort(arr);
        assertArrayEquals(sorted, arr);
    }
}
