package inroduction_to_algorithms;

public class SelectionSort {
    /*
    SELECTION-SORT(A)
        n = A.length                // c1
        for i = 1 to n-1            // c2 * n
            min = i                 // c3 * (n-1)
            for j = i+1 to n        // c4 * (n-i+1)
                if A[j] < A[min]    // c5 * (n-i)
                    min = j         // c6 * (n-i)
            swap A[i] and A[min]    // c7 * (n-1)


    For this algorithm, there is no best-case or worst-case. The running time is the same for any input of size n.

    running time?
    c1 + c2*n + c3*(n-1) + c4*(n + n-1 + ... + 1) + (c5 + c6 + c7)*(n-1 + n-2 + ... + 1)
    = c1 + c2*n + c3*(n-1) + c4*n(n+1)/2 + (c5 + c6 + c7)*n(n-1)/2
    = c1 + c2 * n + c3 * n - c3 + c4 * (n² + n)/2 + (c5 + c6 + c7) * (n² - n)/2
    = c1 + c2 * n + c3 * n - c3 + (c4 / 2) * n² + (c4 / 2) * n + ((c5 + c6 + c7) / 2) * n² - ((c5 + c6 + c7) / 2) * n
    = c1 - c3 + c2 * n + c3 * n + (c4 / 2) * n - ((c5 + c6 + c7) / 2) * n + (c4 / 2) * n² +  ((c5 + c6 + c7) / 2) * n²
    = a + b*n + c*n²
    = teta(n²)

    loop invariant?
    A[1..i] is sorted
    * Initialization: for i = 1, we have one element A[1], so A[1..i] is sorted.
    * Maintenance: in iteration i, we find the smallest element in A[i..n] and we exchange it with A[i]
    so, before the iteration i+1, we have A[i] = min A[i+1..n], as a result A[i] <= A[i+1].
    as A[1..i] is sorted (assumption), we conclude that A[1..i+1] is sorted.
    * Termination: the outer loop terminate when i = n. Using loop invariant, we will have A[1..n] sorted, which proves
    the correctness of the algorithm.

    Why does it need to run for only the first n-1 elements?
    using loop invariant, before iteration i = n, we have A[1:n] is sorted. So, no need to do an iteration for i = n.

    For this algorithm, there is no best-case nor worst-case. The running time is the same for any input of size n.
     */
}
