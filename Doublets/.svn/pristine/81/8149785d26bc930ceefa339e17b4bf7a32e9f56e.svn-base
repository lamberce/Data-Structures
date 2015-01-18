import java.util.Iterator;
import java.util.LinkedList;


/**
 * Chain class
 *
 * @author lamberce.
 *         Created Feb 5, 2014.
 */
public class Chain {
	LinkedList<String> s = new LinkedList<String>();
	/**
	 * Adds a word to the end of the Chain
	 *
	 * @param string
	 * @return
	 */
	public Chain addLast(String string) {
		Chain toReturn = new Chain();
		toReturn.s = (LinkedList<String>) this.s.clone();
		toReturn.s.addLast(string);
		return toReturn;
	}

	/**
	 * Returns the last string in the chain
	 *
	 * @return
	 */
	public String getLast() {
		return this.s.getLast();
	}

	/**
	 * Returns length of the string
	 *
	 * @return
	 */
	public int length() {
		return this.s.size();
	}

	/**
	 * Returns true if the string 
	 *
	 * @param string
	 * @return
	 */
	public boolean contains(String string) {
		return this.s.contains(string);
	}

	/**
	 * Returns an iterator over the chain 
	 *
	 * @return
	 */
	public Iterator<String> iterator() {
		return this.s.iterator();
	}

}
