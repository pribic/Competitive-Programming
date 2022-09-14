package kickstart.Y2021.round1C.SmallerStrings;

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
 * @see <a href="https://codingcompetitions.withgoogle.com/kickstart/round/0000000000435c44/00000000007ebe5e" target="_top">https://codingcompetitions.withgoogle.com/kickstart/round/0000000000435c44/00000000007ebe5e</a>
 * @since 23/05/21 4:33 PM
 */
public class Solution {
  static FastScanner sc = new FastScanner(System.in);

  public static void main(String[] args) {
    try (PrintWriter out = new PrintWriter(System.out)) {
      int T = sc.nextInt();
      for (int tt = 1; tt <= T; tt++) {
        System.out.print("Case #" + tt + ": ");
        int n = sc.nextInt();
        int k = sc.nextInt();
        String str = sc.next();
        long[] maxPalindrome = new long[n + 1];
        maxPalindrome[1] = k;
        maxPalindrome[0] = 1;
        if (n > 1)
          maxPalindrome[2] = k;
        for (int i = 2; i < maxPalindrome.length; i++) {
          maxPalindrome[i] = mul(k, maxPalindrome[i - 2]);
        }
        long ans = 0;
        int len = n;
        for (int i = 0; i < (n + 1) / 2; i++) {
          ans = add(ans, mul(str.charAt(i) - 'a' , maxPalindrome[len - 2]));
          len-= 2;
        }
        System.out.println(ans);
      }
    }
  }

  private static long add(long ans, long mul) {
    return (ans % MOD + mul % MOD ) % MOD;
  }


  private static final int MOD = 1000000000 + 7;

  private static long mul(long a, long b) {
    return ((a % MOD) * (b % MOD)) % MOD;
  }

 
  /*
  
  K----same as first 
  
  f(n) = K * f(n - 2)
       = K when n = 1
       = 0 when n = 0

3
2 3
bc

1 +     

5 5
abcdd

aa4aa

1 5
d


   */

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