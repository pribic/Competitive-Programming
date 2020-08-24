package codeforce.div2.r662;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Scanner;

import static java.util.Comparator.comparingInt;

public class ProblemC {
  public static void main(String[] args) {
    try (Scanner sc = new Scanner(System.in)) {
      int t = sc.nextInt();
      for (int i = 0; i < t; i++) {
        int n = sc.nextInt();
        PriorityQueue<holder> maxHeap = new PriorityQueue<>((h1, h2) -> h2.freq - h1.freq);
        int[] fre = new int[n + 1];
        for (int j = 0; j < n; j++) {
          int val = sc.nextInt();
          fre[val]++;
        }

        for (int j = 0; j <= n; j++) {
          if (fre[j] > 0) {
            maxHeap.add(new holder(fre[j], j));
          }
        }

        int[] ans = new int[n];
        for (int j = 0; j < n; j++) {
          holder h = maxHeap.remove();
          ans[j] = h.val;
          h.freq--;
          maxHeap.add(h);
        }

        int aa = 0;
        Arrays.stream(ans).forEachOrdered(x -> System.out.print(x + " "));
        System.out.println();
        for (int j = 1; j < n; j++) {
          if (ans[j] == ans[0]) {
            aa = j - 1;
            break;
          }
        }
        System.out.println(aa);
      }
    }
  }

  static class holder {
    int freq;
    int val;

    public holder(int freq, int val) {
      this.freq = freq;
      this.val = val;
    }

    @Override
    public String toString() {
      return freq + " " + val;
    }
  }
}


//3 - - 3 - - 3