package codechef.snackdown.y2021.round1A;

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
 * @see <a href="https://www.codechef.com/SNCK1A21/problems/EQBEAUTY" target="_top">https://www.codechef.com/SNCK1A21/problems/EQBEAUTY</a>
 * @since 21/10/21 11:12 PM
 */
public class EQBEAUTY {
  static FastScanner sc = new FastScanner(System.in);

  public static void main(String[] args) {
    try (PrintWriter out = new PrintWriter(System.out)) {
      int T = sc.nextInt();
      for (int tt = 1; tt <= T; tt++) {
        int n = sc.nextInt();
        int[] arr = new int[n];
        TreeSet<Integer> set = new TreeSet<>();
        Map<Integer, Integer> freq = new HashMap<>();
        for (int i = 0; i < n; i++) {
          arr[i] = sc.nextInt();
          set.add(arr[i]);
          freq.put(arr[i], freq.getOrDefault(arr[i], 0) + 1);
        }
        set.add(Integer.MAX_VALUE / 2);
        set.add(Integer.MIN_VALUE / 2);
        if (n == 2) {
          Arrays.sort(arr);
          System.out.println(Math.abs(arr[0] - arr[1]));
        } else if (n == 3) {
          Arrays.sort(arr);
          int diff1 = calc(arr[0], arr[0], arr[1], arr[2]);
          int diff2 = calc(arr[0], arr[1], arr[2], arr[2]);
          System.out.println(Math.min(diff1, diff2));
        } else {
          Arrays.sort(arr);
          int ans = Integer.MAX_VALUE;
          for (int i = 1; i < n - 2; i++) { // 1 51 101
            int max1 = arr[i];
            int diff1 = max1 - arr[0];
            int min2Target = arr[n - 1] - diff1;
            if ((min2Target == max1 && freq.get(max1) == 1)
              || (min2Target == arr[0] && freq.get(arr[0]) == 1)
              || (min2Target == arr[n - 1] && freq.get(arr[n - 1]) == 1))
              continue;
            int closest1 = set.ceiling(min2Target);
            if (closest1 == arr[n - 1] && freq.get(arr[n - 1]) == 1)
              closest1 = Integer.MAX_VALUE / 2;
            int closest2 = set.floor(min2Target);
            if (closest2 == arr[0] && freq.get(arr[0]) == 1)
              closest2 = Integer.MIN_VALUE / 2;
            int nearest = Math.min(Math.abs(closest1 - min2Target), Math.abs(closest2 - min2Target));
            ans = Math.min(ans, nearest);
          }
          System.out.println(ans);
        }
      }
    }
  }

  private static int calc(int min1, int max1, int min2, int max2) {
    return Math.abs(max1 - min1 - max2 + min2);
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