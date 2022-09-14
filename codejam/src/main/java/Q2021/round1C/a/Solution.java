package Q2021.round1C.a;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

import static java.lang.Math.abs;
import static java.lang.Math.max;
import static java.lang.Math.min;

/**
 * @author pribic (Priyank Doshi)
 * @since 01/05/21
 */
public class Solution {

  private static boolean enablePrintGlobal = false;

  public static void main(String[] args) {
    try (Scanner sc = new Scanner(System.in)) {
      int T = sc.nextInt();
      for (int tt = 1; tt <= T; tt++) {
        System.out.print("Case #" + tt + ": ");
        int n = sc.nextInt();
        int k = sc.nextInt();
        int[] cards = new int[n];
        Set<Integer> inputCards = new HashSet<>();
        for (int i = 0; i < n; i++) {
          cards[i] = sc.nextInt();
          inputCards.add(cards[i]);
        }
        Arrays.sort(cards);
        int ourMaxWin = 0;
        for (int card1 = 1; card1 <= k; card1++) {
          for (int card2 = card1; card2 <= k; card2++) {
            if (inputCards.contains(card1) || inputCards.contains(card2))
              continue;
            DebugOutput.print2(true, "car1", card1, "card2", card2);
            //what if we chose card1 and card2 in both cards
            int ourTempWin = 0;
            //lets check for how many numbers we will win
            for (int chosen = 1; chosen <= k; chosen++) {
              if (!inputCards.contains(chosen)) {
                //check who wins if chosen is selected
                // find closest number
                int minDiffFromInput = Integer.MAX_VALUE;
                for (int i = 0; i < cards.length; i++) {
                  minDiffFromInput = min(minDiffFromInput, abs(cards[i] - chosen));
                }
                DebugOutput.print3(false, "card1", card1, "card2", card2, "minDiffFromInput", minDiffFromInput);
                int minDiffFromChosen = min(abs(card1 - chosen), abs(card2 - chosen));
                if (minDiffFromChosen < minDiffFromInput)
                  ourTempWin++;
              }
              DebugOutput.print2(true, "chosen", chosen, "ourtemp win", ourTempWin);
            }
            DebugOutput.print3("card1", card1, "card2", card2, "ourTempWin", ourTempWin);
            ourMaxWin = max(ourMaxWin, ourTempWin);
          }
        }
        System.out.println((float) ourMaxWin / k);
      }
    }
  }

  static class DebugOutput {
    static boolean enablePrint = true;

    private static void print1(boolean enable, String name1, Object val) {
      enablePrint = enable;
      print1(name1, val);
      enablePrint = !enable;
    }

    private static void print1(String name1, Object val) {
      if (enablePrint)
        printRaw(printStr(new String[]{name1}, new Object[]{val}));
    }

    private static void print2(boolean enable, String name1, Object val1, String name2, Object val2) {
      enablePrint = enable;
      print2(name1, val1, name2, val2);
      enablePrint = !enable;
    }

    private static void print2(String name1, Object val1, String name2, Object val2) {
      if (enablePrint)
        printRaw(printStr(new String[]{name1, name2}, new Object[]{val1, val2}));
    }

    private static void print3(boolean enable, String name1, Object val1, String name2, Object val2, String name3, Object val3) {
      enablePrint = enable;
      print3(name1, val1, name2, val2, name3, val3);
      enablePrint = !enable;
    }

    private static void print3(String name1, Object val1, String name2, Object val2, String name3, Object val3) {
      if (enablePrint)
        printRaw(printStr(new String[]{name1, name2, name3}, new Object[]{val1, val2, val3}));
    }

    private static void printRaw(Object obj) {
      if (enablePrintGlobal)
        System.err.println(obj);
    }

    private static String printStr(String[] names, Object[] vals) {
      StringBuilder sb = new StringBuilder();
      for (int i = 0; i < names.length; i++) {
        sb.append(names[i]).append(" = ").append(vals[i]).append(" ");
      }
      return sb.toString();
    }
  }
}