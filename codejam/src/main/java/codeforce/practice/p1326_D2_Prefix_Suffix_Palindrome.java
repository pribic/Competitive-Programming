package codeforce.practice;

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
 * @see <a href="https://codeforces.com/problemset/problem/1326/D2" target="_top">https://codeforces.com/problemset/problem/1326/D2</a>
 * @since 04/12/21 10:38 PM
 */
public class p1326_D2_Prefix_Suffix_Palindrome {
  static FastScanner sc = new FastScanner(System.in);
  static long[] power;

  public static void main(String[] args) {
    buildPower();
    try (PrintWriter out = new PrintWriter(System.out)) {
      int T = sc.nextInt();
      for (int tt = 1; tt <= T; tt++) {
        String s = sc.next();
        if (s.length() == 1)
          System.out.println(s);
        else {
          int l = 0;
          int r = s.length() - 1;
          while (l < r && s.charAt(l) == s.charAt(r)) {
            l++;
            r--;
          }
          if (l >= r) { // abc---cba
            System.out.println(s);
          } else {
            String first = s.substring(0, l);
            String last = s.substring(r + 1);
            s = s.substring(l, r + 1);
            int[] prefix = prefix_function(s + "#" + new StringBuilder(s).reverse());
            int[] suffix = prefix_function(new StringBuilder(s).reverse() + "#" + s);
            int pl = longestPalindromicPrefix(s, 0, s.length() - 1);
            int sl = longestPalindromicPrefix(new StringBuilder(s).reverse().toString(), 0, s.length() - 1);
            // dfdce#ecdfd
            // 0 0 1 0 0 0 0 0 1 2 3
            if (pl >= sl) {
              System.out.println(first + s.substring(0, pl) + last);
            } else {
              System.out.println(first + s.substring(s.length() - sl) + last); // abcd
            }
          }
        }

      }
    }
  }

  private static void buildPower() {
    power = new long[100000 + 1];
    power[0] = 1;
    for (int i = 1; i < 100000 + 1; i++)
      power[i] = power[i - 1] * 31 % mod;
  }

  static int[] prefix_function(String s) {
    //out.println("s = " + s);
    int n = s.length();
    int[] pi = new int[n];
    for (int i = 1; i < n; i++) {
      int j = pi[i - 1];
      while (j > 0 && s.charAt(i) != s.charAt(j))
        j = pi[j - 1];
      if (s.charAt(i) == s.charAt(j))
        j++;
      pi[i] = j;
    }
    return pi;
  }

  private static int longestPalindromicPrefix(String s, int st, int end) { // [st, end] portion of this string
    long[] prefixHash = buildPrefixHash(s);
    long[] suffixHash = buildSuffixHash(s);
    int max = st;
    for (int i = st + 1; i <= end; i++) {
      //check if we have a palindrome ending here
      //now check if [st + 1, i - 1] is palindrome or not in O(1)
      //
      int len = i - st + 1;
      int half = len / 2;
      int firstEnding = st + half - 1;
      int secondStarting = i - half + 1;
      //abcde
      long firstHalfHash = (prefixHash[firstEnding] - (st - 1 >= 0 ? prefixHash[st - 1] : 0) + mod) % mod * modInv(power[st]) % mod;
      long secondHalfHash = (suffixHash[secondStarting] - (i + 1 < s.length() ? suffixHash[i + 1] : 0) + mod) % mod * modInv(power[s.length() - 1 - i]) %
        mod;
      if (firstHalfHash == secondHalfHash)
        max = i;

    }
    return max - st + 1;
  }

  private static long modInv(long n) {
    return fastExpo(n, mod - 2);
  }

  private static long fastExpo(long n, int p) { // returns n ^ p
    if (p == 0)
      return n;
    long half = fastExpo(n, p / 2);
    long half2 = half * half % mod;
    if (p % 2 == 1)
      half2 = half2 * n % mod;
    return half2;
  }

  static int mod = (int) 1e9 + 7;

  private static long[] buildPrefixHash(String s) {
    long[] ph = new long[s.length()];
    long cs = 0;
    for (int i = 0; i < s.length(); i++) {
      cs = (cs + (s.charAt(i) * power[i]) % mod) % mod;
      ph[i] = cs;
    }
    return ph;
  }

  private static long[] buildSuffixHash(String s) {
    long[] sh = new long[s.length()];
    long ch = 0;
    for (int i = s.length() - 1; i >= 0; i--) {
      ch = (ch + (s.charAt(i) * power[s.length() - 1 - i] % mod)) % mod;
      sh[i] = ch;
    }
    return sh;
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