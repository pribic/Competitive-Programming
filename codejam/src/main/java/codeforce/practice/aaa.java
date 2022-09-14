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
import java.util.StringTokenizer;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.HashSet;

import static java.util.Collections.emptySet;
import static java.util.stream.Collectors.joining;


/**
 * @author pribic (Priyank Doshi)
 * @see <a href="https://codeforces.com/contest/1516/problem/D" target="_top">https://codeforces.com/contest/1516/problem/D</a>
 * @since 22/05/21 6:18 PM
 */
public class aaa {
  static FastScanner sc = new FastScanner(System.in);

  public static void main(String[] args) {
    Map<Integer, Set<Integer>> primeFactors = buildPrimeFactors();
    try (PrintWriter out = new PrintWriter(System.out)) {
      int T = 1;//sc.nextInt();
      for (int tt = 1; tt <= T; tt++) {
        int n = sc.nextInt();
        int q = sc.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
          arr[i] = sc.nextInt();
        }
        int[] nxt = buildNextPointers(primeFactors, n, arr);
        SparseTable st = new SparseTable(nxt);
        StringBuilder ansSb = new StringBuilder();
        while (q-- > 0) {
          int l = sc.nextInt() - 1;
          int r = sc.nextInt() - 1;
          int ans = 0;
          for (int i = 16; i >= 0; i--) {
            int nthJump = st.nthJump(l, 1 << i);
            if (nthJump <= r + 1) {
              l = nthJump;
              ans += 1 << i;
            }
          }
          if (l <= r) {
            ans++;
          }
          ansSb.append(ans).append("\n");
        }
        System.out.println(ansSb);
      }
    }
  }

  private static int[] buildNextPointers(Map<Integer, Set<Integer>> primeFactors, int n, int[] arr) {
    int[] nxt = new int[n];
    int l = 0;
    int r = 1;
    Set<Integer> activePrimeFactors = new HashSet<>(primeFactors.get(arr[0]));
    while (l < n) {
      while (r < n && primeFactors.get(arr[r]).stream().noneMatch(activePrimeFactors::contains)) {
        activePrimeFactors.addAll(primeFactors.get(arr[r]));
        r++;
      } // r points to the element which we could not include.. beware that r can be n also meaning all the elements exhausted
      nxt[l] = r;
      //remove factors of l
      activePrimeFactors.removeAll(primeFactors.get(arr[l]));
      l++;
    }
    return nxt;
  }

  private static Map<Integer, Set<Integer>> buildPrimeFactors() {
    Map<Integer, Set<Integer>> primeFactors = new HashMap<>();
    primeFactors.put(1, emptySet());
    boolean[] primes = new boolean[100000 + 1];
    Arrays.fill(primes, true);
    for (int factor = 2; factor <= 100000; factor++) {
      if (primes[factor]) {
        for (int j = factor; j <= 100000; j += factor) {
          primeFactors.putIfAbsent(j, new HashSet<>());
          primeFactors.get(j).add(factor);
          primes[j] = false;
        }
        primes[factor] = true;
      }
    }
    return primeFactors;
  }

  static class SparseTable {
    int[][] jumps; // jumps[i][j] = index of next block after making 2^j jumps from i

    public SparseTable(int[] jump) {
      jumps = new int[jump.length + 1][17];
      for (int i = 0; i < jumps.length; i++)
        Arrays.fill(jumps[i], Integer.MAX_VALUE / 2);
      for (int i = 0; i < jump.length; i++)
        jumps[i][0] = jump[i];
      for (int j = 1; j < 17; j++) {
        for (int i = 0; i + (1 << j) <= jump.length; i++) {
          int halfWay = jumps[i][j - 1];
          if (halfWay < jump.length)
            jumps[i][j] = jumps[halfWay][j - 1];
        }
      }
    }

    int nthJump(int i, int n) {
      //we want to make n jumps from i.. n = 22, so we make i to 16 then 4 then 2
      for (int j = 16; j >= 0; j--) { //try max jump first
        if (((n >> j) & 1) == 1) {
          i = jumps[i][j];
        }
      }
      return i; //returns index of next block after making n jumps
    }

    @Override
    public String toString() {
      StringBuilder sb = new StringBuilder();
      for (int[] row : jumps) {
        for (int cell : row)
          sb.append(cell).append(" ");
        sb.append("\n");
      }
      return sb.toString();
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
      br = new BufferedReader(new InputStreamReader(f));
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