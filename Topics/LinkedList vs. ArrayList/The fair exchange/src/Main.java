import java.util.ArrayList;
import java.util.LinkedList;

class ListOperations {
    public static void changeHeadsAndTails(LinkedList<String> linkedList, ArrayList<String> arrayList) {
        // write your code here
        String first = linkedList.getFirst();
        String last = linkedList.getLast();

        linkedList.removeFirst();
        linkedList.addFirst(arrayList.get(0));
        linkedList.removeLast();
        linkedList.addLast(arrayList.get(arrayList.size() - 1));

        arrayList.set(0, first);
        arrayList.set(arrayList.size() - 1, last);

    }
}