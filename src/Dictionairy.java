/*
  Josh Benner
  Lab 6
  CS 145
  This class creates a PersonInfo Obj and puts them in a hashmap.
  The keys to the hashmap are stored in an AVL Tree. 
 */
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Dictionairy {
    private SearchTree<Integer> tree;
    private Map <Integer,PersonInfo>mapIntToPerson;
   
	//constructor 
    public Dictionairy() {
		tree=new SearchTree<Integer>();
		mapIntToPerson= new HashMap<>();
    }
    
	
	/**
	 * create personInfo obj and store in hashmap. store key in hash
	 * hash map in an AVL tree. 
	 * @param id  key to to map to person and store in tree
	 * @param fName First name
	 * @param lName Lasat Name
	 * @param email
	 * @param phone
	 * @param address
	 * @param city
	 * @param state
	 * @param zip
	 */
	public void addEntry(Integer id, String fName, String lName, String email,
	String phone,String address,String city,String state,String zip ){
		if (!verify(id)) {
			PersonInfo person=new PersonInfo(fName,lName,state,
									address,phone,city,zip,email,id);
			this.tree.add(id);
			this.mapIntToPerson.put(id, person);
		}
	}
    
    // remove an employee
    //param key is the key that maps to employee
    public void remove(int key) {
		tree.delete(key);
    }
   
    //verify that the tree contains key
    // param key is the value stored in tree
    boolean verify(int key) {
		if(tree.contains(key)){
	    	return true;
		}else {
	    	return false;
		}
    }
  
    /**
     * @param key in map
     * @return person that is mapped to key
     */
    public PersonInfo getPerson(Integer key){
		return mapIntToPerson.get(key);
	}
	
	/**
	 * get keys in tree pre order 
	 * @return a list of PersonInfo obj
	 */
	public List <PersonInfo> getEntriesPreOrder() {
		List<PersonInfo> result= new ArrayList<>(); 
		for(Integer i: tree.printPreOrder()){
			PersonInfo p = mapIntToPerson.get(i); 
			result.add(p);
		}
		return result;
	}
	
	/**
	 * get keys in Order
	 * @return list of PersonInfo objects
	 */
	public List <PersonInfo> getEntriesInOrder() {
		List<PersonInfo> result= new ArrayList<>(); 
		for(Integer i: tree.printInOrder())	{
		PersonInfo p = mapIntToPerson.get(i); 
		result.add(p);
		}
		return result;
	}
	
	/**
	 * get keys post order
	 * @return list of PersonInfo Objs
	 */
	public List <PersonInfo> getEntriesPostOrder() {
		List<PersonInfo> result= new ArrayList<>(); 
		for(Integer i: tree.printPostOrder())	{
		PersonInfo p = mapIntToPerson.get(i); 
		result.add(p);
		}
		return result;
	}
	
	/**
	 * @param id is the key stored in AVL Tree
	 * @return all info about person mapped to key. 
	 */
	public String search(Integer id){
		PersonInfo person=mapIntToPerson.get(id);
		return person.toString();
	}
}




