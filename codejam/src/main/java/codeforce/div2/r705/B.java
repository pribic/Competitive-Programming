package codeforce.div2.r705;

import java.util.Scanner;

/**
 * @author pribic (Priyank Doshi)
 * @since 06/03/21
 */
public class B {

  public static void main(String[] args) {
    try (Scanner sc = new Scanner(System.in)) {
      int T = sc.nextInt();
      for (int tt = 1; tt <= T; tt++) {
        int hour = sc.nextInt();
        int minute = sc.nextInt();
        String time = sc.next();
        Time curTime = new Time(time, hour, minute);
        while (!curTime.isValidDigits() || !curTime.reflect().isValidDigits())
          curTime = curTime.next();
        System.out.println(curTime);
      }
    }
  }

  static class Time {
    int hour, min, totalHour, totalMinute;
    static int[] reflection = new int[]{0, 1, 5, -1, -1, 2, -1, -1, 8, -1};

    public int getHour() {
      return hour;
    }

    public void setHour(int hour) {
      this.hour = hour;
    }

    public int getMin() {
      return min;
    }

    public void setMin(int min) {
      this.min = min;
    }

    public Time(String time, int totalHour, int totalMinute) {
      String[] split = time.split(":");
      hour = Integer.parseInt(split[0]);
      min = Integer.parseInt(split[1]);
      this.totalHour = totalHour;
      this.totalMinute = totalMinute;
    }

    public Time(int hour, int min, int totalHour, int totalMinute) {
      this.hour = hour;
      this.min = min;
      this.totalHour = totalHour;
      this.totalMinute = totalMinute;
    }

    boolean isValidDigits() {
      if (getHour() < 0 || getHour() >= totalHour || min < 0 || min >= totalMinute) return false;
      int[] digits = new int[]{getHour() % 10, getHour() / 10, min % 10, min / 10};
      for (int digit : digits)
        if (digit == 3 || digit == 4 || digit == 6 || digit == 7 || digit == 9) return false;
      //find reflection

      return true;
    }

    //assumption is all numbers are valid
    public Time reflect() {
      int newHour = reflectNum(min);
      int newMin = reflectNum(getHour());
      return new Time(newHour, newMin, totalHour, totalMinute);
    }

    private int reflectNum(int num) {
      return 10 * (reflection[num % 10]) + (reflection[num / 10]);
    }

    public Time next() {
      if (min == totalMinute - 1)
        return new Time((hour + 1) % totalHour, (min + 1) % totalMinute, totalHour, totalMinute);
      else
        return new Time(hour, (min + 1) % totalMinute, totalHour, totalMinute);
    }

    @Override
    public String toString() {
      return prependZero(hour) + ":" + prependZero(min);
    }

    private String prependZero(int num) {
      if (num < 10) return "0" + num;
      return num + "";
    }
  }
}