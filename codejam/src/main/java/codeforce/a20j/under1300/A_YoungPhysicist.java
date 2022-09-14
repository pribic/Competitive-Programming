package codeforce.a20j.under1300;

import java.util.Scanner;

public class A_YoungPhysicist {

  public static void main(String[] args) {
    try(Scanner sc = new Scanner(System.in)) {
          int t = sc.nextInt();
          int x = 0;
          int y = 0;
          int z = 0;
          while (t-- > 0) {
            x += sc.nextInt();
            y += sc.nextInt();
            z += sc.nextInt();
          }
          if(x == y && y == z && x ==0)
            System.out.println("YES");
          else
            System.out.println("NO");
    }
  }
}
