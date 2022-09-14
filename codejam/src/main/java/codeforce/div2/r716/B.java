package codeforce.div2.r716;

import java.util.Scanner;

/**
 * @author pribic (Priyank Doshi)
 * @since 19/04/21
 */
public class B {

  public static void main(String[] args) {
    System.out.println(calc(2, 2));
    System.out.println(calc(100000, 20));
    try (Scanner sc = new Scanner(System.in)) {
      int T = sc.nextInt();
      for (int tt = 1; tt <= T; tt++) {

      }
    }
  }

  static int MOD = 1_000_000_000 + 7;

  private static long calc(long n, long k) {
    //nP2 * (n - 2)! * k 

    long nFact = 1;
    long nMinus2Fact = 1;
    for (int i = 1; i <= n; i++) {
      if (i <= n - 2) {
        nMinus2Fact = modMul(nMinus2Fact, i);
      }
      nFact = modMul(nFact, i);
    }
    return modMul(modMul(nFact, k), nMinus2Fact);
  }

  private static long modMul(long a, long b) {
    return ((a % MOD) * (b % MOD)) % MOD;
  }

  private static int modAdd(int a, int b) {
    return ((a % MOD) + (b % MOD)) % MOD;
  }
}
/**
 * 1111 - 15
 * 1110 - 14
 * 1101 - 13
 * 1011 - 11
 * 0111 - 7
 * 0000 - 0
 * <p>
 * 0011 - max sum  is 15
 * 1100
 * <p>
 * //we need at-least 1 zero in each k bit position...
 * //we need at-least 1 one in each k bit position...
 * <p>
 * 1 0 0/1 0/1 0/1  nP2 * (n - 2)! * k
 * <p>
 * 2P2 * 2 -> 2 * 2 = 4
 * <p>
 * 100000 20 -> 100000P2 * (100000-2)! *
 */
