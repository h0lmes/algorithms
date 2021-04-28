package algo;

public class ReverseInteger {

    public static void main(String[] args) {
        System.out.println(new ReverseInteger().reverse(123456789));
        System.out.println(new ReverseInteger().reverse(-123456789));
        System.out.println(new ReverseInteger().reverse(-1234567899));
    }

    public int reverse(int x) {
        if (x == 0) return 0;

        int sign = x < 0 ? -1 : 1;
        if (x < 0) x = -x;
        int result = 0;
        int max = Integer.MAX_VALUE / 10;

        while (x > 0) {
            if (result > max) return 0;
            int lsd = x - (x / 10 * 10);
            result = result * 10 + lsd;
            x /= 10;
        }
        return result * sign;
    }
}
