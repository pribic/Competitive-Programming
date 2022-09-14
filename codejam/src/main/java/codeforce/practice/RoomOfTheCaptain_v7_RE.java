package codeforce.practice;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;
// https://www.codechef.com/CVTA2021/problems/CODVCAPROOM
class RoomOfTheCaptain_v7_RE {
  static BufferedReader br;
  static StringTokenizer st;
  public static void main(String[] args) throws IOException, InterruptedException {
    br = new BufferedReader(new InputStreamReader(System.in));
    int t = 1;// ri();
    for (int tcn = 1; tcn <= t; tcn++) {
      int k = ri();
      Set<String> ones = new HashSet<>();
      Set<String> duplicates = new HashSet<>();
      st = new StringTokenizer(br.readLine());
      String room = null;
      while (st.hasMoreElements()) {
        room = st.nextToken();
        if (duplicates.contains(room)) {
          // do nothing
        } else if (ones.contains(room)) {
          ones.remove(room);
          duplicates.add(room);
        } else {
          ones.add(room);
        }
      }
      if (ones.size() > 1)
        throw new IllegalArgumentException();
      for (String ans : ones) {
        System.out.println(ans);
        break;
      }
    }
  }
  // read int
  static int ri() throws IOException {
    return Integer.parseInt(next());
  }
  static String next() throws IOException {
    while (st == null || !st.hasMoreElements()) {
      st = new StringTokenizer(br.readLine());
    }
    return st.nextToken();
  }
}