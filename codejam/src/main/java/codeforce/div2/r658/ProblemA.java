package codeforce.div2.r658;

import java.util.Scanner;

public class ProblemA {
  public static void main(String[] args) {
    try(Scanner sc = new Scanner(System.in)) {
      int t = sc.nextInt();
      for (int i = 0; i < t; i++) {
        int n = sc.nextInt();
        int m = sc.nextInt();
        boolean[] arr = new boolean[1001];
        for (int j = 0; j < n; j++) {
          arr[sc.nextInt()] = true;
        }
        boolean found = false;
        for (int j = 0; j < m; j++) {
          int in = sc.nextInt();
          if(!found && arr[in]) {
            System.out.println("YES");
            System.out.println("1 " + in);
            found = true;
          }
        }
        if(!found)
          System.out.println("NO");
      }
    }
  }
}
