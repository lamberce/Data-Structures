package editortrees;

// A node in a height-balanced binary tree with rank.
// Except for the NULL_sNODE (if you choose to use one), one node cannot
// belong to two different trees.

public class Node {

	enum Code {
		SAME, LEFT, RIGHT
	};

	// The fields would normally be private, but for the purposes of this class,
	// we want to be able to test the results of the algorithms in addition to
	// the
	// "publicly visible" effects

	char element;
	Node left, right; // subtrees
	int rank; // inorder position of this node within its own subtree.
	Code balance;

	// Node parent; // You may want this field.
	// Feel free to add other fields that you find useful

	// You will probably want to add several other methods

	// For the following methods, you should fill in the details so that they
	// work correctly

	/**
	 * Returns the height of the tree.
	 * 
	 * @return
	 */
	public int height() {
		if(this==null){
			return -1;
		} else if(this.left == null && this.right == null) {
			return 0;
		} else if(this.balance == Code.SAME || this.balance == Code.LEFT) {
			 return 1 + this.left.height();
		} else {
			return 1 + this.right.height();
		}
	}

	/**
	 * Converts tree to an inorder string.
	 */
	public String toString() {
		String s = "";
		if (this.right == null && this.left == null) {
			s += "" + this.element;
			return s;
		} else if (this.right == null) {
			s += "" + this.left.toString() + this.element;
			return s;
		} else if (this.left == null) {
			s += this.element + this.right.toString();
			return s;
		} else {
			s += "" + this.left.toString() + this.element
					+ this.right.toString();
			return s;
		}
	}

	/**
	 * Returns the size of the tree.
	 * 
	 * @return
	 */
	public int size() {
		if(this==null){
			return 0;
		} else if(this.right!=null) {
			return 1 + this.rank + this.right.size();
		} else {
			return 1 + this.rank;
		}
	}

	/**
	 * Adds a node into the subtree of this.
	 * 
	 * @param c
	 * @param i
	 * @param addInt
	 * @return
	 */
	public Node add(char c, IntegerContainer addInt, BooleanContainer rotated) {
		// recursive
		if (this.right != null) {
			this.right = this.right.add(c, addInt, rotated);
			if (!rotated.getHasBeenRotated()) {
				return this.tilt(false, addInt, rotated);
			} else {
				return this;
			}
		}
		// base case
		else {
			this.right = new Node();
			this.right.left = null;
			this.right.right = null;
			this.right.element = c;
			this.right.rank = 0;
			this.right.balance = Node.Code.SAME;
			if (!rotated.getHasBeenRotated()) {
				return this.tilt(false, addInt, rotated);
			} else {
				return this;
			}
		}
	}

	/**
	 * Adds a node into the subtree of this.
	 * 
	 * @param c
	 * @param i
	 * @param addInt
	 * @param rotated
	 * @return
	 */
	public Node add(char c, int pos, IntegerContainer addInt,
			BooleanContainer rotated) {
		//Go right
		if (this.rank < pos) {
			if (this.right == null && pos - rank > 1) {
				throw new IndexOutOfBoundsException();
			} else if (this.right == null) {
				Node newNode = new Node();
				newNode.element = c;
				newNode.balance = Code.SAME;
				newNode.rank = 0;
				this.right = newNode;
				if (!rotated.getHasBeenRotated()) {
					return this.tilt(false, addInt, rotated);
				} else {
					return this;
				}
			} else {
				this.right = this.right.add(c, pos - rank - 1, addInt, rotated);
				if (!rotated.getHasBeenRotated()) {
					return this.tilt(false, addInt, rotated);
				} else {
					return this;
				}
			}
		//Go left
		} else {
			if (this.left == null && pos != 0) {
				throw new IndexOutOfBoundsException();
			} else if (this.left == null && pos == 0) {
				Node newNode = new Node();
				newNode.element = c;
				newNode.balance = Code.SAME;
				newNode.rank = 0;
				this.rank += 1;
				this.left = newNode;
				if (!rotated.getHasBeenRotated()) {
					return this.tilt(true, addInt, rotated);
				} else {
					return this;
				}
			} else {
				this.rank += 1;
				this.left = this.left.add(c, pos, addInt, rotated);
				if (!rotated.getHasBeenRotated()) {
					return this.tilt(true, addInt, rotated);
				} else {
					return this;
				}
			}
		}
	}

