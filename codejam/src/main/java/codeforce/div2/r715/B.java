package codeforce.div2.r715;

import java.util.Scanner;

/**
 * @author pribic (Priyank Doshi)
 * @since 16/04/21
 */
public class B {

  public static void main(String[] args) {
    try (Scanner sc = new Scanner(System.in)) {
      int T = sc.nextInt();
      for (int tt = 1; tt <= T; tt++) {
        int n = sc.nextInt();
        String str = sc.next();
        int cnt = 0;
        boolean isValid = true;
        int freqT = 0;
        int freqM = 0;
        for (int i = 0; i < n; i++) {
          if(str.charAt(i) == 'T') {
            cnt++;
            freqT++;
          }
          else {
            cnt--;
            freqM++;
            if(cnt < 0) {
              isValid = false;
              break;
            }
          }
        }
        cnt = 0;
        for(int i = n - 1; i >= 0; i--) {
          if(str.charAt(i) == 'T') {
            cnt++;
          } else {
            cnt--;
            if(cnt < 0) {
              isValid = false;
              break;
            }
          }
        }
        if(isValid && (freqM*2 == freqT)) {
          System.out.println("YES");
        } else {
          System.out.println("NO");
        }
      }
    }
  }
}