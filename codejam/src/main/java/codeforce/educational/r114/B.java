package codeforce.educational.r114;

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
 * @see <a href="https://codeforces.com/contest/1574/problem/B" target="_top">https://codeforces.com/contest/1574/problem/B</a>
 * @since 20/09/21 8:34 PM
 */
public class B {
  static FastScanner sc = new FastScanner(System.in);

  public static void main(String[] args) {
    try (PrintWriter out = new PrintWriter(System.out)) {
      int T = sc.nextInt();
      for (int tt = 1; tt <= T; tt++) {
        int a = sc.nextInt();
        int b = sc.nextInt();
        int c = sc.nextInt();
        int m = sc.nextInt();
        int[] arr = {a, b, c};
        Arrays.sort(arr);
        // a b c a b c a b c a b c c c  || min = 2
        // a b c a b c a b a b c c c c  || 3
        // a b c a b a b a b c c c c c  || 4
        // a b a b a a b b c c c c c c  || 5
        
        
        // a b b b b b a c c c c c c a || 10
        // a a a b b b b c c c c c || 11
        
        if (m > a - 1 + b - 1 + c - 1 || (m < (arr[2] - arr[0] - (arr[1] - arr[0])) - 1))
          System.out.println("NO");
        else if(m <= arr[2] - 1) // m <= c - 1 , we will use all c for this
          System.out.println("YES"); 
        //else if(m - c + 1 ) // we have used all c, so now we are looking for m - c + 1, we want to make them using a and b only. 
        else
          System.out.println("YES");

      }
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