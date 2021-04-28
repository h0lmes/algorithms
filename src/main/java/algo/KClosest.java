package algo;

import java.util.Arrays;
import java.util.PriorityQueue;

public class KClosest {

    public static void main(String[] args) {
        System.out.println(Arrays.deepToString(new KClosest().kClosest(new int[][]{{1, 3}, {2, 2}}, 1)));
    }

    public int[][] kClosest(int[][] points, int K) {
        if (K == points.length) return points;
        PriorityQueue<int[]> q = new PriorityQueue<>(K, (a, b) -> b[0] * b[0] + b[1] * b[1] - a[0] * a[0] - a[1] * a[1]);
        for (int[] pt : points) {
            q.add(pt);
            if (q.size() > K) q.poll();
        }
        return q.toArray(new int[0][0]);
    }
}
