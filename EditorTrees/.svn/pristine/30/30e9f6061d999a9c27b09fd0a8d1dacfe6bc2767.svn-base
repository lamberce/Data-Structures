package editortrees;

import java.util.LinkedList;
import java.util.Stack;

import editortrees.Node.Code;

// A height-balanced binary tree with rank that could be the basis for a text editor.

/**
* EditTree Class
* 
 * @author lamberce. Created Feb 4, 2014.
*/
public class EditTree {

     private Node root;
     private int rotationCount;

     /**
     * Construct an empty tree
     */
     public EditTree() {
           this.root = null;
           this.rotationCount = 0;
     }

     /**
     * Construct a single-node tree whose element is c
     * 
      * @param c
     */
     public EditTree(char c) {
           this.root = new Node();
           this.root.element = c;
           this.root.balance = Node.Code.SAME;
           this.rotationCount = 0;
     }

     /**
     * Create an EditTree whose toString is s. This can be done in O(N) time,
     * where N is the length of the tree (repeatedly calling insert() would be
     * O(N log N), so you need to find a more efficient way to do this.
     * 
      * @param s
     */
     public EditTree(String s) {
           this.root = new Node();
           this.rotationCount = 0;
           if (s.equals("")) {
                this.root = null;
           } else {
                int mid = s.length() / 2;
                if (mid > s.length() - mid - 1) {
                     this.root.balance = Code.LEFT;
                     this.root.rank = mid;
                } else if (mid < s.length() - mid - 1) {
                     this.root.balance = Code.RIGHT;
                     this.root.rank = mid;
                } else {
                     this.root.balance = Code.SAME;
                     this.root.rank = mid;
                }
                this.root.element = s.charAt(mid);
                this.root.left = new EditTree(s.substring(0, mid)).root;
                this.root.right = new EditTree(s.substring(mid + 1, s.length())).root;
           }
     }

     /**
     * Make this tree be a copy of e, with all new nodes, but the same shape and
     * contents.
     * 
      * @param e
     */
     public EditTree(EditTree e) {
           if (e.root == null) {
                this.root = null;
                this.rotationCount = 0;
           } else {
                this.root = new Node();
                this.root.element = e.root.element;
                this.rotationCount = e.rotationCount;
                this.root.rank = e.root.rank;
                this.root.balance = e.root.balance;
                this.root.left = buildTree(e.root.left);
                this.root.right = buildTree(e.root.right);
           }
     }

     /**
     * Builds Tree given node.
     * 
      * @param left
     * @return
     */
     private Node buildTree(Node current) {
           if (current == null) {
                return null;
                // No children
           } else if (current.left == null && current.right == null) {
                Node base = new Node();
                base.balance = Code.SAME;
                base.element = current.element;
                base.rank = 0;
                return base;
                // Two children
           } else if (current.left != null && current.right != null) {
                Node interior = new Node();
                interior.element = current.element;
                interior.rank = current.rank;
                interior.balance = current.balance;
                interior.left = buildTree(current.left);
                interior.right = buildTree(current.right);
                return interior;
                // Left child only
           } else if (current.left != null) {
                Node interior = new Node();
                interior.element = current.element;
                interior.rank = current.rank;
                interior.balance = current.balance;
                interior.left = buildTree(current.left);
                return interior;
                // Right child only
           } else {
                Node interior = new Node();
                interior.element = current.element;
                interior.rank = current.rank;
                interior.balance = current.balance;
                interior.right = buildTree(current.right);
                return interior;
           }
     }

     /**
     * 
      * @return the height of this tree
     */
     public int height() {
           if (this.root == null) {
                return -1;
           } else {
                return this.root.height();
           }
     }

     /**
     * 
      * returns the total number of rotations done in this tree since it was
     * created. A double rotation counts as two.
     * 
      * @return number of rotations since tree was created.
     */
     public int totalRotationCount() {
           return this.rotationCount; // replace by a real calculation.
     }

