package codeforce.div2.r699;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

/**
 * @author pribic (Priyank Doshi)
 * @since 05/02/21
 */
public class C {

  public static void main(String[] args) {
    try (Scanner sc = new Scanner(System.in)) {
      int T = sc.nextInt();
      for (int tt = 1; tt <= T; tt++) {
        int n = sc.nextInt();
        int m = sc.nextInt();
        int[] initialColor = new int[n];
        int[] sourceF = new int[100000 + 1];
        int[] targetF = new int[100000 + 1];
        int[] painterF = new int[100000 + 1];
        int[] firstOfAnyColor = new int[100000 + 1]; //index of first poll with color c
        Map<Integer, List<Integer>> excess = new HashMap<>();
        for (int i = 0; i < n; i++) {
          int c = sc.nextInt();
          initialColor[i] = c;
          sourceF[c]++;
          if (firstOfAnyColor[c] == 0) {
            firstOfAnyColor[c] = i;
          }
          if (excess.containsKey(c)) {
            List<Integer> list = excess.get(c);
            list.add(i);
          } else {
            List<Integer> list = new ArrayList<>();
            list.add(i);
            excess.put(c, list);
          }
        }
        int[] desiredColor = new int[n];
        for (int i = 0; i < n; i++) {
          desiredColor[i] = sc.nextInt();
          targetF[desiredColor[i]]++;
        }
        int[] painters = new int[m];
        for (int i = 0; i < m; i++) {
          painters[i] = sc.nextInt();
          painterF[painters[i]]++;
        }

        //if we have a painter with color c such that c is not in target, then ans is NO

        List<Integer> extraThatNeedsToUsedAll = new ArrayList<>();
        //find surplus indexes
        for (int i = 0; i < n; i++) {
          if (sourceF[i] > 0 && sourceF[i] > targetF[i]) {
            int extra = sourceF[i] - targetF[i];
            List<Integer> indexes = excess.get(i);
            //get last extra from this
            for (int xx = extra - 1; xx < indexes.size(); xx++) {
              extraThatNeedsToUsedAll.add(i); //add color
              extraThatNeedsToUsedAll.add(indexes.get(xx)); //add index
            }
          }
        }

        boolean flag = false;
        List<Integer> ans = new ArrayList<>();
        int runningIndex = 0;
        for (int i = 0; i < m; i++) {
          int c = painters[i];
          if (targetF[c] == 0) {
            //we don't have any target poll with color c
            flag = true;
            break;
          }
          if (sourceF[c] >= targetF[c]) {
            ans.add(firstOfAnyColor[c]);
          } else if (sourceF[c] < targetF[c]) {
            if (runningIndex >= extraThatNeedsToUsedAll.size()) {
              flag = true;
              break;
            }
            int pollToBeColored = extraThatNeedsToUsedAll.get(runningIndex++);
            int pollToBeColoredIndex = extraThatNeedsToUsedAll.get(runningIndex++);
            ans.add(pollToBeColoredIndex);
            sourceF[pollToBeColored]--;
            sourceF[c]++;
          }
        }
        if (flag) {
          System.out.println("NO");
        } else {
          System.out.println("YES");
          for (int x : ans) System.out.print((x + 1) + " ");
          System.out.println();
        }

      }
    }
  }
}