package codechef.practic;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringJoiner;
import java.util.StringTokenizer;
import java.util.TreeSet;
import java.util.Set;
import java.util.HashSet;

import static java.util.Comparator.comparingInt;
import static java.util.stream.Collectors.joining;


/**
 * @author pribic (Priyank Doshi)
 * @see <a href="https://www.codechef.com/LTIME76A/problems/ICECREM/" target="_top">https://www.codechef.com/LTIME76A/problems/ICECREM/</a>
 * @since 29/05/21 3:18 PM
 */
public class ICECREM {
  static FastScanner sc = new FastScanner(System.in);

  public static void main(String[] args) {
    try (PrintWriter out = new PrintWriter(System.out)) {
      int T = sc.nextInt();
      for (int tt = 1; tt <= T; tt++) {
        int n = sc.nextInt();
        Person[] people = new Person[n];
        for (int i = 0; i < n; i++) {
          people[i] = new Person(sc.nextInt(), sc.nextInt(), sc.nextInt(), i);
        }
        //first half
        Set<Pair> first = new HashSet<>();
        for (int i = 0; i < (1 << n / 2); i++) {
          int curProfit = 0;
          int curPreptime = 0;
          for (int bit = 0; bit < 20; bit++) {
            if (((i >> bit) & 1) == 1) {
              Person candidate = people[bit];
              //System.out.println("candidate1 = " + candidate);
              if (curPreptime <= candidate.w) {
                curProfit += candidate.v;
                curPreptime += candidate.p;
              }
            }
          }
          first.add(new Pair(curProfit, curPreptime - 1));
        }
        // second half
        TreeSet<Pair> second = new TreeSet<>(comparingInt(p -> p.totalTime));
        for (int i = 0; i < (1 << (n - n / 2)); i++) {
          int curProfit = 0;
          int firstCandidateWaitTime = -1;
          for (int bit = 0; bit < 20; bit++) {
            if (((i >> bit) & 1) == 1) {
              Person candidate = people[n / 2 + bit];
              //System.out.println("candidate2 = " + candidate);
              if (firstCandidateWaitTime <= candidate.w) {
                curProfit += candidate.v;
                if (firstCandidateWaitTime == -1)
                  firstCandidateWaitTime = candidate.w;
              }
            }
          }
          second.add(new Pair(curProfit, firstCandidateWaitTime));
        }
        //System.out.println("first = " + first);
        //System.out.println("second = " + second);
        //
        long maxProfit = Long.MIN_VALUE;
        for (Pair one : first) {
          long curProfit = one.profit;
          Pair two = second.higher(one);
          if (two != null)
            curProfit += two.profit;
          maxProfit = Math.max(maxProfit, curProfit);
        }
        System.out.println(maxProfit);
      }
    }
  }

  static class Pair {
    int profit, totalTime;

    public Pair(int profit, int totalTime) {
      this.profit = profit;
      this.totalTime = totalTime;
    }

    @Override
    public String toString() {
      return "[" + profit + "," + totalTime + "]";
    }
  }

  static class Person {
    int w, p, v, idx;

    public Person(int w, int p, int v, int idx) {
      this.w = w;
      this.p = p;
      this.v = v;
      this.idx = idx;
    }

    @Override
    public String toString() {
      return new StringJoiner(", ", "[", "]")
        .add("" + w)
        .add("" + p)
        .add("" + v)
        .add("" + idx)
        .toString();
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
