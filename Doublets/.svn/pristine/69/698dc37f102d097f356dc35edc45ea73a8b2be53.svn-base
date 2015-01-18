import java.util.PriorityQueue;


/**
 * PriorityQueueChainManager Class
 *
 * @author lamberce.
 *         Created Feb 9, 2014.
 */
public class PriorityQueueChainManager extends ChainManager {
	PriorityQueue<Entry> pQueue = new PriorityQueue<Entry>();
	private String endWord;

	/**
	 * Constructs a PriorityQueueChainManager
	 *
	 * @param end
	 */
	public PriorityQueueChainManager(String end){
		this.endWord = end;
	}
	
	@Override
	public void add(Chain chain) {
		Entry c = new Entry(chain, this.numCharDifference(chain.getLast()));
		this.pQueue.add(c);
		if(this.pQueue.size()>this.maxSize()){
			this.setMaxSize(this.pQueue.size());
		}
	}

	@Override
	public Chain next() {
		if(pQueue.isEmpty()){
			return null;
		} else {
			this.incrementNumNexts();
			return pQueue.remove().currentChain;
		}
	}
	
	private int numCharDifference(String current){
		int numDiff = 0;
		for(int i = 0; i < current.length(); i++){
			if(current.charAt(i)!=this.endWord.charAt(i)){
				numDiff++;
			}
		}
		return numDiff;
	}
	
	/**
	 * Holder for chain and chain value
	 *
	 * @author lamberce.
	 *         Created Feb 9, 2014.
	 */
	class Entry implements Comparable<Entry>{
		Chain currentChain = new Chain();
		int chainValue;
		Entry(Chain chain, int diff){
			currentChain = chain;
			chainValue = chain.length() + diff;
		}
		
		@Override
		public int compareTo(Entry comp) {
			return this.chainValue-comp.chainValue;
		}	
	}
}
