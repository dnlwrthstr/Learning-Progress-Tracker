import java.util.Locale;
import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in).useLocale(Locale.US);
        double distance = scanner.nextDouble();
        double travelTime = scanner.nextDouble();

        System.out.println(distance / travelTime);
    }
}