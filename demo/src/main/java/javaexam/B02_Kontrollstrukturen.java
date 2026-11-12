package javaexam;

public class B02_Kontrollstrukturen {

    public static void main(String[] args) {
        
        test_if_else_if_else();

    } // end of main

    static void test_if_else_if_else() {
        System.out.println("*** if, else if, else");
        int tag = 1;

        if (tag==1) {
            System.out.println("1");
        }

        if (tag==1111) {
            System.out.println("2");
        }

        if (tag==1) {
            System.out.println("3.1");
        } else if (tag==2) {
            System.out.println("3.2");
        } else if (tag==3) {
            System.out.println("3.3");
        }

        if(tag==2) {
            System.out.println("4.1");
        } else if(tag==3) {
            System.out.println("4.2");
        } else {
            System.out.println("4.3");
        }
    }

}
