import java.util.Locale;
import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in).useLocale(Locale.US);
        double p = scanner.nextDouble();
        double h = scanner.nextDouble();
        double g = 9.8;

        System.out.println(p * g * h);
    }
}