     /**
     * return the string produced by an inorder traversal of this tree
     */
     @Override
     public String toString() {
           String s = "";
           if (this.root == null) {
                return "";
           } else if (this.root.right == null && this.root.left == null) {
                return "" + this.root.element;
           } else if (this.root.right == null) {
                return "" + this.root.left.toString() + this.root.element;
           } else if (this.root.left == null) {
                return "" + this.root.element + this.root.right.toString();
           } else {
                s += "" + this.root.left.toString() + this.root.element
                           + this.root.right.toString();
                return s;
           }
     }

     /**
     * 
      * @param pos
     *            position in the tree
     * @return the character at that position
     * @throws IndexOutOfBoundsException
     */
     public char get(int pos) throws IndexOutOfBoundsException {
           if (pos < 0) {
                throw new IndexOutOfBoundsException();
           } else if (this.root == null) {
                throw new IndexOutOfBoundsException();
           } else if (this.root.rank == pos) {
                return this.root.element;
           } else if (this.root.rank < pos) {
                if (this.root.right != null) {
                     return this.root.right.get(pos - this.root.rank - 1);
                } else {
                     throw new IndexOutOfBoundsException();
                }
           } else {
                if (this.root.left != null) {
                     return this.root.left.get(pos);
                } else {
                     throw new IndexOutOfBoundsException();
                }
           }
     }

     /**
     * 
      * @param c
     *            character to add to the end of this tree.
     */
     public void add(char c) {
           if (this.root == null) {
                this.root = new Node();
                this.root.element = c;
                this.root.balance = Code.SAME;
                this.root.rank = 0;
           } else {
                IntegerContainer addInt = new IntegerContainer();
                BooleanContainer rotated = new BooleanContainer(false);
                this.root = this.root.add(c, addInt, rotated);
                this.rotationCount += addInt.getInteger();
           }
     }

     /**
     * 
      * @param c
     *            character to add
     * @param pos
     *            character added in this inorder position
     * @throws IndexOutOfBoundsException
     *             id pos is negative or too large for this tree
     */
     public void add(char c, int pos) throws IndexOutOfBoundsException {
           // Negative Position
           if (pos < 0) {
                throw new IndexOutOfBoundsException();
                // Empty tree
           } else if (this.root == null) {
                if (pos == 0) {
                     this.root = new Node();
                     this.root.element = c;
                     this.root.balance = Code.SAME;
                     this.root.rank = 0;
                } else {
                     throw new IndexOutOfBoundsException();
                }
                // Insert right of root
           } else if (this.root.rank < pos) {
                // Base case
                if (this.root.right == null) {
                     if (pos - this.root.rank == 1) {
                           IntegerContainer addInt = new IntegerContainer();
                           Node newNode = new Node();
                           newNode.element = c;
                           newNode.rank = 0;
                           newNode.balance = Code.SAME;
                           this.root.right = newNode;
                           BooleanContainer rotated = new BooleanContainer(false);
                           if (!rotated.getHasBeenRotated()) {
                                this.root = this.root.tilt(false, addInt, rotated);
                           }
                           this.rotationCount += addInt.getInteger();
                     } else {
                           throw new IndexOutOfBoundsException();
                     }
                     // Recursive case
                } else {
                     IntegerContainer addInt = new IntegerContainer();
                     BooleanContainer rotated = new BooleanContainer(false);
                     this.root.right = this.root.right.add(c, pos - this.root.rank
                                - 1, addInt, rotated);
                     if (!rotated.getHasBeenRotated()) {
                          this.root = this.root.tilt(false, addInt, rotated);
                     }
                     this.rotationCount += addInt.getInteger();
                }
                // Insert left of root
           } else if (this.root.rank >= pos) {
                // Invalid Position
                if (this.root.left == null && pos != 0) {
                     throw new IndexOutOfBoundsException();
                     // Base case
                } else if (this.root.left == null && pos == 0) {
                     IntegerContainer addInt = new IntegerContainer();
                     Node newNode = new Node();
                     newNode.element = c;
                     newNode.rank = 0;
                     newNode.balance = Code.SAME;
                     this.root.rank += 1;
                     this.root.left = newNode;
                     BooleanContainer rotated = new BooleanContainer(false);
                     if (!rotated.getHasBeenRotated()) {
                           this.root = this.root.tilt(true, addInt, rotated);
                     }
                     this.rotationCount += addInt.getInteger();
                     // Recursive case
                } else {
                     IntegerContainer addInt = new IntegerContainer();
                     BooleanContainer rotated = new BooleanContainer(false);
                     this.root.rank += 1;
                     this.root.left = this.root.left.add(c, pos, addInt, rotated);

                     if (!rotated.getHasBeenRotated()) {
                           this.root = this.root.tilt(true, addInt, rotated);
                     }
                     this.rotationCount += addInt.getInteger();
                }
           }
     }

