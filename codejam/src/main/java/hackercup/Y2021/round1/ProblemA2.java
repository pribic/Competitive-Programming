package hackercup.Y2021.round1;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class ProblemA2 {
  //static FastScanner sc = new FastScanner(new File("/Users/pdoshi/Downloads/A2_sample.txt"));
  static FastScanner sc = new FastScanner(new File("/Users/pdoshi/Downloads/weak_typing_chapter_2_input.txt"));
  //static FastScanner sc = new FastScanner(new File("/Users/pdoshi/Downloads/weak_typing_chapter_2_validation_input.txt"));
  private static int mod = 1_000_000_007;

  public static void main(String[] args) throws IOException {
    File f = new File("/Users/pdoshi/hackercup/q2021/problemA2" + System.currentTimeMillis() + ".txt");
    //File f = new File("./problemA1" + System.currentTimeMillis() + ".txt");
    f.createNewFile();
    int T = sc.nextInt();
    for (int tt = 1; tt <= T; tt++) {
      FileWriter fw = new FileWriter(f, true);
      fw.append("Case #" + tt + ": ");
      int n = sc.nextInt();
      String w = sc.next();
      long switches = 0;
      int prevI = -1;
      char prev = '.';
      char[] charArray = w.toCharArray();
      for (int i = 0, charArrayLength = charArray.length; i < charArrayLength; i++) {
        char c = charArray[i];
        if (c == 'F')
          continue;
        if (prev == '.') {
          prev = c;
          prevI = i;
        } else if (c != prev) {
          int curContribution = (int) ((prevI + 1L) * (n - i) % mod);
          switches = (switches + curContribution) % mod;
          prev = c;
          prevI = i;
        } else {
          prevI = i;
        }
      }
      print(switches, fw);
      print("\n", fw);
      fw.close();
    }

  }

  private static void print(Object o, FileWriter fw) throws IOException {
    System.out.println(o);
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


