/**
 * @param {character[][]} board
 * @return {void} Do not return anything, modify board in-place instead.
 */
const solveSudoku = function (board) {
  const n = 9;
  const state = getinitState(board);
  search(state, { i: 0, j: 0 });

  function search(state, prevEmptyCell) {
    if (isValidState(state)) return true;
    // find the position of the empty cell
    const currEmptyCell = {};
    outerLoop:
    for (let l = prevEmptyCell.i; l < n; l++) {
      for (let c = l === prevEmptyCell.i ? prevEmptyCell.j : 0; c < n; c++) {
        if (board[l][c] !== '.') continue;
        currEmptyCell.i = l;
        currEmptyCell.j = c;
        break outerLoop;
      }
    }
    const { i, j } = currEmptyCell;
    for (const candidate of getCandidates({ i, j })) {
      board[i][j] = candidate;
      state.emptyCellsLeft--;
      const solutionFound = search(state, currEmptyCell);
      if (solutionFound) return true;
      board[i][j] = '.'; // backtracking
      state.emptyCellsLeft++;
    }
  }

  function isValidState(state) {
    // also emptyCells === solution.length
    return state.emptyCellsLeft === 0;
  }

  function getCandidates({ i, j }) {
    // from 1 to n
    const candidates = new Set(Array.from({ length: n }, (_, i) => (i + 1).toString()));
    // row
    for (let c = 0; c < n; c++) {
      // if (c === j) continue;
      candidates.delete(board[i][c]);
    }
    // column
    for (let l = 0; l < n; l++) {
      // if (l === i) continue;
      candidates.delete(board[l][j])
    }
    //sub-grid
    const subGridN = 3;
    const l = Math.floor(i / subGridN) * subGridN, c = Math.floor(j / subGridN) * subGridN;
    for (let i = l; i < l + subGridN; i++) {
      for (let j = c; j < c + subGridN; j++) {
        candidates.delete(board[i][j])
      }
    }
    return Array.from(candidates);
  }

  function getinitState() {
    const state = { emptyCellsLeft: 0 };
    for (let l = 0; l < n; l++) {
      for (let c = 0; c < n; c++) {
        if (board[l][c] === '.') state.emptyCellsLeft++;
      }
    }
    return state;
  }
};