package codeforce.educational.r93;

import java.util.Scanner;

public class ProblemB {

  public static void main(String[] args) {
    try (Scanner sc = new Scanner(System.in)) {
      int t = sc.nextInt();
      for (int ii = 0; ii < t; ii++) {
        String s = sc.next();

        int[] freq = new int[101];

        int cnt = 0;

        for (char c : s.toCharArray()) {
          if (c == '1') {
            cnt++;
          } else {
            freq[cnt]++;
            cnt = 0;
          }
        }
        freq[cnt]++;

        int ans = 0;
        boolean flag = true;
        for (int i = 100; i > 0;) {
          if (freq[i] > 0) {
           // System.out.println("i = " + i);
           // System.out.println("freq[i] = " + freq[i]);
            //System.out.println("flag = " + flag);
            if (flag) {
              ans += i;
              //System.out.println("ans = " + ans);
            }
            flag = !flag;
            freq[i]--;
          }
          else {
            i--;
          }
        }
        System.out.println(ans);
      }
    }
  }
}
