package editortrees;

/**
 * Allows for a "mutable" passable integer.
 * @author jarvisoa
 *
 */
public class IntegerContainer { 
	private int integer;
	
	/**
	 * Constructs an INtegerContainer with default value zero.
	 */
	public IntegerContainer(){
		integer=0;
	}
	public IntegerContainer(int value){
		integer=value;
	}
	public void increment(int value){
		integer+=value;
	}
	/**
	 * Returns the integers value.
	 * @return
	 */
	public int getInteger() {
		return integer;
	}
	/**
	 * Sets the value of the integer.
	 * @param integer
	 */
	public void setInteger(int integer) {
		this.integer = integer;
	}
}
