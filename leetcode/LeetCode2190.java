package leetcode;

public class LeetCode2190 {
    public int mostFrequent(int[] nums, int key) {

        int[] scores = new int[1001];
        for (int i = 0; i < nums.length - 1; i++) {
            if (nums[i] == key) {
                scores[nums[i+1]]++;
            }
        }

        int max = Integer.MIN_VALUE;
        int res = -1;
        for (int i = 0; i < scores.length; i++) {
            if (scores[i] > max) {
                max = scores[i];
                res = i;
            }
        }

        return res;
    }
}
