package algo;

public class FindInMountainArray {

    private class MountainArray {
        int[] arr = {0,5,3,1};
        public int get(int i) { return arr[i]; }
        public int length() { return arr.length; }
    }

    public static void main(String[] args) {
        new FindInMountainArray();
    }

    public FindInMountainArray() {
        System.out.println(findInMountainArray(1, new MountainArray()));
    }

    public int findInMountainArray(int target, MountainArray mountainArr) {
        int peak = peakIndexInMountainArray(mountainArr);
        if (mountainArr.get(peak) == target)
            return peak;

        int l = 0;
        int r = peak - 1;
        while (l <= r) {
            int mid = l + (r - l) / 2;
            int midVal = mountainArr.get(mid);
            if (midVal > target)
                r = mid - 1;
            else if (midVal < target)
                l = mid + 1;
            else
                return mid;
        }

        l = peak + 1;
        r = mountainArr.length() - 1;
        while (l <= r) {
            int mid = l + (r - l) / 2;
            int midVal = mountainArr.get(mid);
            if (midVal > target)
                l = mid + 1;
            else if (midVal < target)
                r = mid - 1;
            else
                return mid;
        }

        return -1;
    }

    public int peakIndexInMountainArray(MountainArray A) {
        int len = A.length();
        int l = 0;
        int r = len - 1;
        while (l <= r) {
            if (l == r) return l;
            int i = l + (r - l) / 2;
            if (i - 1 >= 0 && A.get(i - 1) > A.get(i)) {
                r = i - 1;
            } else if (i + 1 < len && A.get(i + 1) > A.get(i)) {
                l = i + 1;
            } else
                return i;
        }
        return -1;
    }
}
