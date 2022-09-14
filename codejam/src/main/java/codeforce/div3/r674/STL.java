package codeforce.div3.r674;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class STL {

  public static void main(String[] args) {
    List<Integer> list = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9,10,11, 11);
    System.out.println("list.stream().reduce((a, b) -> a + b) = " + list.stream().reduce(Integer::sum));
    System.out.println("Collections.frequency(list, 11) = " + Collections.frequency(list, 11));
    Collections.reverse(list);
    System.out.println("list.stream().collect(Collectors.partitioningBy(num -> num < 4)) = "
      + list.stream().collect(Collectors.partitioningBy(num -> num < 4)));
    System.out.println("list.stream().collect(Collectors.groupingBy(num -> num < 4)) = "
      + list.stream().collect(Collectors.groupingBy(num -> num + "0")));
  }
}
