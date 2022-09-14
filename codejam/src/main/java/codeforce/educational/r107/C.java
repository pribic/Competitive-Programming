package codeforce.educational.r107;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

/**
 * @author pribic (Priyank Doshi)
 * @since 12/04/21
 */
public class C {

  public static void main(String[] args) {

    try (Scanner sc = new Scanner(System.in)) {
      int T = 1;//sc.nextInt();
      for (int tt = 1; tt <= T; tt++) {
        int n = sc.nextInt();
        int q = sc.nextInt();
        int[] cards = new int[51];
        for (int i = 1; i <= n; i++) {
          int card = sc.nextInt();
          if (cards[card] == 0) {
            cards[card] = i;
          }
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < q; i++) {
          int color = sc.nextInt();
          int idx = cards[color];
          sb.append(idx).append(" ");
          for (int card = 1; card <= 50; card++) {
            if (cards[card] > 0 && cards[card] < idx) {
              cards[card]++;
            }
          }
          cards[color] = 1;
        }
        System.out.println(sb);
      }
    }
  }

}