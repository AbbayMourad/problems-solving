function stateToString(state, n) {
  const formatedState = []
  const arr = new Array(n)
  for (let i = 0; i < arr.length; ++i) arr[i] = '.'

  for (const j of state) {
    arr[j] = 'Q'
    formatedState.push(arr.join(''))
    arr[j] = '.'
  }
  return formatedState
}

function isValidState(state, n) {
  // check if it is a valid solution (N-Queens have been placed)
  return state.length === n
}
// return [..., j, ...]
function getCandidates(state, n) {
  const candidates = new Set(new Array(n).keys())
  state.forEach((j, i) => {
    candidates.delete(j) // same col
    const diagCol = state.length + j - i
    candidates.delete(diagCol) // in diag
    const diagCol2 = i + j - state.length;
    candidates.delete(diagCol2)
  })
  return candidates
}

function search(state, solutions, n) {
  if (isValidState(state, n)) {
    solutions.push(stateToString(state, n))
    return
  }

  for (candidate of getCandidates(state, n)) {
    state.push(candidate)
    search(state, solutions, n)
    state.pop()
  }
}

const solveNQueens = function (n) {
  const solutions = []
  const state = [] // current occuped positions, [j0, j1, ...]
  search(state, solutions, n)
  return solutions
}
