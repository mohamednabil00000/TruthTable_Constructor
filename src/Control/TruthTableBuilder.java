package Control;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Stack;

import Operations.And;
import Operations.IfAndOnlyIf;
import Operations.Implicant;
import Operations.Nand;
import Operations.Nor;
import Operations.Not;
import Operations.Or;
import Operations.Xor;

public class TruthTableBuilder {
	private String expression;
	private ArrayList<Integer> truthTableResult[];// to save the truth table and
													// the value of truth table
													// in the last index
	private ArrayList<String> variableName;// to save variales names in it
	private HashMap<String, Integer> mp;// to save each variable in special
										// index
	private int variable;
	And and = new And();
	Or or = new Or();
	Not not = new Not();
	Implicant imp = new Implicant();
	Xor xor = new Xor();
	IfAndOnlyIf iff = new IfAndOnlyIf();
	Nor nor = new Nor();
	Nand nand = new Nand();

	public TruthTableBuilder(String expression) throws IOException {
		this.expression = expression;

		this.mp = new HashMap();
		this.variable = 0;
		this.variableName = new ArrayList();

	}

	public HashMap<String, Integer> getMp() {
		return mp;
	}

	public void setMp(HashMap<String, Integer> mp) {
		this.mp = mp;
	}

	// get the number of varibles in expression and save it in hashMap
	public int getHowManyVariable() throws IOException {

		for (int i = 0; i < expression.length(); i++) {
			if (expression.charAt(i) >= 'a' && expression.charAt(i) <= 'z') {

				if (!mp.containsKey("" + expression.charAt(i))) {
					mp.put("" + expression.charAt(i), variable);
					variableName.add("" + expression.charAt(i));
					variable++;

				}
			} else if (expression.charAt(i) >= 'A'
					&& expression.charAt(i) <= 'Z') {

				if (!mp.containsKey("" + expression.charAt(i))) {
					mp.put("" + expression.charAt(i), variable);
					variableName.add("" + expression.charAt(i));
					variable++;

				}

			}

		}
		return variable;

	}

	// build the truth table by taking the number of variable (0->2^varaible-1)
	public ArrayList<Integer>[] buildTruthTable(int variable) {
		truthTableResult = new ArrayList[variable + 1];
		for (int i = 0; i < variable + 1; i++) {
			truthTableResult[i] = new ArrayList();
		}
		int limit = 1 << variable;
		String bin = "";
		for (int i = 0; i < limit; i++) {
			bin = Integer.toBinaryString(i);
			while (bin.length() < variable)
				bin = "0" + bin;
			for (int j = 0; j < variable; j++) {
				truthTableResult[j].add(Integer.parseInt("" + bin.charAt(j)));
			}

		}
		return truthTableResult;

	}

