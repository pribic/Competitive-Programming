package leetcode.practice;

import java.util.*;

public class Solution {
  public static void main(String[] args) {
    FrequencyStack fs = new FrequencyStack();
    fs.append(1);
    fs.append(2);
    fs.append(1);
    System.out.println(fs.pop());
    System.out.println(fs.pop());
    System.out.println(fs.pop());
  }
}


class FrequencyStack {
  Map<Integer, List<Integer>> map; // {num vs list of time}
  TreeMap<Integer, TreeSet<Pair1>> map1; // freq vs {time, val}
  Map<Integer, Integer> fmap; // val vs freq
  int time;

  public FrequencyStack() {
    map = new HashMap<>();
    map1 = new TreeMap<>();
    fmap = new HashMap<>();
  }

  private void print(int val) {
    System.out.println("val " + val);
    System.out.println("map " + map);
    System.out.println("map1 " + map1);
    System.out.println("fmap " + fmap);
    System.out.println("time " + time);
  }

  public void append(Integer val) {
    print(val);
    time++;

    if (!map1.containsKey(val)) {
      map.put(val, new ArrayList<>());
      map.get(val).add(time);

      map1.putIfAbsent(1, new TreeSet<>());
      map1.get(1).add(new Pair1(time, val));

      fmap.put(val, 1);
    } else {
      List<Integer> times = map.get(val);
      int lastTime = times.get(times.size() - 1); // get last access time
      int oldF = fmap.get(val);
      map1.get(oldF).remove(new Pair1(lastTime, val));
      fmap.put(val, oldF + 1);
      int newF = oldF + 1;
      map1.putIfAbsent(newF, new TreeSet<>());
      map1.get(newF).add(new Pair1(time, val));
      times.add(time);
    }
    print(val);
  }

  public int pop() {
    print(-1);
    TreeSet<Pair1> pairs = map1.lastEntry().getValue();
    
    Pair1 p = pairs.last();
    //pairs.remove(p);

    if (fmap.get(p.val) == 1) {
      int lastTime = map.get(p.val).get(0);
      map1.get(fmap.get(p.val)).remove(new Pair1(lastTime, p.val));
      if (map1.get(fmap.get(p.val)).isEmpty())
        map1.remove(fmap.get(p.val));
      fmap.remove(p.val);
      map.remove(p.val);
    } else {
      int oldF = fmap.get(p.val);
      int newF = oldF - 1;
      fmap.put(p.val, newF);

      List<Integer> times = map.get(p.val);
      int lastTime = times.get(times.size() - 1);
      int secondLastTime = times.get(times.size() - 2);
      map1.get(oldF).remove(new Pair1(lastTime, p.val));
      map1.putIfAbsent(newF, new TreeSet<>());
      map1.get(newF).add(new Pair1(secondLastTime, p.val));

      times.remove(times.size() - 1);
    }
    print(-1);
    return p.val;
  }
}

class Pair1 implements Comparable<Pair1> {
  int time, val;

  Pair1(int t, int v) {
    time = t;
    val = v;
  }

  public int compareTo(Pair1 o) { // unique time for each pair
    return this.time - o.time;
  }

  @Override
  public String toString() {
    return new StringJoiner(", ", Pair1.class.getSimpleName() + "[", "]")
      .add("time=" + time)
      .add("val=" + val)
      .toString();
  }
}



