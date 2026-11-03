package com.example.tag01;

/**
 * Hello world!
 *
 */
public class App {
    public static void main( String[] args ) {
        System.out.println( "Hello World!" );

        boolean isPaidService = false;
        int maxRetries = maxRetries(isPaidService);

        System.out.println("maxRetries: " + maxRetries);

    }

    static int maxRetries(boolean isPaidService) {
        if (isPaidService) 
            return 2;
        else
            return 5;
    }
}
