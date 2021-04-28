package algo;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class TwoSum {

    public static void main(String[] args) {
        System.out.println(Arrays.toString(new TwoSum().twoSum(new int[]{1, 2, 7, 11, 15}, 9)));
    }

    public int[] twoSum(int[] nums, int target) {
        Map<Integer,Integer> map = new HashMap();
        for (int i = 0; i < nums.length; i++) {
            int complement = target - nums[i];
            if (map.containsKey(complement)) {
                int index = map.get(complement);
                return new int[]{index, i};
            } else {
                map.put(nums[i], i);
            }
        }
        return new int[]{-1, -1};
    }
}
