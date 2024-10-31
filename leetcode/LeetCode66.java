package leetcode;

import java.util.Arrays;

public class LeetCode66 {
    public int[] plusOne(int[] digits) {

        int n = digits.length;
        int carry = 0;
        for (int j = n - 1; j > -1 ; j--) {
            if (digits[j] + 1 == 10) {
                digits[j] = 0;
                carry = 1;
            } else {
                digits[j]++;
                carry = 0;
                break;
            }
        }

        if (carry != 0) {
            int[] res = new int[n + 1];
            res[0] = 1;
            return res;
        } else {
            return digits;
        }
    }
}
