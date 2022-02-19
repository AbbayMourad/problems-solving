import java.util.Arrays;
import java.util.concurrent.ThreadLocalRandom;
import java.util.List;
import java.util.ArrayList;


public class TwoLargestElements {

  // return [largest, second_largest]
  // 1.5*n comparaisons
  private static int[] twoLargestElementsBetter(int[] a) {
    int n = a.length;
    if (n < 2) return null;
    // for each pair memorize the position of the largest
    // this help reduce the problem size to n/2
    int N = (n+1)/2;
    int[] largIndexes = new int[N];
    
    int limit;
    if (n%2 == 0) {
      limit = N;
    } else {
      limit = N-1;
      largIndexes[N-1] = n-1;
    }
    for (int i = 0; i < limit; i++) {
      if (a[2*i] < a[2*i+1])
        largIndexes[i] = 2*i+1;
      else
        largIndexes[i] = 2*i;
    }

    // find the largest element
    int largEleInd = 0;
    for (int i = 0; i < N; i++) {
      int j = largIndexes[i];
      if (a[j] > a[largEleInd])
        largEleInd = j;
    }

    // find the second largest element
    // it help to think of it like solving the same problem by pulling the largest element
    int secLargEleInd;
    if (largEleInd%2 != 0) {
      secLargEleInd = largEleInd - 1;
    } else if (largEleInd == n-1) {
      secLargEleInd = 0; // any valid value
    } else {
      secLargEleInd = largEleInd + 1;
    }
    for (int i = 0; i < N; i++) {
      int j = largIndexes[i];
      if (j != largEleInd && a[j] > a[secLargEleInd])
        secLargEleInd = j;
    }

    return new int[]{a[largEleInd], a[secLargEleInd]};
  }

  // 2*n comparaisons
  private static int[] twoLargestElementsNaive(int[] a) {
    int n = a.length;
    if (n < 2) return null;
    int largEleInd = 0;
    for (int i = 1; i < n; i++) {
      if (a[i] > a[largEleInd]) 
        largEleInd = i;
    }

    int secLargEleInd = largEleInd == 0 ? 1 : 0;
    for (int i = 0; i < n; i++) {
      if (i != largEleInd && a[i] > a[secLargEleInd])
        secLargEleInd = i;
    }

    return new int[]{a[largEleInd], a[secLargEleInd]};
  }

  private static int[] generateTest(int N, int M) {
    int n = ThreadLocalRandom.current().nextInt(2, N+1);
    int[] a = new int[n];
    for (int i = 0; i < n; i++) {
      a[i] = ThreadLocalRandom.current().nextInt(M+1);
    }
    return a;
  }

  private static void stressTest(int N, int M) {
    while (true) {
      int[] a = generateTest(N, M);
      int[] result1 = twoLargestElementsNaive(a);
      int[] result2 = twoLargestElementsFast(a);
      if (Arrays.equals(result1, result2)) {
        System.out.println("OK");
      } else {
        System.out.println("Input: " + Arrays.toString(a));
        System.out.println("result1: " + Arrays.toString(result1));
        System.out.println("result2: " + Arrays.toString(result2));
        break;
      }
    }
  }

  public static void main(String[] args) {
    stressTest(100, 10_000);
  }

  // takes n+log(n)-2 comparaisons
  public static int[] twoLargestElementsFast(int[] a) {
    //step 1: find the largest + remember the elements that lost comparaison against the largest (n-1 comparaisons)  
    //step 2: the second largest is one of the elements that lost to the largest, so search in that list (log(n)-1)
    //step 3: return the answer
    List<List<Integer>> compared = new ArrayList<>(a.length);
    for (int i = 0; i < a.length; ++i)
      compared.add(new ArrayList<>());
  
    int k1 = findLargest(0, a.length-1, a, compared);

    int k2 = compared.get(k1).get(0);
    for (int i = 1; i < compared.get(k1).size(); i++) {
      int j = compared.get(k1).get(i);
      if (a[j] > a[k2])
        k2 = j;
    }

    return new int[]{a[k1], a[k2]};
  }

  // return the index of the largest ele
  private static int findLargest(int i, int j, int[] a, List<List<Integer>> compared) {
    if (i == j)
      return i;
    int k1 = findLargest(i, i + (j-i)/2, a, compared);
    int k2 = findLargest(1+i + (j-i)/2, j, a, compared);
    if (a[k1] > a[k2]) {
      compared.get(k1).add(k2);
      compared.get(k2).clear();
      return k1; 
    } else {
      compared.get(k2).add(k1);
      compared.get(k1).clear();
      return k2;
    }
  }

}