package circularQueue;

import java.lang.reflect.Array;
import java.util.NoSuchElementException;

/**
 * A circular queue that grows as needed.
 * 
 * @author Matt Boutell and <<<your name here>>>
 * @param <T>
 */
public class GrowableCircularArrayQueue<T> {
	private static final int INITIAL_CAPACITY = 5;

	private T[] array;
	private T typedArgument;
	private int start = 0;
	private int end = 0;
	private int sizeOf = INITIAL_CAPACITY;

	/**
	 * Creates an empty queue with an initial capacity of 5
	 * 
	 * @param typedArgument
	 *            Given so that the array of the given type can be constructed.
	 *            This argument is NOT an item of the queue - it is only there
	 *            so its type can be used to make an array of the right type.
	 */
	public GrowableCircularArrayQueue(T typedArgument) {
		this.typedArgument = typedArgument;
		// This is a workaround due to a limitation Java has with
		// constructing generic arrays.
		this.array = (T[]) Array.newInstance(typedArgument.getClass(),
				INITIAL_CAPACITY);
	}

	/**
	 * Removes all the items from this queue and everything back to the initial
	 * state, including setting its capacity back to the initial capacity.
	 */
	public void clear() {
		this.array = (T[]) Array.newInstance(typedArgument.getClass(),
				INITIAL_CAPACITY);
		this.start = 0;
		this.end = 0;
	}

	private void resize() {
		T[] temp = (T[]) Array.newInstance(this.typedArgument.getClass(),
				this.sizeOf*2);
		int count = 0;
		if(this.start>this.end){
			for(int i = this.start; i<this.sizeOf; i++){
				temp[count]=this.array[i];
				count++;
			}
			for(int i=0; i<this.end;i++){
				temp[count]=this.array[i];
				count++;
			}
		} else {
			for(int i = this.start; i<this.end;i++){
				temp[count]=this.array[i];
				count++;
			}
		}
		this.start = 0;
		this.end = this.sizeOf+1;
		this.sizeOf = temp.length;
		this.array = temp;
	}

	/**
	 * Adds the given item to the tail of the queue, resizing the internal array
	 * if needed.
	 * 
	 * @param item
	 */
	public void enqueue(T item) {
		if((((this.start!=0)&&(this.start!=this.sizeOf))&&(this.start==this.end))||(this.end-this.start==this.sizeOf)){
			this.resize();
			this.array[this.end-1] = item;
		} else {
			if(this.end<this.sizeOf){
				this.array[this.end]=item;
				this.end = this.end+1;
			} else{
				if(this.end==this.sizeOf){
					this.end = 1;
					this.array[this.end-1] = item;
				}
			}
		}	
	}

	/**
	 * Removes and returns the item at the head of the queue.
	 * 
	 * @return The item at the head of the queue.
	 * @throws NoSuchElementException
	 *             If the queue is empty.
	 */
	public T dequeue() {
		if(this.isEmpty()){
			throw new NoSuchElementException();
		} else {
			int temp = this.start;
			this.start = (this.start)%this.sizeOf+1;
			return this.array[temp];
		}
	}

	/**
	 * Returns the item at the head of the queue without mutating the queue.
	 * 
	 * @return The item at the head of the queue.
	 * @throws NoSuchElementException
	 *             If the queue is empty.
	 */
	public T peek() throws NoSuchElementException {
		if(this.isEmpty()){
			throw new NoSuchElementException();
		} else{
			return this.array[this.start];
		}
	}

	/**
	 * @return True if and only if the queue contains no items.
	 */
	public boolean isEmpty() {
		if(this.array[this.start]==null){
			return true;
		} else {
			return false;
		}
	}

	/**
	 * @return The number of items in this queue.
	 */
	public int size() {
		if(this.sizeOf==this.end&&this.start==0){
			return this.sizeOf;
		} else {
			return (this.sizeOf-this.start+this.end)%this.sizeOf;
		}
	}

	/**
	 * Searches for the given item in this queue.
	 * 
	 * @param item
	 * @return True if the item is contained within the queue.
	 */
	public boolean contains(T item) {
		if(this.start>this.end){
			for(int i = this.start; i<this.sizeOf; i++){
				if(this.array[i]==item){
					return true;
				}
			}
			for(int i=0; i<this.end;i++){
				if(this.array[i]==item){
					return true;
				}
			}
		} else if(this.start!=this.end) {
			for(int i = this.start; i<this.end;i++){
				if(this.array[i]==item){
					return true;
				}
			}
		}
		return false;
	}

	/**
	 * Displays the contents of the queue in insertion order, with the
	 * most-recently inserted one last, in other words, not wrapped around. Each
	 * adjacent pair will be separated by a comma and a space, and the whole
	 * contents will be bounded by square brackets. See the unit tests for
	 * examples.
	 */
	@Override
	public String toString() {
		String s ="[";
		if(this.start>this.end){
			for(int i = this.start; i<this.sizeOf; i++){
				s+=this.array[i] + ", ";
			}
			for(int i=0; i<this.end;i++){
				if(i!=this.end-1){
					s+=this.array[i]+ ", ";
				} else {
					s+=this.array[i];
				}
			}
		} else if(this.start!=this.end) {
			for(int i = this.start; i<this.end;i++){
				if(i!=this.end-1){
					s+=this.array[i]+ ", ";
				} else {
					s+=this.array[i];
				}
			}
		}
		s+="]";
		return s;
	}
	
	/**
	 * @return length of array.
	 */
	public int sizeGetter(){
		return this.array.length;
	}

}
