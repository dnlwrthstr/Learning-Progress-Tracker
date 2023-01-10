import java.util.Locale;
import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in).useLocale(Locale.US);
        double degC = scanner.nextDouble();
        System.out.println(degC * 1.8 + 32);
    }
}