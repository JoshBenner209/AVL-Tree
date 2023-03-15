/*
 * Josh Benner
 * CS 145
 * March 6
 * 
 * This class takes in a size and prints an equal lateral triangle 
 * with sides lengths maxDots it recursively builds the sierpiński triangle
 * stopping when current row is equal/greater than maxDots
 */

public class ASCIIARt {
   
   public void print(int maxDots) {
        printFractal(maxDots, 0);
    }

    private void printFractal(int maxDots, int currentRow) {
        //base case= current row >= MaxDots
    	//recursive case: 
    	if (!(currentRow >= maxDots)) { 
        // print space
        for (int i = 0; i < currentRow; i++) {
            System.out.print(" ");
        }
        //create fractal 
        for (int j = 0; j + currentRow < maxDots; j++) {
            if ((j & currentRow) != 0) {// print two spaces if not zero
                System.out.print("  ");
            } else {//print . otherwise
                System.out.print(". ");
            }
        }

        System.out.println();

        printFractal(maxDots, currentRow + 1);
    }
 }
}

   