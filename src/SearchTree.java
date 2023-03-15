/*
  Josh Benner
  CS 145
  Lab 6
  March 6
  SearchTree stores and prints an AVL tree of
  objects of type E.
*/

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;


public class SearchTree<E extends Comparable<E>> {
    private SearchTreeNode<E> overallRoot; // root of overall tree
    
    //constructs an empty search tree
    public SearchTree() {
        overallRoot = null;
    }
    
    public int height(){
        if(overallRoot==null) return 0;
        return overallRoot.height;
    }
   
    //add adds value to tree
    //param value is the data to be stored in root
    public void add(E value) {
        overallRoot = add(overallRoot, value);
    }

    //add creates a new searchtree node and adds the value as data
    // param root is the root of the current tree/subtree
    // param value is the data to be added to tree
    private SearchTreeNode<E> add(SearchTreeNode<E> root, E value) {
        if (root == null) {
            root = new SearchTreeNode<E>(value);
        } else if (root.data.compareTo(value) >= 0) {
            root.left = add(root.left, value);
        } else {
            root.right = add(root.right, value);
        }
        //update balance factor and height
        update(root);
        //re-balance tree
        return balance(root);
    }
    /**
     * check the balance factor of subtree. if balance factor>|1|
     * perform rotation
     * @param root of tree/ subtree
     * @return the rotated subtree
     */
    private SearchTreeNode<E> balance(SearchTreeNode<E> root){
        //Left heavy 
        if(root.balanceFactor ==-2){

            //left-left case:
            if(root.left.balanceFactor<=0){
                return leftLeftCase(root);
            //left-right case:
            }else{
                return leftRightCase(root);
            }
        //Right Heavy
        }else if(root.balanceFactor == +2){

            // right-right Case:
            if(root.right.balanceFactor>=0){
                return rightRightCase(root);
            }else{//right-left case:
                return rightLeftCase(root);
            }
        }
        //root is whithin threshold <=|1|
        return root;
    }
    
    /**
     * @param root of tree/subtree
     * @return right rotation of subtree
     */
    private SearchTreeNode<E> leftLeftCase(SearchTreeNode<E> root){
        return rightRotation(root);
    }
    
    /**
     * @param root of tree/subtree
     * @return double rotated subtree
     */
    private SearchTreeNode<E> leftRightCase(SearchTreeNode<E> root){
        root.left = leftRotation(root.left);
        return leftLeftCase(root);
    }
   
    /**
     * @param root of tree/subtree
     * @return left rotated subtree
     */
    private SearchTreeNode<E> rightRightCase(SearchTreeNode<E> root){
        return leftRotation(root);
    }

    /**
     * @param root of subtree/ tree
     * @return double rottated subtree
     */
    private SearchTreeNode<E> rightLeftCase(SearchTreeNode<E> root){
        root.right=rightRotation(root.right);
        return rightRightCase(root);
    }
    
    /**
     * @param root of tree/subtree
     * @return new root of subtree/ tree
     */
    private SearchTreeNode<E> leftRotation(SearchTreeNode<E> root){
        SearchTreeNode<E> newParent = root.right;
        root.right= newParent.left;
        newParent.left= root;
        update(root);
        update(newParent);
        return newParent;
    }
    /**
     * @param root of tree / subtree
     * @return new root of tree/subtree
     */
    private SearchTreeNode<E> rightRotation(SearchTreeNode<E> root){
        SearchTreeNode<E> newParent= root.left;
        root.left = newParent.right;
        newParent.right= root;
        update(root);
        update(newParent);
        return newParent;
    }
    /**
     * update the hieght and balance factor
     * @param root of tree/ subtree
     */
    private void update(SearchTreeNode<E> root){
        int leftRootHeight = (root.left == null) ? -1 : root.left.height;
        int rightRootHeight = (root.right == null) ? -1 : root.right.height;
       // update height
        root.height = 1+ Math.max(leftRootHeight, rightRootHeight);
        // update balance factor
        root.balanceFactor= rightRootHeight-leftRootHeight;
    }
    // post: returns true if tree contains value, returns false otherwise
    public boolean contains(E value) {
        return contains(overallRoot, value);
    }   

