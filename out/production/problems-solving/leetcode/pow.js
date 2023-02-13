const myPow = function (x, n) {
  const power = pow(x, Math.abs(n))
  return n < 0 ? 1 / power : power
}

//! n >= 0
function pow(x, n) {
  if (n == 0) return 1
  let r = pow(x, Math.floor(n / 2))
  r *= r
  if (n & 1) r *= x
  return r
}
