package codeforce.div3.r653;

import java.util.Scanner;

public class ProblemA {

  public static void main(String[] args) {
    try(Scanner sc = new Scanner(System.in)) {
      int t = sc.nextInt();
      for (int tt = 0; tt < t; tt++) {
        int x = sc.nextInt();
        int y = sc.nextInt();
        int n = sc.nextInt();
        int i = n;
        int mod = n % x;
        if(mod == y)
          System.out.println(n);
        else if(mod > y)
          System.out.println(n - (mod - y));
        else
          System.out.println(n - (x - (y - mod) ));
      }
    }
  }
}
