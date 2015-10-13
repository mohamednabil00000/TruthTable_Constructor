package Control;

import java.io.IOException;
import java.util.ArrayList;

public class Validation {
	private String expression;
	private ArrayList<Integer> answer[];
	private ArrayList<String> variableName;

	public Validation(String expression) throws IOException {
		this.expression = expression;

	}

	public void setExpression(String expression) {
		this.expression = expression;
	}

	public String getExpression() {
		return expression;
	}

	public void error() {
		System.out.println("Error In Syntex !");

	}

	// check validation of expression
	public boolean validate() throws IOException {

		String check = "";
		int braket = 0;
		int variable = 0;

		int i;
		// change the expression into another expression to ease the check in
		// validation by all variables take one constant variable(0) and all
		// operation take one varaible (1) exept some operation and brackets
		// to check if two variables constant beside each other return false
		for (i = 0; i < expression.length(); i++) {
			if (expression.charAt(i) == '&' || expression.charAt(i) == '+'
					|| expression.charAt(i) == '>'
					|| expression.charAt(i) == '#'
					|| expression.charAt(i) == '='
					|| expression.charAt(i) == '-'
					|| expression.charAt(i) == '|')
				check += "1";
			else if (expression.charAt(i) == '!')
				check += "2";
			else if (expression.charAt(i) >= 'a' && expression.charAt(i) <= 'z') {
				check += "0";
				variable++;
			} else if (expression.charAt(i) >= 'A'
					&& expression.charAt(i) <= 'Z') {
				check += "0";
				variable++;
			} else if (expression.charAt(i) == '(') {
				check += "3";
				braket++;
			} else if (expression.charAt(i) == ')' && braket > 0) {
				check += "4";
				braket--;
			} else {
				error();

				return false;
			}

		}
		if (variable == 0 || braket != 0 || check(check) == false) {
			error();
			return false;
		}
		return true;

	}

	public ArrayList<Integer>[] getAnswer() {
		return answer;
	}

	public void setAnswer(ArrayList<Integer>[] answer) {
		this.answer = answer;
	}

	public ArrayList<String> getVariableName() {
		return variableName;
	}

	public void setVariableName(ArrayList<String> variableName) {
		this.variableName = variableName;
	}

	// to check if two constant variables beside each other return false like
	// this : a&&b or AA&b
	public static boolean check(String check) {
		if (check.charAt(0) == '1' || check.charAt(check.length() - 1) == '1'
				|| check.charAt(check.length() - 1) == '2')
			return false;

		for (int i = 1; i < check.length(); i++) {
			if (check.charAt(i) == check.charAt(i - 1)
					&& check.charAt(i) != '3' && check.charAt(i) != '4'
					&& check.charAt(i) != '2') {
				return false;

			}
		}
		// for these cases [!&,!|,!->,a!b,(),)!,a(,)b]
		if (check.contains("21") || check.contains("020")
				|| check.contains("34") || check.contains("42")
				|| check.contains("03") || check.contains("40")) {

			return false;
		}
		return true;

	}

}
