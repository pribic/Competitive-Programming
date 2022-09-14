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
 * @see <a href="https://codeforces.com/problemset/problem/1400/E" target="_top">https://codeforces.com/problemset/problem/1400/E</a>
 * @since 15/09/21 5:35 PM
 */
public class p1400E {
  static FastScanner sc = new FastScanner(System.in);

  public static void main(String[] args) {
    try (PrintWriter out = new PrintWriter(System.out)) {
      int T = 1;//sc.nextInt();
      for (int tt = 1; tt <= T; tt++) {
        int n = sc.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
          arr[i] = sc.nextInt();
        }
        System.out.println(sort(arr));
      }
    }
  }

  // 2 2 2 5 3 3 || 2 op
  // 0 0 0 3 1 1 || 1 op
  // 0 0 0 2 0 0
  static int sort(int[] arr) {
    arr = shrink(arr);
    if (arr.length == 0)
      return 0;
    if (arr.length == 1) {
      return arr[0] == 0 ? 0 : 1;
    }
    int minI = 0;
    for (int i = 1; i < arr.length; i++) {
      if (arr[i] < arr[minI]) {
        minI = i;
      }
    }
    int[] left = new int[minI];
    for (int i = 0; i < minI; i++)
      left[i] = arr[i] - (arr[minI] > 0 ? 1 : 0);
    int[] right = new int[arr.length - minI - 1];
    for (int i = minI + 1; i < arr.length; i++)
      right[i - minI - 1] = arr[i] - (arr[minI] > 0 ? 1 : 0);
    int cnt = (int) Arrays.stream(arr).filter(num -> num > 0).count();
    return Math.min(cnt, arr[minI]+ sort(left) + sort(right));
  }

  private static int[] shrink(int[] arr) {
    List<Integer> a = new ArrayList<>();
    int left = 0;
    while (left < arr.length && arr[left] == 0) left++;
    if (left == arr.length)
      return new int[0];
    int right = arr.length - 1;
    while (right >= 0 && arr[right] == 0) right--;
    if (right == -1)
      return new int[0];
    for (int i = left; i <= right; i++)
      a.add(arr[i]);
    arr = new int[a.size()];
    for (int i = 0; i < a.size(); i++)
      arr[i] = a.get(i);
    return arr;
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