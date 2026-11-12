package javaexam;

public class B04_Arrays {

    public static void main(String[] args) {

        // 0 1 2
        int[] arr1 = { 12, 13, 14 };
        System.out.println("arr1[1]: " + arr1[1]); // 13

        // 0 1 2
        Integer[] arr2 = { 12, 13, 14 };
        System.out.println("arr2[1]: " + arr2[1]); // 13

        //
        int[] arr3 = new int[3]; // { 0, 0, 0 }
        System.out.println("arr3[1]: " + arr3[1]); // 0

        /*
         * Index-Operator zum Lesen und Schreiben
         */
        arr3[1] = -17;
        System.out.println("arr3[1]: " + arr3[1]); // -17

        /*
         * Iterieren mit for
         */
        System.out.println("*** index mit der for-Schleife generieren");
        for (int i = 0; i < arr3.length; i++) {
            System.out.println("arr3[" + i + "]: " + arr3[i]);
        }

        /*
         * Iterieren mit forach
         */
        System.out.println("*** Wert für Wert in x mit foreach kopieren");
        for (int x : arr3) {
            System.out.println(x);
        }


        /*
         *  Ausgewählten Beispiele zum Thema 'mehrdim. Arrays'
         */
        int[][] arr4 = {
            { 12, 13, 14 },
            { -77, -55 },
            { 4, 3, 2, 1 },
        };

        System.out.println("*** 2D-Array ausgeben mit for");

        for (int i=0; i<arr4.length; i++) {
            System.out.println("Subarray " + i + " holen");

            int[] subarray = arr4[i];
            for (int j=0; j<subarray.length; j++) {
                System.out.print(subarray[j] + " ");
            }
            System.out.println();
        }

        System.out.println("*** 2D-Array ausgeben mit for, kompakter");
        
        for (int i=0; i<arr4.length; i++) {
            
            for (int j=0; j<arr4[i].length; j++) {
                System.out.print(arr4[i][j] + " ");
            }
            System.out.println();
        }
        
        System.out.println("*** 2D-Array ausgeben mit foreach");

        for (int[] subarray : arr4) {
            for(int x : subarray) {
                System.out.print(x + " ");
            }
            System.out.println();
        }

        /*
            Geben Sie bitte das 2D-Array arr4 so aus, dass zwischen den Elementen
            eines Unterarrays Komma+Leerzeichen als Trensymbol erscheint
            (Aber nicht nach dem letzten Element eines Unterarrays):

            12, 13, 14 
            -77, -55 
            4, 3, 2, 1 
        */
    }
}
