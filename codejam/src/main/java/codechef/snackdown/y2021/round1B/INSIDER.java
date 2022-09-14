package codechef.snackdown.y2021.round1B;

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
import java.util.Iterator;
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
 * @see <a href="https://www.codechef.com/SNCK1B21/problems/INSIDER" target="_top">https://www.codechef.com/SNCK1B21/problems/INSIDER</a>
 * @since 30/10/21 11:52 PM
 */
public class INSIDER {
  static FastScanner sc = new FastScanner(System.in);

  public static void main(String[] args) {
    try (PrintWriter out = new PrintWriter(System.out)) {
      int T = sc.nextInt();
      for (int tt = 1; tt <= T; tt++) {
        int n = sc.nextInt();
        int[] arr = new int[n];
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;
        Set<Integer> pts = new TreeSet<>();
        for (int i = 0; i < n; i++) {
          arr[i] = sc.nextInt();
          min = Math.min(min, arr[i]);
          max = Math.max(max, arr[i]);
          pts.add(arr[i]);
          pts.add(arr[i] + 1);
          pts.add(arr[i] - 1);
        }
        List<Integer> uniqPts = new ArrayList<>(pts);
        int[] mins = new int[n + 1];
        int[] maxs = new int[n + 1];
        Arrays.fill(mins, -1);
        Arrays.fill(maxs, -1);
        for (int num : uniqPts) {
          int[] equivalence = findEquivalance(arr, num);
          int len = findMaxLen(equivalence);
          while (len >= 0 && mins[len] == -1)
            mins[len--] = num;
        }

        for (int i = uniqPts.size() - 1; i >= 0; i--) {
          int num = uniqPts.get(i);
          int[] equivalence = findEquivalance(arr, num);
          int len = findMaxLen(equivalence);
          while (len >= 0 && maxs[len] == -1)
            maxs[len--] = num;
        }

        for (int i = 2; i <= n; i++)
          System.out.print(mins[i] + " ");
        System.out.println();
        for (int i = 2; i <= n; i++)
          System.out.print(maxs[i] + " ");
        System.out.println();

      }
    }
  }

  private static int findMaxLen(int[] equivalance) { // max len of 1 -1 1 -1
    int cnt = 0;
    int st = 0;
    for (int num : equivalance)
      if (num != 0) {
        st = num;
        break;
      }
    if (st == 0)
      return cnt;
    for (int num : equivalance) {
      if (num == st) {
        cnt++;
        st = -1 * st;
      }
    }
    return cnt;
  }

  private static int[] findEquivalance(int[] arr, int num) {
    int[] ans = new int[arr.length];
    for (int i = 0; i < arr.length; i++) {
      if (arr[i] < num)
        ans[i] = -1;
      else if (arr[i] > num)
        ans[i] = 1;
    }
    return ans;
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