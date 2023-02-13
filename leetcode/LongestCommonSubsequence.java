package leetcode;

import java.util.Arrays;

public class LongestCommonSubsequence {
    /*
    Given two strings text1 and text2, return the length of their longest common subsequence. If there is no common subsequence, return 0.

    A subsequence of a string is a new string generated from the original string with some characters (can be none) deleted
    without changing the relative order of the remaining characters.

    For example, "ace" is a subsequence of "abcde".

    A common subsequence of two strings is a subsequence that is common to both strings.
     */

    /*
    example: text1 = "abcde", text2 = "ace" -> The longest common subsequence is "ace" and its length is 3.

    subsequences of a string set of all subsets of the string.
    example: s = "ace" -> subsequences of s are: {"", "a", "c", "e", "ac", "ae", "ce", "ace"}

    An obvious solution is to generate all subsequences of each string, then find the largest common.
    LARGEST-COMMON-SUBSTRING (s1, s2)
        s1_sub_sequences = GET-SUB-SEQUENCES(s1)                                            // c1 for calling,
        s2_sub_sequences = GET-SUB-SEQUENCES(s2)
        largest_common_subsequence = GET-LARGEST-COMMON(s1_sub_sequences, s2_sub_sequences)
        return largest_common_subsequence.length

    GET-SUB-SEQUENCE(s)     // teta((pow(2, n) - n) * (n - 1)) = teta(n * pow(2, n))
        n = s.length
        subsequences = {}   // a set of strings
        for i = 0 to pow(2, n) - 1
            subsequence = ""
            for j = 0 to n-1
                if the bit j of i is set
                    subsequence.add(s[j])
            subsequences.add(subsequence)
        return subsequences

    We can't implement GET-SUB-SEQUENCE procedure as it is right now, because we need to compute pow(2, n) and n <= 1000,
    a value that's greater than what a long can hold (pow(2, 64) - 1).

    Clearly, we can do better.

    text1 = "abcde", text2 = "ace"
    text2 is the smaller
    text2[0] exists in text1
    solve the same problem for text2\{text2[0]} = "ce"
    if the first character is found return 1 + solution-of-the-sub-problem, else return solution-of-the-sub-problem

    LARGEST-COMMON-SUBSTRING(s1, s2)
        // we assume s2 is the smallest string
        v = 0
        if s1 contains s2[0]
            v = 1
        return v + LARGEST-COMMON-SUBSTRING(s1, s2\{s2[0]})

    play with more examples.
    s1="abc", s2="aa"
    s2[1] exists in s1[1..3] -> +1
    s2[2] doesn't exist in s1[2..3] -> +0
     we have an index j, that indicates where we are in s2
     we have an index i, that index that part of s1 where we search for s2[j]
     so, we check if s1[i..n1] contains s2[j]
     if yes, we increment i
     j will be incremented in each recursive call

     LARGEST-COMMON-SUBSTRING(s1, s2, i, j)

        if i > n1 OR j > n2
            return 0

        e = 0
        if s1[i..n1] contains s2[j]
             e = 1
             i = first index of s2[j] in s1 starting from i, plus 1
         j++

         return e + LARGEST-COMMON-SUBSTRING(s1, s2, i, j)

    LCS(s1, s2, i, j)

        if i > n1 OR j > n2
            return 0

        if s2[j] exists in s1[i..n1]
            newI = first index of s2[j] in s1 starting from i, plus 1
            return max(1 + LCS(s1, s2, newI, j+1), LCS(s1, s2, i, j+1))
        else
            return LCS(s1, s2, i, j+1)


    s1 = "abe", s2 = "eab"

     s1 = "oxcpqrsvwf", s2 = "shmtulqrypy"
     n1 = 10, n2 = 11
     expected = 2
     i = 1, j = 1, s1[i..n1] = "oxcpqrsvwf" contains s2[j] = 's', i = 2, j = 2      -> +1
     i = 2, j = 2, s1[i..n1] = "vwf" doesn't contains s2[j] = 'h', i = 2, j = 3     -> +0
     ...
     result = 1

     s1 = "shmtulqrypy", s2 = "oxcpqrsvwf"
     n1 = 11, n2 = 10
     i = 1, j = 1, s1[i..n1] = "shmtulqrypy" doesn't contain s2[j] = 'o', i = 1, j = 2  -> +0
     i = 1, j = 2, s1[i..n1] = "shmtulqrypy" doesn't contain s2[j] = 'x', i = 1, j = 3  -> +0
     i = 1, j = 3, s1[i..n1] = "shmtulqrypy" doesn't contain s2[j] = 'c', i = 1, j = 4  -> +0
     i = 1, j = 4, s1[i..n1] = "shmtulqrypy" contains s2[j] = 'p', i = 10, j = 5        -> +1
     i = 10, j = 5, s1[i..n1] = "y" does not contains s2[j] = 'r', i = 10, j = 6        -> +0
     It will continue like this until j > n2
     result = 1

     We are missing "qr" which is a common subsequence and the largest one.

     Maybe the answer is the max of:
     * the largest common subsequence that starts with s2[1]
     * the lcs that starts with s2[2]
     * etc



     s1 = "ezupkr", s2 = "ubmrapg"
     n1 = 6, n2 = 7
     i = 1, j = 1, s1[1..6] = "ezupkr" contains s2[1] = 'u', i = 4, j = 2       -> +1
     i = 4, j = 2, s1[4..6] = "pkr" doesn't contain s2[2] = 'b', i = 4, j = 3   -> +0
     ...
     i = 4, j = 5, s1[4..6] = "pkr" doesn't contain s2[5] = 'a', i = 4, j = 6   -> +0
     i = 4, j = 6, s1[4..6] = "pkr" contains s2[6] = 'p', i = 5, j = 7          -> +1
     i = 5, j = 7, s1[5..6] = "kr" doesn't contain s2[7], i = 5, j = 8          -> +0
     i = 5, j = 8, (i> n1 OR j > n2) is true                                    -> +0
     Algo seems right, maybe the implementation is wrong

     s1 = "abcde", s2 = "ace"
     n1 = 5, n2 = 3
     i = 1, j = 1, s[1..5] = "abcde" contains s2[1] = 'a', i = 2, j = 2 -> +1
     i = 2, j = 2, s[2..5] = "bcde" contains s2[2] = 'c', i = 4, j = 3  -> +1
     i = 4, j = 3, s[4..5] = "de" contains s2[3] = 'e', i = 6, j = 4    -> +1
     i = 6, j = 4, (i > n1 OR j > n2) is true, -> 0
     final result will be 3 (correct)
     */