     /**
     * 
      * @return the number of nodes in this tree
     */
     public int size() {
           if (this.root == null) {
                return 0;
           } else {
                return this.root.size();
           }
     }

     /**
     * 
      * @param pos
     *            position of character to delete from this tree
     * @return the character that is deleted
     * @throws IndexOutOfBoundsException
     */
     public char delete(int pos) throws IndexOutOfBoundsException {
           // Implementation requirement:
           // When deleting a node with two children, you normally replace the
           // node to be deleted with either its in-order successor or predecessor.
           // The tests assume assume that you will replace it with the
           // *successor*.
           if (this.root == null || pos < 0) {
                throw new IndexOutOfBoundsException();
           } else {
                IntegerContainer addInt = new IntegerContainer();
                CharacterContainer charCon = new CharacterContainer('\0');
                BooleanContainer rotated = new BooleanContainer(true);
                this.root = this.root.delete(pos, charCon, addInt, rotated);
                this.rotationCount += addInt.getInteger();
                return charCon.getCharacter();
           }
     }

     /**
     * This method operates in O(length*log N), where N is the size of this
     * tree.
     * 
      * @param pos
     *            location of the beginning of the string to retrieve
     * @param length
     *            length of the string to retrieve
     * @return string of length that starts in position pos
     * @throws IndexOutOfBoundsException
     *             unless both pos and pos+length-1 are legitimate indexes
     *             within this tree.
     */
     public String get(int pos, int length) throws IndexOutOfBoundsException {
           String s = "";
           if (this.root == null) {
                throw new IndexOutOfBoundsException();
           } else {
                for (int i = pos; i < length + pos; i++) {
                     s += this.root.get(i);
                }
                return s;
           }
     }

     /**
     * This method is provided for you, and should not need to be changed. If
     * split() and concatenate() are O(log N) operations as required, delete
     * should also be O(log N)
     * 
      * @param start
     *            position of beginning of string to delete
     * 
      * @param length
     *            length of string to delete
     * @return an EditTree containing the deleted string
     * @throws IndexOutOfBoundsException
     *             unless both start and start+length-1 are in range for this
     *             tree.
     */
     public EditTree delete(int start, int length)
                throws IndexOutOfBoundsException {
           if (start < 0 || start + length >= this.size())
                throw new IndexOutOfBoundsException(
                           (start < 0) ? "negative first argument to delete"
                                     : "delete range extends past end of string");
           EditTree t2 = this.split(start);
           EditTree t3 = t2.split(length);
           this.concatenate(t3);
           return t2;
     }

     /**
     * Append (in time proportional to the log of the size of the larger tree)
     * the contents of the other tree to this one. Other should be made empty
     * after this operation.
     * 
      * @param other
     * @throws IllegalArgumentException
     *             if this == other
     */
     public void concatenate(EditTree other) throws IllegalArgumentException {
           // Cannot concatenate tree to itself
           if (other == this) {
                throw new IllegalArgumentException();
           }
           Node q = null;
           // This is bigger than or equal to other, so find left most node
           if (this.height() >= other.height()) {
                try {
                     q = other.root;
                     while (q != null && q.left != null) {
                           q = q.left;
                     }
                     other.delete(0);
                } catch (IndexOutOfBoundsException e) {
                     return;
                }
                // This is smaller than other, so find right most node
           } else if (this.height() < other.height()) {
                try {
                     q = this.root;
                     while (q != null && q.right != null) {
                           q = q.right;
                     }
                     this.delete(this.size() - 1);
                } catch (IndexOutOfBoundsException e) {
                     this.root = other.root;
                     other.root = null;
                     return;
                }
           }

           IntegerContainer numRot = new IntegerContainer();
           BooleanContainer rotated = new BooleanContainer(false);
           int heightThis = this.height();
           int heightOther = other.height();
           Node p = this.root;
           // Recursively concatenate to the right
           if (heightThis > heightOther) {
                concatenateRight(p, q, other.root, heightThis, heightOther);
                this.root = this.root.tilt(false, numRot, rotated);
                if (this.root.left != null) {
                     this.root.rank = this.root.left.size();
                } else {
                     this.root.rank = 0;
                }
                // Recursively concatenate to the left
           } else if (this.height() < other.height()) {
                concatenateLeft(p, q, other.root, heightThis, heightOther);
                this.root = other.root.tilt(true, numRot, rotated);
                if (this.root.left != null) {
                     this.root.rank = this.root.left.size();
                } else {
                     this.root.rank = 0;
                }
                // Base case
           } else {
                q.left = this.root;
                if (this.root != null) {
                     q.rank = this.root.size();
                } else {
                     q.rank = 0;
                }
                q.right = other.root;
                this.root = q;
           }
           other.root = null;
           this.rotationCount += numRot.getInteger();
     }

