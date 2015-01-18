package evaluator;

import java.util.Stack;

/**
 * Evaluates infix expressions.
 *
 * @author lamberce and whiteer
 *         Created Dec 15, 2013.
 */
public class InfixEvaluator extends Evaluator {

	@Override
	public int evaluate(String expression) throws ArithmeticException {
		int total = -1;
		String post = convertToPostfix(expression);
		String[] temp3 = post.toString().split(" ");
		Stack<String> operands = new Stack<String>();
		int numOperands = 0;
		int numOperators = 0;
		for(int i = 0; i < temp3.length;  i++){
			if(isOperand(temp3[i])){
				operands.push(temp3[i].trim());
				numOperands++;
			} else {
				if(operands.size() < 2){
					throw new ArithmeticException();
				}
				int temp1 = Integer.parseInt(operands.pop());
				int temp2 = Integer.parseInt(operands.pop());
				numOperators++;
				if(temp3[i].equals("+")){
					total = temp2 + temp1;
					operands.push(String.valueOf(total));
				} else if(temp3[i].equals("-")){
					total = temp2 - temp1;
					operands.push(String.valueOf(total));
				} else if(temp3[i].equals("/")){
					if(temp1 == 0){
						throw new ArithmeticException(); 
					}
					total = temp2/temp1;
					operands.push(String.valueOf(total));
				} else if(temp3[i].equals("*")){
					total = temp2 * temp1;
					operands.push(String.valueOf(total));
				} else if(temp3[i].equals("^")){
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
	/**
	 * Converts a infix expression to postfix.
	 *
	 * @param exp
	 * @return
	 */
	public String convertToPostfix(String exp) 
	{
		StringBuilder post = new StringBuilder();
		String[] temp = exp.split(" ");
		Stack<String> operatorStack = new Stack<String>();
		int c = 0;
		int[] numParan = {0,0};
		for(int i = 0; i < temp.length; i++)
		{		
			if(isOperand(temp[i]))
			{
				post.append(temp[i] + " ");
			}  
			else if(isOperator(temp[i]))
			{
				c=1;				
				if(temp[i].equals("("))
				{
					numParan[0]++;
					operatorStack.push(temp[i]);
				} 
				else if(temp[i].equals(")"))
				{
					numParan[1]++;
					if(numParan[1] > numParan[0])
					{
						throw new ArithmeticException();
					}
					do{
						post.append(operatorStack.pop() + " ");
					}while(!operatorStack.peek().equals("("));
					operatorStack.pop();
				} 
				else if(operatorStack.isEmpty()||operatorStack.peek().equals("("))
				{
					operatorStack.push(temp[i]);
				} 
				else if(precedenceHelper(temp[i]) > precedenceHelper(operatorStack.peek()))
				{
					operatorStack.push(temp[i]);
				} 
				else if(precedenceHelper(temp[i]) == precedenceHelper(operatorStack.peek()))
				{
					post.append(operatorStack.pop() + " ");
					operatorStack.push(temp[i]);
				} 
				else if(precedenceHelper(temp[i]) < precedenceHelper(operatorStack.peek()))
				{
					while(!operatorStack.isEmpty()&&(precedenceHelper(temp[i]) <= precedenceHelper(operatorStack.peek())))
					{
						post.append(operatorStack.pop() + " ");
					}
					operatorStack.push(temp[i]);
				}			
			}
			else{
				throw new ArithmeticException();
			}
		}
		while(!operatorStack.isEmpty())
		{
			if(operatorStack.peek().equals("(") || operatorStack.peek().equals(")"))
			{
				throw new ArithmeticException();
			}
			post.append(operatorStack.pop() + " ");
		}

		if(c == 0)
		{
			throw new ArithmeticException();
		}
		if((numParan[0] - numParan[1] != 0))
		{
			throw new ArithmeticException();
		}
		return post.toString().trim().replaceAll("\\s+", " ");
	}

	/**
	 * @param op1
	 * @param op2
	 * @return false when right is equal or higher prec. then left; true when when left is higher prec.
	 */
	public int precedenceHelper(String op1) throws ArithmeticException
	{
		if(op1.equals("+"))
		{
			return 1;
		}
		else if(op1.equals("-"))
		{
			return 1;
		}
		else if(op1.equals("/"))
		{
			return 2;
		}
		else if(op1.equals("*"))
		{
			return 2;
		}
		else if(op1.equals("^"))
		{
			return 3;
		}
		else
		{
			throw new ArithmeticException();
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
