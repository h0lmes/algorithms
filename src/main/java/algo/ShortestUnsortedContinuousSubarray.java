package algo;

public class ShortestUnsortedContinuousSubarray {

    static int[] arr = {-1,1,1,1,1,1,4,10,0,3,5,5,5,5};

    public static void main(String[] args) {
        System.out.println(findUnsortedSubarray(arr));
    }

    public static int findUnsortedSubarray(int[] nums) {
        int left = 0;
        int right = nums.length - 1;
        int min = nums[right--];
        int max = nums[left++];
        int start = 0;
        int end = -1;

        for (; right >= 0; right--) {
            if (nums[left] >= max)
                max = nums[left];
            else
                end = left;

            if (nums[right] <= min)
                min = nums[right];
            else
                start = right;

            left++;
        }

        return end - start + 1;
    }
}
