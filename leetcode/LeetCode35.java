package leetcode;

public class LeetCode35 {
    public int searchInsert(int[] nums, int target) {

        int l = 0;
        int r = nums.length - 1;
        int mid;
        int midVal;
        while (l <= r) {
            mid = l + (r - l) / 2;
            midVal = nums[mid];
            if (target == midVal) {
                return mid;
            } else if (target > midVal) {
                l = mid + 1;
            } else { // target < midVal
                r = mid - 1;
            }
        }
        return l;
    }
}
