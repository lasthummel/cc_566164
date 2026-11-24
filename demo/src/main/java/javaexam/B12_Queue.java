package javaexam;

import java.util.ArrayDeque;
import java.util.Queue;

public class B12_Queue {

    public static void main(String[] args) {
        
        // Queue (Schlange)

        Queue<String> queue = new ArrayDeque<>(); // FIFO
        queue.add("mo");
        queue.add("di");
        queue.add("mi");

        komponenteDieMitSchlangeArbeitet(queue);
    }

    static void komponenteDieMitSchlangeArbeitet(Queue<String> queue) {

        while ( !queue.isEmpty() ) {
            String next = queue.remove();
            System.out.println("next: " + next);
        }

    }
}
