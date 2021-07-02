/**
 * @param {number[]} nums
 * @param {number} k
 * @return {number}
 */
const longestOnes = function (nums, k) {
  let l = 0, r = -1;
  let numZero = 0; // number of zeros in the current window
  let longestOnes = 0;

  while (r < nums.length - 1) {
    if (nums[r + 1] === 0) {
      if (numZero < k) {
        r++; numZero++;
      }
      else if (numZero === k) {
        longestOnes = Math.max(longestOnes, r - l + 1);

        while (l < nums.length && nums[l] !== 0) l++;
        l++;
        if (numZero > 0) numZero--;
        if (r < l-1)  r = l-1;  
      }
    }
    else { // next item is 1
      r++;
    }
  }

  return Math.max(longestOnes, r - l + 1);
};
