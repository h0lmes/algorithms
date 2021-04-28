package algo;

import java.util.Arrays;

public class MergeIntervals {

    public static void main(String[] args) {
        System.out.println(Arrays.deepToString(new MergeIntervals().merge(new int[][]{{1, 3}, {2, 6}, {8, 10}, {15, 18}})));
    }

    public int[][] merge(int[][] intervals) {
        if (intervals.length < 2)
            return intervals;

        Arrays.sort(intervals, (a, b) -> Integer.compare(a[0], b[0]));
        int[][] results = new int[intervals.length][2];
        int numberOfResults = 0;
        int start = intervals[0][0];
        int end = intervals[0][1];
        for (int i = 1; i < intervals.length; i++) {
            if (end >= intervals[i][0]) {
                if (end < intervals[i][1])
                    end = intervals[i][1];
            }
            else {
                results[numberOfResults][0] = start;
                results[numberOfResults][1] = end;
                numberOfResults++;
                start = intervals[i][0];
                end = intervals[i][1];
            }
        }
        results[numberOfResults][0] = start;
        results[numberOfResults][1] = end;
        numberOfResults++;

        int[][] results2 = new int[numberOfResults][2];
        for (int i = 0; i < numberOfResults; i++) {
            results2[i][0] = results[i][0];
            results2[i][1] = results[i][1];
        }
        return results2;
    }
}
