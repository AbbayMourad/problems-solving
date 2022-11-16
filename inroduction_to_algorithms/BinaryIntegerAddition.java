package inroduction_to_algorithms;

import java.util.Arrays;
import java.util.concurrent.ThreadLocalRandom;

public class BinaryIntegerAddition {
    /*
    BINARY-INTEGER-ADDITION(A, B)
        if A.length != B.length
            error
        n = A.length
        C[1..n+1]
        carry = 0
        for i = n down to 1
            s = A[i] + B[i] + carry
            C[i+1] = s % 2;
            carry = s / 2   // integer division
        C[1] = carry
        return C
     */
    static byte[] add(byte[] a, byte[] b) throws IllegalArgumentException {
        if (a.length != b.length)
            throw new IllegalArgumentException("Expected the two arrays to have the same length");
        int n = a.length;
        byte[] c = new byte[n + 1];
        byte carry = 0;
        for (int i = n - 1; i >= 0; i--) {
            byte s = (byte) (a[i] + b[i] + carry);
            c[i + 1] = (byte) (s % 2);
            carry = (byte) (s / 2);
        }
        c[0] = carry;
        return c;
    }

    static int binaryIntegerToDecimal(byte[] bits) {
        int decimalInt = 0;
        int n = bits.length;
        for (int i = n - 1; i >= 0; i--)
            decimalInt += Math.pow(2, n - 1 - i) * bits[i];
        return decimalInt;
    }

    static int generateRandomInt(int min, int max) {
        return ThreadLocalRandom.current().nextInt(min, max + 1);
    }

    static byte generateBit() {
        return (byte) ThreadLocalRandom.current().nextInt(0, 2);
    }

    static byte[] generateBinaryInteger(int numBits) {
        byte[] res = new byte[numBits];
        for (int i = 0; i < res.length; i++) res[i] = generateBit();
        return res;
    }

    static boolean isBinaryIntegerAdditionCorrect(byte[] a, byte[] b, byte[] c) {
        int aInDecimal = binaryIntegerToDecimal(a);
        int bInDecimal = binaryIntegerToDecimal(b);
        int cInDecimal = binaryIntegerToDecimal(c);
        return (aInDecimal + bInDecimal) == cInDecimal;
    }

    static void test(int maxNumBits) { // maxNumBits shouldn't exceed 30
        while (true) {
            int numBits = generateRandomInt(0, maxNumBits);
            byte[] a = generateBinaryInteger(numBits);
            byte[] b = generateBinaryInteger(numBits);
            byte[] c = add(a, b);
            if (!isBinaryIntegerAdditionCorrect(a, b, c)) {
                System.out.println("input:");
                System.out.println(Arrays.toString(a));
                System.out.println(Arrays.toString(b));
                System.out.println("output");
                System.out.println(Arrays.toString(c));
                break;
            } else {
                System.out.println("OK");
            }
        }
    }

    public static void main(String[] args) {
        test(30);
    }
}
