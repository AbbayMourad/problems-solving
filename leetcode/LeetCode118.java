package leetcode;

import java.util.ArrayList;
import java.util.List;

public class LeetCode118 {

    public List<List<Integer>> generate(int numRows) {

        List<List<Integer>> res = new ArrayList<>(numRows);
        res.add(List.of(1));
        for (int i = 1; i < numRows; i++) {
            List<Integer> nums = new ArrayList<>();
            List<Integer> prev = res.get(i-1);
            nums.add(1);
            for (int j = 1; j < i; j++) {
                nums.add(prev.get(j-1) + prev.get(j));
            }
            nums.add(1);
            res.add(nums);
        }

        return res;
    }
}
