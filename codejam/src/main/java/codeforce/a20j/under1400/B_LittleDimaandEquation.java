package codeforce.a20j.under1400;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * @author pribic (Priyank Doshi)
 * @since 07/03/21
 */
public class B_LittleDimaandEquation {

  public static void main(String[] args) {
    try (Scanner sc = new Scanner(System.in)) {
      int T = 1;//sc.nextInt();
      for (int tt = 1; tt <= T; tt++) {
        int a = sc.nextInt();
        int b = sc.nextInt();
        int c = sc.nextInt();
        List<Long> ans = new ArrayList<>();
        for(int i = 1; i <= 81; i++) {
          long newNum =  b * (long)Math.pow(i, a) + c;
          if(newNum > 0 && newNum < 1_000_000_000 && sumOfDigits(newNum) == i)
            ans.add(newNum);
        }
        System.out.println(ans.size());
        for(long num : ans) System.out.print(num + " ");
        System.out.println();
      }
    }
  }

  private static int sumOfDigits(long newNum) {
    int sum = 0;
    while (newNum > 0) {
      sum += newNum % 10;
      newNum /= 10;
    }
    return sum;
  }
}