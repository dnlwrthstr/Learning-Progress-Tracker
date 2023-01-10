import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Scanner;


class MapFunctions {

    public static void printWithSameLetter(Map<String, String> map) {
        // write your code herek;
        map.forEach((k, v) -> {
            if (Objects.equals(k.charAt(0), v.charAt(0))) {
                System.out.println(k + " " + v);
            }
        });

    }
}

/* Do not change code below */
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Map<String, String> map = new HashMap<>();

        while (scanner.hasNextLine()) {
            String s = scanner.nextLine();
            String[] pair = s.split(" ");
            map.put(pair[0], pair[1]);
        }

        MapFunctions.printWithSameLetter(map);
    }
}