// num: array of numbers
function threeSum(num) {
  // sort num in ascending order
  num.sort((a, b) => a - b);
  const res = [];
  for (let i = 0; i < num.length; i++) {
    if (i > 0 && num[i] === num[i - 1]) continue;
    for (let j = i + 1; j < num.length; j++) {
      if (j > i + 1 && num[j] === num[j - 1]) continue;
      // apply binary search algo to find target = -(num[i] + num[j])
      // between j+1 & n-1
      // if target found, add triplets to the result set
      // else, move on
      const index = binarySearch(num, j + 1, num.length - 1, -num[i] - num[j]);
      if (index === -1) continue;
      // add triplet to the result set
      res.push([num[i], num[j], num[index]]);
    }
  }
  return res;
}

function binarySearch(arr, start, end, target) {
  let a = start, b = end, mid;
  while (a <= b) {
    mid = Math.floor((a + b) / 2)
    if (arr[mid] > target) b = mid - 1;
    else if (arr[mid] < target) a = mid + 1;
    else return mid;
  }
  return -1; // not found
}
