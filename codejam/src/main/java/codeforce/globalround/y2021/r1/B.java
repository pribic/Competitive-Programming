package codeforce.globalround.y2021.r1;

import java.util.Scanner;

/**
 * @author pribic (Priyank Doshi)
 * @since 28/02/21
 */
public class B {

  public static void main(String[] args) {
    try (Scanner sc = new Scanner(System.in)) {
      int T = sc.nextInt();
      for (int tt = 1; tt <= T; tt++) {
        int n = sc.nextInt();
        long u = sc.nextInt();
        long v = sc.nextInt();
        int[] obstacles = new int[n];
        for (int i = 0; i < n; i++) {
          obstacles[i] = sc.nextInt();
        }
        //check for top left chain
        int index = 1;
        while (index < n && Math.abs(obstacles[index] - obstacles[index - 1]) <= 1) index++;
        boolean allVerticle = true;
        for (int i = 1; i < n; i++) {
          if (obstacles[i] != obstacles[i - 1]) allVerticle = false;
        }
        if (allVerticle) {
          System.out.println(Math.min(u + v, v + v));
        } else if (index == n) {
          //full chain
          System.out.println(Math.min(u, v));
        } else {
          long ans = 0;
          if (obstacles[index - 1] == 0) {
            ans += Math.min(u, v);
          }
          //check bottom right chain
          index = n - 2;
          while (Math.abs(obstacles[index] - obstacles[index + 1]) <= 1) index--;
          if (obstacles[index + 1] == 1000000 + 1) {
            ans += Math.min(u, v);
          }
          System.out.println(ans);
        }
      }
    }
  }
}