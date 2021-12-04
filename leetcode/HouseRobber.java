public class HouseRobber {
    private static final HouseRobber houseRobber = new HouseRobber();

    private int rob(int[] nums) {
        int n = nums.length;
        int[] max = new int[n];
        int result = Integer.MIN_VALUE;
        for (int i = n - 1; i > -1; i--) {
            int maxValue = 0;
            if (i + 3 < n) {
                maxValue = Math.max(max[i+2], max[i+3]);
            } else if (i + 2 < n) {
                maxValue = max[i+2];
            }
            max[i] = maxValue + nums[i];
            result = Math.max(result, max[i]);
        }
        return result;
    }

    public static void main(String[] args) {
        int[] nums = {1,2,3,1};
        int result = houseRobber.rob(nums);
        System.out.println(result);
    }
}
