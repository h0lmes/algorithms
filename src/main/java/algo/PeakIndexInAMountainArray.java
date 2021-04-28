package algo;

public class PeakIndexInAMountainArray {

    static int[] arr = {1,6,5,4,3};

    public static void main(String[] args) {
        System.out.println(peakIndexInMountainArray(arr));
    }

    public static int peakIndexInMountainArray(int[] A) {
        int len = A.length;
        int l = 0;
        int r = len - 1;

        while (l <= r) {
            int i = l + (r - l) / 2;
            if (r == l) {
                return l;
            } else if (i - 1 >= 0 && A[i - 1] > A[i]) {
                r = i - 1;
            } else if (i + 1 < len && A[i + 1] > A[i]) {
                l = i + 1;
            } else
                return i;
        }
        return -1;
    }
}
