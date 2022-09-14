package atcoder.beginnercontest.r176;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

/**
 * @author pribic (Priyank Doshi)
 * @since 29/08/20
 */
public class Main {

  public static void main(String[] args) {
    try (Scanner sc = new Scanner(System.in)) {
      int h, w, m;
      h = sc.nextInt();
      w = sc.nextInt();
      m = sc.nextInt();
      int[] ha = new int[h + 1];
      int[] wa = new int[w + 1];
      int maxh = -1;
      int maxw = -1;
      for (int i = 0; i < m; i++) {
        int x = sc.nextInt();
        int y = sc.nextInt();
        ha[x]++;
        wa[y]++;
        maxh = Math.max(maxh, ha[x]);
        maxw = Math.max(maxw, wa[y]);
      }
      System.out.println(maxh + maxw);
    }
  }

  
   
}