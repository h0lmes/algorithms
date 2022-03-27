package algo;

import java.util.Arrays;

public class MergeSortedArrays {

    public static void main(String[] args) {
        int[] nums1 = {1, 2, 3, 7, 100, 0, 0, 0};
        int[] nums2 = {2, 5, 6};
        new MergeSortedArrays().merge(nums1, 5, nums2, 3);
        System.out.println(Arrays.toString(nums1));

        int[] nums3 = {0};
        int[] nums4 = {1};
        new MergeSortedArrays().merge(nums3, 0, nums4, 1);
        System.out.println(Arrays.toString(nums3));
    }

    public void merge(int[] nums1, int m, int[] nums2, int n) {
        if (n == 0) return;
        int i1 = m - 1;
        int i2 = n - 1;
        int to = m + n - 1;

        while (i1 >= 0 && i2 >= 0)
            nums1[to--] = nums1[i1] > nums2[i2] ? nums1[i1--] : nums2[i2--];

        //while (i1 >= 0) nums1[to--] = nums1[i1--];
        while (i2 >= 0) nums1[to--] = nums2[i2--];
    }
}
