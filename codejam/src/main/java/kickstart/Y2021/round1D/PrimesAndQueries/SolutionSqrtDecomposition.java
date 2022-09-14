package kickstart.Y2021.round1D.PrimesAndQueries;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

import static java.util.stream.Collectors.joining;


/**
 * @author pribic (Priyank Doshi)
 * @see <a href="https://codingcompetitions.withgoogle.com/kickstart/round/00000000004361e3/000000000082bcf4" target="_top">https://codingcompetitions.withgoogle.com/kickstart/round/00000000004361e3/000000000082bcf4</a>
 * @since 11/07/21 12:22 PM
 */
public class SolutionSqrtDecomposition {
  static FastScanner sc = new FastScanner(System.in);

  public static void main(String[] args) {
    try (PrintWriter out = new PrintWriter(System.out)) {
      int T = sc.nextInt();
      for (int tt = 1; tt <= T; tt++) {
        System.out.print("Case #" + tt + ": ");
        int n = sc.nextInt();
        int q = sc.nextInt();
        int p = sc.nextInt();
        long[] arr = new long[n];
        for (int i = 0; i < n; i++) {
          arr[i] = sc.nextLong();
        }
        int sqrt = (int) Math.sqrt(n) + 1;
        long[][] block = new long[4][sqrt];
        for (int s = 0; s < 4; s++) {
          long[] curblock = block[s];
          for (int i = 0; i < n; i++) {
            int blockId = i / sqrt;
            curblock[blockId] += calcV(p, arr[i], s + 1);
          }
        }
        while (q-- > 0) {
          int type = sc.nextInt();
          if (type == 1) {
            int pos = sc.nextInt() - 1;
            int newVal = sc.nextInt();
            for (int s = 0; s < 4; s++) {
              long[] curblock = block[s];
              int blockId = pos / sqrt;
              curblock[blockId] -= calcV(p, arr[pos], s + 1);
              curblock[blockId] += calcV(p, newVal, s + 1);
            }
            arr[pos] = newVal;
          } else {
            int s = sc.nextInt();
            int l = sc.nextInt() - 1;
            int r = sc.nextInt() - 1;
            long ans = 0;
            int leftBlockId = l / sqrt;
            int rightBlockId = r / sqrt;
            if (leftBlockId == rightBlockId) {
              for (int i = l; i <= r; i++) {
                ans += calcV(p, arr[i], s);
              }
            } else {
              for (int i = l; i <= r; ) {
                if (i % sqrt == 0 && (i + sqrt <= r)) { //check if a block starts here and if this entire block is part of query range
                  ans += block[s - 1][i / sqrt];
                  i += sqrt;
                } else {
                  ans += calcV(p, arr[i], s);
                  i++;
                }
              }
            }

            System.out.print(ans + " ");
          }
        }
        System.out.println();
      }
    }
  }

  private static long calcV(int p, long num, int s) {
    long ans = 0;
    long diff = (long) (Math.pow(num, s) - Math.pow(num % p, s));
    while (diff > 0 && diff % p == 0) {
      ans++;
      diff /= p;
    }
    return ans;
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