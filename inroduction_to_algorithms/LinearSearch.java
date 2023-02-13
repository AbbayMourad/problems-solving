package inroduction_to_algorithms;

public class LinearSearch {
    /*
    SEARCH(A, v)
        for i = 1 to A.length   // c1 * num-of-ele-checked
            if A[i] == v        // c2 * num-of-ele-checked
                return i        // c3
        return NIL              // c4

    Best case: A[1] == v, we check one element
    Worst case: A[n] == v or array A doesn't contain v, we check n elements

    How many elements of the input sequence need to be checked on the average, assuming that the element being
    searched for is equally likely to be any element in the array?
    P(A[1] == v) * 1 + P(A[2] == v) * 2 + ... + P(A[n] == v) * n    // P[A[i] == v] = 1/n
    = 1/n * (1 + ... + n)
    = (1/n) * n(n + 1)/2
    = (n + 1) / 2
    = (best-case + worst-case) / 2

    Average-case running time?
    On average, we check (n+1)/2 elements, the running time is:
    c1 *(n+1)/2 + c2*(n+1)/2 + c3 + c4
    = ((c1+c2)/2)*n + (c1+c2)/2 + c3 + c4
    = a*n + b

    Worst-case running time?
    In worst-case, we check n elements, the running time is:
    c*n + d


     */
}
