package codeforce.div2.r712;

import java.util.Arrays;
import java.util.Scanner;

/**
 * 5
 * 10
 * 01 00 10 1100
 * 01 00 10 1100
 * 
 * 10 11 0 1 00 00
 * 01 00 1 0 11 00
 * <p>
 * <p>
 * 4
 * 0000
 * 0000
 * 3
 * 001
 * 000
 * <p>
 * 12
 * 1010 10 01 1010
 * 1001 10 01 1010
 * 6
 * 1110 00
 * 1101 00
 *
 * @author pribic (Priyank Doshi)
 * @since 03/04/21
 */
public class B {

  public static void main(String[] args) {
    try (Scanner sc = new Scanner(System.in)) {
      int T = sc.nextInt();
      for (int tt = 1; tt <= T; tt++) {
        int n = sc.nextInt();
        String a = sc.next();
        String b = sc.next();
        int[] diff = new int[n];
        int curDiff = 0;
        for (int i = 0; i < n; i++) {
          if (a.charAt(i) == '1') {
            curDiff++;
          } else {
            curDiff--;
          }
          diff[i] = curDiff;
        }
        //System.out.println("Arrays.toString(diff) = " + Arrays.toString(diff));
        boolean possible = true;
        int flips = 0;
        for (int i = n - 1; i >= 0; i--) {
          if (mapFlip(a.charAt(i), flips) != b.charAt(i)) {
            if (diff[i] != 0) {
              possible = false;
              break;
            } else {
              flips++;
            }
          }

        }
        System.out.println(possible ? "YES" : "NO");
      }
    }
  }

  private static char mapFlip(char c, int flips) {
    if (flips % 2 == 0) return c;
    else return c == '1' ? '0' : '1';
  }
}