    /*
    LCS(s1, s2, i, j)

        if i > n1 OR j > n2
            return 0

        if LCS(i, j) is already computed
            return the computed value
        res
        if s2[j] exists in s1[i..n1]
            newI = first index of s2[j] in s1 starting from i, plus 1
            res = max(1 + LCS(s1, s2, newI, j+1), LCS(s1, s2, i, j+1))
        else
            res = LCS(s1, s2, i, j+1)

        store the value of res as LCS(i, j)

        return res
     */

    /*
    There is another way to solve this problem, using dynamic programming.
    DP is similar to divide-and-conquer in that we divide the problem into sub-problems.
    In divide-and-conquer the sub-problems are independent, but in DP they are not, they share sub-sub-problems.
    DP makes sure we solve a problem once and reuse the result when the problem appear again.

    To understand this approach, let's consider some examples.

    A = "abcde", B = "abc"
    A[0] = B[0], LCS(0, 0) = 1 + LCS(1, 1)
    ...
    we will get 3 (correct).

    A = "ab", B = "ca"
    A[0] diff B[0], LCS(0, 0) = max( LCS(0, 1), LCS(1, 0) )
    LCS(0, 1) = 1 + LCS(1, 2), LCS(1, 2) = 0

    In general:
    we start with i = j = 0
    lcs(i, j) = 1 + lcs(i+1, j+1), if A[i] = B[j]
    lcs(i, j) = max(lcs(i, j+1), lcs(i+1, j)), otherwise
    lcs(i, j) = 0, if i >= n1 or j >= n2

    can be solved in top-bottom approach, which can be improved by avoiding solving the same problem more than once.
    can be solved in bottom-up approach, by filling the matrix from bottom to top (i: n1 - 1 -> 0) and from right to left (j: n2 - 1 -> 0)
     */


    static int[][] m;
    static int lcs(String s1, String s2, int i, int j) {
        int n1 = s1.length(), n2 = s2.length();
        if (i >= n1 || j >= n2) {
            return 0;
        }
        int lcs = m[i][j];
        if (lcs != -1) {
            return lcs;
        }
        String s1SubStr = s1.substring(i);
        String s2SubStr = s2.substring(j, j + 1);
        if (s1SubStr.contains(s2SubStr)) {
            int newI = s1.indexOf(s2SubStr, i) + 1;
            lcs = Math.max(1 + lcs(s1, s2, newI, j + 1), lcs(s1, s2, i, j + 1));
        }
        else {
            lcs = lcs(s1, s2, i, j + 1);
        }
        m[i][j] = lcs;
        return lcs;
    }

    static int lcs(String s1, String s2) {
        m = new int[s1.length()][s2.length()];
        for (int i = 0; i < s1.length(); i++) {
            for (int j = 0; j < s2.length(); j++) {
                m[i][j] = -1;
            }
        }
        return lcs(s1, s2, 0, 0);
    }

    public static void main(String[] args) {
        int res = lcs("pgq", "qpg");
        assert res == 2;
        System.out.println(res);

        res = lcs("ezupkr", "ubmrapg");
        assert res == 2;
        System.out.println(res);

        res = lcs("oxcpqrsvwf", "shmtulqrypy");
        assert res == 2;
        System.out.println(res);

        res = lcs("ylqpejqbalahwr", "yrkzavgdmdgtqpg");
        assert res == 3;
        System.out.println(res);
    }






    // in the algo: i: 1 -> n1
    // in the impl: i: 0 -> n1 - 1
    int largestCommonSubstring(String s1, String s2, int i, int j) {
        int n1 = s1.length(), n2 = s2.length();
        if (i >= n1 || j >= n2)
            return 0;
        int e = 0;
        String s1SubStr = s1.substring(i);
        String s2SubStr = s2.substring(j, j + 1);
        if (s1SubStr.contains(s2SubStr)) {
            e = 1;
            i = s1.indexOf(s2SubStr, i) + 1;
            //i = s1.indexOf(s2SubStr) + 1;
        }
        j++;
        return e + largestCommonSubstring(s1, s2, i, j);
    }

    static int largestCommonSubstring(String s1, String s2) {
        int lcs = 0;
        LongestCommonSubsequence LCS = new LongestCommonSubsequence();
        for (int j = 0; j < s2.length(); j++) {
            lcs = Math.max(lcs, LCS.largestCommonSubstring(s1, s2, 0, j));
        }
        return lcs;
    }

}
