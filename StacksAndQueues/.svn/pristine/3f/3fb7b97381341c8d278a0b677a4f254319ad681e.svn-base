package evaluator;

import java.util.Stack;

/**
 * TODO Put here a description of what this class does.
 *
 * @author lamberce and whiteer
 *         Created Dec 15, 2013.
 */
public class PostfixEvaluator extends Evaluator {

	@Override
	public int evaluate(String expression) throws ArithmeticException {
		int total = -1;
		String[] temp = expression.split(" ");
		Stack<String> operands = new Stack<String>();
		int numOperands = 0;
		int numOperators = 0;
		for(int i = 0; i < temp.length;  i++){
			if(isOperand(temp[i])){
				operands.push(temp[i].trim());
				numOperands++;
			} else {
				if(operands.size() < 2){
					throw new ArithmeticException();
				}
				int temp1 = Integer.parseInt(operands.pop());
				int temp2 = Integer.parseInt(operands.pop());
				numOperators++;
				if(temp[i].equals("+")){
					total = temp2 + temp1;
					operands.push(String.valueOf(total));
				} else if(temp[i].equals("-")){
					total = temp2 - temp1;
					operands.push(String.valueOf(total));
				} else if(temp[i].equals("/")){
					total = temp2/temp1;
					operands.push(String.valueOf(total));
				} else if(temp[i].equals("*")){
					total = temp2 * temp1;
					operands.push(String.valueOf(total));
				} else if(temp[i].equals("^")){
					total = (int) Math.pow(temp2,temp1);
					operands.push(String.valueOf(total));
				}
				
			}
		}
		if((numOperands - numOperators) != 1){
			throw new ArithmeticException();
		}
		if(operands.isEmpty()){
			return total;
		} else {
			return Integer.parseInt(operands.pop());
		}
	}
	public boolean isOperand(String token) {
		char[] allTokens = token.toCharArray();
		int check = 0;
		for(int i = 0; i < allTokens.length; i++)
		{
			if("0123456789".contains(String.valueOf(allTokens[i])))
			{
				check = check + 0;
			}
			else
			{
				check = check + 1;
			}
		}
		return check == 0;
	}
}
