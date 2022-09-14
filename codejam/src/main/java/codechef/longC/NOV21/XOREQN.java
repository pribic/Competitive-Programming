package codechef.longC.NOV21;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;
import java.util.stream.IntStream;
import java.util.stream.LongStream;

import static java.lang.System.out;
import static java.util.stream.Collectors.joining;


/**
 * @author pribic (Priyank Doshi)
 * @see <a href="https://www.codechef.com/NOV21B/problems/XOREQN" target="_top">https://www.codechef.com/NOV21B/problems/XOREQN</a>
 * @since 06/11/21 12:29 PM
 */
public class XOREQN {
  static FastScanner sc = new FastScanner(System.in);

  public static void main(String[] args) {
    try (PrintWriter out = new PrintWriter(System.out)) {
      int T = sc.nextInt();
      for (int tt = 1; tt <= T; tt++) {
        int n = sc.nextInt();
        long[] arr = new long[n];
        int maxlen = 0;
        long max = 0;
        for (int i = 0; i < n; i++) {
          arr[i] = sc.nextLong();
          maxlen = Math.max(maxlen, Long.toBinaryString(arr[i]).length());
          max = Math.max(max, arr[i]);
        }
        if (max < 1001 && n < 1001) { // small input
          int x = 0;
          while (!check(arr, x) && x < 2000) x++;
          System.out.println(x == 2000 ? -1 : x);
        } else { // large input
          int[] carry = new int[n];
          StringBuilder x = new StringBuilder();
          for (int i = 0; i < maxlen; i++) {
            int cntOne = countOnes(arr, carry, i);
            if (cntOne % 2 == 0) {
              x.append(0);
              propogateCarry(arr, carry, i, 0);
            } else {
              x.append(1);
              propogateCarry(arr, carry, i, 1);
            }
          }
          System.out.println(IntStream.of(carry).sum() == 0 ? Long.valueOf(x.reverse().toString(), 2) : -1);
        }
      }
    }
  }

  private static void propogateCarry(long[] arr, int[] carry, int idx, int add) {
    for (int i = 0; i < arr.length; i++) {
      long num = arr[i];
      carry[i] = ((int) ((num >> idx) & 1) + carry[i] + add) / 2;
    }
  }

  private static int countOnes(long[] arr, int[] carry, int idx) {
    int cnt = 0;
    for (int i = 0; i < arr.length; i++) {
      long num = arr[i];
      if ((((num >> idx) & 1) + carry[i]) == 1)
        cnt++;
    }
    return cnt;
  }

  private static boolean check(long[] arr, int x) {
    long xor = 0;
    for (long num : arr)
      xor = xor ^ (num + x);
    return xor == 0;
  }

  static class FastScanner {
    BufferedReader br;
    StringTokenizer st;

    public FastScanner(File f) {
      try {
        br = new BufferedReader(new FileReader(f));
      } catch (FileNotFoundException e) {
        e.printStackTrace();
      }
    }

    public FastScanner(InputStream f) {
      br = new BufferedReader(new InputStreamReader(f), 32768);
    }

    String next() {
      while (st == null || !st.hasMoreTokens()) {
        String s = null;
        try {
          s = br.readLine();
        } catch (IOException e) {
          e.printStackTrace();
        }
        if (s == null)
          return null;
        st = new StringTokenizer(s);
      }
      return st.nextToken();
    }

    boolean hasMoreTokens() {
      while (st == null || !st.hasMoreTokens()) {
        String s = null;
        try {
          s = br.readLine();
        } catch (IOException e) {
          e.printStackTrace();
        }
        if (s == null)
          return false;
        st = new StringTokenizer(s);
      }
      return true;
    }

    int nextInt() {
      return Integer.parseInt(next());
    }

    long nextLong() {
      return Long.parseLong(next());
    }

    double nextDouble() {
      return Double.parseDouble(next());
    }
  }
}