package test;

import java.util.Stack;

import com.avalon.ms.enums.CalcOperator;

public class RPNS {
	
	private static final String PLUS ="+";
	private static final String SUBTRACT ="-";
	private static final String MULTIPLY ="*";
	private static final String DIVIDE ="/";
	
	
	private static Stack stack = new Stack<String>();
	
	public static void add(String item){
		
		switch(item){
			case PLUS:
				stack.push(calc(PLUS));
				break;
			case SUBTRACT:
				stack.push(calc(SUBTRACT));
				break;
			case MULTIPLY:
				stack.push(calc(MULTIPLY));
				break;
			case DIVIDE:
				stack.push(calc(DIVIDE));
				break;
			default :
				stack.push(item);
				break;
		}
	}
	
	public static String calc(String  operator){
		String strA = (String) stack.pop();
		String strB = (String) stack.pop();
		System.out.println(" 1 "+strA+" 2 "+strB);
		int numA = Integer.valueOf(strA);
		int numB = Integer.valueOf(strB);
		int result = 0;
		switch(operator){
			case PLUS:
				result = numA+numB;
				break;
			case SUBTRACT:
				result = numA-numB;
				break;
			case MULTIPLY:
				result = numA*numB;
				break;
			case DIVIDE:
				result = numA/numB;
				break;
		}
		System.out.println("result:"+result);
		return String.valueOf(result);
	}
	
	public static String getResult(){
		return (String) stack.pop();
	}
	
	public static void main(String[] args) {

		RPNS.add("123");
		RPNS.add("4");
		RPNS.add("+");
		RPNS.add("5");
		RPNS.add("*");
		RPNS.add("6");
		RPNS.add("/");
		RPNS.add("23");
		RPNS.add("+");
		RPNS.add("34");
		RPNS.add("-");
		RPNS.add("4");
		RPNS.add("*");
		RPNS.add("5");
		RPNS.add("/");
		System.out.println(RPNS.getResult());
	}
}
