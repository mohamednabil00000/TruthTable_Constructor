package Control;

import java.util.Scanner;

import javax.swing.JFrame;
import javax.swing.JPanel;

import Features.Compare;
import Features.GetValue;
import Features.Tautology;
import Features.TruthTable;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.Color;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Main {

	// And...........&
	// Or............+
	// not...........!
	// xor...........#
	// Iff...........=
	// implicant.....>
	// Nand..........|
	// Nor...........-
	// O((variable-1)*2^variable)<10^8
	// 26 variables maximum

	// priority of operations
	// 1- not
	// 2-and & nand
	// 3-Or & xor & nor
	// 4- implicant
	// 5- biconditional
	public static void main(String[] args) throws Throwable {

		Scanner in = new Scanner(System.in);

		// while (true) {
		// System.out.println("If you want to create Truth Table press 1");
		// System.out.println("If you want to check tautology press 2");
		// System.out
		// .println("If you want to compare between two expression press 3");
		// System.out
		// .println("If you want to get the value of expression press 4");
		// System.out.println("If you want to exit press 0");
		// try {
		// int k = in.nextInt();
		// in.nextLine();
		// try {
		// if (k == 1) {
		// System.out.println("Enter the Expression :");
		// String exp = in.nextLine();
		long startTime = System.nanoTime();
		String exp = "a&b|(c&d)&g#a-c&!d+g&f&j";
		TruthTable tabl = new TruthTable(exp);
		long endTime = System.nanoTime();
		long duration = (endTime - startTime); 
		System.out.println("Execution time is = "+duration);
		// } else if (k == 2) {
		// System.out.println("Enter the Expression :");
		// String exp = in.nextLine();
		// Tautology tau = new Tautology(exp);
		//
		// } else if (k == 3) {
		// System.out.println("Enter the First Expression :");
		// String exp1 = in.nextLine();
		// System.out.println("Enter the Second Expression :");
		// String exp2 = in.nextLine();
		// Compare comp = new Compare(exp1, exp2);
		//
		// } else if (k == 4) {
		// System.out.println("Enter the First Expression :");
		// String exp1 = in.nextLine();
		// GetValue val = new GetValue(exp1);
		//
		// }
		// } catch (Exception e) {
		// System.out.println("Error !");
		// // e.printStackTrace();
		//
		// }
		// } catch (Exception e) {
		// System.out.println("Error !");
		// in.nextLine();
		//
		// }
		// }

	}
}
