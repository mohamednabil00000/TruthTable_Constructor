package Features;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

import Control.TruthTableBuilder;

import Control.Validation;

public class TruthTable {

	private String expression;
	Scanner in = new Scanner(System.in);
	private ArrayList<String> variablesName;
	private ArrayList<Integer> truthTable[];

	public TruthTable(String expression) throws IOException {
		this.expression = expression;
		CreateTruthTable();

	}

	public void CreateTruthTable() throws IOException {
		Validation valid = new Validation(expression);

		if (valid.validate()) {
			TruthTableBuilder exec = new TruthTableBuilder(expression);
			// get the number of variables of expressions
			int var = exec.getHowManyVariable();
			// get the names of variables
			variablesName = exec.getVariableName();
			// get the truthtble
			truthTable = exec.buildTruthTable(var);
			// change the expression into postfix notation
			String postfix = exec.postFix();
			// get the result of truth table
			exec.getTruthValue(postfix, truthTable);
			// return the array of arraylist having the truthtable and the
			// result
			truthTable = exec.getTruthTableResult();
	//		while (true) {
//				System.out
//						.println("If you Want to Print Truth Table in console press 1");
//				System.out
//						.println("If you Want to Print Truth Table in file press 2");
//				System.out
//						.println("If you Want to Enter another expression press 0");
//				int k = in.nextInt();

//				if (k == 1)
					printInConsole();
//				else if (k == 2)
//					printInFile();
//				else if (k == 0) {
//					break;
//
//				}
		//	}
		}
	}

	// print truthtable in console
	public void printInConsole() {
		StringBuilder sb = new StringBuilder();

		for (int i = 0; i < variablesName.size(); i++) {
			sb.append(variablesName.get(i) + "   ");
		}

		sb.append(expression + "\n");

		for (int i = 0; i < truthTable[0].size(); i++) {
			for (int j = 0; j < truthTable.length; j++) {
				sb.append(truthTable[j].get(i) + " | ");
			}
			sb.append("\n");
		}
		System.out.print(sb);

	}

	// print truth table into file txt
	public void printInFile() throws IOException {
		PrintWriter out = new PrintWriter(new FileWriter("TruthTable.txt"));
		for (int i = 0; i < variablesName.size(); i++) {
			out.print(variablesName.get(i) + "   ");
		}

		out.println(expression);

		for (int i = 0; i < truthTable[0].size(); i++) {
			for (int j = 0; j < truthTable.length; j++) {
				out.print(truthTable[j].get(i) + " | ");
			}
			out.println();
		}
		out.close();
		System.out.println("Done !");

	}

}
