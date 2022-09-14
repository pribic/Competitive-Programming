package codeforce.div2.r704;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * @author pribic (Priyank Doshi)
 * @since 23/02/21
 */
public class B {

  public static void main(String[] args) {
    try (Scanner sc = new Scanner(System.in)) {
      int T = sc.nextInt();
      for (int tt = 1; tt <= T; tt++) {
/**
 * p1 p2 p3 p4
 *
 * n^3 * p1 + n^2 * p2 + n ^ 1 * p3 + p4
 *
 * 1 2 3 4
 *
 * 4 3 2 1 -> 64*4 + 16 * 3 + 4 * 2 + 1 -> 256 + 48 + 8 + 1 -> 313
 *
 * ***
 * 1 5 2 4 3
 *
 *4 2 5 3 6 1
 */
        int n = sc.nextInt();
        int[] deck = new int[n];
        int[] index = new int[n + 1];
        for (int i = 0; i < n; i++) {
          deck[i] = sc.nextInt();
          index[deck[i]] = i;
        }
        int right = n;
        List<Integer> ans = new ArrayList<>(n);
        for(int i = n; i>= 1; i--) {
          int indexOfMax = index[i];
          if(indexOfMax >= right) continue;
          for(int j = indexOfMax; j < right; j++) {
            ans.add(deck[j]);
          }
          right = indexOfMax;
        }
        for(int x : ans) System.out.print(x + " ");
        System.out.println();
      }
    }
  }
}