package algo;

public class MaximalRectangle {

    public static void main(String[] args) {
        run(new char[][]{{'1','0','1','0','0'},{'1','0','1','1','1'},{'1','1','1','1','1'},{'1','0','0','1','0'}}); // 6
        run(new char[][]{{'1','0','1','1','1'},{'0','1','0','1','0'},{'1','1','0','1','1'},{'1','1','0','1','1'},{'0','1','1','1','1'}}); // 6
    }

    private static void run(char[][] matrix) {
        System.out.println(new MaximalRectangle().maximalRectangle(matrix));
    }

    public int maximalRectangle(char[][] matrix) {
        int H = matrix.length;
        if (H == 0) return 0;
        int W = matrix[0].length;
        if (W == 0) return 0;

        int max = 0;
        int[][] dp = new int[H][W];

        for (int x = 0; x < W; x++) {
            for (int y = 0; y < H; y++) {
                if (matrix[y][x] == '1') {
                    dp[y][x] = 1 + (x > 0 ? dp[y][x - 1] : 0);
                }
            }
        }

        for (int x = W - 1; x >= 0; x--) {
            for (int y = H - 1; y >= 0; y--) {
                int addY = 1;
                int maxval = dp[y][x];
                if (max < maxval) max = maxval;
                while (y - addY >= 0 && maxval > 0) {
                    maxval = Math.min(maxval, dp[y - addY][x]);
                    addY++;
                    if (max < maxval * addY) max = maxval * addY;
                }
            }
        }

        return max;
    }
}
