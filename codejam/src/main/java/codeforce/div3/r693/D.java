package codeforce.div3.r693;

import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Scanner;

/**
 * @author pribic (Priyank Doshi)
 * @since 05/01/21
 */
public class D {

  public static void main(String[] args) {
    try (Scanner sc = new Scanner(System.in)) {
      int T = sc.nextInt();
      for (int tt = 1; tt <= T; tt++) {
        int n = sc.nextInt();
        int[] arr = new int[n];
        PriorityQueue<Integer>[] pqs = new PriorityQueue[2];
        Arrays.fill(pqs, new PriorityQueue<Integer>());
        for (int i = 0; i < n; i++) {
          arr[i] = sc.nextInt();
          pqs[i % 2].add(-arr[i]);
        }
        long[] scores = new long[2];
        int turn = 0;
        while (n-- > 0) {
          int oddVal = pqs[1].isEmpty() ? 0 : -pqs[1].peek();
          int evenVal = pqs[0].isEmpty() ? 0 : -pqs[0].peek();
          if (evenVal >= oddVal) {
            //take even val
            scores[turn % 2] += evenVal;
            //put back odd val.
            pqs[turn % 2].remove();
          } else {
            pqs[1 - turn % 2].remove();
          }
          turn++;
        }
        if (scores[0] == scores[1]) {
          System.out.println("Tie");
        } else if (scores[0] > scores[1])
          System.out.println("Alice");
        else
          System.out.println("Bob");
      }
    }
  }
}