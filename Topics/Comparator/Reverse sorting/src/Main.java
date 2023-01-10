import java.util.Comparator;
import java.util.List;

class Utils {

    public static void sortStrings(List<String> strings) {
        Comparator<String> comp = (String::compareTo);
        strings.sort(comp.reversed());
    }
}