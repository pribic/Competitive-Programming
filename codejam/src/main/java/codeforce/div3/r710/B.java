package codeforce.div3.r710;

import java.util.Scanner;

/**
 * @author pribic (Priyank Doshi)
 * @since 25/03/21
 */
public class B {

  public static void main(String[] args) {
    try (Scanner sc = new Scanner(System.in)) {
      int T = sc.nextInt();
      for (int tt = 1; tt <= T; tt++) {
        int n = sc.nextInt();
        int k = sc.nextInt();
        char[] str = sc.next().toCharArray();
        int ans = 0;
        if(str[0] == '*') {
          ans++;
          str[0] = 'x';
        }
        if(str[n - 1] == '*') {
          ans++;
          str[n - 1] = 'x';
        }
        if(ans < 2)
          System.out.println(ans);
        else {
          //had to replace at front and back, hence fill middle ones
          int l = 0;
          int star = -1;
          while (l < n) {
            for (int start = l + 1; start < l + k && start < n; start++) {
              if (str[start] == '*')
                star = start;
            }
            ans++;
            l = star + 1;
          }
          System.out.println(ans);
        }
      }
    }
  }
}