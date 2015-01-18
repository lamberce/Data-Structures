import java.util.ArrayList;

/**
 * This is a tree factory.
 * 
 * @author Curt Clifton. Created Jan 24, 2008.
 */
public final class BuildTree {

	/**
	 * Generates a binary tree whose node contents and pre-order traversal 
	 * order come from charElements.  For each of those nodes, 
	 * the corresponding element in childCodes indicates the
	 * number of children, where L means one left child, R means one right child,
	 * and 0 and 2 mean zero and two children respectively.
	 *
	 * @param charElements
	 * @param childCodes
	 * @return the binary tree constructed from charElements and childCodes
	 */
	public static BinaryTree preOrderBuild(CharSequence charElements,
			                               CharSequence childCodes) {
		CharSequence elements = charElements;
		CharSequence codes = childCodes;
		int temp = 0;
		int counter1 = 0;
		int counter2 = 0;
		if(elements.length()==0){
			return new BinaryTree();
		}
		BinaryTree returnTree = new BinaryTree(charElements.charAt(0));
		BinaryNode tempNode = new BinaryNode();
		int j = 0;
		while((j<elements.length())&&((counter2!=counter1)||j==0)){
			if(j==0&&codes.charAt(0)=='L'){
				counter1++;
			}
			if(codes.charAt(j)=='2'){
				counter1++;
			} else if(codes.charAt(j)=='0'){
				counter2++;
			}
			if(counter1==counter2){
				temp = j;
			}
			j++;
		}
		j = temp;
		
		if(codes.charAt(0)=='2'){
			returnTree.merge(elements.charAt(0), preOrderBuild(elements.subSequence(1,j+1),codes.subSequence(1,j+1)), 
			preOrderBuild(elements.subSequence(j+1,elements.length()),codes.subSequence(j+1,elements.length())));
			return returnTree;
		} else if(codes.charAt(0)=='L'){
			returnTree.merge(charElements.charAt(0), preOrderBuild(elements.subSequence(1,j+1),codes.subSequence(1,j+1)), new BinaryTree());
			return returnTree;
		} else if(codes.charAt(0)=='R'){
			returnTree.merge(charElements.charAt(0), new BinaryTree(), preOrderBuild(elements.subSequence(1,elements.length()),codes.subSequence(1,elements.length())));
			return returnTree;
		} else {
			return returnTree;
		}
	}
}
