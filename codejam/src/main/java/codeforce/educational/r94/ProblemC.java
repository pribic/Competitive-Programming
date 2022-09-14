package codeforce.educational.r94;

import java.util.Arrays;
import java.util.Scanner;

public class ProblemC {

  public static void main(String[] args) {
    try(Scanner sc = new Scanner(System.in)){
      int t = sc.nextInt();
      for (int tt = 0; tt < t; tt++) {
        String s = sc.next();
        int x = sc.nextInt();
        char[] ans = new char[s.length()];
        Arrays.fill(ans, '2');
        for (int i = 0; i < x; i++) {
          //determines i + x position
          if(i + x < s.length())
            ans[i + x] = s.charAt(i);
        }
        boolean impossible = false;
        for (int i = s.length() - 1; i > s.length() - 1 - x; i--) {
          //determines i - x position
          if ( i - x >= 0 && ans[i - x] != '2' && ans[i - x] != s.charAt(i)) {
            impossible = true;
            break;
          }
          if(i - x >=0)
          ans[i - x] = s.charAt(i);
        }

        // - - - - - -
        if (x > s.length() / 2) {
          //overlapping
          for (int i = s.length() - x; i < x; i++) {
            if(i - x >= 0)
              ans[i - x] = s.charAt(i);
            if(i + x < s.length())
              ans[i + x] = s.charAt(i);
          }
        } else {
          for(int i = x; i < s.length() - x; i++) {
              ans[i - x] = s.charAt(i);
              ans[i + x] = s.charAt(i);
          }
        }
        if (impossible) {
          System.out.println(-1);
        } else {
          for (int i = 0; i < ans.length; i++) {
            System.out.print(ans[i]);
          }
          System.out.println();
        }
      }
    }
  }
}
