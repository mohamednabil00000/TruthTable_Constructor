package Features;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import Control.TruthTableBuilder;
import Control.Validation;

public class Compare {
	private String expression1;
	private String expression2;

	public Compare(String expresseion1, String expression2) throws IOException {
		this.expression1 = expresseion1;
		this.expression2 = expression2;
		compare();
	}

	public void compare() throws IOException {
		// check validation of first and second expression

		Validation valid1 = new Validation(expression1);
		Validation valid2 = new Validation(expression2);

		if (valid1.validate() && valid2.validate()) {
			// build object of each expression
			TruthTableBuilder exec1 = new TruthTableBuilder(expression1);
			TruthTableBuilder exec2 = new TruthTableBuilder(expression2);
			// get the number of variables of each expression
			int var1 = exec1.getHowManyVariable();
			HashMap<String, Integer> variablesName1 = exec1.getMp();
			int var2 = exec2.getHowManyVariable();
			ArrayList<String> variablesName2 = exec2.getVariableName();

			// check if number of variable of first expression not equal the
			// number of variables in second expression
			if (var1 != var2
					|| !checkVariableNames(variablesName1, variablesName2)) {
				System.out.println("Not Equal !");
				return;
			}
			// build the truth table for each expression
			exec1.buildTruthTable(var1);
		
			// return the array of arrayList having the truth table to send it
			// to another method to get the value of truth table
			ArrayList<Integer> truthTable[] = exec1.getTruthTableResult();
			// change the first expression into postfix notation
			String postfix = exec1.postFix();
			// get the value of truth table by using stacks
			ArrayList<Integer> ans1 = exec1.getTruthValue(postfix, truthTable);
			// return the array of arrayList have the truth table and its result
			//truthTable = exec2.getTruthTableResult();
			// change the second expression into postfix notation
			postfix = exec2.postFix();
			// return the array of arrayList have the truth table and its result
			ArrayList<Integer> ans2 = exec1.getTruthValue(postfix, truthTable);

			// comparing the result of two expressions
			for (int i = 0; i < ans1.size(); i++) {
				if (ans1.get(i) != ans2.get(i)) {
					System.out.println("Not Equal !");
					return;
				}
			}
			System.out.println("Equal");

		}
		return;
	}
	//check the equality of variables names in two expressions.
	public boolean checkVariableNames(HashMap<String, Integer> one,
			ArrayList<String> two) {
		for (int i = 0; i < two.size(); i++) {
			if (!one.containsKey(two.get(i)))
				return false;
		}
		return true;

	}

}
