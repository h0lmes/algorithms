package algo;

import java.util.Arrays;
import java.util.HashSet;

public class PrisonCellsAfterNDays {

    public static void main(String[] args) {
        System.out.println(Arrays.toString(new PrisonCellsAfterNDays().prisonAfterNDays(new int[]{0, 1, 0, 1, 1, 0, 0, 1}, 7)));
        System.out.println(Arrays.toString(new PrisonCellsAfterNDays().prisonAfterNDays(new int[]{1, 0, 0, 1, 0, 0, 1, 0}, 1000000000)));
    }

    public int[] prisonAfterNDays(int[] cells, int N) {

        HashSet<Integer> set = new HashSet<>();
        int icells = arrayToInt(cells);
        int cycle = 0;
        int i;
        int next;
        for (i = 0; i < N; i++) {
            next = mutate(icells);
            if (set.contains(next)) {
                N %= cycle;
                for (i = 0; i < N; i++) icells = mutate(icells);
                break;
            } else {
                set.add(next);
                cycle++;
            }
            icells = next;
        }

        return intToArray(icells);
    }

    private int mutate(int b) {
        int r = 0;
        for (int i = 1; i <= 6; i++) {
            boolean left = (b & 1 << (i - 1)) != 0;
            boolean right = (b & 1 << (i + 1)) != 0;
            if ((left && right) || (!left && !right))
                r |= 1 << i;
            else
                r &= ~(1 << i);
        }
        return r;
    }

    private int arrayToInt(int[] cells) {
        int r = 0;
        for (int i = 0; i <= 7; i++) {
            if (cells[i] == 1)
                r |= (1 << i);
        }
        return r;
    }

    private int[] intToArray(int b) {
        int[] r = new int[8];
        for (int i = 0; i <= 7; i++) {
            if ((b & (1 << i)) != 0)
                r[i] = 1;
        }
        return r;
    }
}
