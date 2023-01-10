import java.util.*;

class ListOperations {
    public static void removeTheSame(LinkedList<String> linkedList, ArrayList<String> arrayList) {
        // write your code here
        //LinkedList<String> ll = new LinkedList<>(linkedList);
        //ArrayList<String> al = new ArrayList<>(arrayList);

        for (int i = arrayList.size() - 1; i >= 0; i--) {
            if (Objects.equals((String) linkedList.get(i), (String) arrayList.get(i))) {
                linkedList.remove(i);
                arrayList.remove(i);
            }
        }
    }
}