    //returns boolean value false if tree doesnt contain value and true if
    //it does
    //param root is the root of tree/subtree
    //param value is the value that is being searched in tree
    private boolean contains(SearchTreeNode<E> root, E value) {
        if (root == null) {
            return false;
        } else {
            int compare = value.compareTo(root.data);
            if (compare == 0) {
                return true;
            } else if (compare < 0) {
                return contains(root.left, value);
            } else {   // compare > 0
                return contains(root.right, value);
            }
        }
    }

    // prints the tree in -order
    //returns array list of this order
    public List<E> printInOrder() {
        return printInorder(overallRoot);
    }

    //does the work for public method above
    private List<E> printInorder(SearchTreeNode<E> root) {
        List<E> list= new ArrayList<>();
    	if (root != null) {
            list.addAll(printInorder(root.left));
            list.add(root.data);
            //System.out.println(root.data);//debug print 
            
            list.addAll(printInorder(root.right));
        }
    	return list;
    }
    
    // prints in pre order
    //returns a list of pre order 
    public List<E> printPreOrder(){
	   return printPreOrder(overallRoot);  
   }

    //does work for public method above
    private List<E> printPreOrder(SearchTree.SearchTreeNode<E> root) {
    	List<E> list= new ArrayList<>();
    	if (root != null) {
    		  list.add(root.data);
    		  //System.out.println(root.data);//debug print 
    		  list.addAll(printInorder(root.left));  
    		  list.addAll(printInorder(root.right));
        }
   
    	return list;
    }
    
    //print post-order
    //return list of post-order
    public List<E> printPostOrder(){
    	return printPostOrder(overallRoot);
    }
    
    // does work for public method above
    private List<E> printPostOrder(SearchTree.SearchTreeNode<E> root) {
	   	List<E> list= new ArrayList<>();
    	if (root != null) {
    		  list.addAll(printInorder(root.left));  
    		  list.addAll(printInorder(root.right));
    		  list.add(root.data);
    		  //System.out.println(root.data); // debug print 
        }
    	return list;
	}
    
    //calls private delete 
    public void delete(E value) {
		overallRoot=delete(overallRoot,value);  
	}
	
    //find node containg value and modifys the node.
    // param root is the current root/ subtree
    //param value is the value being looked for
    //return root is the modifyed root. 
    private SearchTreeNode<E> delete (SearchTree.SearchTreeNode<E> root, E value) {
		if(root==null) {
			return null;
		
		} else if(root.data.compareTo(value) >0) {
			
			root.left=delete(root.left,value);
		
		} else if(root.data.compareTo(value) <0) {
			
			root.right=delete(root.right,value);
		}else {
			if(root.right==null) {
				return root.left;
			}else if(root.left==null) {
				return root.right;
			}else {
				root.data=getMin(root.right);
				root.right=delete(root.right,root.data);
			}
		}
        update(root);
		return balance(root);	  
	}

    //serches for the minimum value in tree
    public E getMin() {
		if(overallRoot==null) {
			throw new NoSuchElementException();
		}
		return getMin(overallRoot);
	}
    
    // param root is the tree/subree
    //return E is the minimum value found
    private E getMin(SearchTree.SearchTreeNode<E> root) {
		if (root.left==null) {
			return root.data;
		}else {
			return getMin(root.left);
		}
	}
   
    // static inner class where the object represents a node
    // in the BST
	private static class SearchTreeNode<E> {
        public E data;                   // data stored in this node
        public SearchTreeNode<E> left;   // left subtree
        public SearchTreeNode<E> right;  //  right subtree
        public int height;
        public int balanceFactor;
        //  constructs a leaf node with given data
        public SearchTreeNode(E data) {
            this(data, null, null,0,0);
          
        }

        // constructs a node with the given data and links
        public SearchTreeNode(E data, SearchTreeNode<E> left,
                              SearchTreeNode<E> right, int height, int balanceFactor) {
            this.data = data;
            this.left = left;
            this.right = right;
            this.height=height;
            this.balanceFactor=balanceFactor;
        }
    }
}

