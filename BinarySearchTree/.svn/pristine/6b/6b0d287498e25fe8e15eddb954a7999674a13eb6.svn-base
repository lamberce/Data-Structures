import java.util.ArrayList;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Stack;


/**
 * This class implements a Binary Search Tree.
 *
 * @author lamberce.
 *         Created Jan 10, 2014.
 * @param <T>
 */
public class BinarySearchTree<T extends Comparable<T>> implements Iterable<T>   {
	private int modCount = 0;
	private BinaryNode root;
	private BinaryNode NULL_NODE = new BinaryNode();
	
	/**
	 * Constructs an empy Binary Search Tree;
	 *
	 */
	public BinarySearchTree(){
		root = NULL_NODE;
	}
	
	/**
	 * Constructs a Binary Search Tree with n as its root.
	 *
	 * @param n
	 */
	public BinarySearchTree(BinaryNode n){
		root = n;
	}
	
	/**
	 * Checks wether the Binary Search Tree is empty.
	 *
	 * @return true if empty, else false
	 */
	public boolean isEmpty(){
		return (this.root == NULL_NODE);
	}
	
	/**
	 * Finds the height of the Binary Search Tree.
	 *
	 * @return height of tree. -1 if tree is empty.
	 */
	public int height(){
		if(this.isEmpty()){
			return -1;
		} else {
			return this.root.height();
		}
	}

	/**
	 * Returns true if element is in the tree, else it returns false.
	 *
	 * @param i
	 * @return
	 */
	public boolean contains(Object target) {
		return this.root.contains(target);
	}
	
	@Override
	public String toString() {
		ArrayList list = this.toArrayList();
		return list.toString();
	}
	
	/**
	 * Creates an ArrayList containing the tree.
	 *
	 * @return the ArrayList
	 */
	public ArrayList<Object> toArrayList(){
		return (ArrayList<Object>) this.root.toArrayList();
	}

	@Override
	public Iterator<T> iterator() {
		return new InOrderIterator(this);
	}

	/**
	 * Creates an array containing the tree
	 *
	 * @return the array
	 */
	public Object[] toArray() {
		ArrayList<Object> list = this.toArrayList();
		Object[] list2 = new Object[list.size()];
		for(int i = 0; i < list2.length; i++){
			list2[i] = list.get(i);
		}
		return list2;
	}

	/**
	 * Finds the number of elements in the array.
	 *
	 * @return the size.
	 */
	public int size() {
		ArrayList list = this.toArrayList();
		return list.size();
	}

	/**
	 * Creates a preOrderIterator.
	 *
	 * @return a preOrderIterator.
	 */
	public Iterator preOrderIterator() {
		return new PreOrderIterator(this);
	}

	/**
	 * Creates an ArrayList containing the tree.
	 *
	 * @return the ArrayList.
	 */
	private ArrayList toPreOrderArrayList() {
		return this.root.toPreOrderArrayList();
	}

	/**
	 * Inserts an element into the tree.
	 *
	 * @param i
	 * @return tree if the element is added, else false. 
	 */
	public boolean insert(Object i) {
		if(i==null){
			throw new IllegalArgumentException();
		} else if(this.root.equals(NULL_NODE)){
			this.root = new BinaryNode(i);
		}
		this.modCount++;
		return this.root.insert(i);
	}
	
	/**
	 * Gets an element from a tree.
	 *
	 * @param target
	 * @return the element if in the tree, else null.
	 */
	public T get(T target){
		if(this.contains(target)){
			return target;
		} else {
			return null;
		}
	}

	/**
	 * Removes the element from the tree.
	 *
	 * @param i
	 * @return true if the element was removed, else false.
	 */
	public boolean remove(Object i) {
		this.modCount++;
		if(i==null){
			throw new IllegalArgumentException();
		} else if(this.root==NULL_NODE){
			return false;
		} else if(!this.contains(i)) {
			return false;
		} else if(this.root.getLeftChild()==NULL_NODE&&this.root.getRightChild()==NULL_NODE){
			this.root = NULL_NODE;
			return true;
		} else {
			return this.root.remove(i);
		}
	}
	
	/**
	 * This class implements a BinaryNode
	 *
	 * @author lamberce.
	 *         Created Jan 10, 2014.
	 */
	class BinaryNode {
		private Object element;
		private BinaryNode leftChild;
		
		/**
		 * Returns the value of the field called 'leftChild'.
		 * @return Returns the leftChild.
		 */

		private BinaryNode rightChild;
		
		//NULL_NODE's value will never be looked upon
		/**
		 * Constructs a NULL_NODE.
		 *
		 */
		public BinaryNode(){
			this.element = null;
			this.leftChild = null;
			this.rightChild = null;
		}
		
