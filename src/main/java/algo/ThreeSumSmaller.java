package algo;

import java.util.Arrays;

public class ThreeSumSmaller {

    public static void main(String[] args) {
        System.out.println(new ThreeSumSmaller().threeSumSmaller(new int[]{-2, 0, 1, 3}, 2));
    }

    public int threeSumSmaller(int[] nums, int target) {
        int len = nums.length;
        if (len < 3) return 0;
        Arrays.sort(nums);
        int result = 0;
        for (int i = 0; i < len - 2; i++)
            result += twoSumSmaller(nums, target - nums[i], i + 1, len - 1);
        return result;
    }

    private int twoSumSmaller(int[] nums, int target, int start, int end) {
        int result = 0;
        while (start < end) {
            if (nums[start] + nums[end] < target) {
                result += end - start;
                start++;
            } else {
                end--;
            }
        }
        return result;
    }
}
