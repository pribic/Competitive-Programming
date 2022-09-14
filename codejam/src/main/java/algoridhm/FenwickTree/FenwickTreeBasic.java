package algoridhm.FenwickTree;

import java.util.Arrays;
import java.util.Scanner;
import java.util.stream.IntStream;

/**
 * @author pribic (Priyank Doshi)
 * @since 25/03/21
 */
public class FenwickTreeBasic {

  public static void main(String[] args) {
    System.out.println(10 & (-10));
    System.out.println(2 & (-2));
    System.out.println(8 & (-8));
    BIT bit = new BIT();
    int[] arr = new int[]{0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
    int[] bitArr = new int[]{0, 1, 3, 3, 10, 5, 11, 7, 36, 9, 19};
    bit.BIT = bitArr;
    Scanner sc = new Scanner(System.in);
    int t = sc.nextInt();
    while (t-- > 0) {
      int l = sc.nextInt();
      int r = sc.nextInt();
      System.out.println("bit=" + bit.rangeSum(l, r));
      System.out.println("bruteforce=" + Arrays.stream(arr, l , r + 1).sum());
    }
  }

  static class BIT {
    int[] arr;
    int[] BIT;

    void update(int i, int val) {

    }

    //both inclusive
    int rangeSum(int l, int r) {
      return get(r) - get(l - 1);
    }

    private int get(int n) {
      if (n <= 0) return 0;
      return BIT[n] + get(n - (n & -n));
    }
  }
}