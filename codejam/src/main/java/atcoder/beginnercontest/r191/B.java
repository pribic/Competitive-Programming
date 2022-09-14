package atcoder.beginnercontest.r191;

import java.util.Arrays;
import java.util.Scanner;

/**
 * @author pribic (Priyank Doshi)
 * @since 13/02/21
 */
public class B {

  public static void main(String[] args) {
    try (Scanner sc = new Scanner(System.in)) {
      int T = 1;//sc.nextInt();
      for (int tt = 1; tt <= T; tt++) {
        int n = sc.nextInt();
        int x = sc.nextInt();
        Integer[] arr = new Integer[n];
        for (int i = 0; i < n; i++) {
          arr[i] = sc.nextInt();
        }
        String ans = Arrays.stream(arr)
          .filter(num -> num != x)
          .map(String::valueOf)
          .reduce((a, b) -> a + " " + b).get();
        System.out.println(ans);
      }
    }
  }
  /**
   * 30 28 33 49 27 37 48
   * 
   * 
   */
}
