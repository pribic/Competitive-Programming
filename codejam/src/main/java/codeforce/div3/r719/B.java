package codeforce.div3.r719;

import java.util.Scanner;

/**
 * @author pribic (Priyank Doshi)
 * @since 05/05/21
 */
public class B {

  public static void main(String[] args) {
    try (Scanner sc = new Scanner(System.in)) {
      int T = sc.nextInt();
      for (int tt = 1; tt <= T; tt++) {
        int n = sc.nextInt();
        int ans = 0;
        int cnt = 0;
        int backup = n;
        int lastDigit = -1;
        while (n > 0) {
          cnt++;
          lastDigit = n % 10;
          n /= 10;
        }
        n = backup;
        if(n == 1) {
          System.out.println(n);
        } else {
          //5 , cnt 5
          long newDigit = 0;
          for(int i = 0 ; i < cnt; i++) {
            newDigit *= 10L;
            newDigit += lastDigit;
          }
          System.out.println(9 * (cnt - 1) + (lastDigit - 1) + (newDigit <= n ? 1 : 0));
        }
      }
    }
  }
}