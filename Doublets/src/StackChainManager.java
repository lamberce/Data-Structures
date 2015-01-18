import java.util.Stack;

/**
 * StackChainManager class.
 *
 * @author lamberce.
 *         Created Feb 5, 2014.
 */
public class StackChainManager extends ChainManager {
	/**
	 * Creates a stack of chains
	 */
	Stack<Chain> chainStack = new Stack<Chain>();
	
	@Override
	public void add(Chain chain) {
		this.chainStack.push(chain);
		if(this.chainStack.size()>this.maxSize()){
			this.setMaxSize(this.chainStack.size());
		}
	}

	@Override
	public Chain next() {
		if(!this.chainStack.isEmpty()){
			this.incrementNumNexts();
			return this.chainStack.pop();
		} else {
			return null;
		}
	}

}
