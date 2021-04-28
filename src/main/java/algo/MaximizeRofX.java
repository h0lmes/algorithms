package algo;

public class MaximizeRofX {

    public static void main(String[] args) {
        System.out.println(maximize(17));
    }

    public static int maximize(int n) {
        double max = 0;
        int x = 0;

        for (int i = 1; i <= n; i++) {
            double rx = (double)i / sx(i);
            if (rx >= max) {
                max = rx;
                x = i;
            }
        }

        return x;
    }

    public static int sx(int n) {
        int sum = 1 + n;
        for (int i = 2; i < Math.sqrt(n); i++) {
            if (n % i == 0) {
                if (i == n / i)
                    sum += i;
                else
                    sum += i + n / i;
            }
        }
        return sum;
    }
}