     /**
     * Helper method for concatenate.
     * 
      * @param p
     * @param q
     * @param other
     * @param heightThis
     * @param heightOther
     * @return node
     */
     public Node concatenateRight(Node p, Node q, Node other, int heightThis,
                int heightOther) {
           IntegerContainer numRot = new IntegerContainer();
           BooleanContainer rotated = new BooleanContainer(false);
           // Recursive case
           if (heightThis - heightOther > 1) {
                if (p.balance == Code.LEFT) {
                     heightThis -= 2;
                } else {
                     heightThis -= 1;
                }
                p.right = concatenateRight(p.right, q, other, heightThis,
                           heightOther);
                p.right = p.right.tilt(false, numRot, rotated);
                // Base case
           } else {
                q.left = p.right;
                if (p.right != null) {
                     q.rank = p.right.size();
                } else {
                     q.rank = 0;
                }
                q.right = other;
                p.right = q;
                if (heightThis == heightOther) {
                     q.balance = Code.SAME;
                } else {
                     q.balance = Code.RIGHT;
                }
                if (q.left == null && q.right == null) {
                     q.balance = Code.SAME;
                }
           }
           return p;
     }

     /**
     * Helper method for concatenate.
     * 
      * @param p
     * @param q
     * @param other
     * @param heightThis
     * @param heightOther
     * @return Node
     */
     public Node concatenateLeft(Node p, Node q, Node other, int heightThis,
                int heightOther) {
           IntegerContainer numRot = new IntegerContainer();
           BooleanContainer rotated = new BooleanContainer(false);
           // Recursive case
           if (heightOther - heightThis > 1) {
                if (other.balance == Code.RIGHT) {
                     heightOther -= 2;
                } else {
                     heightOther -= 1;
                }
                other.left = concatenateLeft(p, q, other.left, heightThis,
                           heightOther);
                other.left = other.left.tilt(true, numRot, rotated);
                // Base case
           } else {
                q.left = p;
                if (p != null) {
                     q.rank = p.size();
                } else {
                     q.rank = 0;
                }
                q.right = other.left;
                other.left = q;

                if (heightThis == heightOther) {
                     q.balance = Code.SAME;
                } else {
                     q.balance = Code.LEFT;
                }
                if (q.left == null && q.right == null) {
                     q.balance = Code.SAME;
                }
                if (q != null) {
                     other.rank = q.size();
                }
           }
           return other;
     }