	/**
	 * Decides between single left, single right, double left, and double right
	 * rotations.
	 * 
	 * @param parent
	 * @param isLeft
	 * @param numRot
	 * @return
	 */
	Node rotate(Node parent, boolean isLeft, IntegerContainer numRot) {
		Code balanceParent = parent.balance;
		Code balanceChild;
		Node child;
		if (isLeft) {
			child = parent.left;
			balanceChild = child.balance;
		} else {
			child = parent.right;
			balanceChild = child.balance;
		}
		if (balanceParent == Code.LEFT && balanceChild == Code.LEFT) {
			// single right
			numRot.setInteger(numRot.getInteger() + 1);
			return singleRotateRight(parent, child);
		} else if (balanceParent == Code.RIGHT && balanceChild == Code.RIGHT) {
			// single left
			numRot.setInteger(numRot.getInteger() + 1);
			return singleRotateLeft(parent, child);
		} else if (balanceParent == Code.RIGHT && balanceChild == Code.LEFT) {
			// double left
			numRot.setInteger(numRot.getInteger() + 2);
			return doubleRotateLeft(parent, child);
		} else if (balanceParent == Code.LEFT && balanceChild == Code.RIGHT) {
			// double right
			numRot.setInteger(numRot.getInteger() + 2);
			return doubleRotateRight(parent, child);
		}
		return this;
	}

	/**
	 * Updates the balance codes and rotates if needed.
	 * 
	 * @param addedLeft
	 * @param numRot
	 * @param rotated
	 * @return
	 */
	public Node tilt(boolean addedLeft, IntegerContainer numRot,
			BooleanContainer rotated) {
			if (addedLeft) {
				if (this.balance == Code.RIGHT) {
					rotated.setHasBeenRotated(true);
					this.balance = Code.SAME;
				} else if (this.balance == Code.SAME) {
					this.balance = Code.LEFT;
				} else {
					rotated.setHasBeenRotated(true);
					return rotate(this, addedLeft, numRot);
				}
			} else {
				if (this.balance == Code.LEFT) {
					rotated.setHasBeenRotated(true);
					this.balance = Code.SAME;
				} else if (this.balance == Code.SAME) {
					this.balance = Code.RIGHT;
				} else {
					rotated.setHasBeenRotated(true);
					return rotate(this, addedLeft, numRot);
				}
			}
	//	}
		return this;
	}

	/**
	 * Performs a single left rotation.
	 * 
	 * @param parent
	 * @param child
	 * @return
	 */
	private Node singleRotateLeft(Node parent, Node child) {
		parent.right = child.left;
		child.left = parent;
		parent.balance = Code.SAME;
		child.balance = Code.SAME;
		child.rank = child.rank + parent.rank + 1;
		return child;
	}

	/**
	 * Performs a single right rotation.
	 * 
	 * @param parent
	 * @param child
	 * @return
	 */
	private Node singleRotateRight(Node parent, Node child) {
		parent.left = child.right;
		child.right = parent;
		child.balance = Code.SAME;
		parent.balance = Code.SAME;

		parent.rank = parent.rank - (child.rank + 1);
		return child;
	}

	/**
	 * Performs a double left rotation.
	 * 
	 * @param parent
	 * @param child
	 * @return
	 */
	private Node doubleRotateLeft(Node parent, Node child) {
		Node grandChild = child.left;
		parent.right = grandChild.left;
		child.left = grandChild.right;
		grandChild.left = parent;
		grandChild.right = child;
		if (grandChild.balance == Code.LEFT) {
			parent.balance = Code.SAME;
			child.balance = Code.RIGHT;
			grandChild.balance = Code.SAME;
		} else if (grandChild.balance == Code.RIGHT) {
			parent.balance = Code.LEFT;
			child.balance = Code.SAME;
			grandChild.balance = Code.SAME;
		} else {
			parent.balance = Code.SAME;
			child.balance = Code.SAME;
			grandChild.balance = Code.SAME;
		}
		child.rank = child.rank - (grandChild.rank + 1);
		grandChild.rank += parent.rank + 1;

		return grandChild;
	}

