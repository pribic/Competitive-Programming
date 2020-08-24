package codeforce.div2.r652;

import java.util.Scanner;

 
public class ProblemC {
  public static void main(String[] args) {
    try (Scanner sc = new Scanner(System.in)) {
      int t = sc.nextInt();
      for (int ii = 0; ii < t; ii++) {
        String s = sc.next();
        int[] netDiff = new int[s.length()];
        int curDiff = 0;
        for (int i = 0; i < s.length(); i++) {
          curDiff = curDiff + (s.charAt(i) == '+' ? 1 : -1);
          netDiff[i] = curDiff;
        }
        long res = 0;
        int lastIndex = 0;
        for (int init = 0; init < Integer.MAX_VALUE; init++) {
          long cur = init;
          boolean ok = true;
          int length = s.length();
          res = res + (lastIndex);
          cur = cur + ( lastIndex == 0 ? 0 : netDiff[lastIndex - 1]);
          for (int i = lastIndex; i < length; i++) {
            res = res + 1;
            if(s.charAt(i) == '+')
              cur = cur + 1;
            else
              cur = cur - 1;
            if(cur < 0) {
              lastIndex = i;
              ok = false;
              break;
            }
          }
          if(ok) {
            break;
          }
        }
        System.out.println(res);
      }
    }
  }
}
