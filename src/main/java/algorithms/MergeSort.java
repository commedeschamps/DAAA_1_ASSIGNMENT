package algorithms;

public class MergeSort {
    public static void mergeSort(int[] array) {
        if (array == null || array.length <= 1) return;
        int[] temp = new int[array.length];
        mergeSort(array, temp, 0, array.length - 1);
    }


    private static void mergeSort(int[] array, int[] temp, int left, int right) {
        if (right - left <= 16) { // small array → insertion sort
            insertionSort(array, left, right);
            return;
        }

        if (left < right) {
            int mid = (left + right) / 2;
            mergeSort(array, temp, left, mid);
            mergeSort(array, temp, mid + 1, right);
            merge(array, temp, left, mid, right);
        }
    }

    private static void merge(int[] array, int[] temp, int left, int mid, int right) {
        for (int i = left; i <= right; i++) {
            temp[i] = array[i];
        }

        int i = left, j = mid + 1, k = left;
        while (i <= mid && j <= right) {
            if (temp[i] <= temp[j]) {
                array[k++] = temp[i++];
            } else {
                array[k++] = temp[j++];
            }
        }

        while (i <= mid) {
            array[k++] = temp[i++];
        }

    }

    private static void insertionSort(int[] array, int left, int right) {
        for (int i = left + 1; i <= right; i++) {
            int key = array[i];
            int j = i - 1;
            while (j >= left && array[j] > key) {
                array[j + 1] = array[j];
                j--;
            }
            array[j + 1] = key;
        }
    }
}
