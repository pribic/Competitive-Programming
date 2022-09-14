package codeforce.practice;

import java.util.stream.*;

import java.util.*;

import java.util.function.*;


class HelloWorld {


  public static void main(String args[]) {

    List<String> words = Arrays.asList("hello", "hello", "mighty", "Hello");

    System.out.println(frequencyMap(words.stream()));

  }

  private static Map<String, Long> frequencyMap(Stream<String> stream) {
    return stream.collect(Collectors.groupingBy(String::toUpperCase, Collectors.counting()));
  }

}