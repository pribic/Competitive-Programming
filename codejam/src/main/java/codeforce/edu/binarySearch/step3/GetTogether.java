package codeforce.edu.binarySearch.step3;

import java.util.Scanner;

public class GetTogether {

  public static void main(String[] args) {
    try(Scanner sc = new Scanner(System.in)) {
      int n = sc.nextInt();
      long[] x = new long[n];
      long[] v = new long[n];
      for (int i = 0; i < n; i++) {
        x[i] = sc.nextLong();
        v[i] = sc.nextLong();
      }
      
      double l = -1e-20;
      double r = 1e20;
      while (r - l > 10e-7){
        double mid = l + (r - l) / 2;
        if(f(mid, x, v)) {
          r = mid;
        } else {
          l = mid;
        }
      }
      System.out.println(r);
    }
  }
  
  private static boolean f(double t, long[] x, long[] v) {
    double intersectionStart = x[0] - t * v[0];
    double intersectionEnd = x[0] + t * v[0];
    for (int i = 1; i < x.length; i++) {
      intersectionStart = Math.max(intersectionStart, x[i] - t * v[i]);
      intersectionEnd = Math.min(intersectionEnd, x[i] + t * v[i]);
      if(intersectionStart > intersectionEnd) 
        return false;
    }
    return true;
  }
  
}
