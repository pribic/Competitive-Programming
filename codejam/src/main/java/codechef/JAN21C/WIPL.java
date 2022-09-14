package codechef.JAN21C;

import java.util.Arrays;
import java.util.Scanner;

/**
 * @author pribic (Priyank Doshi)
 * @since 01/01/21
 */
public class WIPL {

  public static void main(String[] args) {
    try (Scanner sc = new Scanner(System.in)) {
      int T = sc.nextInt();
      for (int tt = 1; tt <= T; tt++) {
        int n = sc.nextInt();
        int k = sc.nextInt();
        int[] arr = new int[n];
        boolean[] used = new boolean[n];
        for (int i = 0; i < n; i++) {
          arr[i] = sc.nextInt();
        }
        Arrays.sort(arr);
        boolean flag = false;
        int cnt = 0;
        for (int i = 0; i < 2; i++) {
          int index = n - 1 - i;
          int sum = 0;
          while (sum < k && index >= 0) {
            sum += arr[index];
            cnt++;
            used[index] = true;
            index -= 2;
            if(i == 1 && index>= -1 && !used[index + 1]) index++;
          }
          if (sum < k) {
            flag = true;
            break;
          }
          //if(flag) cnt--;
        }
        System.out.println(flag ? -1 : cnt);

      }
    }
  }
}