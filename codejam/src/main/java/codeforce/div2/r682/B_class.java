package codeforce.div2.r682;

import java.util.Scanner;

public class B_class {

  public static void main(String[] args) {
    double fisherman = 1d;
    double sum = 0.0d;
    for(int curlevel = 1; curlevel <= 25; curlevel++) {
      System.out.println(curlevel -1 + "=" + fisherman);
      sum += fisherman;
      fisherman *= 1.1d;
    }
    System.out.println("sum = " + sum);
    try (Scanner sc = new Scanner(System.in)) {
      int t = sc.nextInt();
      outer: while (t-- > 0) {
        int n = sc.nextInt();
        long[] b = new long[n];
        for (int i = 0; i < n; i++) {
          b[i] = sc.nextLong();
        }
        
        System.out.println("NO");
      }
    }
  }
}
