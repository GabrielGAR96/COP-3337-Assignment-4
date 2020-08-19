import java.util.Stack;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Preprocessor {
	
	Stack parenth; //stores the quantity of parenthesis
	Stack cBraces; //stores the quantity of curved braces
	Stack sBraces; //stores the quantity of square braces
	
	String expression; //math expression
	
	public Preprocessor(String expression)
	{
		this.expression = expression; 
		parenth = new Stack();
		cBraces = new Stack();
		sBraces = new Stack();
	}
	
	void display()
	{
		System.out.println("Input: " + expression); //prints out the expression before removing whitespace characters
		
		expression = expression.replaceAll("\\s+", "");  //removes all whitespace characters
		
		if(isValid())
		{
			System.out.println("The statement is valid.");
		}
		else System.out.println("The statement is NOT valid.");
	}
	
	
	boolean isValid()
	{
		int index = 0;
        boolean fail = false;	
        Pattern pattern1 = Pattern.compile("\\d|\\)|\\]|\\}"); //regex to check if it matches with a number, parenthesis or bracket on the left
        Pattern pattern2 = Pattern.compile("\\d|\\(|\\[|\\{"); //regex to check if it matches with a number, parenthesis or bracket on the right
        
                while (index < expression.length() && !fail)
                {
                        char ch = expression.charAt(index);
                        switch (ch) 
                        {
                                case '(':
                                	parenth.push(new Character(ch)); //stores the parenthesis in the stack
                                	break;
                                case '[':
                                	sBraces.push(new Character(ch)); //stores the bracket in the stack
                                	break;
                                case '{':
                                	cBraces.push(new Character(ch)); //stores the bracket in the stack
                                	break;
                                case ')':
                                	if(!parenth.isEmpty()) // checks if an opening parenthesis was used before
                                	parenth.pop(); 
                                	else fail = true;
                                	break;
                                case ']':
                                	if(!sBraces.isEmpty()) // checks if an opening brackets was used before
                                	sBraces.pop();
                                	else fail = true;
                                	break;
                                case '}':
                                	if(!cBraces.isEmpty()) // checks if an opening brackets was used before
                                	cBraces.pop();
                                	else fail = true;
                                	break;
                                case '*':
                                	if(index >= 1 && index < expression.length()-1) { 
                                	String m1 = "";
                                	String m2 = "";
                                	m1 += expression.charAt(index-1);
                                	m2 += expression.charAt(index+1);
                                	Matcher matcher1 = pattern1.matcher(m1);
                                	Matcher matcher2 = pattern2.matcher(m2);
                                	if(!matcher1.matches() || !matcher2.matches())
                                		fail = true;
                                	} else fail = true;
                                	break;
                                case '/':
                                	if(index >= 1 && index < expression.length()-1) {
	                                	String m1 = "";
	                                	String m2 = "";
	                                	m1 += expression.charAt(index-1);
	                                	m2 += expression.charAt(index+1);
	                                	Matcher matcher1 = pattern1.matcher(m1);
	                                	Matcher matcher2 = pattern2.matcher(m2);
	                                	if(!matcher1.matches() || !matcher2.matches())
	                                		fail = true;
                                	} else fail = true;
                                	break;
                                default:

                                break;
                        }
                        index++;
                }
                
        if (parenth.empty() && sBraces.empty() && sBraces.empty() && !fail)
                return true;
        else
                return false;
		
	}

}
