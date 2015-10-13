package Features;

import java.io.IOException;
import java.util.ArrayList;

import Control.TruthTableBuilder;
import Control.Validation;

public class Tautology {
	ArrayList<Integer> truthTable[];
	String expression;

	public Tautology(String expression) throws IOException {
		this.expression = expression;
		checkTautology();

	}

	public void checkTautology() throws IOException {
		Validation valid = new Validation(expression);

		if (valid.validate()) {
			// make object of truthtable builder
			TruthTableBuilder exec = new TruthTableBuilder(expression);
			// get the number of variables
			int var = exec.getHowManyVariable();
			exec.buildTruthTable(var);// build truth table
			truthTable = exec.getTruthTableResult();
			// change the expression into postfix notation
			String postfix = exec.postFix();
			// get the truthtable values
			exec.getTruthValue(postfix, truthTable);
			truthTable = exec.getTruthTableResult();
			// check all the results are true or not
			int numberOfZeros = 0;
			int numberOfOnes = 0;
			for (int i = 0; i < truthTable[truthTable.length - 1].size(); i++) {
				if (truthTable[truthTable.length - 1].get(i) == 0) {
					numberOfZeros++;
					if (numberOfOnes > 0) {
						System.out
								.println("Niether Tautology Nor Contradiction");
						return;
					}

				} else {
					numberOfOnes++;
					if (numberOfZeros > 0) {
						System.out
								.println("Niether Tautology Nor Contradiction");
						return;
					}

				}
			}
			if (numberOfZeros == 0 && numberOfOnes > 0)
				System.out.println("Tautology");
			else if (numberOfOnes == 0 && numberOfZeros > 0)
				System.out.println("Contradiction");
		}
		return;
	}

}
