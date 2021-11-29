
import java.util.ArrayList;

public class Password{
	private String accountPassword;
	private ArrayList<Expression> expressions = new ArrayList<>();
	
	public Password(String potentialPassword) {
		this.accountPassword = null;
		//8 or more characters
		expressions.add(new Rule(".{8,}", "8 or more characters"));
		//1 or more digits
		expressions.add(new Rule(".*\\d+.*", "1 or more digits"));
		//1 or more capital letters and 1 or more lowercase letters
		expressions.add(new AndExpression(new Rule(".*[A-Z]+.*", "1 or more Uppercase Letters"), new Rule(".*[a-z]+.*", "1 or more Lowercase Letters")));
		//1 or more !%&^$
		expressions.add(new OrExpression(new Rule(".*[!%]+.*", "Special characters: !%"), new Rule(".*[&^$]+.*", "Special characters: &^$")));
		//no spaces
		expressions.add(new Rule("[^\\s]*", "No Spaces"));
		this.setPassword(potentialPassword);
	}
	
	public Password() {
		this.accountPassword = null;
		//8 or more characters
		expressions.add(new Rule(".{8,}", "8 or more characters"));
		//1 or more digits
		expressions.add(new Rule(".*\\d+.*", "1 or more digits"));
		//1 or more capital letters and 1 or more lowercase letters
		expressions.add(new AndExpression(new Rule(".*[A-Z]+.*", "1 or more Uppercase Letters"), new Rule(".*[a-z]+.*", "1 or more Lowercase Letters")));
		//1 or more !%&^$
		expressions.add(new OrExpression(new Rule(".*[!%]+.*", "Special characters: !%"), new Rule(".*[&^$]+.*", "Special characters: &^$")));
		//no spaces
		expressions.add(new Rule("[^\\s]*", "No Spaces"));
		
	}
	
	private boolean isValid(String potentialPassword) {
		for(Expression e: expressions){
	    	  if (!e.interpret(potentialPassword)) {
	    		  System.out.println("Failed on: " + e.getExpression());
	    		  return false;
	    	  }
	      }
	      return true;
	}
	
	public String getPassword() {
		return this.accountPassword;
	}
	
	public boolean setPassword(String potentialPassword) {
		if (isValid(potentialPassword)) {
			this.accountPassword = potentialPassword;
			return true;
		}
		else System.out.println("Something went wrong!");
		return false;
		
	}

}