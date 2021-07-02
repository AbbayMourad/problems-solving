/**
 * @param {number} target
 * @param {number[]} nums
 * @return {number}
 */
const minSubArrayLen = function (target, nums) {
  let l = 0, r = 0;
  let sum = nums[0];
  let minLen = Infinity;

  while (r < nums.length) {
    if (sum >= target) {
      minLen = Math.min(minLen, r - l + 1);
      sum -= nums[l];
      l++;
    } else {
      r++;
      if (r < nums.length) sum += nums[r];
    }
  }

  return minLen === Infinity ? 0 : minLen;
};