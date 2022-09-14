import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * ...#
 * #...
 * #.#.
 * #..#
 *
 * @author pribic (Priyank Doshi)
 * @since 03/04/21
 */
public class MainC {
  public static void main(String[] args) {
    try (Scanner sc = new Scanner(System.in)) {
      int t = 1;//sc.nextInt();
      for (int tt = 0; tt < t; tt++) {
        int n = sc.nextInt();
        int k = sc.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
          arr[i] = sc.nextInt();
        }
        Arrays.sort(arr);
        Map<Integer, Integer> groups = new HashMap<>();
        Map<Integer, Integer> groupLeader = new HashMap<>();
        for (int num : arr) {
          if (num % k != 0 || !groupLeader.containsKey(num / k)) {
            groups.put(num, 1);
            groupLeader.put(num, num);
          } else {
            int curGroupLeader = groupLeader.get(num / k);
            groups.put(curGroupLeader, groups.get(curGroupLeader) + 1);
            groupLeader.put(num, curGroupLeader);
          }
        }
        long ans = 0;
        for (Integer key : groups.keySet()) {
          ans += (groups.get(key) + 1) / 2;
        }
        System.out.println(ans);
      }
    }
  }

}