		/**
		 * Creates a BinaryNode containing the element.
		 *
		 * @param element
		 */
		public BinaryNode(Object element){
			this.element = element;
			this.leftChild = NULL_NODE;
			this.rightChild = NULL_NODE;		
		}
		
		/**
		 * Helper method for height() of BinarySearchTree.
		 *
		 * @return height of tree.
		 */
		public int height(){
			if(this==NULL_NODE){
				return -1;
			}
			
			return 1 + Math.max(leftChild.height(), rightChild.height());
		}
		
		/**
		 * Helper method for contains() of BinarySearchTree.
		 *
		 * @param target
		 * @return
		 */
		public boolean contains(Object target) {
				if(this == NULL_NODE){
					return false;
				} else {
					return this.element.equals(target)|| this.leftChild.contains(target) || this.rightChild.contains(target);
				}
		}
		
		@Override
		public String toString() {
			if(this == NULL_NODE){
				return "";
			}
			
			return this.leftChild.toString() + " " + this.element.toString() + this.rightChild.toString();
		}

		/**
		 * Helper method for toArrayList() of BinarySearchTree.
		 *
		 * @return
		 */
		public ArrayList<T> toArrayList() {
			ArrayList list = new ArrayList();
			if (this != NULL_NODE){
				list.addAll(leftChild.toArrayList());
				list.add(this.element);
				list.addAll(rightChild.toArrayList());
				return list;
			} else {
				return list;
			}
		}
		
		/**
		 * Used to set the LeftChild of a BinaryNode.
		 *
		 * @param leftChild
		 */
		public void setLeftChild(BinaryNode leftChild){
			this.leftChild = leftChild;
		}
		
		/**
		 * Used to set the RightChild of a BinaryNode.
		 *
		 * @param rightChild
		 */
		public void setRightChild(BinaryNode rightChild){
			this.rightChild = rightChild;
		}
		
		/**
		 * Gets the LeftChild of the BinaryNode.
		 *
		 * @return LeftChild
		 */
		public BinaryNode getLeftChild() {
			return this.leftChild;
		}

		/**
		* Gets the RightChild of the BinaryNode.
		 *
		 * @return RightChild
		 */
		public BinaryNode getRightChild() {
			return this.rightChild;
		}

		/**
		 * Helper method for toPreOrderArrayList() method.
		 *
		 * @return
		 */
		public ArrayList<T> toPreOrderArrayList() {
			ArrayList list = new ArrayList();
			if (this != NULL_NODE){
				list.add(this.element);
				list.addAll(leftChild.toPreOrderArrayList());
				list.addAll(rightChild.toPreOrderArrayList());
				return list;
			} else {
				return list;
			}
		}

		/**
		 * Helper method for insert() in BinarySearchTree.
		 *
		 * @param i
		 * @return tree if inserted, else false.
		 */
		@SuppressWarnings("unchecked")
		public boolean insert(Object i) {
			if(this.element.equals(i)){
				return false;
			} else if(((Comparable<Object>) i).compareTo(this.element)<0) {
				if(this.getLeftChild().equals(NULL_NODE)){
					BinaryNode newNode = new BinaryNode(i);
					this.setLeftChild(newNode);
					return true;
				} else {
					return this.getLeftChild().insert(i);
				}
			} else {
				if(this.getRightChild().equals(NULL_NODE)){
					BinaryNode newNode = new BinaryNode(i);
					this.setRightChild(newNode);
					return true;
				} else {
					return this.getRightChild().insert(i);
				}
			}
		}

		/**
		 * Helper method for remove() in BinarySearchTree.
		 *
		 * @param i
		 * @return true if removed, else false.
		 */
		@SuppressWarnings("unchecked")
		public boolean remove(Object i) {
			BinaryNode changing = this;
			if(this==root&&i.equals(this.element)){
				BinaryNode temp = this.getLeftMaxLeft(); 
				if(temp == root.leftChild&&temp.rightChild==NULL_NODE){
					temp.rightChild = root.rightChild;
					root = temp;
				} else {
					root.element = temp.rightChild.element;
					temp.rightChild = NULL_NODE;
				}
				return true;
			} else if(this.leftChild!=NULL_NODE&&i.equals(this.leftChild.element)) {
				if(this.leftChild.leftChild==NULL_NODE&&this.leftChild.rightChild==NULL_NODE){
					this.leftChild=NULL_NODE;
				} else if(this.leftChild.leftChild==NULL_NODE) {
					BinaryNode temp = this.leftChild.rightChild;
					this.leftChild = temp;
				} else if(this.leftChild.rightChild==NULL_NODE){
					BinaryNode temp = this.leftChild.leftChild;
					this.leftChild = temp;
				} else if(this.leftChild.rightChild!=NULL_NODE) {
					BinaryNode temp = this.leftChild.getLeftMaxLeft().rightChild; 
					this.leftChild.element = temp.element;
					this.leftChild.leftChild = temp.leftChild;
				}
				return true;
			} else if(this.rightChild!=NULL_NODE&&i.equals(this.rightChild.element)){
				if(this.rightChild.leftChild==NULL_NODE&&this.rightChild.rightChild==NULL_NODE){
					this.rightChild=NULL_NODE;
				} else if(this.rightChild.leftChild==NULL_NODE) {
					BinaryNode temp = this.rightChild.rightChild;
					this.rightChild = temp;
				} else if(this.rightChild.rightChild==NULL_NODE){
					BinaryNode temp = this.rightChild.leftChild;
					this.rightChild = temp;
				} else {
					BinaryNode temp = this.rightChild.getLeftMaxLeft().rightChild; 
					this.rightChild.element = temp.element;
					this.rightChild.leftChild = temp.leftChild;
				}
				return true;
			}else if(((Comparable<Object>) i).compareTo(this.element)<0){
				return this.leftChild.remove(i);
			} else {
				return this.rightChild.remove(i);
			}
		}

