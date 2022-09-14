package codeforce.educational.r109;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Stack;
import java.util.StringJoiner;
import java.util.StringTokenizer;
import java.util.TreeSet;
import java.util.stream.Collectors;
import java.util.List;

import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;


/**
 * @author pribic (Priyank Doshi)
 * @see <a href="https://codeforces.com/contest/1525/problem/C" target="_top">https://codeforces.com/contest/1525/problem/C</a>
 * @since 16/05/21 1:49 PM
 */
public class C {
  static FastScanner sc = new FastScanner(System.in);

  public static void main(String[] args) {
    try (PrintWriter out = new PrintWriter(System.out)) {
      int T = sc.nextInt();
      for (int tt = 1; tt <= T; tt++) {
        int n = sc.nextInt();
        int m = sc.nextInt();
        Car[] cars = new Car[n];
        for (int i = 0; i < n; i++) {
          cars[i] = new Car(0, false, i);
          cars[i].pos = sc.nextInt();
        }
        for (int i = 0; i < n; i++) {
          cars[i].dir = sc.next().equals("R");
        }
        Arrays.sort(cars);
        List<Car> oddPosCar = Arrays.stream(cars).filter(car -> car.pos % 2 == 1).collect(toList());
        List<Car> evenPosCar = Arrays.stream(cars).filter(car -> car.pos % 2 == 0).collect(toList());
        int[] ans = new int[n];
        solve(oddPosCar, ans, m);
        solve(evenPosCar, ans, m);
        for (int pos : ans)
          System.out.print((pos == 0 ? -1 : pos) + " ");
        System.out.println();
      }
    }

  }
// - L - R - - - 8
  private static void solve(List<Car> cars, int[] ans, int m) {
   // System.out.println("cars.toString() = " + cars.toString());
    Stack<Car> stack = new Stack<>();
    for (Car car : cars) {
      if (stack.isEmpty()) {
        if (car.dir) {
          stack.push(car);
        }
      } else {
        if (car.dir) {
          stack.push(car);
        } else {
          //found a match
          Car car1 = stack.pop();
          Car car2 = car;
          int dist = Math.abs(car1.pos - car2.pos);
          ans[car1.idx] = ans[car2.idx] = dist / 2;
        }
      }
    }
    //get rid of R
    List<Car> allRight = cars.stream().filter(car -> ans[car.idx] == 0 && car.dir).collect(Collectors.toList());
    List<Car> allLeft = cars.stream().filter(car -> ans[car.idx] == 0 && !car.dir).collect(Collectors.toList());
    for (int i = allRight.size() - 2; i >= 0; i -= 2) {
      Car car1 = allRight.get(i);
      Car car2 = allRight.get(i + 1);
      int dist = m - car2.pos + (Math.abs(car1.pos - car2.pos) / 2);
      ans[car1.idx] = ans[car2.idx] = dist;
    }

    for (int i = 1; i < allLeft.size(); i += 2) {
      Car car1 = allLeft.get(i - 1);
      Car car2 = allLeft.get(i);
      int dist = car1.pos + (Math.abs(car1.pos - car2.pos) / 2);
      ans[car1.idx] = ans[car2.idx] = dist;
    }
    List<Car> remaining = cars.stream().filter(car -> ans[car.idx] == 0).collect(toList());
    if (remaining.size() == 2) {
      Car left = remaining.get(0);
      Car right = remaining.get(1);

      int distLeft = left.pos;
      int distRight = m - right.pos;

      if (distLeft == distRight) {
        int dist = distLeft + m / 2;
        ans[left.idx] = ans[right.idx] = dist;
      } else if (distLeft < distRight) {
        int dist = left.pos; // move left to 0
        left.pos = 0;
        right.pos += dist;
        dist += m - right.pos; //move right to m
        left.pos += m - right.pos;
        right.pos = m;
        //now by 2
        dist += Math.abs(left.pos - right.pos) / 2;
        ans[left.idx] = ans[right.idx] = dist;
      } else {
        int dist = m - right.pos; // move right to m
        left.pos -= (m - right.pos);
        right.pos = m;
        dist += left.pos; // move left to 0
        right.pos -= left.pos;
        left.pos = 0;
        //now by 2
        dist += Math.abs(right.pos - left.pos) / 2;
        ans[left.idx] = ans[right.idx] = dist;
      }
    }

    // R R
    //above code will match all R L
    // R R R or L L L
    // L L L R R R R R
    // L R
  }

  static class Car implements Comparable<Car> {
    int pos, idx;
    boolean dir;

    public Car(int pos, boolean dir, int idx) {
      this.pos = pos;
      this.dir = dir;
      this.idx = idx;
    }

    @Override
    public String toString() {
      return new StringJoiner(", ", "[", "]")
        .add("p=" + pos)
        .add("i=" + idx)
        .add("d=" + dir)
        .toString();
    }

    @Override
    public int compareTo(Car o) {
      return pos - o.pos;
    }
  } 
/*
cars will collide in group of 2,
we cannot have more than 2 cars collide at the same time at same point

1 - - - - 6 - - - 10W

all even positioned can collide 
all odd positioned can collide
 */


  static class FastScanner {
    BufferedReader br;
    StringTokenizer st;

    public FastScanner(File f) {
      try {
        br = new BufferedReader(new FileReader(f));
      } catch (FileNotFoundException e) {
        e.printStackTrace();
      }
    }

    public FastScanner(InputStream f) {
      br = new BufferedReader(new InputStreamReader(f));
    }

    String next() {
      while (st == null || !st.hasMoreTokens()) {
        String s = null;
        try {
          s = br.readLine();
        } catch (IOException e) {
          e.printStackTrace();
        }
        if (s == null)
          return null;
        st = new StringTokenizer(s);
      }
      return st.nextToken();
    }

    boolean hasMoreTokens() {
      while (st == null || !st.hasMoreTokens()) {
        String s = null;
        try {
          s = br.readLine();
        } catch (IOException e) {
          e.printStackTrace();
        }
        if (s == null)
          return false;
        st = new StringTokenizer(s);
      }
      return true;
    }

    int nextInt() {
      return Integer.parseInt(next());
    }

    long nextLong() {
      return Long.parseLong(next());
    }

    double nextDouble() {
      return Double.parseDouble(next());
    }
  }
}