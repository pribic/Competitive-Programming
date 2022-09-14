package codeforce.div3.r748;

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
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.HashSet;

import static java.lang.Math.max;
import static java.lang.Math.min;
import static java.lang.System.out;
import static java.util.stream.Collectors.joining;


/**
 * @author pribic (Priyank Doshi)
 * @see <a href="https://codeforces.com/contest/1593/problem/D2" target="_top">https://codeforces.com/contest/1593/problem/D2</a>
 * @since 13/10/21 9:09 PM
 */
public class D2 {
  static FastScanner sc = new FastScanner(System.in);
  static int maxK;

  public static void main(String[] args) {
    try (PrintWriter out = new PrintWriter(System.out)) {
      int T = sc.nextInt();
      for (int tt = 1; tt <= T; tt++) {
        maxK = -1;
        int n = sc.nextInt();
        int[] arr = new int[n];
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;
        Map<Integer, Integer> freq = new HashMap<>();
        for (int i = 0; i < n; i++) {
          arr[i] = sc.nextInt();
          min = min(min, arr[i]);
          max = max(max, arr[i]);
          freq.put(arr[i], freq.getOrDefault(arr[i], 0) + 1);
        }
        Predicate<Void> more = (Void v) -> {
          for (int f : freq.values())
            if (f >= n / 2)
              return true;
          return false;
        };
        int diff = max - min;
        if (diff == 0 || more.test(null)) {
          System.out.println(-1);
          continue;
        }

        //combination(arr, 0, new ArrayList<>(), Integer.MAX_VALUE, Integer.MIN_VALUE);
        Set<Integer> diffs = new HashSet<>();

        for (int i = 0; i < n; i++) {
          for (int j = i + 1; j < n; j++) {
            diffs.add(Math.max(arr[i], arr[j]) - Math.min(arr[i], arr[j]));
          }
        }
        Set<Integer> divisors = new TreeSet<>(Collections.reverseOrder());
        for (int diff1 : diffs)
          divisors.addAll(divisors(diff1));
        //System.out.println(divisors);
        outer:
        for (int div : divisors) {
          // this means div is a possible k
          Map<Integer, Integer> modVsFreq = new HashMap<>();
          for (int num : arr) {
            int key = num >= 0 ? num % div : div + num % div;
            modVsFreq.put(key, modVsFreq.getOrDefault(key, 0) + 1);
          }
          for (int f : modVsFreq.values())
            if (f >= n / 2) {
             // System.out.println(modVsFreq);
              System.out.println(div);
              break outer;
            }

        }
      }
    }
  }

  //48 13 -27 41 -67
  //

  private static boolean calc(List<Integer> arr, int min, int max) {
    int diff = max - min;
    List<Integer> divisors = divisors(diff);
    //System.out.println(divisors);
    for (int div : divisors) {
      boolean possible = true;
      //check if all the diffs are divisible by div
      for (int num : arr) {
        if ((num - min) % div != 0) {
          possible = false;
          break;
        }
      }
      if (possible) {
        return true;
      }
    }
    return false; //we shall never reach here
  }


  private static List<Integer> divisors(int diff) {
    List<Integer> divs = new ArrayList<>();
    for (long i = 1; i * i <= diff; i++) {
      if (diff % i == 0) {
        divs.add((int) i);
        if (i != diff / i)
          divs.add((int) (diff / i));
      }
    }
    Collections.sort(divs);
    Collections.reverse(divs);
    return divs;
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