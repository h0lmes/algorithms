package codeforces.p2024.b;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws FileNotFoundException {
        Scanner sc = new Scanner(new FileInputStream("./src/main/java/codeforces/p2024/b/input.txt"));
        while (sc.hasNext()) {
            System.out.println(sc.nextInt());
        }
        sc.close();
    }
}
