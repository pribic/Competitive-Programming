package codeforce.div2.r658;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ProblemC {
  public static void main(String[] args) {
    try (Scanner sc = new Scanner(System.in)) {
      int t = sc.nextInt();
      for (int i = 0; i < t; i++) {
        int n = sc.nextInt();
        String s1 = sc.next();
        String s2 = sc.next();
        boolean[] s = new boolean[s1.length()];
        boolean[] d = new boolean[s2.length()];
        for (int j = 0; j < n; j++) {
          s[j] = s1.charAt(j) == '1';
          d[j] = s2.charAt(j) == '1';
        }
        List<Integer> ans = new ArrayList<>();
        for (int j = n - 1; j >= 0; j--) {
          if (s[j] != d[j]) {
            if (s[0] != d[j]) {
              ans.add(j + 1);
              swapAndChange(s, 0, j);
            } else {
              ans.add(1);
              swapAndChange(s, 0, 0);
              ans.add(j + 1);
              swapAndChange(s, 0, j);
            }
          }
        }
        if (ans.size() == 0) {
          System.out.print("0");
        } else {
          System.out.print(ans.size() + " ");
          for (Integer aa : ans)
            System.out.print(aa + " ");
        }
        System.out.println();
      }
    }
  }

  private static void swapAndChange(boolean[] s, int i, int j) { //1 5
    int n = j - i + 1; //5
    int ii = i;
    while (i < j) {
      boolean temp = s[i];
      s[i] = s[j];
      s[j] = temp;
      s[i] = !s[i];
      s[j] = !s[j];
      i++;
      j--;
    }
    if( n % 2 == 1) {
      s[ii + n/2] = !s[ii + n/2];
    }
  }
}
