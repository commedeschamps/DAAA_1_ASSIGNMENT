package algorithms;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

public class ClosestPair {

    public static double closestPair(Point[] points) {
        if (points == null || points.length < 2) return Double.POSITIVE_INFINITY;
        Point[] px = points.clone();
        Point[] py = points.clone();
        Arrays.sort(px, (a, b) -> Double.compare(a.x, b.x));
        Arrays.sort(py, (a, b) -> Double.compare(a.y, b.y));
        double minDistSq = closestRec(px, py);
        return Math.sqrt(minDistSq);
    }

    private static double closestRec(Point[] px, Point[] py) {
        int n = px.length;
        if (n <= 3) {
            return bruteForceMinSq(px);
        }

        int mid = n / 2;
        Point midPoint = px[mid];

        Point[] leftPx = Arrays.copyOfRange(px, 0, mid);
        Point[] rightPx = Arrays.copyOfRange(px, mid, n);

        HashSet<Point> leftSet = new HashSet<>(Arrays.asList(leftPx));

        List<Point> leftPyList = new ArrayList<>(leftPx.length);
        List<Point> rightPyList = new ArrayList<>(rightPx.length);
        for (Point p : py) {
            if (leftSet.contains(p)) leftPyList.add(p);
            else rightPyList.add(p);
        }
        Point[] leftPy = leftPyList.toArray(new Point[0]);
        Point[] rightPy = rightPyList.toArray(new Point[0]);

        double dlSq = closestRec(leftPx, leftPy);
        double drSq = closestRec(rightPx, rightPy);
        double dSq = Math.min(dlSq, drSq);
        double d = Math.sqrt(dSq);

        List<Point> stripList = new ArrayList<>();
        for (Point p : py) {
            if (Math.abs(p.x - midPoint.x) < d) {
                stripList.add(p);
            }
        }
        Point[] strip = stripList.toArray(new Point[0]);

        double minStripSq = dSq;
        for (int i = 0; i < strip.length; i++) {
            for (int j = i + 1; j < strip.length && (strip[j].y - strip[i].y) < d; j++) {
                double distSq = distSq(strip[i], strip[j]);
                if (distSq < minStripSq) {
                    minStripSq = distSq;
                    d = Math.sqrt(minStripSq);
                }
            }
        }

        return Math.min(dSq, minStripSq);
    }

    private static double bruteForceMinSq(Point[] pts) {
        double minSq = Double.POSITIVE_INFINITY;
        int n = pts.length;
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                minSq = Math.min(minSq, distSq(pts[i], pts[j]));
            }
        }
        return minSq;
    }

    private static double distSq(Point a, Point b) {
        double dx = a.x - b.x;
        double dy = a.y - b.y;
        return dx * dx + dy * dy;
    }
}
