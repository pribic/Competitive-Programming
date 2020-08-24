package codeforce.div2.r655;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Scanner;

public class ProblemD {
  public static void main(String[] args) {
    try (Scanner sc = new Scanner(System.in)) {
      int n = sc.nextInt();
      Queue<Holder> input = new PriorityQueue<>(n, (h1, h2) -> h1.val - h2.val);
      for (int i = 0; i < n; i++) {
        input.add(new Holder(sc.nextInt(), i));
      }
      
      while (n-- > 1) {
        Holder min = input.remove();
        int index = min.index;
          
        }
      }
    }
  }

   class Holder {
    int val;
    int index;

    public Holder(int val, int index) {
      this.val = val;
      this.index = index;
    }
  }
