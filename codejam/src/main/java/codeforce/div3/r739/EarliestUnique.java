package codeforce.div3.r739;

import java.util.*;

public class EarliestUnique {

  public static void main(String[] args) {
    EarliestUnique e = new EarliestUnique(new int[]{1, 2, 3});
    e.add(1);
    System.out.println(e.earliestUnique());
    e.add(2);
    System.out.println(e.earliestUnique());
    e.add(3);
    System.out.println(e.earliestUnique());
  }

  TreeMap<Integer, Integer> unique = new TreeMap<>();
  Map<Integer, Integer> un = new HashMap<>();
  Map<Integer, Integer> freq = new HashMap<>();
  int ts = 0;

  public EarliestUnique(int[] nums) {
    for (int num : nums) {
      add(num);
    }
  }

  public void add(int num) {
    ts++;
    if (freq.containsKey(num)) {
      unique.remove(un.get(num));
      un.remove(num);
    } else {
      un.put(num, ts);
      unique.put(ts, num);
    }
    freq.put(num, freq.getOrDefault(num, 0) + 1);
  }

  public int earliestUnique() {
    return  unique.isEmpty() ? -1 : unique.firstEntry().getValue();
  }
}