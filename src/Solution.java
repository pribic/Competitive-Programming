import java.io.*;
import java.math.*;
import java.text.*;
import java.util.*;
import java.util.regex.*;

public class Solution {


  // Complete the distinctNumbers function below.
  static int distinctNumbers(int n, int k) {
    boolean[] primeNumbers = new boolean[n + 1];
    findPrimeTill(n, primeNumbers);
    List<Integer> primes = new ArrayList<>(n);
    
    for (int i = 0; i < primeNumbers.length; i++) {
      if (primeNumbers[i])
        primes.add(i);
    }
    System.out.println(Arrays.deepToString(primes.toArray()));
    // Return the number of distinct numbers you can get and return the answer modulo 1000000007
    int ans = 0;
    for (int i = 1; i <= n; i++) {
      ans = ans % 1000000007 + ((i % 1000000007) * (findQ(n, k, i, primes) % 1000000007)) % 1000000007;
    }
    return ans % 1000000007;

  }

  private static void findPrimeTill(int n, boolean[] primeNumbers) {
    Arrays.fill(primeNumbers, true);
    primeNumbers[0] = false;
    primeNumbers[1] = false;
    for (int multiplier = 2; multiplier <= n / 2; multiplier++) {
      int start = multiplier + multiplier;
      while (start <= n) {
        primeNumbers[start] = false;
        start = start + multiplier;
      }
    }

  }

  private static int findQ(int n, int k, int i, List<Integer> primeNumbers) {
    int originianlI = i;
    Set<Integer> distinctNumbers = new HashSet<>();
    distinctNumbers.add(i);
    for (int prime : primeNumbers) {
      i = originianlI;
      for (int ii = 1; ii <= k; ii++) {
        if (i % prime == 0) {
          i = i % prime;
          distinctNumbers.add(i);
        } else {
          i = i * prime;
          distinctNumbers.add(i);
        }
      }
    }
    return distinctNumbers.size();
  }

  private static final Scanner scanner = new Scanner(System.in);

  public static void main(String[] args) throws IOException {
    /* TODO: fix this stuff \u002a\u002f\u0053\u0079\u0073\u0074\u0065\u006d\u002e\u006f\u0075\u0074\u002e\u0070\u0072\u0069\u006e\u0074\u006c\u006e\u0028\u0022\u0048\u0065\u006c\u006c\u006f\u0020\u0057\u006f\u0072\u006c\u0064\u0021\u0022\u0029\u003b\u002f\u002a */
    //BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

    String[] nk = scanner.nextLine().split(" ");

    int n = Integer.parseInt(nk[0]);

    int k = Integer.parseInt(nk[1]);

    int result = distinctNumbers(n, k);

   // bufferedWriter.write(String.valueOf(result));
   // bufferedWriter.newLine();

   //4 bufferedWriter.close();

    scanner.close();
  }
}
