package atcoder.beginnercontest.r179;

import java.util.Scanner;

public class GotoJail {

  public static void main(String[] args) {
    try (Scanner sc = new Scanner(System.in)) {
      int n = sc.nextInt();
      int cnt = 0;
      boolean flag = false;
      for (int i = 0; i < n; i++) {
        int a = sc.nextInt();
        int b = sc.nextInt();
        if(a == b) {
          cnt++;
        } else {
          cnt = 0;
        }
        if(cnt == 3) {
          flag = true;
        }
      }
      System.out.println(flag ? "Yes" : "No");
    }
  }
}
