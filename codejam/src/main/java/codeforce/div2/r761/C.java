package codeforce.div2.r761;

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
 * @see <a href="https://codeforces.com/contest/1617/problem/C" target="_top">https://codeforces.com/contest/1617/problem/C</a>
 * @since 16/12/21 8:28 PM
 */
public class C {
  static FastScanner sc = new FastScanner(System.in);

  public static void main(String[] args) {
    try (PrintWriter out = new PrintWriter(System.out)) {
      int T = sc.nextInt();
      for (int tt = 1; tt <= T; tt++) {
        int n = sc.nextInt();
        int[] arr = new int[n];
        TreeSet<Integer> pending = new TreeSet<>();
        for (int i = 0; i < n; i++) {
          pending.add(i + 1);
        }
        List<Integer> input = new ArrayList<>();
        for (int i = 0; i < n; i++) {
          arr[i] = sc.nextInt();
          if (!pending.remove(arr[i])) {
            input.add(arr[i]);
          }
        }
        Collections.sort(input);
        int cnt = 0;
        for (int num : input) {
          if (pending.isEmpty()) {
            cnt = -1;
            break;
          }
          int target = pending.first();
          pending.remove(target);
          if (num == target)
            continue;
          // 18 -> [1, 8]
          // 19 -> [1, 9]
          if (target < (num + 1) / 2)
            cnt++;
          else {
            cnt = -1;
            break;
          }
        }
        System.out.println(cnt);
      }
    }
  }

  private static void sort(int[] arr) {
    List<Integer> list = new ArrayList<>();
    for (int i = 0; i < arr.length; i++) {
      list.add(arr[i]);
    }
    list = list.stream().sorted().collect(Collectors.toList());
    for (int i = 0; i < arr.length; i++) {
      arr[i] = list.get(i);
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