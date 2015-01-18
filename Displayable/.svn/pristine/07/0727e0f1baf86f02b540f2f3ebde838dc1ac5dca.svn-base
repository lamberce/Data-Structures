import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Polygon;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.util.ArrayList;

import javax.swing.JComponent;


/**
 * Helps DisplayableBinaryTree display the tree.
 *
 * @author lamberce.
 *         Created Jan 13, 2014.
 */
public class DisplayNode extends JComponent {
	private int xComp;
	private int yComp;
	private int width;
	private int height;
	private int deltaX;
	private int deltaY;
	private int rad;
	private BinaryTree tree;
	private ArrayList<Integer> heights = new ArrayList<Integer>();
	
	public DisplayNode(BinaryTree tree, int radius, int width, int height){
		this.tree = tree;
		this.rad = radius;
		this.width = width;
		this.height = height;
		this.deltaX = this.rad*2;
		this.deltaY = this.rad*2;
	}
	
	@Override
	protected void paintComponent(Graphics g){
		super.paintComponent(g);
		this.deltaX = this.rad*2;
		this.deltaY = this.getHeight()/(this.tree.getRoot().height(this.tree.getRoot())+1)-5;
		this.rad = this.getWidth()/(2*(this.tree.size()+1));
		this.width = this.getWidth();
		this.height = this.getWidth();
		Graphics2D g2 = (Graphics2D) g;
		int totalHeight = this.tree.getHeight();
		CharSequence store = this.tree.inOrder();
		InOrder in = this.tree.inOrderIterator();
	//	ArrayList<Integer> heights = new ArrayList<Integer>();
		in.first();
		while(in.isValid()){
			heights.add(tree.getRoot().findDepth(in.current,0));
	//		heights.add(totalHeight - in.current.height(in.current));
			in.advance();
		}
		for(int i = 0; i < store.length(); i++){
			Ellipse2D.Double circle = new Ellipse2D.Double(this.deltaX*i,heights.get(i)*this.deltaY,2*rad,2*rad);
			
			g2.draw(circle);
//			g2.setColor(Color.white);
//			g2.fill(circle);
			String s = "" + store.charAt(i);
//			g2.setColor(Color.black);

			this.drawArrow(this.tree.getRoot(), g2);
			g2.setFont(new Font("monospaced", Font.PLAIN, this.rad));
			g2.drawString(s, this.deltaX*i+this.rad, heights.get(i)*this.deltaY+this.rad);
		}
	}
	
