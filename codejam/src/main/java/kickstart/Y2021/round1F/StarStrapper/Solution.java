package kickstart.Y2021.round1F.StarStrapper;

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

import static java.util.stream.Collectors.joining;


/**
 * @author pribic (Priyank Doshi)
 * @see <a href="https://codingcompetitions.withgoogle.com/kickstart/round/0000000000435bae/0000000000888d45" target="_top">https://codingcompetitions.withgoogle.com/kickstart/round/0000000000435bae/0000000000888d45</a>
 * @since 19/09/21 12:23 AM
 */
public class Solution {
  static FastScanner sc = new FastScanner(System.in);

  public static void main(String[] args) {
    try (PrintWriter out = new PrintWriter(System.out)) {
      int T = sc.nextInt();
      for (int tt = 1; tt <= T; tt++) {
        System.out.print("Case #" + tt + ": ");
        int n = sc.nextInt();
        int[][] stars = new int[n][2];
        for (int i = 0; i < n; i++) {
          stars[i][0] = sc.nextInt();
          stars[i][1] = sc.nextInt();
        }

        int bx = sc.nextInt(), by = sc.nextInt();

        double minPerimeter = Long.MAX_VALUE;
        for (int i = 0; i < n - 2; i++) {
          for (int j = i + 1; j < n - 1; j++) {
            for (int k = j + 1; k < n; k++) {
              if (isInside(stars[i][0], stars[i][1], stars[j][0], stars[j][1], stars[k][0], stars[k][1], bx, by)) {
                //check if point is not on any perimeter
                if (!onLine(stars[i], stars[j], bx, by)
                  && !onLine(stars[j], stars[k], bx, by)
                  && !onLine(stars[k], stars[i], bx, by)) {
                  double curPerimeter = dist(stars[i], stars[j]) + dist(stars[j], stars[k]) + dist(stars[k], stars[i]);
                  minPerimeter = Math.min(minPerimeter, curPerimeter);
                }
              }
            }
          }
        }
        for (int i = 0; i < n - 3; i++) {
          for (int j = i + 1; j < n - 2; j++) {
            for (int k = j + 1; k < n - 1; k++) {
              for (int l = k + 1; l < n; l++) {
                if (atCenter(stars[i][0], stars[i][1], stars[j][0], stars[j][1], stars[k][0], stars[k][1], stars[l][0], stars[l][1], bx, by)) {
                  //check if point is not on any perimeter
                  double curPerimeter = 2;//dist(stars[i], stars[j]) + dist(stars[j], stars[k]) + dist(stars[k], stars[l]) + dist(stars[l], stars[i]);
                  //pick i,
                  if (onLine(stars[i], stars[j], bx, by)) {
                    // this is the diagonal
                    curPerimeter *= dist(stars[i], stars[k]) + dist(stars[i], stars[l]);
                  } else if (onLine(stars[i], stars[k], bx, by)) {
                    curPerimeter *= dist(stars[i], stars[j]) + dist(stars[i], stars[l]);
                  } else {
                    curPerimeter *= dist(stars[i], stars[k]) + dist(stars[i], stars[j]);
                  }
                  minPerimeter = Math.min(minPerimeter, curPerimeter);
                }
              }
            }
          }
        }
        System.out.println(minPerimeter == Long.MAX_VALUE ? "IMPOSSIBLE" : minPerimeter);
      }
    }
  }

  private static boolean atCenter(int x1, int y1, int x2,
                                  int y2, int x3, int y3, int x4, int y4, long x, long y) {
    double d1 = (x1 - x) * (x1 - x) + (y1 - y) * (y1 - y);
    double d2 = (x2 - x) * (x2 - x) + (y2 - y) * (y2 - y);
    double d3 = (x3 - x) * (x3 - x) + (y3 - y) * (y3 - y);
    double d4 = (x4 - x) * (x4 - x) + (y4 - y) * (y4 - y);
    double[] a = new double[]{d1, d2, d3, d4};
    Arrays.sort(a);
    return a[0] == a[1] && a[2] == a[3];
  }

  private static boolean onLine(int[] a, int[] b, long bx, long by) {
    int x1 = a[0];
    int x2 = b[0];
    int y1 = a[1];
    int y2 = b[1];
    // if it is parallel to X
    if (y1 == y2) {
      return by == y1 && bx >= Math.min(x1, x2) && bx <= Math.max(x1, x2);
    }

    //if parallel to y
    if (x1 == x2) {
      return bx == x1 && by >= Math.min(y1, y2) && by <= Math.max(y1, y2);
    }

    if ((by - y1) * (x2 - x1) == (bx - x1) * (y2 - y1)) {
      return bx >= Math.min(x1, x2) && bx <= Math.max(x1, x2)
        && by >= Math.min(y1, y2) && by <= Math.max(y1, y2);
    }

    return false;
  }

  private static double dist(int[] a, int[] b) {
    double d = Math.sqrt((long) (a[0] - b[0]) * (a[0] - b[0]) + (long) (a[1] - b[1]) * (a[1] - b[1]));
    return d;
  }

  /* A function to check whether point P(x, y) lies
       inside the triangle formed by A(x1, y1),
       B_Inversions_2(x2, y2) and C(x3, y3) */
  static boolean isInside(int x1, int y1, int x2,
                          int y2, int x3, int y3, int x, int y) {
    /* Calculate area of triangle ABC */
    double A = area(x1, y1, x2, y2, x3, y3);

    /* Calculate area of triangle PBC */
    double A1 = area(x, y, x2, y2, x3, y3);

    /* Calculate area of triangle PAC */
    double A2 = area(x1, y1, x, y, x3, y3);

    /* Calculate area of triangle PAB */
    double A3 = area(x1, y1, x2, y2, x, y);

    /* Check if sum of A1, A2 and A3 is same as A */
    return (A == A1 + A2 + A3);
  }

  /* A utility function to calculate area of triangle
       formed by (x1, y1) (x2, y2) and (x3, y3) */
  static double area(int x1, int y1, int x2, int y2,
                     int x3, int y3) {
    return Math.abs((x1 * (y2 - y3) + x2 * (y3 - y1) +
      x3 * (y1 - y2)) / 2.0);
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