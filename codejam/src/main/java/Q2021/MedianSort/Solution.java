package Q2021.MedianSort;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * @author pribic (Priyank Doshi)
 * @since 26/03/21
 */
public class Solution {

  public static void main(String[] args) {
    try (Scanner sc = new Scanner(System.in)) {
      int T = sc.nextInt();
      int n = sc.nextInt();
      int q = sc.nextInt();
      for (int tt = 1; tt <= T; tt++) {
        List<Integer> left = new ArrayList<>();
        List<Integer> middle = new ArrayList<>();
        List<Integer> right = new ArrayList<>();
        int[] pivots = {1, n / 2, n};
        query(pivots[0], pivots[1], pivots[2]);
        int ans = readMedian(sc);
        middle.add(ans);
        int leftBoundary = -1, rightBoundary = -1;
        for (int i = 0; i < pivots.length; i++) {
          if (ans == pivots[i]) {
            leftBoundary = pivots[(i + 1) % pivots.length];
            rightBoundary = pivots[(i + 2) % pivots.length];
            break;
          }
        }
        for (int query = 2; query <= n - 1; query++) {
          if(query != n/2) {
            query(leftBoundary, rightBoundary, query);
            int tempMedian = readMedian(sc);
            if (tempMedian == query) { // leftB < tempMedian < rightB
              middle.add(query);
            } else if (tempMedian == leftBoundary) { // query < leftB < rightB
              left.add(query);
            } else { // leftB < rightB < query
              right.add(query);
            }
          }
        }
        int[] finalArray = new int[n];
        finalArray[left.size()] = leftBoundary;
        finalArray[left.size() + 1 + middle.size()] = rightBoundary;
        solveLeft(finalArray, left, leftBoundary, sc, left.size() - 1);
        solveLeft(finalArray, middle, rightBoundary, sc, left.size() + middle.size());
        solveRight(finalArray, right, rightBoundary, sc, left.size() + 1 + middle.size() + 1);
        for (int num : finalArray) System.out.print(num + " ");
        System.out.println();
        System.out.flush();
        int judgeAns = sc.nextInt();
        if (judgeAns == -1) break;
      }
    }
  }

  private static void query(int a, int b, int c) {
    System.out.println(a + " " + b + " " + c);
    System.out.flush();
  }

  private static void solveRight(int[] finalArray, List<Integer> list, int lowerBound, Scanner sc, int idx) {
    if (idx < 0 || idx >= finalArray.length || list.isEmpty()) return;
    int pivot = list.get(0);
    if (list.size() == 1) {
      finalArray[idx] = pivot;
      return;
    }
    if (list.size() == 2) {
      // - - upperBound
      query(list.get(0), list.get(1), lowerBound);
      int tempMedian = readMedian(sc);
      if (tempMedian == pivot) { // lowerBound < pivot < list.get(1)
        finalArray[idx] = pivot;
        finalArray[idx + 1] = list.get(1);
      } else { // / lowerBound < list.get(1) < pivot
        finalArray[idx] = list.get(1);
        finalArray[idx + 1] = pivot;
      }
      return;
    }
    List<Integer> pivotLeft = new ArrayList<>();
    List<Integer> pivotRight = new ArrayList<>();
    for (int i = 1; i < list.size(); i++) {
      int curNum = list.get(i);
      query(lowerBound, pivot, curNum);
      int tempMedian = readMedian(sc);
      if (tempMedian == pivot) { // lowerBound < pivot < curNum
        pivotRight.add(curNum);
      } else {
        pivotLeft.add(curNum);
      }
    }
    int pivotIdx = idx + pivotLeft.size();
    finalArray[pivotIdx] = pivot;
    solveLeft(finalArray, pivotLeft, pivot, sc, pivotIdx - 1);
    solveRight(finalArray, pivotRight, pivot, sc, pivotIdx + 1);
  }

  private static void solveLeft(int[] finalArray, List<Integer> list, int upperBound, Scanner sc, int idx) {
    if (idx < 0 || idx >= finalArray.length || list.isEmpty()) return;
    int pivot = list.get(0);
    if (list.size() == 1) {
      finalArray[idx] = pivot;
      return;
    }
    if (list.size() == 2) {
      // - - upperBound
      query(list.get(0), list.get(1), upperBound);
      int tempMedian = readMedian(sc);
      if (tempMedian == pivot) { // list.get(1) < pivot < upperBound
        finalArray[idx] = pivot;
        finalArray[idx - 1] = list.get(1);
      } else { // pivot < list.get(1) <  upperBound
        finalArray[idx] = list.get(1);
        finalArray[idx - 1] = pivot;
      }
      return;
    }
    List<Integer> pivotLeft = new ArrayList<>();
    List<Integer> pivotRight = new ArrayList<>();
    for (int i = 1; i < list.size(); i++) {
      Integer curNum = list.get(i);
      query(pivot, upperBound, curNum);
      int tempMedian = readMedian(sc);
      if (tempMedian == pivot) { // curNum < pivot < upperBound
        pivotLeft.add(curNum);
      } else {
        pivotRight.add(curNum);
      }
    }
    int pivotIdx = idx - pivotRight.size();
    finalArray[pivotIdx] = pivot;
    solveLeft(finalArray, pivotLeft, pivot, sc, pivotIdx - 1);
    solveRight(finalArray, pivotRight, pivot, sc, pivotIdx + 1);
  }

  private static int readMedian(Scanner sc) {
    int ans = sc.nextInt();
    if (ans == -1)
      System.exit(0);
    return ans;
  }
}
//4 1 2 3


//1 47 46 45 44 43 2 == 1128