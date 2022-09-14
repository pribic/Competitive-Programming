package codeforce.practice;

import java.util.Scanner;

/**
 * @author pribic (Priyank Doshi)
 * @since 18/04/21
 */
public class Test {

  public static void main(String[] args) {
    try (Scanner sc = new Scanner(System.in)) {
      int T = sc.nextInt();
      for (int tt = 1; tt <= T; tt++) {
       int n = sc.nextInt();
       int k = sc.nextInt();
        double[] speed = new double[n];
        double[] distance = new double[n];
        for (int i = 0; i < n; i++) {
          speed[i] = sc.nextInt();
          distance[i] = sc.nextInt();
        }
        System.out.println(race(n, k, speed, distance));
      }
    }
  }

  private static double race(int n, int k, double[] speed, double[] distance) {
    double l = 0d;
    double r = k;
    for(int i = 0; i < 200; i++) {
      double m1 = l + (r - l) /3;
      double m2 = r - (r - l) /3;
      if(f1(m1, speed, distance) < f1(m2, speed, distance)) {
        r = m2;
      } else {
        l = m1;
      }
    }
    return f1(l, speed, distance);
  }

  private static double f1(double time, double[] speed, double[] distance) {
    double max = Double.MIN_VALUE;
    double min = Double.MAX_VALUE;
    for(int i = 0; i < speed.length; i++) {
      double dist = speed[i] * time + distance[i];
      max = Math.max(max, dist);
      min = Math.min(min, dist);
    }
    return max - min;
  }

  public static double ternarySearch(double b, double c) {
    double l = 0.0d;
    double r = Math.PI / 2;
    for (int i = 0; i < 200 && r > l; i++) {
      double m1 = l + (r - l) / 3;
      double m2 = r - (r - l) / 3;
      if (f(b, c, m2) < f(b, c, m1)) {
        l = m1;
      } else {
        r = m2;
      }
    }
    return f(b, c, l);
  }

  public static double f(double b, double c, double x) {
    return (x * x + b * x + c) / Math.sin(x);
  }
}