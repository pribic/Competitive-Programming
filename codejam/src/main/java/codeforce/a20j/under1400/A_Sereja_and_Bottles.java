package codeforce.a20j.under1400;

import java.util.Scanner;

/**
 * @author pribic (Priyank Doshi)
 * @since 07/03/21
 */
public class A_Sereja_and_Bottles {

  public static void main(String[] args) {
    try (Scanner sc = new Scanner(System.in)) {
      int T = sc.nextInt();
      for (int tt = 1; tt <= T; tt++) {
        int n = sc.nextInt();
        int[][] bottle = new int[n][2];
        for (int i = 0; i < n; i++) {
          bottle[i][0] = sc.nextInt();
          bottle[i][1] = sc.nextInt();
        }
        boolean[] canOpen = new boolean[n];
        Integer curBottle = 0;
        boolean anyChange = true;
        while (anyChange) {
          Integer nextBottleToOpen = bottle[curBottle][1];
          if(curBottle != nextBottleToOpen) {
            canOpen[nextBottleToOpen] = true;
          }
          
        }
      }
    }
  }
}