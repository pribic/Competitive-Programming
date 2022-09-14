package codeforce.div3.r674;

import java.util.Scanner;

public class C_IncreaseAndCopy {

  public static void main(String[] args) {

    for (int i = 2; i <= 3; i++) {
      if(priyank(i) != viral(i)) {
        System.out.println(i + " " + priyank(i) + " " + viral(i));
      }
    }
  }
  
  private static long priyank(long n) {
    double sqrt = Math.sqrt(n);
    if (Math.floor(sqrt) == sqrt) {
      //perfect square
      return ((long) sqrt - 1) * 2;
    } else {
      long ans = (long) Math.floor(sqrt);
      long abc = 0;
      for (long j = ans + 1; j <= n; j++) {
        if (j * ans >= n) {
          abc = j;
          break;
        }
      }
      return abc + ans - 2;
    }
  }
  

  static long viral(long n) {
    double ans = Math.ceil(Math.sqrt(n));
    ans += Math.ceil(n / ans) - 2;
    if (ans < 0) {
      ans = 0;
    }
    return (long) ans;
  }

}

