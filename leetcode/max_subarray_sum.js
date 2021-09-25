/**
 * @param {number[]} nums
 * @return {number}
 */
const maxSubArray = function(nums) {
  let l = 0, r = 1, currSum = nums[0], answer = nums[0]
  while (r < nums.length) {
    if (currSum < 0) {
      l = r
      currSum = nums[l]
    } 
    else  currSum += nums[r]
    r++
    answer = Math.max(answer, currSum) 
  }
  return answer  
};