	private void drawArrow(BinaryNode node, Graphics2D g){
		int y = this.deltaY*(this.tree.getRoot().findDepth(node,0))+this.rad;
		int yLeftChild = this.deltaY*(this.tree.getRoot().findDepth(node.getLeft(),0))+this.rad;
		int yRightChild = this.deltaY*(this.tree.getRoot().findDepth(node.getRight(),0))+this.rad;
		InOrder in = this.tree.inOrderIterator();
		in.first();
		int i = 0;
		while(in.isValid()&&!(in.current==node)){
			in.advance();
			i++;
		}
		in.first();
		int j = 0;
		while(in.isValid()&&!(in.current==node.getLeft())){
			in.advance();
			j++;
		}
		in.first();
		int k = 0;
		while(in.isValid()&&!(in.current==node.getRight())){
			in.advance();
			k++;
		}
		int x = i*this.deltaX+this.rad;
		int xLeftChild = j*this.deltaX+this.rad;
		int xRightChild = k*this.deltaX+this.rad;
		//go left go left
		if(node.getLeft()!=null&&node.getLeft().getLeft()==null){
			double theta = Math.atan((yLeftChild-y)/(xLeftChild-x));
			double y4= yLeftChild+this.rad*Math.sin(theta);
			double x4 = xLeftChild+this.rad*Math.cos(theta);
			double y3 = y-this.rad*Math.sin(theta);
			double x3 = x-this.rad*Math.cos(theta);
			double theta2=Math.PI-theta-(Math.PI/4);
			double x5 = x4-(this.rad/5)*Math.cos(theta2);
			double y5 = y4+(this.rad/5)*Math.sin(theta2);
			double theta3 = theta-Math.PI/4;
			double x6 = x4+(this.rad/5)*Math.cos(theta3);
			double y6 = y4+(this.rad/5)*Math.sin(theta3);
			Polygon triangle = new Polygon();
			triangle.addPoint((int)Math.round(x4), (int)Math.round(y4));
			triangle.addPoint((int)Math.round(x5), (int)Math.round(y5));
			triangle.addPoint((int)Math.round(x6), (int)Math.round(y6));
			g.drawPolygon(triangle);
			g.fillPolygon(triangle);
		//	Polygon2D.Double triangle = new Polygon2D.Double(x4, y4, x5, y5, x6, y6);
			Line2D.Double leftLine = new Line2D.Double(x3,y3,x4,y4);
			g.draw(leftLine);
		}
		//go right go right
		if(node.getRight()!=null&&node.getRight().getRight()==null){
			double theta = Math.atan((yRightChild-y)/(xRightChild-x));
			double y4= yRightChild-this.rad*Math.sin(theta);
			double x4 = xRightChild-this.rad*Math.cos(theta);
			double y3 = y+this.rad*Math.sin(theta);
			double x3 = x+this.rad*Math.cos(theta);
			double theta2=Math.PI-theta-(Math.PI/4);
			double x5 = x4+(this.rad/5)*Math.cos(theta2);
			double y5 = y4-(this.rad/5)*Math.sin(theta2);
			double theta3 = theta-Math.PI/4;
			double x6 = x4-(this.rad/5)*Math.cos(theta3);
			double y6 = y4-(this.rad/5)*Math.sin(theta3);
			Polygon triangle = new Polygon();
			triangle.addPoint((int)Math.round(x4), (int)Math.round(y4));
			triangle.addPoint((int)Math.round(x5), (int)Math.round(y5));
			triangle.addPoint((int)Math.round(x6), (int)Math.round(y6));
			g.drawPolygon(triangle);
			g.fillPolygon(triangle);
			
			Line2D.Double rightLine = new Line2D.Double(x3,y3,x4,y4);
			g.draw(rightLine);
		}
		
		//go left go left
		if(node.getLeft()!=null&&node.getLeft().getLeft()!=null){
			drawArrow(node.getLeft(), g);
			double theta = Math.atan((yLeftChild-y)/(xLeftChild-x));
			double y4= yLeftChild+this.rad*Math.sin(theta);
			double x4 = xLeftChild+this.rad*Math.cos(theta);
			double y3 = y-this.rad*Math.sin(theta);
			double x3 = x-this.rad*Math.cos(theta);
			double theta2=Math.PI-theta-(Math.PI/4);
			double x5 = x4-(this.rad/5)*Math.cos(theta2);
			double y5 = y4+(this.rad/5)*Math.sin(theta2);
			double theta3 = theta-Math.PI/4;
			double x6 = x4+(this.rad/5)*Math.cos(theta3);
			double y6 = y4+(this.rad/5)*Math.sin(theta3);
			Polygon triangle = new Polygon();
			triangle.addPoint((int)Math.round(x4), (int)Math.round(y4));
			triangle.addPoint((int)Math.round(x5), (int)Math.round(y5));
			triangle.addPoint((int)Math.round(x6), (int)Math.round(y6));
			g.drawPolygon(triangle);
			g.fillPolygon(triangle);
			Line2D.Double leftLine = new Line2D.Double(x3,y3,x4,y4);
			g.draw(leftLine);
		} 
		
		if(node.getLeft()!=null&&node.getLeft().getRight()!=null){
			drawArrow(node.getLeft(), g);
			double theta = Math.atan((yLeftChild-y)/(xLeftChild-x));
			double y4= yLeftChild+this.rad*Math.sin(theta);
			double x4 = xLeftChild+this.rad*Math.cos(theta);
			double y3 = y-this.rad*Math.sin(theta);
			double x3 = x-this.rad*Math.cos(theta);
			double theta2=Math.PI-theta-(Math.PI/4);
			double x5 = x4-(this.rad/5)*Math.cos(theta2);
			double y5 = y4+(this.rad/5)*Math.sin(theta2);
			double theta3 = theta-Math.PI/4;
			double x6 = x4+(this.rad/5)*Math.cos(theta3);
			double y6 = y4+(this.rad/5)*Math.sin(theta3);
			Polygon triangle = new Polygon();
			triangle.addPoint((int)Math.round(x4), (int)Math.round(y4));
			triangle.addPoint((int)Math.round(x5), (int)Math.round(y5));
			triangle.addPoint((int)Math.round(x6), (int)Math.round(y6));
			g.drawPolygon(triangle);
			g.fillPolygon(triangle);
			Line2D.Double leftLine = new Line2D.Double(x3,y3,x4,y4);
			g.draw(leftLine);
		} 
		
		//go right go right
		if(node.getRight()!=null&&node.getRight().getRight()!=null){
			double theta = Math.atan((yRightChild-y)/(xRightChild-x));
			double y4= yRightChild-this.rad*Math.sin(theta);
			double x4 = xRightChild-this.rad*Math.cos(theta);
			double y3 = y+this.rad*Math.sin(theta);
			double x3 = x+this.rad*Math.cos(theta);
			double theta2=Math.PI-theta-(Math.PI/4);
			double x5 = x4+(this.rad/5)*Math.cos(theta2);
			double y5 = y4-(this.rad/5)*Math.sin(theta2);
			double theta3 = theta-Math.PI/4;
			double x6 = x4-(this.rad/5)*Math.cos(theta3);
			double y6 = y4-(this.rad/5)*Math.sin(theta3);
			Polygon triangle = new Polygon();
			triangle.addPoint((int)Math.round(x4), (int)Math.round(y4));
			triangle.addPoint((int)Math.round(x5), (int)Math.round(y5));
			triangle.addPoint((int)Math.round(x6), (int)Math.round(y6));
			g.drawPolygon(triangle);
			g.fillPolygon(triangle);
			drawArrow(node.getRight(), g);
			Line2D.Double rightLine = new Line2D.Double(x3,y3,x4,y4);
			g.draw(rightLine);
		}
		
		if(node.getRight()!=null&&node.getRight().getLeft()!=null){
			double theta = Math.atan((yRightChild-y)/(xRightChild-x));
			double y4= yRightChild-this.rad*Math.sin(theta);
			double x4 = xRightChild-this.rad*Math.cos(theta);
			double y3 = y+this.rad*Math.sin(theta);
			double x3 = x+this.rad*Math.cos(theta);
			double theta2=Math.PI-theta-(Math.PI/4);
			double x5 = x4+(this.rad/5)*Math.cos(theta2);
			double y5 = y4-(this.rad/5)*Math.sin(theta2);
			double theta3 = theta-Math.PI/4;
			double x6 = x4-(this.rad/5)*Math.cos(theta3);
			double y6 = y4-(this.rad/5)*Math.sin(theta3);
			Polygon triangle = new Polygon();
			triangle.addPoint((int)Math.round(x4), (int)Math.round(y4));
			triangle.addPoint((int)Math.round(x5), (int)Math.round(y5));
			triangle.addPoint((int)Math.round(x6), (int)Math.round(y6));
			g.drawPolygon(triangle);
			g.fillPolygon(triangle);
			
			drawArrow(node.getRight(), g);
			Line2D.Double rightLine = new Line2D.Double(x3,y3,x4,y4);
			g.draw(rightLine);
		}
		
	}
}
