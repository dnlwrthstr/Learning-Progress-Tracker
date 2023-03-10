import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        // put your code here
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        Deque<Integer> deque = new ArrayDeque<>();
        for(int i = 0; i < n; i++) {
            deque.push(scanner.nextInt());
        }
        for(Integer i : deque) {
            System.out.println(i);
        }
    }
}