package inroduction_to_algorithms;

public class MergeSort {
    /*
    MERGE(A, p, q, r)
        // we assume A[p..q] and A[q+1..r] are sorted
        n1 = q - p + 1
        n2 = r - q
        let L1[1..n1] and R[1..n2] be new arrays
        for i = 1 to n1
            L[i] = A[p + i - 1]
        for j = 1 to n2
            R[j] = A[q + j]
        i = 1
        j = 1
        k = p
        while i <= n1 and j <= n2
            if L[i] <= R[i]
                A[k] = L[i]
                i++
            else
                A[k] = R[j]
                j++
            k++
        for i1 = i to n1
            A[k] = L[i1]
            k++
        for j1 = j to n2
            A[k] = R[j1]
            k++
     */

    /*
    Use mathematical induction to show that when n is an exact power of 2, the solution of the recurrence
    T(n) = {
        2 if n = 2
        2T(n/2) + n if n = 2^k, for k > 1
    }
    is T(n) = nlg(n).
    * for n = 2: T(n) = 2 = 2 * lg(2).
    * assuming: T(n) = n * lg(n).
    * let's prove that: T(2n) = 2n * lg(2n)
    T(2n) = 2T(n) + 2n = 2n * lg(n) + 2n = 2n(1 + lg(n)) = 2n(lg(2) + lg(n)) = 2n * lg(2n)
    Based on the recurrence principle: T(n) = n * lg(n) for n = 2^k and k > 0
     */

    /*
    We can express insertion sort as a recursive procedure as follows.
    In order to sort A[1..n], we recursively sort A[1..n-1] and then insert A[n] into the sorted array A[1..n-1].
    Write a recurrence for the running time of this recursive version of insertion sort.
    T(n) = T(n-1) + D(n) + C(n)
    D(n) = c1
    C(n) = teta(n) (
    T(n) = T(n-1) + teta(n)
    INSERTION-SORT(A, p, r)
        if p < r
            INSERTION-SORT(A, p, r-1)
            INSERT(A, p, r) // insert A[r] in the sorted array A[p..r-1]
    */

    /*
    Referring back to the searching problem (see Exercise 2.1-3), observe that if the sequence A is sorted, we can check
    the midpoint of the sequence against v and eliminate half of the sequence from further consideration.
    The binary search algorithm repeats this procedure, halving the size of the remaining portion of the sequence each time.
    * Write pseudocode, either iterative or recursive, for binary search.
    BINARY-SEARCH-RECURSIVE(A, v, l, r)
        if l <= r
            mid = the greatest integer less than or equal to (l+r)/2
            if A[mid] = v
                return mid
            elseif v < A[mid]
                return BINARY-SEARCH-RECURSIVE(A, v, l, mid-1)
            else // v > A[mid]
                return BINARY-SEARCH-RECURSIVE(A, v, mid+1, r)
        else
            return -1

    * Argue that the worst-case running time of binary search is teta(lg(n))
    worst case: n -> n/2 -> ... -> 1 (-> 0), T(0) = c
    T(n) = c + T(n/2), let c be the max cost of work at a level
         = 2*c + T(n/4)
         = k*c + T(n/(2^k))
         = c*lg(n) + T(1)
         = (c+1)*lg(n)
    T(n) = teta(lg(n))
     */

    /*
    Observe that the while loop of lines 5–7 of the INSERTION-SORT procedure in Section 2.1
    uses a linear search to scan (backward) through the sorted subarray A[1..j-1].
    Can we use a binary search (see Exercise 2.3-5) instead to improve
    the overall worst-case running time of insertion sort to teta(n*lgn)?
    we look for the first item (going backward) so that A[k] <= A[j]
    A[mid] < A[j], then? A[mid+1] maybe < A[j] as well

    example [1, 2, 6], we want to insert 8
    A[mid] = 2, 2 < 8, this means we can forget about the left side
    right side [6], A[mid] = 6 < 8, we skip the left side, but there will be no right side, so A[j] will stay in position j

    example A[1..j-1] = [1, 3, 4], A[j] = 2
    A[mid] = 3 > 2, forget about the right side
    [1], 2
    A[mid] = 1 < 2, we can't go further, this means A[j] should at position 1

    example A[1..j-1] = [1, 2, 9], A[j] = 8
    iteration1: l = 1, r = 3, mid = 2, A[mid] = 2 < 8 -> go right
    iteration2: l= mid+1 = 3, r = 3, mid = 3, A[mid] = 9 > 8 -> go left
    iteration3: l = 3, r = mid - 1 = 2, l > r -> stop
    where to insert A[j]? position 3
    where is this info? maybe l? maybe when we are left with one item the decision (go left/right) indicate the answer
    if the decision is go left, we insert at position_last_ele (A[j] < last_ele)
    if decision is go right, we insert at position_last_ele + 1 (A[j] > last_ele)

    implementation? is there a need for a moving elements?

    after all, the worst case running time for the INSERT procedure is teta(j), the while loop is teta(lg(j)) and the for loop is teta(j),
    the sum is teta(j).

    Even tough we can use binary search to find the position where we can insert A[j], we still need to perform a
    linear loop to move some items to make place for A[j]. the number of operation we need to insert A[j] is j + lg(j) which is greater than j.
    So, this solution is not better than the linear scan.

    INSERT(A, j)
        // insert A[j] into the sorted subarray A[1..j-1]
        l = 1
        r = j-1
        while l < r
            mid = the largest integer less than or equal to (l+r)/2
            if A[mid] < A[j]
                l = mid + 1
            else
                r = mid -1
        // l = r
        // p: the position where we can insert A[j]
        p
        if A[l] < A[j]
            p = l + 1
        else
            p = l

        for i = j -1 downto p
            A[i+1] = A[i]
        A[p] = A[j]
     */

    /*
    Describe a teta(n*lgn)-time algorithm that, given a set S of n integers and another integer x,
    determines whether or not there exist two elements in S whose sum is exactly x.

    example: S = {1, 2, 3, 4}, x = 7 -> yes, 3 + 4 = 7

    TWO-SUM-SEARCH(A, x)
        n = A.length
        for i = 1 to n
            if BINARY-SEARCH(A, x - A[i], i + 1, n) != NIL
                return true
        return false

    running time is: teta(n*lg(n))
     */
}
