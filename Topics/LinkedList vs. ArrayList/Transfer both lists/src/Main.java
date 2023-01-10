import java.util.*;

class ListOperations {
    public static void transferAllElements(LinkedList<String> linkedList, ArrayList<String> arrayList) {
        // write your code here
        for (int i = 0; i < arrayList.size(); i++) {
            String s = arrayList.get(i);
            arrayList.set(i, linkedList.get(i));
            linkedList.set(i, s);
        }

    }
}