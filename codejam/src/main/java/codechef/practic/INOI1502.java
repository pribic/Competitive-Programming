package codechef.practic;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Collections;
import java.util.Scanner;
import java.util.StringTokenizer;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.HashSet;

import static java.lang.System.out;
import static java.util.stream.Collectors.joining;


/**
 * @author pribic (Priyank Doshi)
 * @see <a href="https://www.codechef.com/UARDP001/problems/INOI1502" target="_top">https://www.codechef.com/UARDP001/problems/INOI1502</a>
 * @since 01/07/21 7:04 PM
 */
public class INOI1502 {
  static FastScanner sc = new FastScanner(System.in);

  public static void main(String[] args) {
    int maxLimit = 150000;
    int[] spf = new int[maxLimit + 1];
    for (int i = 2; i < spf.length; i++) {
      if (spf[i] == 0)
        spf[i] = i;
      if ((long) i * i < spf.length) {
        for (int j = i * i; j < spf.length; j += i) {
          if (spf[j] == 0)
            spf[j] = i;
        }
      }
    }
    spf[1] = 1;
    try (PrintWriter out = new PrintWriter(System.out)) {
      int T = 1;//sc.nextInt();
      for (int tt = 1; tt <= T; tt++) {
        int n = sc.nextInt();
        int m = sc.nextInt();
        System.out.println(solve(n, m, spf));
      }
    }
  }

  private static int solve(int n, int mod, int[] spf) {
    if (n == 1)
      return 2;
    if (spf[n] == n) {
      return fastExpo(2, n, mod) - 2;
    }
    List<Integer> factors = fact(n);
    int ans = 1;
    for (int fact : factors) {
      ans += fastExpo(solve(fact, mod, spf), n / fact, mod) - 1;
      ans %= mod;
    }
    return ans;
  }

  private static List<Integer> fact(int n) {
    List<Integer> list = new ArrayList<>();
    for (int i = 2; i * i <= n; i++) {
      if (n % i == 0)
        list.add(i);
      if (i != n / i)
        list.add(n / i);
    }
    return list;
  }

  private static int fastExpo(int n, int m, int mod) {
    if (m == 1)
      return n;
    int half = fastExpo(n, m / 2, mod);
    half = (half * half) % mod;
    if (m % 2 == 1)
      half = (half * n) % mod;
    return half % mod;
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