// You can experiment here, it won’t be checked

import java.util.Locale;
import java.util.Scanner;

public class Task {
  public static void main(String[] args) {
    // put your code here
    Scanner scanner = new Scanner(System.in).useLocale(Locale.US);
    double C = scanner.nextDouble();
    System.out.println(C * 1.8 + 32);
  }
}
