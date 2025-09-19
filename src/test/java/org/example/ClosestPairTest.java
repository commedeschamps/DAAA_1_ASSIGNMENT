package org.example;
import algorithms.ClosestPair;
import algorithms.Point;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ClosestPairTest {

    @Test
    void simpleTest() {
        Point[] pts = {
                new Point(2, 3),
                new Point(12, 30),
                new Point(40, 50),
                new Point(5, 1),
                new Point(12, 10),
                new Point(3, 4)
        };
        double minDist = ClosestPair.closestPair(pts);
        assertEquals(Math.sqrt(2), minDist, 1e-6); // (2,3) and (3,4)
    }

    @Test
    void smallRandomTestMatchesBruteForce() {
        java.util.Random rnd = new java.util.Random(123);
        for (int t = 0; t < 10; t++) {
            int n = 100;
            Point[] pts = new Point[n];
            for (int i = 0; i < n; i++) pts[i] = new Point(rnd.nextDouble() * 1000, rnd.nextDouble() * 1000);
            double fast = ClosestPair.closestPair(pts);
            double brute = bruteForce(pts);
            assertEquals(brute, fast, 1e-6);
        }
    }

    private double bruteForce(Point[] pts) {
        double best = Double.POSITIVE_INFINITY;
        for (int i = 0; i < pts.length; i++) {
            for (int j = i + 1; j < pts.length; j++) {
                double dx = pts[i].x - pts[j].x;
                double dy = pts[i].y - pts[j].y;
                best = Math.min(best, Math.sqrt(dx*dx + dy*dy));
            }
        }
        return best;
    }
}
