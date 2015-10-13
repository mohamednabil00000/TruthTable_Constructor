package Features;

import java.awt.font.NumericShaper;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

import Control.TruthTableBuilder;
import Control.Validation;

public class GetValue {
	private String expression;
	private Validation valid;
	private ArrayList<Integer> truthTable[];

	public GetValue(String expression) throws Throwable {
		this.expression = expression;
		getValue();
	}

	public void getValue() throws Throwable {
		Scanner in = new Scanner(System.in);
		valid = new Validation(expression);
		// check validation of expression
		if (valid.validate()) {

			TruthTableBuilder exec = new TruthTableBuilder(expression);
			// get the number of variables to build the arrayList
			int numberOfVariable = exec.getHowManyVariable();

			truthTable = new ArrayList[numberOfVariable + 1];
			for (int i = 0; i < numberOfVariable + 1; i++) {
				truthTable[i] = new ArrayList();
			}
			// return the variables name
			ArrayList<String> variableName = exec.getVariableName();
			// take the value of each variable name
			for (int i = 0; i < variableName.size(); i++) {
				System.out.print("Enter the value of " + variableName.get(i)
						+ " = ");
				String value = in.next();
				// for wrong input
				while (!value.equals("1") && !value.equals("0")) {
					System.out.println("you should enter 1 or 0");
					System.out.print("Enter the value of "
							+ variableName.get(i) + " = ");
					value = in.next();

				}

				truthTable[i].add(Integer.parseInt(value));

			}
			// return the value of the expression
			ArrayList<Integer> ans = exec.getTruthValue(exec.postFix(),
					truthTable);

			System.out.println("the answer of given propositions :"
					+ ans.get(0));

		}

	}
}