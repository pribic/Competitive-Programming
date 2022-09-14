package hackercup.Y2021.round1;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class ProblemB {
  //static FastScanner sc = new FastScanner(new File("/Users/pdoshi/Downloads/B_sample.txt"));
  //static FastScanner sc = new FastScanner(new File("/Users/pdoshi/Downloads/traffic_control_validation_input.txt"));
  static FastScanner sc = new FastScanner(new File("/Users/pdoshi/Downloads/traffic_control_input.txt"));
  private static int mod = 1_000_000_007;

  public static void main(String[] args) throws IOException {
    File f = new File("/Users/pdoshi/hackercup/q2021/problemB" + System.currentTimeMillis() + ".txt");
    //File f = new File("./problemA1" + System.currentTimeMillis() + ".txt");
    f.createNewFile();
    int T = sc.nextInt();
    for (int tt = 1; tt <= T; tt++) {
      FileWriter fw = new FileWriter(f, true);
      print("Case #" + tt + ": ", fw);
      int n = sc.nextInt();
      int m = sc.nextInt();
      int A = sc.nextInt();
      int B = sc.nextInt();
      int[][] grid = new int[n][m];
      for (int[] row : grid)
        Arrays.fill(row, 2);
      for (int col = 0; col < m - 1; col++)
        grid[0][col] = grid[n - 1][col] = 1;
      for (int row = 0; row < n; row++)
        grid[row][0] = 1;
      grid[0][m - 1] = B - n - (m - 2);
      grid[n - 1][m - 1] = A - n - (m - 2);
      if (grid[0][m - 1] < 1 || grid[n - 1][m - 1] < 1)
        print("Impossible\n", fw);
      else {
        print("Possible\n", fw);
        for (int[] row : grid) {
          for (int cell : row)
            print(cell + " ", fw);
          print("\n", fw);
        }
      }
      //print("\n", fw);
      fw.close();
    }

  }

  private static void print(Object o, FileWriter fw) throws IOException {
    System.out.print(o);
    fw.write(o.toString());
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


