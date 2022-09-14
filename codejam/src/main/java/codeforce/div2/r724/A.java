package codeforce.div2.r724;

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
import java.util.TreeSet;
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
 * @see <a href="https://codeforces.com/contest/1536/problem/A" target="_top">https://codeforces.com/contest/1536/problem/A</a>
 * @since 06/06/21 8:12 PM
 */
public class A {
  static FastScanner sc = new FastScanner(System.in);

  public static void main(String[] args) {
    try (PrintWriter out = new PrintWriter(System.out)) {
      int T = sc.nextInt();
      for (int tt = 1; tt <= T; tt++) {
        int n = sc.nextInt();
        List<Long> arr = new ArrayList<>();
        Set<Long> nums = new TreeSet<>();
        boolean anyneg = false;
        for (int i = 0; i < n; i++) {
          arr.add((long) sc.nextInt());
          nums.add(arr.get(i));
          if(arr.get(i) < 0)
            anyneg = true;
        }
        if(anyneg) {
          System.out.println("NO");
          continue;
        }
        boolean change = true;
        while (arr.size() <= 300 && change) {
          change = false;
          Set<Long> extra = new HashSet<>();
          for (int i = 0; i < arr.size() - 1; i++) {
            for (int j = i + 1; j < arr.size(); j++) {
              long abs = Math.abs(arr.get(i) - arr.get(j));
              if (!nums.contains(abs)) {
                change = true;
                extra.add(abs);
                nums.add(abs);
              }
            }
          }
          //System.out.println(arr);
          arr.addAll(extra);
          //System.out.println(arr);
        }
        if(arr.size() <= 300 && !change) {
          System.out.println("YES");
          System.out.println(arr.size());
          for (long num : arr)
            System.out.print(num + " ");
          System.out.println();
        } else {
          System.out.println("NO");
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