package kickstart.Y2021.round1B;

import java.util.Scanner;

/**
 * @author pribic (Priyank Doshi)
 * @since 19/04/21
 */
public class Solution {

  public static void main(String[] args) {
    try (Scanner sc = new Scanner(System.in)) {
      int T = sc.nextInt();
      for (int tt = 1; tt <= T; tt++) {
        System.out.print("Case #" + tt + ": ");
        long n = sc.nextLong();
        int rootN = (int) Math.ceil(Math.sqrt(n));
        //find a prime greater than rootN
        int prime1 = rootN + 1;
        while (true) {
          if (isPrime(prime1))
            break;
          prime1++;
        }
        int prime2 = rootN;
        while (true) {
          if (isPrime(prime2))
            break;
          prime2--;
        }
        int prime3 = prime2 - 1;
        while (true) {
          if (isPrime(prime3))
            break;
          prime3--;
        }
        if ((long)prime1 * prime2 <= n)
          System.out.println((long)prime1 * prime2);
        else
          System.out.println((long)prime2 * prime3);
      }
    }
  }

  private static boolean isPrime(int n) {
    for(int i = 2; i <= (int)Math.sqrt(n); i++) {
      if(n % i == 0)
        return false;
    }
    return true;
  }
  private static boolean miller_robin(int n) {
    if (n < 2) {
      return false;
    }
    int d = n - 1;
    int r = 0;
    while (d % 2 == 0) {
      d /= 2;
      r++;
    }
    for (int smallPrime : new int[]{2, 3, 5, 7, 11, 13, 17, 23, 29, 31, 37}) {
      if (n == smallPrime)
        return true;
      if (check_composite(n, smallPrime, d, r))
        return false;
    }
    return true;
  }

  private static boolean check_composite(long n, long smallPrime, long nWithout2, long powerOf2) {
    long x = binPower(smallPrime, nWithout2, n);
    if (x == 1 || x == n - 1) {
      return false;
    }
    for (int r = 1; r < powerOf2; r++) {
      x = x * x % n;
      if (x == n - 1)
        return false;
    }
    return true;
  }

  private static long binPower(long smallPrime, long nWithout2, long mod) {
    long result = 1;
    smallPrime = smallPrime % mod;
    while (nWithout2 > 0) {
      if (nWithout2 % 2 == 1) {
        result = result * smallPrime % mod;
      }
      smallPrime = smallPrime * smallPrime % mod;
      nWithout2 /= 2;
    }
    return result;
  }

}