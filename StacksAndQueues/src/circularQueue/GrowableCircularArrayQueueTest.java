package circularQueue;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.NoSuchElementException;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;

/**
 * Tests the growable array queue.
 * 
 * @author Matt Boutell. Created Nov 30, 2013.
 */
public class GrowableCircularArrayQueueTest {

	// I gave you a couple to start with.
	private static int points = 0;
	private GrowableCircularArrayQueue<Integer> intQueue;
	private GrowableCircularArrayQueue<String> stringQueue;

	/**
	 * Creates some test cases.
	 * 
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		this.intQueue = new GrowableCircularArrayQueue<Integer>(17);
		this.stringQueue = new GrowableCircularArrayQueue<String>("hello");
	}

	/**
	 * Test method 
	 */
	@Test
	public void testCaseFromSpecification() {
		assertEquals(0, this.stringQueue.size());
		this.stringQueue.enqueue("a");
		assertEquals(1, this.stringQueue.size());
		this.stringQueue.enqueue("b");
		this.stringQueue.enqueue("c");
		this.stringQueue.enqueue("d");
		this.stringQueue.enqueue("e");
		assertEquals("[a, b, c, d, e]", this.stringQueue.toString());
		// forces capacity to double
		this.stringQueue.enqueue("f");
		this.stringQueue.enqueue("g");
		this.stringQueue.enqueue("h");
		assertEquals(8, this.stringQueue.size());
		assertEquals("[a, b, c, d, e, f, g, h]", this.stringQueue.toString());
		
		assertEquals("a", this.stringQueue.dequeue());
		assertEquals("b", this.stringQueue.dequeue());
		assertEquals("c", this.stringQueue.dequeue());
		assertEquals("d", this.stringQueue.dequeue());
		assertEquals("e", this.stringQueue.dequeue());
		assertEquals("[f, g, h]", this.stringQueue.toString());
		
		this.stringQueue.enqueue("i");
		this.stringQueue.enqueue("j");
		// Now it is full. Next one will wrap around
		this.stringQueue.enqueue("k"); 
		assertEquals("[f, g, h, i, j, k]", this.stringQueue.toString());
		points += 10;
	}

	/**
	 * Test method for {@link GrowableCircularArrayQueue#peek()}.
	 */
	@Test
	public void testPeekEmptyQueue() {
		// This is how you test to see if an expected exception was thrown.
		try {
			this.intQueue.peek();
			fail("Should have thrown a NoSuchElement exception");

		} catch (NoSuchElementException e) {
			this.stringQueue.clear();
			assertEquals("[]",this.stringQueue.toString());
			points++;
		}
	}


	/**
	 * Test method for {@link java.lang.Object#toString()}.
	 */
	@Test
	public void testToStringEmpty() {
		assertEquals("[]", this.intQueue.toString());
		assertEquals("[]", this.stringQueue.toString());
		points += 1;
	}
	
	/**
	 * Test method to see if clear works
	 *
	 */
	@Test
	public void testClear() {
		this.stringQueue.enqueue("1");
		this.stringQueue.enqueue("2");
		this.stringQueue.enqueue("3");
		this.stringQueue.dequeue();
		this.stringQueue.clear();
		assertEquals("[]",this.stringQueue.toString());
		this.stringQueue.enqueue("4");
		this.stringQueue.enqueue("4");
		assertEquals("[4, 4]",this.stringQueue.toString());
	}
	
	/**
	 * test Dequeuing Until Empty Then Enqueing
	 *
	 */
	@Test
	public void testDequeuingUntilEmptyThenEnqueing(){
		this.stringQueue.enqueue("1");
		this.stringQueue.enqueue("2");
		this.stringQueue.enqueue("3");
		this.stringQueue.enqueue("4");
		this.stringQueue.enqueue("5");
		this.stringQueue.dequeue();
		this.stringQueue.dequeue();
		this.stringQueue.dequeue();
		this.stringQueue.dequeue();
		this.stringQueue.dequeue();
		this.stringQueue.enqueue("7");
		assertEquals("[7]", this.stringQueue.toString());
	}
	
	/**
	 * Test whether contains finds and returns the value being searched for.
	 *
	 */
	@Test
	public void testContains(){
		this.stringQueue.enqueue("4");
		this.stringQueue.enqueue("5");
		this.stringQueue.dequeue();
		assertEquals(true, this.stringQueue.contains("5"));
		assertEquals(false, this.stringQueue.contains("4"));
		assertEquals(false, this.stringQueue.contains("7"));
		this.stringQueue.dequeue();
		assertEquals(false, this.stringQueue.contains("5"));
	}
	
	/**
	 * Checks the size method and checks whether enqueue resizes the array.
	 *
	 */
	@Test
	public void testSizeandResize(){
		this.stringQueue.enqueue("1");
		this.stringQueue.enqueue("2");
		this.stringQueue.enqueue("3");
		this.stringQueue.enqueue("4");
		this.stringQueue.enqueue("5");
		assertEquals(5,this.stringQueue.size());
		this.stringQueue.enqueue("5");
		assertEquals(6,this.stringQueue.size());
		assertEquals(10,this.stringQueue.sizeGetter());
	}

	/**
	 * Displays how many points were earned.
	 */
	@AfterClass
	public static void showPoints() {
		System.out.printf("This implementation earned %d/12 points. Write more tests!", points);
	}
}