	/**
	 * Performs a double right rotation.
	 * 
	 * @param parent
	 * @param child
	 * @return
	 */
	private Node doubleRotateRight(Node parent, Node child) {

		Node grandChild = child.right;
		parent.left = grandChild.right;
		child.right = grandChild.left;
		grandChild.left = child;
		grandChild.right = parent;
		if (grandChild.balance == Code.LEFT) {
			child.balance = Code.SAME;
			parent.balance = Code.RIGHT;
			grandChild.balance = Code.SAME;
		} else if (grandChild.balance == Code.RIGHT) {
			parent.balance = Code.SAME;
			child.balance = Code.LEFT;
			grandChild.balance = Code.SAME;
		} else {
			parent.balance = Code.SAME;
			child.balance = Code.SAME;
			grandChild.balance = Code.SAME;
		}
		parent.rank = parent.rank - (child.rank + grandChild.rank + 2);
		grandChild.rank += child.rank + 1;
		return grandChild;
	}

	/**
	 * Returns the character at the given position.
	 * 
	 * @param pos
	 * @return
	 */
	public char get(int pos) {
		if (this.rank == pos) {
			return this.element;
		} else if (this.rank < pos) {
			if (this.right != null) {
				return this.right.get(pos - this.rank - 1);
			} else {
				throw new IndexOutOfBoundsException();
			}
		} else {
			if (this.left != null) {
				return this.left.get(pos);
			} else {
				throw new IndexOutOfBoundsException();
			}
		}
	}

	/**
	 * Deletes a node
	 * 
	 * @param pos
	 * @param left2
	 */
	public Node delete(int pos, CharacterContainer charCon, IntegerContainer addInt , BooleanContainer rotated) {
		if (pos == this.rank) {
			//No children
			if (this.left == null && this.right == null) {
				charCon.setCharacter(this.element);
				rotated.setHasBeenRotated(true);
				return null;
			}
			//Two children
			if (this.left != null && this.right != null) {
				charCon.setCharacter(this.element);
				Node current = this.right;
				if(this.balance!=Code.SAME){
					rotated.setHasBeenRotated(true);
				} else {
					rotated.setHasBeenRotated(true);
				}
				while(current.left != null) {
					current = current.left;
				}
				CharacterContainer dummyChar=new CharacterContainer('\0');
				Code tempCode=this.balance;
				this.right=this.right.delete(0,dummyChar,addInt, rotated);
				current.right = this.right;
				current.left = this.left;
				current.rank=this.rank;		
				current.balance=this.balance;
				if(this.balance!=Code.SAME){
					return current.tilt(true, addInt, rotated);
				}else{
					return current;
				}
			//Right child only
			} else if (this.right != null) {
				charCon.setCharacter(this.element);
				rotated.setHasBeenRotated(true);
				return this.right;
			} else {// this.left!=null
				charCon.setCharacter(this.element);
				rotated.setHasBeenRotated(true);
				return this.left;
			}
		//Go left
		} else if (this.rank > pos) {
			//Invalid position
			if(this.left==null){
				throw new IndexOutOfBoundsException();
			}
			this.rank-=1;
			Node temp = this;
			this.left = this.left.delete(pos, charCon, addInt, rotated);
			if(rotated.getHasBeenRotated()){ 
				if(!(temp.balance==Code.SAME)){
					temp= this.tilt(false, addInt, rotated);
				}else{
					if(temp.left==null){
						temp.balance=Code.RIGHT;
					}else if(temp.left.balance==Code.SAME){
						temp.balance = Code.RIGHT;  
					} else {
						temp.balance = Code.SAME;
					}
					rotated.setHasBeenRotated(false);
				}		
				
			}
			if(temp.balance!=Code.SAME){
				rotated.setHasBeenRotated(false);
			} else {
				rotated.setHasBeenRotated(true);
			}
			return temp;	
		//Go right
		} else {
			if(this.right==null){
				throw new IndexOutOfBoundsException();
			}
			Node temp=this;
			this.right= this.right.delete(pos - rank - 1, charCon,addInt, rotated);
			if(rotated.getHasBeenRotated()){
				if(!(temp.balance==Code.SAME)){
					temp= this.tilt(true, addInt, rotated);
				}else{
					if(temp.right==null){
						temp.balance=Code.LEFT;
					}else if(temp.right.balance==Code.SAME){
						temp.balance=Code.LEFT;
					} else {
						temp.balance = Code.SAME;
					}
					rotated.setHasBeenRotated(false);
				}
			}
			
			return temp;
		}
	}

}