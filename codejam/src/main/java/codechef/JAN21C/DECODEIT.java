package codechef.JAN21C;

import java.util.Scanner;

/**
 * @author pribic (Priyank Doshi)
 * @since 01/01/21
 */
public class DECODEIT {

  public static void main(String[] args) {
    try (Scanner sc = new Scanner(System.in)) {
      int T = sc.nextInt();
      for (int tt = 1; tt <= T; tt++) {
        int n = sc.nextInt();
        String s = sc.next();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i += 4) {
          int index = 0;
          for (int j = 0; j < 4; j++) {
            index += Math.pow(2, 3 - j) * getBit(s, i + j);
          }
          sb.append((char) ('a' + index));
        }
        System.out.println(sb);
      }
    }
  }

  private static int getBit(String s, int i) {
    return s.charAt(i) - '0';
  }
}