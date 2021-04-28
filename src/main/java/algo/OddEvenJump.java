package algo;

import java.util.*;

public class OddEvenJump {

    public static void main(String[] args) {
        System.out.println(new OddEvenJump().oddEvenJumps(new int[]{2, 3, 1, 1, 4})); // 3
        System.out.println(new OddEvenJump().oddEvenJumps(new int[]{10,13,12,14,15})); // 2
    }

    public int oddEvenJumps(int[] A) {
        int len = A.length;
        if (len == 1) return 1;

        int[] even = new int[len];
        int[] odd = new int[len];
        even[len - 1] = 1;
        odd[len - 1] = 1;
        int result = 1;

        TreeMap<Integer, Integer> map = new TreeMap<>();
        map.put(A[len - 1], len - 1);
        for (int i = len - 2; i >= 0; i--) {
            // if I've made even number of jumps up to this index
            Map.Entry<Integer, Integer> lookFor = map.ceilingEntry(A[i]);
            if (lookFor != null) {
                even[i] = odd[lookFor.getValue()];
            }

            if (even[i] == 1)
                result++;

            // if I've made odd number of jumps up to this index
            lookFor = map.floorEntry(A[i]);
            if (lookFor != null) {
                odd[i] = even[lookFor.getValue()];
            }

            map.put(A[i], i);
        }

        return result;
    }
}

// jump must be at least one index further to closest possible index
//  odd jumps: smallest of not less
// even jumps: largest of not greater
//
// i = len - 1: even (we made even number of jumps to this index) = true, odd = true
// i = len - 2:
//            even = true if A[i] <= min of A previous (for i = i + 1 to len - 1)
//             odd = true if A[i] >= A[i + 1])