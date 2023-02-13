/**
 * @param {string} s
 * @return {string[]}
 */
//TODO test it
const findRepeatedDnaSequences = function (s) {
  const seqLength = 10;
  let l = 0, r = seqLength - 1;
  const map = new Map;

  while (r < s.length) {
    const currSeq = s.substr(l, seqLength);
    if (!map.has(currSeq)) {
      map.set(currSeq, 1);
    } else {
      map.set(currSeq, map.get(currSeq) + 1);
    }
    l++; r++;
  }
  
  const res = [];
  for (const seq of map.keys()) {
    if (map.get(seq) > 1) {
      res.push(seq);
    }
  }

  return res;
};