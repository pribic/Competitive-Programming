package codechef.JAN21C;

import java.util.Arrays;
import java.util.Scanner;

/**
 * @author pribic (Priyank Doshi)
 * @since 01/01/21
 */
public class FAIRELCT {

  public static void main(String[] args) {
    try (Scanner sc = new Scanner(System.in)) {
      int T = sc.nextInt();
      for (int tt = 1; tt <= T; tt++) {
        int n = sc.nextInt();
        int m = sc.nextInt();
        int[] arrN = new int[n];
        int[] arrM = new int[m];
        int sumN = 0;
        int sumM = 0;
        for (int i = 0; i < n; i++) {
          arrN[i] = sc.nextInt();
          sumN += arrN[i];
        }
        for (int i = 0; i < m; i++) {
          arrM[i] = sc.nextInt();
          sumM += arrM[i];
        }
        Arrays.sort(arrN);
        Arrays.sort(arrM);
        int indexN = 0;
        int indexM = m - 1;
        int cnt = 0;
        while (sumN <= sumM && indexN < n && indexM >= 0) {
          int diff = arrM[indexM] - arrN[indexN];
          sumN += diff;
          sumM -= diff;
          indexN++;
          indexM--;
          cnt++;
        }
        System.out.println(sumN > sumM ? cnt : -1);
      }
    }
  }
}