package codeforce.div2.r743;

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
 * @see <a href="https://codeforces.com/contest/1573/problem/B" target="_top">https://codeforces.com/contest/1573/problem/B</a>
 * @since 18/09/21 8:24 PM
 */
public class B {
  static FastScanner sc = new FastScanner(System.in);

  public static void main(String[] args) {
    try (PrintWriter out = new PrintWriter(System.out)) {
      int T = sc.nextInt();
      for (int tt = 1; tt <= T; tt++) {
        int n = sc.nextInt();
        int[] odd = new int[n];
        for (int i = 0; i < n; i++) {
          odd[i] = sc.nextInt();
        }
        int[] even = new int[n];
        for (int i = 0; i < n; i++) {
          even[i] = sc.nextInt();
        }
        int l = 0;
        int r = 0;
        while (r < n && even[r] < odd[l]) r++;
        int minSwaps = n + 1;
        do {
          minSwaps = Math.min(minSwaps, l + r);
          //move l to next smaller
          int j = l + 1;
          while (j < n && odd[j] > odd[l]) j++;
          l = j; // l points to now next smaller element
          //move r to the left until 
          j = r - 1;
          while (j >= 0 && even[j] < odd[l]) j--;
        } while (l < n);
        // decreasing nums from odd
        // increasing nums from even
        // 9 7
        // - - - - >9
        System.out.println(minSwaps);
        //all we need is one zero surrounded by 2 non zero
      }
    }
  }
// a b c - - - 
// f f f f f f f f
// 0 0 0 - - - // in 2 steps, we can make 3 numbs 0
  // so upper bound is ceil(n / 3);

// 0 0 0 a b
// 0 0 0 0 0

//1 1 1 1 0
//1 1 0 0 0
// x 0 x || 0 x x | x x 0
  // c c 0
  // 0 0 0
  //1 1 1 1
//   
  
  
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