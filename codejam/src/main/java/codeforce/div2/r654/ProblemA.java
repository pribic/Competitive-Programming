package codeforce.div2.r654;

  import java.util.Scanner;

public class ProblemA {
  public static void main(String[] args) {
    try(Scanner sc = new Scanner(System.in)) {
      int t = sc.nextInt();
      for (int tt = 0; tt < t; tt++) {
        int n = sc.nextInt();
        System.out.println(n/2 + n%2);
      }
    }
  }
}
