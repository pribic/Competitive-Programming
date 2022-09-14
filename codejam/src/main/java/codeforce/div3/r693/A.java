package codeforce.div3.r693;

import java.util.Scanner;

/**
 * @author pribic (Priyank Doshi)
 * @since 05/01/21
 */
public class A {

  public static void main(String[] args) {
    try (Scanner sc = new Scanner(System.in)) {
      int T = sc.nextInt();
      for (int tt = 1; tt <= T; tt++) {
        int w = sc.nextInt();
        int h = sc.nextInt();
        int n = sc.nextInt();
        if(n == 1)
          System.out.println("YES");
        else {
          if( w % 2 == 1 && h % 2 == 1)
            System.out.println("NO");
          else {
            int max = 1;
            while (w % 2 == 0) {
              max *= 2;
              w /= 2;
            }
            while (h % 2 == 0) {
              max *= 2;
              h /= 2;
            }
            System.out.println(max >= n ? "YES" : " NO");
          }
        }
        //3 3 2
        //1 4
        //  2 1
      }
    }
  }
}