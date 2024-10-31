package leetcode;

public class LeetCode27 {

    public int removeElement(int[] nums, int val) {

        int currInsertPos = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != val) {
                nums[currInsertPos++] = nums[i];
            }
        }
        return currInsertPos;
    }
}
