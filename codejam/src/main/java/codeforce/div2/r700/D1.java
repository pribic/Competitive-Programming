package codeforce.div2.r700;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/**
 * @author pribic (Priyank Doshi)
 * @since 07/02/21
 */
public class D1 {

  public static void main(String[] args) {
    int nn = 1000;
    boolean[] isPrime = new boolean[nn + 1];
    Arrays.fill(isPrime, true);
    for (int i = 2; i <= Math.sqrt(nn); i++) {
      if (isPrime[i]) {
        int j = i * i;
        while (j <= nn) {
          isPrime[j] = false;
          j += i;
        }
      }
    }
    for (int i = 2; i <= nn; i++) if (isPrime[i]) System.out.println(" prime is =" + i);
    System.exit(1);
    try (Scanner sc = new Scanner(System.in)) {
      int T = 1;//sc.nextInt();
      for (int tt = 1; tt <= T; tt++) {
        int n = sc.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
          arr[i] = sc.nextInt();
        }
        if (n == 1) {
          System.out.println(n);
          continue;
        }
        List<Integer> white = new ArrayList<>();
        List<Integer> black = new ArrayList<>();
        white.add(arr[0]);
        black.add(arr[1]);
        for (int i = 2; i < n; i++) {
          if (arr[i] != white.get(white.size() - 1)) white.add(arr[i]);
          else black.add(arr[i]);
        }

        System.out.println(countSeg(white) + countSeg(black));
      }
    }
  }

  /**
   * 1 2 1
   * 1 3 1
   *
   * @param black
   * @return
   */
  private static int countSeg(List<Integer> black) {
    if (black.size() <= 1) return black.size();
    int cnt = 1;
    for (int i = 1; i < black.size(); i++) {
      if (!black.get(i).equals(black.get(i - 1))) cnt++;
    }
    return cnt;
  }
}