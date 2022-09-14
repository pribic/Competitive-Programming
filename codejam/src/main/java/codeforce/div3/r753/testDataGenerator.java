package codeforce.div3.r753;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.Writer;
import java.util.Collections;
import java.util.Scanner;
import java.util.StringTokenizer;
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
 * @see <a href="testDataGenerator" target="_top">testDataGenerator</a>
 * @since 02/11/21 10:24 PM
 */
public class testDataGenerator {

  public static void main(String[] args) {
    /*
    1
    2
    10 20
     */
    PrintWriter wr = new PrintWriter(System.out);
    wr.print("8" + System.lineSeparator() +
      "1" + System.lineSeparator() +
      "10" + System.lineSeparator() +
      "2" + System.lineSeparator() +
      "0 0" + System.lineSeparator() +
      "3" + System.lineSeparator() +
      "-1 2 0" + System.lineSeparator() +
      "4" + System.lineSeparator() +
      "2 10 1 7" + System.lineSeparator() +
      "2" + System.lineSeparator() +
      "2 3" + System.lineSeparator() +
      "5" + System.lineSeparator() +
      "3 2 -4 -2 0" + System.lineSeparator() +
      "2" + System.lineSeparator() +
      "-1 1" + System.lineSeparator() +
      "1" + System.lineSeparator() +
      "-2" + System.lineSeparator());
    wr.flush();
  }
}