const addTwoNumbers = function (l1, l2) {
  let carry = 0 // carryaining
  let n1 = l1 // current node on l1
  let n2 = l2 // current node on l2
  let res = {}
  let innerNode = null
  do {
    // do sum
    const a = n1 ? n1.val : 0
    const b = n2 ? n2.val : 0
    const sum = add(a, b + carry) // a + b + carry
    const { value } = sum
    carry = sum.carry
    // insert node in the result linked list
    const node = { val: value, next: null }
    if (!innerNode) res = node
    else innerNode.next = node
    innerNode = node
    // prepare for the next iteration
    if (n1) n1 = n1.next
    if (n2) n2 = n2.next
  } while (n1 || n2)
  if (carry)  innerNode.next = { val: carry, next: null }  
  return res
};

function add(a, b) {
  let sum = a + b
  let carry = 0
  if (sum > 9) {
    carry = Math.floor(sum / 10)
    sum %= 10
  }
  return { value: sum, carry }
}

