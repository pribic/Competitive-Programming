package codeforce.practice;

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
import static java.util.stream.Collectors.toList;


/**
 * @author pribic (Priyank Doshi)
 * @see <a href="https://codeforces.com/contest/1213/problem/D2" target="_top">https://codeforces.com/contest/1213/problem/D2</a>
 * @since 21/06/21 7:15 PM
 */
public class p582D2 {
  static FastScanner sc = new FastScanner(System.in);

  public static void main(String[] args) {
    try (PrintWriter out = new PrintWriter(System.out)) {
      int T = 1;//sc.nextInt();
      for (int tt = 1; tt <= T; tt++) {
        int n = sc.nextInt();
        int k = sc.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
          arr[i] = sc.nextInt();
        }
        sort(arr);
        List<Integer>[] map = new List[2 * 100000 + 1];
        for (int num : arr) {
          int cnt = 0;
          while (num > 0) {
            if (map[num] == null)
              map[num] = new ArrayList<>();
            map[num].add(cnt);
            num /= 2;
            cnt++;
          }
        }
        int min = Integer.MAX_VALUE;
        for (int key = 1; key < map.length; key++)
          if (map[key] != null && map[key].size() >= k) {
            int cnt = 0;
            for (int i = 0; i < k; i++) {
              cnt += map[key].get(i);
            }
            min = Math.min(min, cnt);
          }
        System.out.println(min);
      }
    }
  }

  private static void sort(int[] arr) {
    List<Integer> list = Arrays.stream(arr).boxed().sorted().collect(toList());
    for (int i = 0; i < arr.length; i++) arr[i] = list.get(i);
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