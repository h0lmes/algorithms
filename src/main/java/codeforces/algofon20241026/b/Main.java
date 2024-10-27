package codeforces.algofon20241026.b;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int iterations = sc.nextInt();
        for (int i = 0; i < iterations; i++) {
            System.out.println(work(sc));
        }
        sc.close();
    }

    public static int work(Scanner sc) {
        int columns = sc.nextInt();
        int water = sc.nextInt();

        int[] coral = new int[columns];
        int min = 1000000000;
        for (int i = 0; i < columns; i++) {
            coral[i] = sc.nextInt();
            if (coral[i] < min) min = coral[i];
        }
        for (int i = 0; i < columns; i++) {
            coral[i] = coral[i] - min;
        }

        if (columns == 1) {
            return water + min;
        }

        int height = min;
        boolean zeroes = false;
        while (water > 0) {
            height++;
            zeroes = true;
            for (int i = 0; i < columns; i++) {
                if (coral[i] == 0) {
                    if (water == 0) return height - 1;
                    water--;
                }
                else {
                    zeroes = false;
                    coral[i]--;
                }
            }
            if (zeroes) {
                return (water / columns) + height;
            }
            if (water <= 0) return height;
        }
        return height;
    }
}
