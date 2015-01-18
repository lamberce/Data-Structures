import java.util.ArrayList;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;


/**
 * TODO Put here a description of what this class does.
 *
 * @author lamberce.
 *         Created Dec 17, 2013.
 */
public class TreeIterator implements Iterator {
	private ArrayList list;
	private int index;
	private boolean hasNextBeenCalled = false;

	/**
	 * TODO Put here a description of what this constructor does.
	 *
	 * @param arrayList
	 */
	public TreeIterator(ArrayList arrayList) {
		this.list = arrayList;
	}

	@Override
	public boolean hasNext() {
		return this.index < this.list.size();
	}

	@Override
	public Object next() {
		if(!hasNext()){
			throw new NoSuchElementException();
		}
		this.hasNextBeenCalled = true;
		return this.list.get(this.index++);
	}

	@Override
	public void remove() {
		if(!this.hasNextBeenCalled){
			throw new IllegalStateException();
		} else {
			this.hasNextBeenCalled = false;
			this.list.remove(this.index);
		}
	}
}
