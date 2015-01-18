/**
 * A node of a threaded binary tree.
 * 
 * @author Claude Anderson
 * @param <T>
 */
class ThreadedBinaryNode<T> {

	/**
	 * The stored element.
	 */
	T element;

	/**
	 * The left child tree.
	 */
	ThreadedBinaryNode<T> left;

	/**
	 * The right child tree.
	 */
	ThreadedBinaryNode<T> right;

	/**
	 * When isLeftThread is true, it means that the left reference is a thread;
	 * it points to the node's in-order predecessor.
	 */
	boolean isLeftThread;

	/**
	 * When isRightThread is true, it means that the right reference is a
	 * thread; it points to the node's in-order successor.
	 */
	boolean isRightThread;

	/**
	 * Creates a single node threaded binary tree.
	 * 
	 * @param theElement
	 */
	ThreadedBinaryNode(T theElement) {
		this(theElement, null, null, true, true);
	}

	/**
	 * Creates a threaded binary tree with the given left and right references,
	 * both assumed to be threads.
	 * 
	 * @param theElement
	 * @param lt
	 * @param rt
	 */
	ThreadedBinaryNode(T theElement, ThreadedBinaryNode<T> lt,
			ThreadedBinaryNode<T> rt) {
		this(theElement, lt, rt, true, true);
	}

	/**
	 * Creates a threaded binary tree with the given left and right references
	 * and the given values of isLeftThread and isRightThread
	 * 
	 * @param theElement
	 * @param lt
	 * @param rt
	 * @param isLeftThread
	 * @param isRightThread
	 */
	ThreadedBinaryNode(T theElement, ThreadedBinaryNode<T> lt,
			ThreadedBinaryNode<T> rt, boolean isLeftThread,
			boolean isRightThread) {
		this.element = theElement;
		this.left = lt;
		this.right = rt;
		this.isLeftThread = isLeftThread;
		this.isRightThread = isRightThread;
	}

	// A somewhat strange implementation to facilitate debugging.
	@Override
	public String toString() {
		return "(" + this.element + "      "
				+ (this.left == null ? "null" : this.left.element.toString())
				+ " "
				+ (this.right == null ? "null" : this.right.element.toString())
				+ "      " + this.isLeftThread + " " + this.isRightThread + ")";
	}

	/**
	 * @return the next in-order node of the threaded tree in which this node
	 *         lives
	 */
	public ThreadedBinaryNode<T> inOrderSuccessor() {
		ThreadedBinaryNode<T> temp = this.right;
		if(this.isRightThread){
			return temp;
		} else {
			while(!temp.isLeftThread){
				temp = temp.left;
			}
			return temp;
		}
	}

	/**
	 * @return the previous in-order node of the threaded tree in which this
	 *         node lives
	 */
	public ThreadedBinaryNode<T> inOrderPredecessor() {
		ThreadedBinaryNode<T> temp = this.left;
		if(this.isLeftThread){
			return temp;
		} else {
			while(!temp.isRightThread){
				temp = temp.right;
			}
			return temp;
		}
	}

	/**
	 * TODO Put here a description of what this method does.
	 *
	 * @return
	 */
	public ThreadedBinaryNode<T> firstInOrderNode() {
		if(this.left==null){
			return this;
		} else {
			return this.left.firstInOrderNode();
		}
	}

	/**
	 * TODO Put here a description of what this method does.
	 *
	 * @return
	 */
	public ThreadedBinaryNode<T> lastInOrderNode() {
		if(this.right==null){
			return this;
		} else {
			return this.right.lastInOrderNode();
		}
	}

}
