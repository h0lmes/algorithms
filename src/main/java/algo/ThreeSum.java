package algo;

import java.util.*;

public class ThreeSum {

    public static void main(String[] args) {
        run(new int[]{-1,0,1,2,-1,-4});
        run(new int[]{1,2,-2,-1});
        run(new int[]{0,0,0,0});
        run(new int[]{-2,0,1,1,2});
    }

    public static void run(int[] nums) {
        new ThreeSum().threeSum(nums).forEach(list -> {
            System.out.print("[");
            list.forEach(i -> System.out.print(i + ","));
            System.out.print("],");
        });
        System.out.println();
    }

    public List<List<Integer>> threeSum(int[] nums) {
        int len = nums.length;
        List<List<Integer>> result = new ArrayList<>();
        if (len < 3) return result;

        Arrays.sort(nums);

        int prev = Integer.MIN_VALUE;
        for (int i = 0; i < len; i++) {
            if (nums[i] > 0)
                return result;
            if (prev == nums[i])
                continue;
            prev = nums[i];

            List<int[]> complement = twoSum(nums, -nums[i], i + 1, len - 1);
            for (int[] arr : complement) {
                List<Integer> triplet = new ArrayList<>();
                triplet.add(nums[i]);
                triplet.add(nums[arr[0]]);
                triplet.add(nums[arr[1]]);
                result.add(triplet);
            }
        }

        return result;
    }

    private List<int[]> twoSum(int[] nums, int target, int start, int end) {
        List<int[]> result = new ArrayList<>();

        int prevS;
        int prevE;
        while (start < end) {
            int sum = nums[start] + nums[end];
            if (sum == target) {
                prevS = nums[start];
                prevE = nums[end];
                result.add(new int[]{start++, end--});
                while (start < end && nums[start] == prevS) start++;
                while (start < end && nums[end] == prevE) end--;
            } else if (sum < target) {
                start++;
            } else {
                end--;
            }
        }
        return result;
    }
}
