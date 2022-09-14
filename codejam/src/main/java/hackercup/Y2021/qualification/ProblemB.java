package hackercup.Y2021.qualification;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

public class ProblemB {
  /*
  FOXEN -> 
  FBHC
  
  
   */
  public static void main(String[] args) throws IOException {
    try (Scanner sc = new Scanner(System.in)) {
      File f = new File("/Users/pdoshi/hackercup/q2021/problemB" + System.currentTimeMillis() + ".txt");
      f.createNewFile();

      int T = sc.nextInt();
      for (int tt = 1; tt <= T; tt++) {
        FileWriter fw = new FileWriter(f, true);
        fw.append("Case #" + tt + ": ");
        String str = sc.next();
        int q = sc.nextInt();
        int[][] cost = new int[26][26];
        for (int[] row : cost) Arrays.fill(row, 1000);
        while (q-- > 0) {
          char[] pair = sc.next().toCharArray();
          cost[pair[0] - 'A'][pair[1] - 'A'] = 1;
        }
        for (int via = 0; via < 26; via++) {
          for (int source = 0; source < 26; source++) {
            for (int dest = 0; dest < 26; dest++) {
              cost[source][dest] = Math.min(cost[source][dest], cost[source][via] + cost[via][dest]);
            }
          }
        }
        int minCost = Integer.MAX_VALUE;
        for (char target = 'A'; target <= 'Z'; target++) {
          int curcost = 0;
          for (char cur : str.toCharArray()) {
            if (cur != target) {
              //can we change cur in target.
              if (cost[cur - 'A'][target - 'A'] < 26) {
                curcost += cost[cur - 'A'][target - 'A'];
              } else {
                curcost = Integer.MAX_VALUE;
                break;
              }
            }
          }
          minCost = Math.min(minCost, curcost);
        }
        print(minCost == Integer.MAX_VALUE ? -1 : minCost, fw);
        fw.append("\n");
        fw.close();
      }

    }

  }

  private static boolean isVovel(Character ch) {
    return ch == 'A' || ch == 'E' || ch == 'I' || ch == 'O' || ch == 'U';
  }

  private static void print(Object o, FileWriter fw) throws IOException {
    System.out.println(o);
    fw.write(o.toString());
  }
}
