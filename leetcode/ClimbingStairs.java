package leetcode;

public class ClimbingStairs {
    /*
    You are climbing a staircase. It takes n steps to reach the top.
    Each time you can either climb 1 or 2 steps. In how many distinct ways can you climb to the top?
     */

    /*
    n: is the number of stairs. It's also the number of steps to reach the top.
    stairs are indexed from 1 to n

    example1: n = 1 -> one way to the top

    example: n = 2 -> 2 ways, 0->2 or 0->1->2 (0: represent the ground level)

    example: n = 3 -> ? ways, 0->1->2->3, 0->2->3, 0->1->3

    // s: is a stair index, 0 <= s <= n-3
    // n: is the number of stairs
    numWaysToTop(s) = numWaysToTop(s+1) + numWaysToTop(s+2)
    numWaysToTop(n-1) = 1
    numWaysToTop(n-2) = 2
     */

    /*
    // when first calling this procedure, the value of stairIndex will be 0 (ground level)
    NUM-OF-WAYS-TO-TOP(stairIndex, numOfStairs)
        if stairIndex >= numOfStairs - 1
            // numOfStairs-1 -> 2, numOfStairs-1 -> 1
            return numOfStairs - stairIndex
        else
            return NUM-OF-WAYS-TO-TOP(stairIndex + 1, numOfStairs) + NUM-OF-WAYS-TO-TOP(stairIndex + 2, numOfStairs)

    running time: T(n) is the worst case running time and c the max cost of work done in each level
    T(n) = T(n-1) + T(n-2) + c
    using recurrence tree: levels 0 -> n-2, amount of work in levels 0->n-3: leq pow(2, l) * c, amount of work in last level: 2*c
    T(n) = teta(pow(2, n-2))

    We solve the same problem multiple times. A bottom-top approach can solve this. We know the answer for stair n-1 and n-2.
    We can calculate the answer for stair n-3, then n-4 and so on until we know the answer for stair 0 (ground level).
     */

    /*
    NUM-OF-WAYS-TO-TOP(numOfStairs)
        if n < 3
            return n
        // numOfWaysToTop[i]: is the number of ways to top starting from stair that has index i
        // first stair has index 1. the stair before the last, has index numOfStairs-1.
        // the numbers of ways to top starting from ground (can be seen as stair of index 0) is: numOfWaysToTop[1] + numWaysToTop[2]
        numOfWaysToTop[1..numOfStairs-1]
        numOfWaysToTop[numOfStairs - 1] = 1
        numOfWaysToTop[numOfStairs - 2] = 2
        for i = n-3 downto 1
            numOfWaysToTop[i] = numOfWaysToTop[i+1] + numOfWaysToTop[i+2]
        return numOfWaysToTop[1] + numOfWaysToTop[2]
     */
    public int numOfWaysToTop(int numOfStairs) {
        if (numOfStairs < 3)
            return numOfStairs;
        int[] numOfWaysToTop = new int[numOfStairs-1];
        numOfWaysToTop[numOfStairs-2] = 1;
        numOfWaysToTop[numOfStairs-3] = 2;
        for (int i = numOfStairs-4; i >=0 ; i--) {
            numOfWaysToTop[i] = numOfWaysToTop[i + 1] + numOfWaysToTop[i + 2];
        }
        return numOfWaysToTop[0] + numOfWaysToTop[1];
    }

    /*
    We can do it without an array, because in this case we only need the final answer. (not working for now)

    NUM-OF-WAYS-TO-TOP(numOfStairs)
        numWaysToTopFromStairOneStepAway = 1
        numWaysToTopFromStairTwoStepAway = 2
        numWaysToTopFromCurrStair = numOfStairs
        for currStair = numOfStairs - 3 to 0
            numWaysToTopFromCurrStair = numWaysToTopFromStairOneStepAway + numWaysToTopFromStairTwoStepAway
            numWaysToTopFromStairTwoStepAway = numWaysToTopFromStairOneStepAway
            numWaysToTopFromStairOneStepAway = numWaysToTopFromCurrStair
        return numWaysToTopFromCurrStair
     */
    public int numOfWaysToTop2(int numOfStairs) {
        int numWaysToTopFromStairOneStepAway = 1;
        int numWaysToTopFromStairTwoStepAway = 2;
        int numWaysToTopFromCurrStair = numOfStairs;
        for (int currStair = numOfStairs-3; currStair >= 1; currStair--) {
            numWaysToTopFromCurrStair = numWaysToTopFromStairOneStepAway + numWaysToTopFromStairTwoStepAway;
            numWaysToTopFromStairTwoStepAway = numWaysToTopFromStairOneStepAway;
            numWaysToTopFromStairOneStepAway = numWaysToTopFromCurrStair;
        }
        return numWaysToTopFromStairOneStepAway + numWaysToTopFromStairTwoStepAway;
    }
}
