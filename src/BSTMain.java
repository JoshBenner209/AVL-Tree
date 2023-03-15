

/*Josh Benner
  CS 145
  Lab 6
  March 6 2023
  This is the main class that test the program.
 */

public class BSTMain {

    public static void main(String[] args) {
        DictionairyFrame frame = new DictionairyFrame();
        //testTree(25);
	}
	public static void testTree(int elements){
        SearchTree<Integer> avlTree = new SearchTree<Integer>();
        for (int i= 1; i<elements; i++){
            avlTree.add(i);
           
        }
        System.out.println((avlTree.printPreOrder()));
    }
}