     /**
     * This operation must be done in time proportional to the height of this
     * tree.
     * 
      * @param pos
     *            where to split this tree
     * @return a new tree containing all of the elements of this tree whose
     *         positions are >= position. Their nodes are removed from this
     *         tree.
     * @throws IndexOutOfBoundsException
     */
     public EditTree split(int pos) throws IndexOutOfBoundsException {
           EditTree s = new EditTree();
           EditTree t = new EditTree();
           LinkedList<Node> path = new LinkedList<Node>();
           Node right;
           Node left;
           // Invalid position or empty tree
           if (this.root == null || pos < 0) {
                throw new IndexOutOfBoundsException();
           } else {
                Node current = this.root;
                // Build up stack from split point to root.
                while (pos >= 0) {
                     if (current == null) {
                           throw new IndexOutOfBoundsException();
                     }
                     left = current.left;
                     right = current.right;
                     if (pos == current.rank) {
                           path.push(current);
                           break;
                     } else if (pos > current.rank) {
                           path.push(current);
                           pos = pos - current.rank - 1;
                           current = right;
                     } else {
                          path.push(current);
                           current = left;
                     }
                }
                current = path.pop();
                // Take what is less than current and put it in s
                if (current.left != null) {
                     s.root = current.left;
                }
                // Take what is greator than or equal to current and put it in t
                t.add(current.element);
                if (current.right != null) {
                     EditTree temp = new EditTree();
                     temp.root = current.right;
                     t.concatenate(temp);
                }
                // Pop off the stack until back at the root
                while (!path.isEmpty()) {
                     Node child = current;
                     current = path.pop();
                     // Moving up the tree to the left
                     if (child == current.right) {
                           s.add(current.element, 0);
                           if (current.left != null) {
                                EditTree temp = new EditTree();
                                temp.root = current.left;
                                temp.concatenate(s);
                                s = temp;
                           }
                           // Moving up the tree to the right
                     } else {
                           EditTree temp2 = new EditTree(current.element);
                           t.concatenate(temp2);
                           if (current.right != null) {
                                EditTree temp = new EditTree();
                                temp.root = current.right;
                                t.concatenate(temp);
                           }
                     }
                }
           }

           this.root = s.root;
           return t;
     }

     /**
     * Don't worry if you can't do this one efficiently.
     * 
      * @param s
     *            the string to look for
     * @return the position in this tree of the first occurrence of s; -1 if s
     *         does not occur
     */
     public int find(String s) {
           if (s.equals("")) {
                return 0;
           }
           Stack<Node> nodes = new Stack<Node>();
           if (this.root != null) {
                nodes.push(this.root);
                while (nodes.peek().left != null) {
                     nodes.push(nodes.peek().left);
                }
                Node lastVisited = nodes.peek();
                int index = 0;
                int position = 0;

                while (!nodes.isEmpty() && index < s.length()) {
                     while (nodes.peek().left != null
                                && (lastVisited.rank > nodes.peek().left.rank)) {
                           nodes.push(nodes.peek().left);
                     }
                     Node leftMost = nodes.pop();

                     if (leftMost.right != null) {
                           nodes.push(leftMost.right);
                     }
                     lastVisited = leftMost;
                     if (lastVisited.element != s.charAt(index)) {
                           position++;
                           index = 0;
                     } else {
                           index++;
                     }
                }
                if (index == s.length()) {
                     return position;
                }
           }
           return -1;
     }

     /**
     * 
      * @param s
     *            the string to search for
     * @param pos
     *            the position in the tree to begin the search
     * @return the position in this tree of the first occurrence of s that does
     *         not occur before position pos; -1 if s does not occur
     */
     public int find(String s, int pos) {
           if (s.equals("")) {
                return 0;
           }
           Stack<Node> nodes = new Stack<Node>();
           if (this.root != null) {
                nodes.push(this.root);
                while (nodes.peek().left != null) {
                     nodes.push(nodes.peek().left);
                }
                Node lastVisited = nodes.peek();
                int index = 0;
                int position = 0;

                while (!nodes.isEmpty() && index < s.length()) {
                     while (nodes.peek().left != null
                                && (lastVisited.rank > nodes.peek().left.rank)) {
                           nodes.push(nodes.peek().left);
                     }
                     Node leftMost = nodes.pop();

                     if (leftMost.right != null) {
                           nodes.push(leftMost.right);
                     }
                     lastVisited = leftMost;
                     if (position >= pos) {
                           if (lastVisited.element != s.charAt(index)) {
                                position++;
                                index = 0;
                           } else {
                                index++;
                           }
                     } else {
                           position++;
                     }
                }
                if (index == s.length()) {
                     return position;
                }
           }
           return -1;
     }

     /**
     * @return The root of this tree.
     */
     public Node getRoot() {
           return this.root;
     }
}
