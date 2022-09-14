package codeforce.div2.r717;

import java.util.Scanner;

/**
 * first observation : if we make all numbers same somehow then we got it
 * second observation : if we make all numbers equal - say 10 , then we can reduce them to 2
 * or if we make them to odd count, say 11, then we can reduce them to 3 and yet we can answer YES
 * <p>
 * 00010101   when we have a position where bit in both numbers are diff, then output is 1, if both bits are same , then output is 0
 * 11010010
 * --------
 * 11000111
 * <p>
 * 00
 * 10
 * 10
 * <p>
 * 010
 * 011
 * 100
 *
 * @author pribic (Priyank Doshi)
 * @since 21/04/21
 */
public class B {

  public static void main(String[] args) {
    try (Scanner sc = new Scanner(System.in)) {
      int T = sc.nextInt();
      for (int tt = 1; tt <= T; tt++) {
        int n = sc.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
          arr[i] = sc.nextInt();
        }
        int xorSoFar = 0;
        boolean foundAns = false;
        for (int i = 0; !foundAns && i < n; i++) {
          //here i indicates end of first sub array which is our base
          xorSoFar = xorSoFar ^ arr[i]; // give xor of [0,i] range
          int j = i + 1;
          while (!foundAns && j < n) {
            int runningXor = 0;
            boolean foundXor = false;
            for (; j < n; j++) {
              runningXor ^= arr[j];
              if (runningXor == xorSoFar) {
                foundXor = true;
              }
              if(foundXor && runningXor != xorSoFar) {
                break;
              }
            }
            if (foundXor && j == n) {
              foundAns = true;
            }
          }
        }
        System.out.println(foundAns ? "YES" : "NO");
      }
    }
  }
}

// 1 2 3 4 5
// 1, 1^2, 1^2^3, 1^2^3^4, 1^2^3^4^5
  
/*
0 0 - 0 
0 1 - 1
1 0 - 1
1 1 - 0
 */
