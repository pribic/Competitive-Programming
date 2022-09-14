package codeforce.div3.r748;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Collection;
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
 * @see <a href="https://codeforces.com/contest/1593/problem/D1" target="_top">https://codeforces.com/contest/1593/problem/D1</a>
 * @since 13/10/21 8:47 PM
 */
public class D1 {
  static FastScanner sc = new FastScanner(System.in);

  public static void main(String[] args) {
    try (PrintWriter out = new PrintWriter(System.out)) {
      int T = sc.nextInt();
      for (int tt = 1; tt <= T; tt++) {
        int n = sc.nextInt();
        int[] arr = new int[n];
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < n; i++) {
          arr[i] = sc.nextInt();
          min = Math.min(min, arr[i]);
          max = Math.max(max, arr[i]);
        }
        int diff = max - min;
        if(diff == 0) {
          System.out.println(-1);
          continue;
        }
        List<Integer> divisors = divisors(diff);
        //System.out.println(divisors);
        for (int div : divisors) {
          boolean possible = true;
          //check if all the diffs are divisible by div
          for (int num : arr) {
            if ((num - min) % div != 0) {
              possible = false;
              break;
            }
          }
          if (possible) {
            System.out.println(div);
            break;
          }
        }
      }
    }
  }

  private static List<Integer> divisors(int diff) {
    List<Integer> divs = new ArrayList<>();
    for (long i = 1; i * i <= diff; i++) {
      if (diff % i == 0) {
        divs.add((int) i);
        if (i != diff / i)
          divs.add((int) (diff / i));
      }
    }
    Collections.sort(divs);
    Collections.reverse(divs);
    return divs;
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