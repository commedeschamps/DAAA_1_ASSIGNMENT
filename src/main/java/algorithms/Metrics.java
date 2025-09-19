package algorithms;

public class Metrics {
    public int depth = 0;
    public int maxDepth = 0;
    public long comparisons = 0;
    public long allocations = 0;

    public void enter() {
        depth++;
        if (depth > maxDepth) maxDepth = depth;
    }
    public void exit() {
        depth--;
    }
    public void comp() { comparisons++; }
}
