package codeforce.div2.r651;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ProblemB {
  public static void main(String[] args) {
    try (Scanner sc = new Scanner(System.in)) {
      int t = sc.nextInt();
      for (int ii = 0; ii < t; ii++) {
        int n = sc.nextInt();
        List<Holder> odd = new ArrayList<>();
        List<Holder> even = new ArrayList<>();
        for (int i = 0; i < 2 * n; i++) {
          int x = sc.nextInt();
          Holder holder = new Holder(i + 1, x);
          if (x % 2 == 0)
            even.add(holder);
          else
            odd.add(holder);
        }
        if (odd.size() % 2 == 0) {
          if (odd.size() == 0) {
            even.remove(0);
            even.remove(0);
          } else {
            odd.remove(0);
            odd.remove(0);
          }
        } else {
          odd.remove(0);
          even.remove(0);
        }

        List<Holder> combined = new ArrayList<>();
        combined.addAll(even);
        combined.addAll(odd);
        for (int i = 0; i < 2*(n - 1); i = i + 2) {
          System.out.println(combined.get(i).index + " " + combined.get(i + 1).index);
        }
      }
    }
  }

  static class Holder {
    int index;
    int val;

    public Holder(int index, int val) {
      this.index = index;
      this.val = val;
    }
  }

}
