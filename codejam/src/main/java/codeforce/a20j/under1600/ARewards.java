package codeforce.a20j.under1600;

import java.util.Scanner;

/**
 * @author pribic (Priyank Doshi)
 * @since 02/05/21
 */
public class ARewards {

  public static void main(String[] args) {
    try (Scanner sc = new Scanner(System.in)) {
      int T = 1;//sc.nextInt();
      for (int tt = 1; tt <= T; tt++) {
        int trophy = sc.nextInt() + sc.nextInt() + sc.nextInt();
        int medals = sc.nextInt() + sc.nextInt() + sc.nextInt();
        int shelves = sc.nextInt();
        System.out.println( (trophy + 4) / 5 + (medals + 9) / 10 <= shelves ? "YES" : "NO");
      }
    }
  }
}