package kickstart.Y2021.round1F.Festival;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Iterator;
import java.util.StringJoiner;
import java.util.StringTokenizer;
import java.util.TreeMap;


/**
 * @author pribic (Priyank Doshi)
 * @see <a href="https://codingcompetitions.withgoogle.com/kickstart/round/0000000000435bae/0000000000887dba" target="_top">https://codingcompetitions.withgoogle.com/kickstart/round/0000000000435bae/0000000000887dba</a>
 * @since 18/09/21 10:45 PM
 */
public class Solution {
  static FastScanner sc = new FastScanner(System.in);

  public static void main(String[] args) {
    try (PrintWriter out = new PrintWriter(System.out)) {
      int T = sc.nextInt();
      for (int tt = 1; tt <= T; tt++) {
        System.out.print("Case #" + tt + ": ");
        int d = sc.nextInt();
        int n = sc.nextInt();
        int k = sc.nextInt();
        Interval[] intervals = new Interval[n];
        Event[] events = new Event[2 * n];
        for (int i = 0; i < n; i++) {
          intervals[i] = new Interval(sc.nextInt(), sc.nextInt(), sc.nextInt());
          events[2 * i] = new Event(intervals[i].s, intervals[i], true, i);
          events[2 * i + 1] = new Event(intervals[i].e, intervals[i], false, i);
        }
        Arrays.sort(events, (a, b) -> {
          if (a.p == b.p) {
            if (a.start && b.start)
              return a.interval.e - b.interval.e;
            else if (a.start)
              return -1;
            else if (b.start)
              return 1;
            return 0;
          }
          return a.p - b.p;
        });
        // System.out.println("events.toString() = " + Arrays.toString(events));
        TreeMap<Integer, Integer> maxK = new TreeMap<>();
        TreeMap<Integer, Integer> rest = new TreeMap<>();
        int maxHappiness = 0;
        int curH = 0;
        int curIntervalsInMax = 0;
        /*

         */
        for (Event event : events) {
          if (event.start) { // if we have less than k intervals so far, then we will definately consider current interval so just add them.
            // if we have >= k intervals, then for current interval happiness, if it is inside top k happiness then we consider it, otherwise we don't
            // if i keep track of happiness of kth one,
            if (curIntervalsInMax < k) {
              curH += event.interval.h;
              add(maxK, event.interval.h);
              curIntervalsInMax++;
            } else {
              int minFromMax = maxK.keySet().iterator().next();
              if (minFromMax > event.interval.h) {
                add(rest, event.interval.h);
              } else {
                remove(maxK, minFromMax);
                add(maxK, event.interval.h);
                curH -= minFromMax;
                curH += event.interval.h;
              }
            }
          } else {
            //remove(multiset, event.interval.h);
            maxHappiness = Math.max(maxHappiness, curH);
          }
        }
        System.out.println(maxHappiness);
      }
    }
  }

  private static void add(TreeMap<Integer, Integer> multiset, int key) {
    if (multiset.containsKey(key))
      multiset.put(key, multiset.get(key) + 1);
    else
      multiset.put(key, 1);
  }

  private static void remove(TreeMap<Integer, Integer> multiset, int key) {
    if (multiset.get(key) == 1)
      multiset.remove(key);
    else
      multiset.put(key, multiset.get(key) - 1);
  }

  private static boolean overlapping(Interval[] intervals, int a, int b) {
    if (intervals[b].e < intervals[a].s || intervals[b].s > intervals[a].e)
      return false;
    return true;
  }

  static class Interval {
    int s, e, h;

    public Interval(int h, int s, int e) {
      this.s = s;
      this.e = e;
      this.h = h;
    }
  }

  static class Event {

    int p;
    Interval interval;
    boolean start;
    int idx;

    public Event(int p, Interval interval, boolean start, int idx) {
      this.p = p;
      this.interval = interval;
      this.start = start;
      this.idx = idx;
    }

    @Override
    public String toString() {
      return new StringJoiner(", ", Event.class.getSimpleName() + "[", "]")
        .add("p=" + p)
        .add("interval=" + interval.s + " " + interval.e)
        .add("start=" + start)
        .add("idx=" + idx)
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