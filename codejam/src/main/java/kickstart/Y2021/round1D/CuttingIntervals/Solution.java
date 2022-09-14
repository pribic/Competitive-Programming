package kickstart.Y2021.round1D.CuttingIntervals;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;
import java.util.StringTokenizer;
import java.util.List;
import java.util.TreeMap;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.joining;


/**
 * @author pribic (Priyank Doshi)
 * @see <a href="https://codingcompetitions.withgoogle.com/kickstart/round/00000000004361e3/000000000082b933" target="_top">https://codingcompetitions.withgoogle.com/kickstart/round/00000000004361e3/000000000082b933</a>
 * @since 11/07/21 1:14 PM
 */
public class Solution {
  static FastScanner sc = new FastScanner(System.in);

  static class Interval {
    long position;
    char type;

    public Interval(long position, char type) {
      this.position = position;
      this.type = type;
    }

    @Override
    public String toString() {
      return position + " " + type;
    }
  }

  public static void main(String[] args) {
    try (PrintWriter out = new PrintWriter(System.out)) {
      int T = sc.nextInt();
      for (int tt = 1; tt <= T; tt++) {
        System.out.print("Case #" + tt + ": ");
        int n = sc.nextInt();
        long c = sc.nextLong();
        List<Interval> intervals = new ArrayList<>();
        for (int i = 0; i < n; i++) {
          long l = sc.nextLong();
          long r = sc.nextLong();
          if (l + 1 < r) {
            intervals.add(new Interval(l + 1, '('));
            intervals.add(new Interval(r - 1, ')'));
          }
        }
        intervals.sort((i1, i2) -> {
          if (i1.position == i2.position) {
            if (i1.type == '(' && i2.type == ')')
              return -1;
            else if (i1.type == '(' && i2.type == '(')
              return 0;
            else if (i1.type == ')' && i2.type == '(')
              return 1;
            else
              return 0;
          } else {
            if (i1.position < i2.position)
              return -1;
            else
              return 1;
          }
        });
       // System.out.println(intervals);
        long total = n;
        Map<Long, Integer> freq = new TreeMap<>(Collections.reverseOrder());
        Stack<Interval> stack = new Stack<>();
        for (Interval interval : intervals) {
          if (interval.type == '(') {
            stack.push(interval);
          } else {
            Interval lastRange = stack.pop();
            freq.put(interval.position - lastRange.position + 1, stack.size() + 1);
          }
        }
        // System.out.println("freq = " + freq);
        for (Long range : freq.keySet()) {
          if (range <= c) {
            total += range * freq.get(range);
            c -= range;
          } else {
            total += c * freq.get(range);
            break;
          }
        }
        System.out.println(total);
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