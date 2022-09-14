import java.util.Arrays;
import java.util.Scanner;
import java.util.function.Function;

/**
 * ...#
 * #...
 * #.#.
 * #..#
 *
 * @author pribic (Priyank Doshi)
 * @since 03/04/21
 */
public class Sieve {
  public static void main(String[] args) {
    try (Scanner sc = new Scanner(System.in)) {
      int t = sc.nextInt();
      for (int tt = 0; tt < t; tt++) {
        int n = sc.nextInt();


        primes(n, Sieve::basicSieve1);
        primes(n, Sieve::basicSieve2);
        primes(n, Sieve::basicSieve3);
        Boolean[] isPrime = basicSieve3(1_000_000);
        for(int i = 2; i < isPrime.length; i++) {
          if(isPrime[i] && !miller_robin(i)) {
            System.out.println("incorrect result " + i);
          }
        }
        //primes(n, Sieve::basicSieve);

      }
    }
  }

  private static void primes(int n, Function<Integer, Boolean[]> sieve) {
    long start = System.currentTimeMillis();
    int primes = count(sieve.apply(n));
    System.out.println("total time = " + (System.currentTimeMillis() - start) + ", cnt=" + primes);
  }

  private static int count(Boolean[] basicSieve) {
    int cnt = 0;
    for (int i = 0, basicSieveLength = basicSieve.length; i < basicSieveLength; i++) {
      boolean b = basicSieve[i];
      if (i % 2 == 1 && b)
        cnt++;
    }
    return cnt;
  }

  private static Boolean[] basicSieve(int n) {
    Boolean[] primes = new Boolean[n + 1];
    Arrays.fill(primes, true);
    for (int i = 2; i <= n; i++) {
      for (int j = 2 * i; j <= n; j += i) {
        primes[j] = false;
      }
    }
    return primes;
  }

  /**
   * only traverse prime nums
   */
  private static Boolean[] basicSieve1(int n) {
    Boolean[] primes = new Boolean[n + 1];
    Arrays.fill(primes, true);
    for (int i = 2; i <= n; i++) {
      if (primes[i]) {
        for (int j = 2 * i; j <= n; j += i) {
          primes[j] = false;
        }
      }
    }
    return primes;
  }

  /**
   * traverse prime nums + upto root n
   */
  private static Boolean[] basicSieve2(int n) {
    Boolean[] primes = new Boolean[n + 1];
    Arrays.fill(primes, true);
    for (int i = 2; i * i <= n; i++) {
      if (primes[i]) {
        for (int j = 2 * i; j <= n; j += i) {
          primes[j] = false;
        }
      }
    }
    return primes;
  }

  /**
   * traverse only prime + upto root n + start with prime^2
   */
  private static Boolean[] basicSieve3(int n) {
    Boolean[] primes = new Boolean[n + 1];
    Arrays.fill(primes, true);
    for (int i = 2; i * i <= n; i++) {
      if (primes[i]) {
        for (int j = i * i; j <= n; j += i) {
          primes[j] = false;
        }
      }
    }
    return primes;
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