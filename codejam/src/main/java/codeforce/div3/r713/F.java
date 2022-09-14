package codeforce.div3.r713;

import java.util.Scanner;
import java.util.function.Predicate;
import java.util.stream.IntStream;

/**
 * @author pribic (Priyank Doshi)
 * @since 04/05/21
 */
public class F {


  public static void main(String[] args) {
    try (Scanner sc = new Scanner(System.in)) {
      int T = sc.nextInt();
      for (int tt = 1; tt <= T; tt++) {
        int n = sc.nextInt();
        int c = sc.nextInt();
        int[] earning = new int[n];
        for (int i = 0; i < n; i++) {
          earning[i] = sc.nextInt();
        }
        int[] promotion = new int[n - 1];
        for (int i = 0; i < n - 1; i++) {
          promotion[i] = sc.nextInt();
        }
        int[] daysToReachHere = new int[n - 1];
        daysToReachHere[0] = (promotion[0] + earning[0] - 1) / earning[0];
        int[] daysIfWeStayHere = new int[n - 1];
        int moneyLeft = daysToReachHere[0] * earning[0] - promotion[0];
        daysIfWeStayHere[0] = daysToReachHere[0] + (c - moneyLeft + earning[0] - 1) / earning[0];
        for (int lastPromotion = 1; lastPromotion < n - 1; lastPromotion++) {
          //I have moneyLeft as of now
          //I want to collect promotion[lastPromotion] amound
          int weNeed = promotion[lastPromotion] - moneyLeft;
          //we need 10,
          // we earn 8 per day
          //we need 3 days
          daysToReachHere[lastPromotion] = (weNeed + earning[lastPromotion - 1] - 1) / earning[lastPromotion - 1];
          moneyLeft = daysToReachHere[lastPromotion] * earning[lastPromotion - 1] - promotion[lastPromotion];
          daysIfWeStayHere[lastPromotion] = daysToReachHere[lastPromotion] + (c - moneyLeft + earning[lastPromotion - 1] - 1) / earning[lastPromotion - 1];
        }

        System.out.println(IntStream.of(daysIfWeStayHere).min().getAsInt());

      }
    }
  }
}