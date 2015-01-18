import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.JFrame;

/**
 * A wrapper class for binary trees that can display the wrapped tree in a
 * window.
 * 
 * @author Curt Clifton. Created Jan 24, 2008.
 */
public class DisplayableBinaryTree {

	private int width;

	private int height;
	
	private int x;
	
	private int y;
	
	private int radius;

	private BinaryTree tree;

	/**
	 * Constructs a new displayable binary tree, set to default to the given
	 * window size for display.
	 * 
	 * @param tree
	 * @param windowWidth
	 *            in pixels
	 * @param windowHeight
	 *            in pixels
	 */
	public DisplayableBinaryTree(BinaryTree tree, int windowWidth,
			int windowHeight) {
		this.width = windowWidth;
		this.height = windowHeight;
		this.tree = tree;
	}

	/**
	 * Creates a new window, using the current size information stored in this,
	 * and renders the current state of the tree wrapped by this.
	 */
	public void display() {
		this.radius = this.width/(2*(this.tree.size()+1));
		
		JFrame mainFrame = new JFrame();
		mainFrame.setSize(this.width,this.height);
		mainFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		mainFrame.add(new DisplayNode(this.tree,this.radius, this.width, this.height));
		mainFrame.setVisible(true);
	}

	/**
	 * Sets the default size for the next window displayed.
	 * 
	 * @param windowWidth
	 *            in pixels
	 * @param windowHeight
	 *            in pixels
	 */
	public void setSize(int windowWidth, int windowHeight) {
		this.width = windowWidth;
		this.height = windowHeight;
	}
}