	// change the expression into postfix notation
	public String postFix() throws IOException {
		Stack<String> post = new Stack();
		String postfix = "";
		for (int i = 0; i < expression.length(); i++) {
			if (expression.charAt(i) == '&' || expression.charAt(i) == '|') {
				while (!post.isEmpty()) {
					String check = post.peek();
					if (check.equals("("))
						break;
					if (!check.equals("!") && !check.equals("&")
							&& !check.equals("|"))
						break;
					postfix += check;
					post.pop();
				}
				post.add("" + expression.charAt(i));
			} else if (expression.charAt(i) == '-'
					|| expression.charAt(i) == '+'
					|| expression.charAt(i) == '#') {

				while (!post.isEmpty()) {
					String check = post.peek();
					if (check.equals("("))
						break;
					if (check.equals(">") || check.equals("="))
						break;
					postfix += check;
					post.pop();
				}
				post.add("" + expression.charAt(i));

			} else if (expression.charAt(i) == '>') {

				while (!post.isEmpty()) {
					String check = post.peek();
					if (check.equals("("))
						break;
					if (check.equals("="))
						break;
					postfix += check;
					post.pop();
				}
				post.add("" + expression.charAt(i));

			} else if (expression.charAt(i) == '=') {

				while (!post.isEmpty()) {
					String check = post.peek();
					if (check.equals("("))
						break;

					postfix += check;
					post.pop();
				}
				post.add("" + expression.charAt(i));

			}

			//
			// if (expression.charAt(i) == '&' || expression.charAt(i) == '+'
			// || expression.charAt(i) == '>'
			// || expression.charAt(i) == '#'
			// || expression.charAt(i) == '='
			// || expression.charAt(i) == '-'
			// || expression.charAt(i) == '|') {
			// while (!post.isEmpty()) {
			// String check = post.peek();
			// if (check.equals("("))
			// break;
			// postfix += check;
			// post.pop();
			//
			// }
			// post.add("" + expression.charAt(i));
			//
			// }
			else if (expression.charAt(i) == '!' || expression.charAt(i) == '(') {
				post.add("" + expression.charAt(i));
			} else if (expression.charAt(i) == ')') {
				while (!post.isEmpty()) {
					String check = post.peek();
					if (check.equals("(")) {
						post.pop();
						break;
					}
					postfix += check;
					post.pop();

				}
			} else {
				postfix += expression.charAt(i);
			}

		}
		while (!post.isEmpty()) {
			postfix += post.pop();
		}

		return postfix;

	}

	// get the truth values by using stacks ...
	// ex:AB&C+
	// stack take A and B then pop A and B and add A&B , C and then pop A&B and
	// C and then return A&B+c
	public ArrayList getTruthValue(String postfix,
			ArrayList<Integer> truthTable[]) throws IOException {

		Stack<ArrayList> answer = new Stack();
		ArrayList<Integer> one;
		ArrayList<Integer> two;
		ArrayList<Integer> ans;
		for (int i = 0; i < postfix.length(); i++) {
			if (postfix.charAt(i) == '&') {
				two = answer.pop();
				one = answer.pop();
				ans = and.AndOperation(one, two);
				answer.add(ans);
			} else if (postfix.charAt(i) == '+') {
				two = answer.pop();
				one = answer.pop();
				ans = or.OrOperation(one, two);
				answer.add(ans);
			} else if (postfix.charAt(i) == '>') {
				two = answer.pop();
				one = answer.pop();
				ans = imp.ImplicantOperation(one, two);
				answer.add(ans);
			} else if (postfix.charAt(i) == '#') {
				two = answer.pop();
				one = answer.pop();
				ans = xor.XorOperation(one, two);
				answer.add(ans);
			} else if (postfix.charAt(i) == '!') {
				one = answer.pop();
				ans = not.NotOperation(one);
				answer.add(ans);
			} else if (postfix.charAt(i) == '=') {
				two = answer.pop();
				one = answer.pop();
				ans = iff.IfAndOnlyIfOperation(one, two);
				answer.add(ans);
			} else if (postfix.charAt(i) == '-') {
				two = answer.pop();
				one = answer.pop();
				ans = nor.NorOperation(one, two);
				answer.add(ans);
			} else if (postfix.charAt(i) == '|') {
				two = answer.pop();
				one = answer.pop();
				ans = nand.NandOperation(one, two);
				answer.add(ans);
			} else {

				int index = mp.get("" + postfix.charAt(i));

				answer.add(truthTable[index]);

			}
		}

		truthTable[variable] = answer.pop();
		return truthTable[variable];

	}

	public ArrayList<Integer>[] getTruthTableResult() {
		return truthTableResult;
	}

	public void setTruthTableResult(ArrayList<Integer>[] truthTableResult) {
		this.truthTableResult = truthTableResult;
	}

	public int getVariable() {
		return variable;
	}

	public void setVariable(int variable) {
		this.variable = variable;
	}

	public ArrayList<String> getVariableName() {
		return variableName;
	}

	public void setVariableName(ArrayList<String> variableName) {
		this.variableName = variableName;
	}

}
