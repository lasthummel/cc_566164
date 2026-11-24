package javaexam;

import java.util.ArrayDeque;
import java.util.Collections;
import java.util.Deque;
import java.util.Queue;

public class B12_Queue {

    public static void main(String[] args) {
        
        // Queue (Schlange)

        Queue<String> queue = new ArrayDeque<>(); // FIFO
        queue.add("mo");
        queue.add("di");
        queue.add("mo");
        queue.add("mi");
        queue.add("mo");

        System.out.println("*** Daten aus FIFO-Schlange holen");
        komponenteDieMitSchlangeArbeitet(queue); // aus FIFO kommen die Elemente in der Reihenfolge: mo, di, mi
        
        Deque<String> deck = new ArrayDeque<>();
        deck.push("mo");
        deck.push("di");
        deck.push("mi");
        Queue<String> lifoQueue = Collections.asLifoQueue(deck);
        
        System.out.println("*** Daten aus LIFO-Schlange holen");
        komponenteDieMitSchlangeArbeitet(lifoQueue);
    }

    static void komponenteDieMitSchlangeArbeitet(Queue<String> queue) {

        while ( !queue.isEmpty() ) {
            String next = queue.remove();
            System.out.println("next: " + next);
        }

    }
}
