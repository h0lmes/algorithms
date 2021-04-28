package algo;

public class MedianOfTwoSortedArrays {

    public static void main(String[] args) {
        int[] a1 = {2, 4, 6, 8};
        int[] a2 = {1, 1, 3, 5, 7, 9, 11, 13};
        double result = medianOfArrays(a1, a2, a1.length, a2.length);
        System.out.println(result);
    }

    public static double medianOfArrays(int[] a1, int[] a2, int len1, int len2) {
        if (len1 == 0 && len2 == 0) {
            return -1;
        } else if (len1 == 0) {
            return medianOfArray(a2, len2);
        } else if (len2 == 0) {
            return medianOfArray(a1, len1);
        } else if (len1 == 1 && len2 == 1) {
            return medianOf2(a1[0], a2[0]);
        } else if (len1 == 1 && len2 == 2) {
            return medianOf3(a1[0], a2[0], a2[1]);
        } else if (len1 == 2 && len2 == 2) {
            return medianOf4(a1[0], a2[0], a1[1], a2[1]);
        }

        int start1 = 0;
        int end1 = len1 - 1;
        int start2 = 0;
        int end2 = len2 - 1;
        int mid1;
        int mid2;

        while (start1 < end1 && start2 < end2) {
            mid1 = start1 + (end1 - start1) / 2;
            mid2 = start2 + (end2 - start2) / 2;
            if (a1[mid1] >= a2[mid2]) {
                start2 += (end1 - start1 + 1) / 2;
                end1 -= (end1 - start1 + 1) / 2;
            } else {
                end2 -= (end1 - start1 + 1) / 2;
                start1 += (end1 - start1 + 1) / 2;
            }
        }

        return medianOf2(a1[start1], a2[start2]);
    }

    private static double medianOfArray(int[] a, int len) {
        if (len == 1) {
            return a[0];
        } else if (len % 2 == 1) {
            return a[len / 2];
        } else {
            return medianOf2(a[len / 2 - 1], a[len / 2]);
        }
    }

    private static double medianOf2(int a, int b) {
        return (a + b) / 2d;
    }

    private static double medianOf3(int a, int b, int c) {
        int max = a;
        int min = a;
        if (b > max) max = b;
        else if (b < min) min = b;
        if (c > max) max = c;
        else if (c < min) min = c;
        return a + b + c - max - min;
    }

    private static double medianOf4(int a, int b, int c, int d) {
        int max = a;
        int min = a;
        if (b > max) max = b;
        else if (b < min) min = b;
        if (c > max) max = c;
        else if (c < min) min = c;
        if (d > max) max = d;
        else if (d < min) min = d;
        return (a + b + c + d - max - min) / 2d;
    }
}
