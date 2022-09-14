package codeforce.globalround.y2021.r17;

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
 * @see <a href="https://codeforces.com/contest/1610/problem/D" target="_top">https://codeforces.com/contest/1610/problem/D</a>
 * @since 23/11/21 10:56 PM
 */
public class D {
  static FastScanner sc = new FastScanner(System.in);
  //static int mod = (int) 1e9 + 7;
  static int mod = 5;

  public static void main(String[] args) {
    try (PrintWriter out = new PrintWriter(System.out)) {
      int T = 1;//sc.nextInt();
      for (int tt = 1; tt <= T; tt++) {
        int oddc = 0;
        int evenc = 0;
        int n = sc.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
          arr[i] = sc.nextInt();
          if (arr[i] % 2 == 0)
            evenc++;
          else
            oddc++;
        }
        int total = 0;
        for (int i = 1; i <= oddc; i++) {
          total = add(total, ncr(oddc, i));
        }
        int totalO = total;
        System.out.println(modInv(4));
        System.out.println("modInv(1) = " + modInv(1));
        System.out.println("modInv(2) = " + modInv(2));
        System.out.println("3C2=" + ncr(3, 2));
        System.out.println("totalO = " + totalO);
        for (int i = 2; i <= evenc; i += 2) {
          total = add(total, ncr(evenc, i));
          System.out.println("total = " + total);
          //total = add(total, mul(ncr(evenc, i), totalO));
        }
        System.out.println(total);

      }
    }
  }

  private static int ncr(int n, int r) {
    if (n == r || r == 0)
      return 1;
    return mul(fact(n), modInv(fact(n - r)), modInv(fact(r)));
  }

  private static int mul(int... arr) {
    int ans = 1;
    for (int num : arr)
      ans = mul(num, ans);
    return ans;
  }

  static int fact(int n) {
    int f = 1;
    for (int i = 2; i <= n; i++) {
      f = mul(i, f);
    }
    return f;
  }

  static int add(int a, int b) {
    return (a + b) % mod;
  }

  static int sub(int a, int b) {
    return (a - b + mod) % mod;
  }

  static int mul(long a, long b) {
    return (int) ((a * b) % mod);
  }

  static int div(long a, long b) {
    return (int) ((mul(a, modInv(b))) % mod);
  }

  static private int modInv(long n) {
    return fastExpo(n, mod - 2);
  }

  private static int fastExpo(long n, int power) {
    if (power == 0)
      return 1;
    int res = fastExpo(n, power / 2) % mod;
    int resDouble = mul(res, res) % mod;
    if (power % 2 == 0) {
      return resDouble;
    } else {
      return mul(n, res);
    }
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