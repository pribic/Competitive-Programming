package unacadamy.test.dp;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringJoiner;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.counting;
import static java.util.stream.Collectors.mapping;
import static java.util.stream.Collectors.toList;

public class D {

  public static void main(String[] args) {

    List<DTO> dto = Arrays.asList(new DTO("name1", "id1", "color1"),
      new DTO("name2", "id2", "color2"),
      new DTO("name1", "id3", "color3"));

    Map<String, List<Pair>> collect = dto.stream()
      .collect(
        Collectors.groupingBy(DTO::getName, mapping(dto1 -> {
          return new Pair(dto1.accountId, dto1.color);
        }, toList()))
      );
    System.out.println("collect.entrySet().stream().map( e -> new OutPut(e.getKey(), e.getValue())) = " + collect.entrySet().stream().map(e -> new OutPut(e.getKey(), e.getValue())));
    System.out.println("dto.stream().collect(Collectors.groupingBy(DTO::getName)) = " + collect);

    for (int i = 1; i < 31; i++) {
      Map<Integer, Integer> memo = new HashMap<>();
     // System.out.println(i + "=" + findWays(i, memo));
    }

  }

  private static int findWays(int n, Map<Integer, Integer> memo) {
    if (n == 1 || n == 2)
      return n;
    if (memo.containsKey(n))
      return memo.get(n);
    int ans = findWays(n - 2, memo) + findWays(n - 1, memo);
    memo.put(n, ans);
    return ans;
  }

  static class DTO {

    String name;
    String accountId;
    String color;

    @Override
    public String toString() {
      return new StringJoiner(", ", DTO.class.getSimpleName() + "[", "]")
        .add("name='" + name + "'")
        .add("accountId='" + accountId + "'")
        .add("color='" + color + "'")
        .toString();
    }

    public DTO(String name, String accountId, String color) {
      this.name = name;
      this.accountId = accountId;
      this.color = color;
    }

    public String getName() {
      return name;
    }

    public void setName(String name) {
      this.name = name;
    }

    public String getAccountId() {
      return accountId;
    }

    public void setAccountId(String accountId) {
      this.accountId = accountId;
    }

    public String getColor() {
      return color;
    }

    public void setColor(String color) {
      this.color = color;
    }
  }

  static class OutPut {
    String name;

    List<Pair> pairs;

    public OutPut(String name, List<Pair> pairs) {
      this.name = name;
      this.pairs = pairs;
    }

    @Override
    public String toString() {
      return name + " " + Arrays.deepToString(pairs.toArray());
    }
  }

  static class Pair {
    String accountId;
    String color;

    public Pair(String accountId, String color) {
      this.accountId = accountId;
      this.color = color;
    }

    @Override
    public String toString() {
      return accountId + " " + color;
    }
  }

}
