package codeforce.educational.r94;

import java.util.Scanner;

public class ProblemB {

  public static void main(String[] args) {
    try(Scanner sc = new Scanner(System.in)){
      int t = sc.nextInt();
      for (int i = 0; i < t; i++) {
        int p = sc.nextInt();
        int f = sc.nextInt();
        int cntS = sc.nextInt();
        int cntA = sc.nextInt();
        int s = sc.nextInt();
        int a = sc.nextInt();
        int ans = 0;
        if(s <= a) {
          ans += Math.min(cntS, p/s);
          cntS -= ans;
          if(cntS > 0) {
            ans += Math.min(cntS, f/s);
          }
        }
        System.out.println();
      }
    }
  }
}
