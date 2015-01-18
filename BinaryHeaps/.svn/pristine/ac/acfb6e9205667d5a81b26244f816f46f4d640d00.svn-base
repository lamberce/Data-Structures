import java.util.ArrayList;


/**
 * TODO Put here a description of what this class does.
 *
 * @author lamberce.
 *         Created Feb 20, 2014.
 */
public class BinaryHeap<T extends Comparable<? super T>> {
	ArrayList<T> heap;
	
	/**
	 * TODO Put here a description of what this constructor does.
	 *
	 */
	public BinaryHeap(){
		this.heap = new ArrayList<T>();
		this.heap.add(null);
	}

	/**
	 * TODO Put here a description of what this method does.
	 *
	 * @return
	 */
	public T deleteMin() {
		if(this.heap.size()==1){
			return null;
		} else if(this.heap.size()==2){
			return this.heap.remove(1);
		} else {
			T returning = this.heap.get(1);
			this.heap.set(1, this.heap.get(this.heap.size()-1));
			this.percolateDown(1);
			return returning;
		}
	}

	/**
	 * TODO Put here a description of what this method does.
	 *
	 * @param i
	 */
	public boolean insert(T beingAdded) {
		if(beingAdded==null){
			return false;
		} else {
			this.heap.add(beingAdded);
			this.percolateUp(this.heap.size()-1);
			return true;
		}
	}
	
	private void percolateUp(int hole){
		if(hole==1){
			return;
		} else if(hole%2==0) {
			if(this.heap.get(hole).compareTo(this.heap.get(hole/2))<0){
				T temp = this.heap.get(hole);
				this.heap.set(hole,this.heap.get(hole/2));
				this.heap.set(hole/2, temp);
				this.percolateUp(hole/2);
			} else {
				return;
			}
		} else {
			if(this.heap.get(hole).compareTo(this.heap.get((hole-1)/2))<0){
				T temp = this.heap.get(hole);
				this.heap.set(hole, this.heap.get((hole-1)/2));
				this.heap.set((hole-1)/2, temp);
				this.percolateUp((hole-1)/2);
			} else {
				return;
			}
		}
	}

	private void percolateDown(int hole){
		if(hole*2>=this.heap.size()){
			this.heap.remove(this.heap.size()-1);
			return;
		} else {
			if(hole*2<this.heap.size()&&(hole*2+1)<this.heap.size()){
				if(this.heap.get(hole*2).compareTo(this.heap.get(hole*2+1))<0){
					T temp = this.heap.get(hole);
					if(temp.compareTo(this.heap.get(hole*2))<=0){
						this.heap.remove(this.heap.size()-1);
						return;
					}
					this.heap.set(hole,this.heap.get(hole*2));
					this.heap.set(hole*2, temp);
					this.percolateDown(hole*2);
				} else if(this.heap.get(hole*2).compareTo(this.heap.get(hole*2+1))>=0){
					T temp = this.heap.get(hole);
					if(temp.compareTo(this.heap.get(hole*2+1))<=0){
						this.heap.remove(this.heap.size()-1);
						return;
					}
					this.heap.set(hole,this.heap.get(hole*2+1));
					this.heap.set(hole*2+1, temp);
					this.percolateDown(hole*2+1);
				} 
			} else {
				T temp = this.heap.get(hole);
				if(temp.compareTo(this.heap.get(hole*2))<=0){
					this.heap.remove(this.heap.size()-1);
					return;
				}
				this.heap.set(hole, this.heap.get(hole*2));
				this.heap.set(hole*2, temp);
				this.percolateDown(hole*2);
			}
		}
	}
	
	/**
	 * TODO Put here a description of what this method does.
	 * 
	 * @param toBeSorted 
	 */
	public void sort(T[] toBeSorted) {
		BinaryHeap<T> tempHeap = new BinaryHeap<T>();
		for(int i = 0; i<toBeSorted.length; i++){
			tempHeap.insert(toBeSorted[i]);
		}
		for(int i = 0; i<toBeSorted.length; i++){
			toBeSorted[i] = tempHeap.deleteMin();
		}
	}
	
	@Override
	public String toString(){
		return this.heap.toString();
	}
}
