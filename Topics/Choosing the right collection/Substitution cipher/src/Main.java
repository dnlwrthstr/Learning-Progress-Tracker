import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;
import java.util.stream.Collectors;
import java.util.stream.Stream;

class Main {
    public static <K, V> Stream<K> keys(Map<K, V> map, V value) {
        return map
                .entrySet()
                .stream()
                .filter(entry -> value.equals(entry.getValue()))
                .map(Map.Entry::getKey);
    }

    public static void main(String[] args) {
        TreeMap<Character, Character> encoding = new TreeMap<>();
        Scanner scanner = new Scanner(System.in);
        String line = scanner.nextLine();
        List<Character> keys = line.chars().mapToObj(c -> (char) c).collect(Collectors.toList());

        line = scanner.nextLine();
        List<Character> values = line.chars().mapToObj(c -> (char) c).collect(Collectors.toList());

        for (int i = 0; i < keys.size(); i++) {
            encoding.put(keys.get(i), values.get(i));
        }

        line = scanner.nextLine();
        char[] chars = line.toCharArray();
        StringBuffer cipher = new StringBuffer(chars.length);
        for (char k : chars) {
            if (encoding.containsKey(k)) {
                cipher.append(encoding.get(k));
            }
        }

        line = scanner.nextLine();
        chars = line.toCharArray();
        StringBuilder text = new StringBuilder(chars.length);
        for (char v : chars) {
            if (encoding.containsValue(v)) {
                Stream<Character> stream = keys(encoding, v);
                Character key = stream.findFirst().get();
                text.append(key);
            }
        }
        System.out.println(cipher);
        System.out.println(text);
    }
}