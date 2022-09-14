package codeforce.globalround.y2021.r15;

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
 * @see <a href="https://codeforces.com/contest/1552/problem/D" target="_top">https://codeforces.com/contest/1552/problem/D</a>
 * @since 25/07/21 9:12 PM
 */
public class D {
  static FastScanner sc = new FastScanner(System.in);

  public static void main(String[] args) {
    try (PrintWriter out = new PrintWriter(System.out)) {
      int T = sc.nextInt();
      for (int tt = 1; tt <= T; tt++) {
        int n = sc.nextInt();
        int[] arr = new int[n];
        boolean valid = false;
        for (int i = 0; i < n; i++) {
          arr[i] = Math.abs(sc.nextInt());
        }
        Set<Integer> subsets = new HashSet<>();
        for (int i = 0; i < (1 << n); i++) {
          int sum = 0;
          for (int j = 0; j < n; j++) {
            if (((i >> j) & 1) == 1) {
              sum += arr[j];
            }
          }
          if (subsets.contains(sum)) {
            //System.out.println("yes subset sum " + i);
            valid = true;
            break;
          }
          subsets.add(sum);
        }

        System.out.println(valid ? "YES" : "NO");
      }
    }
  }
  
  /*
  n*n pairs -> we just need n pairs out of n*n which equals a given number in array
  
diff 5 7 12 10

orig 20 27 15

diff  1 3 7 -4 

orig  -2 3 1 5  
  
  
  -3 2 10 2
   a1 a2 a3 a4
   
   a1 = b1/10 - b1/10 -> n * n -> 100 * 100
   a2 =                        -> 100 ^ 10 -> 1e12
   
   b1    b1 - a1
   
   0 
    
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