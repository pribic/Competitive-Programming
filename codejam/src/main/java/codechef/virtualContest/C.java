package codechef.virtualContest;

import java.util.Collection;
import java.util.Collections;
import java.util.Objects;
import java.util.Scanner;
import java.util.StringJoiner;
import java.util.TreeSet;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.HashSet;

import static java.lang.System.out;
import static java.util.stream.Collectors.joining;

/**
 * @author pribic (Priyank Doshi)
 * @since 10/05/21
 */
public class C {

  public static void main(String[] args) {
    try (Scanner sc = new Scanner(System.in)) {
      int T = 1;//sc.nextInt();
      for (int tt = 1; tt <= T; tt++) {
        int h = sc.nextInt();
        int w = sc.nextInt();
        int n = sc.nextInt();
        TreeSet<Integer> horizontalCut = new TreeSet<>();
        TreeSet<Integer> verticalCut = new TreeSet<>();
        horizontalCut.add(0);
        horizontalCut.add(h);
        verticalCut.add(0);
        verticalCut.add(w);
        MultiSet<Integer> horizontalBar = new MultiSet<>();
        MultiSet<Integer> verticalBar = new MultiSet<>();
        horizontalBar.add(h);
        verticalBar.add(w);
        StringBuilder ans = new StringBuilder();
        while (n-- > 0) {
          char type = sc.next().charAt(0);
          int pos, left, right, oldBar;
          switch (type) {
            case 'H':
              pos = sc.nextInt();
              left = verticalCut.floor(pos);
              right = verticalCut.ceiling(pos);
              verticalBar.remove(right - left);
              verticalBar.add(right - pos);
              verticalBar.add(pos - left);
              verticalCut.add(pos);
              ans.append((long) horizontalBar.last() * verticalBar.last());
              ans.append("\n");
              break;
            case 'V':
              pos = sc.nextInt();
              left = horizontalCut.floor(pos);
              right = horizontalCut.ceiling(pos);
              horizontalBar.remove(right - left);
              horizontalBar.add(right - pos);
              horizontalBar.add(pos - left);
              horizontalCut.add(pos);
              ans.append((long) horizontalBar.last() * verticalBar.last());
              ans.append("\n");
              break;
          }
        }
        out.println(ans);
      }
    }
  }

  static class MultiSet<T> extends TreeSet<T> {
    Map<T, Integer> frequency;

    public MultiSet() {
      this.frequency = new HashMap<>();
    }

    @Override
    public boolean add(T t) {
      frequency.put(t, frequency.getOrDefault(t, 0) + 1);
      return super.add(t);
    }


    @Override
    public boolean remove(Object o) {
      if (!frequency.containsKey(o))
        return false;
      frequency.put((T) o, frequency.getOrDefault(o, 1) - 1);
      if (frequency.get(o) == 0) {
        frequency.remove(o);
        super.remove(o);
      }
      return true;
    }

    @Override
    public String toString() {
      return new StringJoiner(", ", MultiSet.class.getSimpleName() + "[", "]")
        .add("frequency=" + frequency)
        .add(super.toString())
        .toString();
    }
  }
}