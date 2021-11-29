
import java.util.regex.*;
public class Rule implements Expression {
	private Pattern pt;
	private Matcher mt;
	private String regEx;
	private String ruleInEnglish;
	
	public Rule(String regExp, String ruleInEnglish) {
		this.pt = Pattern.compile(regExp);
		this.regEx = regExp;
		this.ruleInEnglish = ruleInEnglish;
		
				
	}
	
	public void resetRule(String newRegex, String newRuleInEnglish) {
		this.pt = Pattern.compile(newRegex);
		this.regEx = newRegex;
		this.ruleInEnglish = newRuleInEnglish;
	}
	@Override
	public boolean interpret(String context) {
		this.mt = pt.matcher(context);
		return mt.matches();
	}

	@Override
	public String getExpression() {
		return this.ruleInEnglish;
	}
}

