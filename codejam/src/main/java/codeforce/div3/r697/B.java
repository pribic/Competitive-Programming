package codeforce.div3.r697;

import java.util.Scanner;

/**
 * @author pribic (Priyank Doshi)
 * @since 25/01/21
 */
public class B {

  public static void main(String[] args) {
    try (Scanner sc = new Scanner(System.in)) {
      int T = sc.nextInt();
      for (int tt = 1; tt <= T; tt++) {
        int n = sc.nextInt();
        boolean found = false;
        for (int a = 0; a <= (1000000 + 2019) / 2020; a++) {
          if (n >= 2020 * a && (n - 2020 * a) % 2021 == 0) {
            found = true;
            System.err.println(a + " " + ((n - 2020 * a) / 2021));
            break;
          }
        }
        System.out.println(found ? "YES" : "NO");
      }
    }
  }
}