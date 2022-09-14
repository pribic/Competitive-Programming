package codeforce.div3.r764;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
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
 * @see <a href="https://codeforces.com/contest/1624/problem/D" target="_top">https://codeforces.com/contest/1624/problem/D</a>
 * @since 10/01/22 8:58 PM
 */
public class D {
  static FastScanner sc = new FastScanner(System.in);

  public static void main(String[] args) {
    try (PrintWriter out = new PrintWriter(System.out)) {
      int T = sc.nextInt();
      for (int tt = 1; tt <= T; tt++) {
        int n = sc.nextInt();
        int k = sc.nextInt();
        char[] str = sc.next().toCharArray();
        if (k == n) {
          System.out.println(1);
          continue;
        }
        int[] fr = new int[26];
        for (char c : str)
          fr[c - 'a']++;
        int pairs = Arrays.stream(fr).map(num -> num / 2).sum();
        int single = Arrays.stream(fr).map(num -> num % 2).sum();
        // i want to equally distribute pairs to k groups
        int each = pairs / k;
        int extra = single + 2 * (pairs % k);
        // these extra i want to add 1 in each pair
        System.out.println(2 * each + Math.min(1, extra / k));
      }
    }
  }
  /*
  xyabayx
  bdb
  adda
  cc
  // bz
  // xyabayx
  //
  // make longest palindrome
  // then break it into k palindrome
  
  eatoohd

oo


claim: no group will have len difference > 2

if group1 has len a, group2 has len 2, such as b > a + 2, then we take two boundary elements from b, move it to a's boundary

reducing difference by 4

or odd len and even len, 


diff of 3 can be reduced to diff of 1,

12 13

diff of 2, can't do anything
12
10


abcdddcba
bdb
adda
cc

bfvfbv
bfvvfb

bvb
fvf
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