package algorithms;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;

public class MergeSortTest {

    @Test
    void testSimpleArray() {
        int[] arr = {5, 2, 9, 1, 6, 3};
        int[] expected = {1, 2, 3, 5, 6, 9};
        MergeSort.mergeSort(arr);
        assertArrayEquals(expected, arr);
    }

    @Test
    void testEmptyArray() {
        int[] arr = {};
        int[] expected = {};
        MergeSort.mergeSort(arr);
        assertArrayEquals(expected, arr);
    }

    @Test
    void testSortedArray() {
        int[] arr = {1, 2, 3, 4, 5};
        int[] expected = {1, 2, 3, 4, 5};
        MergeSort.mergeSort(arr);
        assertArrayEquals(expected, arr);
    }
}
