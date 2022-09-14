package kickstart.Y2021.round1D.finalExam;

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
import java.util.StringJoiner;
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

import static java.lang.System.in;
import static java.lang.System.out;
import static java.util.stream.Collectors.joining;


/**
 * @author pribic (Priyank Doshi)
 * @see <a href="https://codingcompetitions.withgoogle.com/kickstart/round/00000000004361e3/000000000082bffc" target="_top">https://codingcompetitions.withgoogle.com/kickstart/round/00000000004361e3/000000000082bffc</a>
 * @since 11/07/21 11:16 AM
 */
public class Solution {
  static FastScanner sc = new FastScanner(System.in);

  public static void main(String[] args) {
    try (PrintWriter out = new PrintWriter(System.out)) {
      int T = sc.nextInt();
      for (int tt = 1; tt <= T; tt++) {
        System.out.print("Case #" + tt + ": ");
        int n = sc.nextInt();
        int m = sc.nextInt();
        TreeSet<Interval> intervals = new TreeSet<>((i1, i2) -> {
          if (i1.l == i2.l)
            return 0;
          else if (i1.l < i2.l)
            return -1;
          else
            return 1;
        });
        for (int i = 0; i < n; i++) {
          long l = sc.nextLong();
          long r = sc.nextLong();
          intervals.add(new Interval(l, r));
        }

        // System.out.println("intervals = " + intervals);

        StringBuilder ans = new StringBuilder();
        for (int i = 0; i < m; i++) {
          long skill = sc.nextLong();
          long problem = 0;
          Interval dummyInterval = new Interval(skill, 0);
          Interval leftSide = intervals.floor(dummyInterval);
          // System.out.println("skill = " + skill);
          // System.out.println("leftSide = " + leftSide);
          //entirely inside
          //skill is inside leftSide
          if (leftSide != null && leftSide.l <= skill && leftSide.r >= skill) {
            problem = skill;
            intervals.remove(leftSide);
            if (leftSide.l != leftSide.r) {
              if (skill == leftSide.l) {
                intervals.add(new Interval(leftSide.l + 1, leftSide.r));
              } else if (skill == leftSide.r) {
                intervals.add(new Interval(leftSide.l, leftSide.r - 1));
              } else {
                intervals.add(new Interval(leftSide.l, skill - 1));
                intervals.add(new Interval(skill + 1, leftSide.r));
              }
            }
            ans.append(problem).append(" ");
            continue;
          }
          Interval rightSide = intervals.ceiling(dummyInterval);

          //when skill is far right such that nothing is right to it
          if (rightSide == null && leftSide != null) { // - - - point
            problem = leftSide.r;
            ans.append(problem).append(" ");
            if (leftSide.l == leftSide.r)
              intervals.remove(leftSide);
            else
              leftSide.r = leftSide.r - 1;
            continue;
          }
          //when skill is far left such that nothing is left, everything is right to it
          if (leftSide == null && rightSide != null) {// point - - -
            problem = rightSide.l;
            ans.append(problem).append(" ");
            if (rightSide.l == rightSide.r) {
              intervals.remove(rightSide);
            } else {
              intervals.remove(rightSide);
              intervals.add(new Interval(rightSide.l + 1, rightSide.r));
            }
            continue;
          }
          //last case where skill is in middle of left and right side
          if (skill - leftSide.r <= rightSide.l - skill) { // - - - point - - -
            problem = leftSide.r;
            if (leftSide.l == leftSide.r)
              intervals.remove(leftSide);
            else
              leftSide.r = leftSide.r - 1;
          } else {
            problem = rightSide.l;
            intervals.remove(rightSide);
            if (rightSide.l < rightSide.r) {
              intervals.add(new Interval(rightSide.l + 1, rightSide.r));
            }
          }
          ans.append(problem).append(" ");
        }
        System.out.println(ans);
      }
    }
  }

  static class Interval {
    long l, r;

    public Interval(long l, long r) {
      this.l = l;
      this.r = r;
    }

    @Override
    public String toString() {
      return l + "," + r;
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