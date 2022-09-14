package atcoder.beginnercontest.r198;

import java.util.Scanner;

/**
 * @author pribic (Priyank Doshi)
 * @since 11/04/21
 */
public class B {

  public static void main(String[] args) {
    try (Scanner sc = new Scanner(System.in)) {
      int T = 1;//sc.nextInt();
      for (int tt = 1; tt <= T; tt++) {
          String str = sc.next();
          int idx = str.length() - 1;
          while (idx >= 0 && str.charAt(idx) == '0') idx--;
          if(idx <= 0) {
            System.out.println("Yes");
          } else {
            boolean isPalin = true;
            for(int i = 0; i <= idx / 2; i++) {
              if(str.charAt(i) != str.charAt(idx - i)) {
                isPalin = false;
                break;
              }
            }
            System.out.println(isPalin ? "Yes" : "No");
          }
      }
    }
  }
}