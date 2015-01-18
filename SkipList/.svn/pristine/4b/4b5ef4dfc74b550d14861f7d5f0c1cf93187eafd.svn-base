import java.util.ArrayList;
import java.util.Random;

/**
 * A SkipList is a randomized multi-level linked list
 * 
 * @param <T>
 *            The generic type of the list.
 */
public class SkipList<T extends Comparable<? super T>> {

	private Node root;
	private Node end;
	private int numNodesVisited;
	private Random randomizer;

	private static int MAX_LINKS = 10;

	/**
	 * Creates a skip list with truly random numbers. We won't use this
	 * constructor for our tests.
	 */
	public SkipList() {
		this.randomizer = new Random();
		reset();
	}

	/**
	 * Creates a SkipList with a given fixed seed. This is better for testing.
	 * 
	 * @param seed
	 *            A random seed.
	 */
	public SkipList(int seed) {
		this.randomizer = new Random(seed);
		reset();
	}

	// Creates the skip list's root and end nodes and does any other
	// initialization needed.
	private void reset() {
		this.root = new Node(null);
		this.end = new Node(null);
		//Set up head links
		for(int i = 0; i < 10 ; i++){
			this.root.links.add(this.end);
		}
	}

	/**
	 * Grabs the next random integer from the array
	 * 
	 * @return 0 or 1.
	 */
	public int getRand() {
		int temp = this.randomizer.nextInt(2);
		// Uncomment to see random values generated.
		//System.out.println("Random = " + temp);
		return temp;
	}

	/**
	 * @return the root node
	 */
	public Node getRoot() {
		return this.root;
	}

	/**
	 * Inserts the elements in the array in the SkipList in order, then iterates
	 * through the list, copying them back into the array, thus sorting the
	 * array.
	 * 
	 * @param array
	 */
	public void sort(T[] array) {
		// (For the next assignment, not this one.)

		// TODO: Increase MAX_LINKS, since 10 will be too small when sorting
		// large arrays.

		this.reset();
		// TODO: finish implementing sort.

	}

	/**
	 * Inserts the given element in the list
	 * 
	 * @param e
	 * @return true if successful; false otherwise
	 */
	public boolean insert(T e) {
		if(e==null){
			return false;
		}
		int height = 0;
		Node toAdd = new Node(e);
		while(this.getRand()==1){
			height++;
		}
		ArrayList<Node> nodeArray = new ArrayList<Node>();
		Node current = this.getRoot();
		int count = MAX_LINKS-1; //Due to size 

		for(int i = 0; i< MAX_LINKS; i++){
			nodeArray.add(null);
		}
		Node next = current.getLinks().get(count);

		while(true){
			//Points to end
			if(next.equals(this.end)){
				if(count==0){
					nodeArray.set(0,current);
					break;
				} else if(count<=height) {
					nodeArray.set(count,current);
					count--;
					next = current.getLinks().get(count);
				} else {
					count--;
					next = current.getLinks().get(count);
				}
			//Points to a node less than the one being inserted
			} else if(next.element.compareTo(e)<0){
				current = next;
				this.numNodesVisited++;
				next = current.getLinks().get(count);
				count = current.getLinks().size()-1;
			//Points to a node greaer than the one being visited
			} else {
				this.numNodesVisited++;
				if(count==0){
					nodeArray.set(count,current);
					break;
				} else {
					if(count<=height){
						nodeArray.set(count,current);
					}
					count--;
					next = current.getLinks().get(count);
				}
			} 
		}
		
		//Reassign pointers
		for(int i = 0; i<=height; i++){
			toAdd.getLinks().add(i, nodeArray.get(i).getLinks().get(i));
			nodeArray.get(i).getLinks().set(i, toAdd);
		}
		return true;
	}

	/**
	 * Removes the given element from the list
	 * 
	 * @param e
	 * @return true if successful; false otherwise
	 */
	public boolean remove(T e) {
		if(e==null){
			return false;
		}
		Node toDelete;
		ArrayList<Node> nodeArray = new ArrayList<Node>();
		Node current = this.getRoot();
		int count = MAX_LINKS-1; //Due to size 

		for(int i = 0; i< MAX_LINKS; i++){
			nodeArray.add(null);
		}
		Node next = current.getLinks().get(count);

		while(true){
			//Points to end
			if(next.equals(this.end)){
				if(count==0){
					return false;
				} else {
					count--;
					next = current.getLinks().get(count);
				}
			//Points to something less than what we are looking for
			} else if(next.element.compareTo(e)<0){
				current = next;
				this.numNodesVisited++;
				next = current.getLinks().get(count);
				count = current.getLinks().size()-1;
			//Points to something greater than what we are looking for
			} else if(next.element.compareTo(e)>0) {
				this.numNodesVisited++;
				if(count==0){
					return false;
				} else {
					count--;
					next = current.getLinks().get(count);
				}
			//Points to what we are looking for
			} else {
				toDelete = next;
				if(count==0){
					nodeArray.set(count, current);
					break;
				} else {
					nodeArray.set(count, current);
					count--;
					next = current.getLinks().get(count);
				}
			}
		}
		
		//Reassign pointers
		for(int i = 0; i<toDelete.getLinks().size(); i++){
			nodeArray.get(i).getLinks().set(i, toDelete.getLinks().get(i));
		}
		return true;
	}

	/**
	 * @return the number of nodes visited during insertion and removal
	 */
	public int getNodesVisited() {
		return this.numNodesVisited;
	}

	@Override
	public String toString() {
		// TODO: implement.
		return null;
	}

	/**
	 * A Node holds data and links to the next node on its levels.
	 * 
	 */
	public class Node {

		private T element;
		private ArrayList<Node> links;

		// You should not add any more fields.
		/**
		 * Instantiates this node with the given element and links
		 * 
		 * @param e
		 * @param size
		 */
		public Node(T e) {
			this.element = e;
			this.links = new ArrayList<Node>();
		}

		/**
		 * Returns the list of links this node is holding
		 * 
		 * @return a list of links
		 */
		public ArrayList<Node> getLinks() {
			return this.links;
		}

		/**
		 * @return this node's element
		 */
		public T getElement() {
			return this.element;
		}
	}
}