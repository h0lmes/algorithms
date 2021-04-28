package algo;

public class RottingOranges {

    public static void main(String[] args) {
        System.out.println("4 = " + new RottingOranges().orangesRotting( new int[][]{{2,1,1},{1,1,0},{0,1,1}} ));
        System.out.println("2 = " + new RottingOranges().orangesRotting( new int[][]{{1,1,2,0,2,0}} ));
    }

    public int orangesRotting(int[][] grid) {
        int h = grid.length;
        int w = grid[0].length;
        int days = 0;
        while (true) {
            boolean changed = false;
            boolean hasFresh = false;

            for (int y = 0; y < h; y++) {
                for (int x = 0; x < w; x++) {
                    if (spread(grid, w, h, x, y))
                        changed = true;
                    if (grid[y][x] == 1)
                        hasFresh = true;
                }
            }

            if (!changed) {
                if (hasFresh) return -1;
                break;
            }
            days++;
        }

        return days;
    }

    private boolean spread(int[][]grid, int w, int h, int x, int y) {
        boolean result = false;
        int val = grid[y][x];

        if (val == 3) {
            grid[y][x] = 2;
        }
        else if (val == 2) {
            if (y > 0 && grid[y - 1][x] == 1) {
                grid[y - 1][x] = 3;
                result = true;
            }
            if (y < h - 1 && grid[y + 1][x] == 1) {
                grid[y + 1][x] = 3;
                result = true;
            }
            if (x > 0 && grid[y][x - 1] == 1) {
                grid[y][x - 1] = 3;
                result = true;
            }
            if (x < w - 1 && grid[y][x + 1] == 1) {
                grid[y][x + 1] = 3;
                result = true;
            }
        }

        return result;
    }
}