		/**
		 * Helper method for remove() in BinaryNode.
		 *
		 * @return
		 */
		private BinaryNode getLeftMaxLeft() {
			BinaryNode leftMax = this.leftChild;
			if(leftMax.rightChild==NULL_NODE){
				return leftMax;
			}
			while(leftMax.rightChild.rightChild!=NULL_NODE){
				leftMax = leftMax.rightChild;
			}
			return leftMax;
		}
	}
	
	/**
	 * Implements a PreOrderIterator.
	 *
	 * @author lamberce.
	 *         Created Jan 10, 2014.
	 */
	class PreOrderIterator implements Iterator<T>{
		private Stack<BinaryNode> preOrder = new Stack<BinaryNode>();
		private BinarySearchTree<T> tree;
		private Object prev = null;
		private int expected;
		private boolean hasNextBeenCalled = false;
		
		/**
		 * Constructs a preOrderIterator over the given tree.
		 *
		 * @param binarySearchTree
		 */
		public PreOrderIterator(BinarySearchTree<T> itTree) {
			pushRight(itTree.root);
			this.tree = itTree;
			this.expected = this.tree.modCount;
		}
		
		@SuppressWarnings("unqualified-field-access")
		private void pushRight(BinaryNode node){
			if(node!=NULL_NODE&&node.rightChild==NULL_NODE){
				preOrder.push(node);
			} else if(node!=NULL_NODE&&node.rightChild!=NULL_NODE){
				pushRight(node.rightChild);
				preOrder.push(node);
			}
		}

		@Override
		public boolean hasNext() {
			return !this.preOrder.isEmpty();
		}

		@Override
		public T next() {
			hasNextBeenCalled = true;
			if(modCount!=expected){
				throw new ConcurrentModificationException();
			} else if(!hasNext()){
				throw new NoSuchElementException();
			}
			BinaryNode nextOne = this.preOrder.pop();
			pushRight(nextOne.leftChild);
			prev = nextOne.element;
			return (T) nextOne.element;
		}

		@Override
		public void remove() {
			if(!hasNextBeenCalled){
				throw new IllegalStateException();
			} else {
				tree.remove(prev);
			}
			hasNextBeenCalled = false;
		}
		
	}
	
	private class InOrderIterator implements Iterator<T>{
		private Stack<BinaryNode> inOrder = new Stack<BinaryNode>();
		private BinarySearchTree<T> tree;
		private int expected;
		private Object prev = null;
		private boolean hasNextBeenCalled = false;
		
		InOrderIterator(BinarySearchTree<T> ittree){
			pushLeft(ittree.root);
			this.tree = ittree;
			this.expected = tree.modCount;
		}
		
		@SuppressWarnings("unqualified-field-access")
		private void pushLeft(BinaryNode node){
			while(node!=NULL_NODE){
				this.inOrder.push(node);
				node = node.leftChild;
			}
		}

		@Override
		public boolean hasNext() {
			return !this.inOrder.isEmpty();
		}

		@Override
		public T next() {
			if(modCount!=expected){
				throw new ConcurrentModificationException();
			} else if(!hasNext()){
				throw new NoSuchElementException();
			}
			BinaryNode nextOne = this.inOrder.pop();
			pushLeft(nextOne.rightChild);
			hasNextBeenCalled = true;
			prev = nextOne.element;
			return (T) nextOne.element;
		}

		@Override
		public void remove() {
			if(!hasNextBeenCalled ){
				throw new IllegalStateException();
			} else {
				tree.remove(prev);
			}
			hasNextBeenCalled = false;
		}
	}
}
