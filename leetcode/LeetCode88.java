package leetcode;

import java.util.Arrays;

public class LeetCode88 {

    public void merge(int[] nums1, int m, int[] nums2, int n) {

        int[] a = new int[m];
        System.arraycopy(nums1, 0, a, 0, m);

        int i1 = 0, i2 = 0, k = 0;

        while (i1 < m && i2 < n) {
            if (a[i1] <= nums2[i2]) {
                nums1[k++] = a[i1++];
            } else {
                nums1[k++] = nums2[i2++];
            }
        }

        while (i1 < m) {
            nums1[k++] = a[i1++];
        }
        while (i2 < n) {
            nums1[k++] = nums2[i2++];
        }

        // can a and nums2 have elements left, or only one of them
        // I don't think  so, the first loop execute until one has no elements
    }
}
