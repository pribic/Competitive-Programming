package codeforce.educational.r117;

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
 * @see <a href="https://codeforces.com/contest/1612/problem/B" target="_top">https://codeforces.com/contest/1612/problem/B</a>
 * @since 22/11/21 3:23 PM
 */
public class B {
  static FastScanner sc = new FastScanner(System.in);

  public static void main(String[] args) {
    try (PrintWriter out = new PrintWriter(System.out)) {
      int T = sc.nextInt();
      for (int tt = 1; tt <= T; tt++) {
        int n = sc.nextInt();
        int a = sc.nextInt();
        int b = sc.nextInt();
        List<Integer> left = new ArrayList<>(n / 2);
        List<Integer> right = new ArrayList<>(n / 2);
        if(a < b) {
          left.add(a);
          right.add(b);
        }
        for (int i = 1; i < a; i++)
          right.add(i);
        for (int i = n; i > b; i--)
          left.add(i);
        for (int i = a + 1; i < b; i++) {
          if (left.size() < n / 2) {
            left.add(i);
          } else {
            right.add(i);
          }
        }
        //check
        boolean valid = true;
        if (left.size() != n / 2 || right.size() != n / 2) {
          System.out.println(-1);
        } else {
          List<Integer> combo = new ArrayList<>();
          combo.addAll(left);
          combo.addAll(right);
          int[] freq = new int[101];
          for (int num : combo)
            freq[num]++;
          for (int i = 1; i <= n; i++)
            if (freq[i] != 1)
              valid = false;
          if (!valid)
            System.out.println(-1);
          else {
            for (int num : combo)
              System.out.print(num + " ");
            System.out.println();
          }
        }
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
//  1 2 3 4 5