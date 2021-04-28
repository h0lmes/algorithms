package algo;

import java.util.HashMap;
import java.util.Map;

public class SubarraySumEqualsK {

    public static void main(String[] args) {
        System.out.println(new SubarraySumEqualsK().subarraySum(new int[]{1, 1, 1}, 2));
        System.out.println(new SubarraySumEqualsK().subarraySum(new int[]{1}, 1));
        System.out.println(new SubarraySumEqualsK().subarraySum(new int[]{-1, -1, 1}, 0));
    }

    public int subarraySum(int[] nums, int k) {
        int len = nums.length;
        if (len == 0)
            return 0;

        int result = 0;
        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, 1);

        int sum = 0;
        for (int i = 0; i < len; i++) {
            sum += nums[i];
            if (map.containsKey(sum - k)) {
                result += map.get(sum - k);
            }
            if (map.containsKey(sum)) {
                map.put(sum, map.get(sum) + 1);
            }
            else {
                map.put(sum, 1);
            }
        }

        return result;
    }
}
