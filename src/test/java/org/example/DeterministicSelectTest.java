package org.example;

import algorithms.DeterministicSelect;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import java.util.Arrays;

public class DeterministicSelectTest {

    @Test
    void testSelectRandom() {
        int[] a = {12, 3, 5, 7, 4, 19, 26, 1, 9};
        int[] b = a.clone();
        Arrays.sort(b);
        for (int k = 0; k < a.length; k++) {
            int res = DeterministicSelect.select(a.clone(), k);
            assertEquals(b[k], res);
        }
    }

    @Test
    void testWithDuplicates() {
        int[] a = {5, 3, 3, 7, 5, 2, 2, 9};
        int[] b = a.clone();
        Arrays.sort(b);
        for (int k = 0; k < a.length; k++) {
            assertEquals(b[k], DeterministicSelect.select(a.clone(), k));
        }
    }
}

