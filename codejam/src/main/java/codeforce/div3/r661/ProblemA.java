package codeforce.div3.r661;

import java.util.Scanner;

public class ProblemA {

  public static void main(String[] args) {
    try(Scanner sc = new Scanner(System.in)) {
      int t = sc.nextInt();
      for (int tt = 0; tt < t; tt++) {
        int n = sc.nextInt();
        boolean[] present = new boolean[101];
        int min = 200;
        int max = -1;
        for (int i = 0; i < n; i++) {
          int val = sc.nextInt();
          present[val] = true;
          min = Math.min(min, val);
          max = Math.max(max, val);
        }
        boolean flag = false;
        for(int i = min + 1; i <= max; i++) {
          if(!present[i]) {
            flag = true;
            break;
          }
        }
        if(flag) {
          System.out.println("NO");
        } else
          System.out.println("YES");
      }
    }
  }
}
