package Operations;

import java.util.ArrayList;

public class Nand {
	// take two arraylist and make nand operation and return arraylist
		// have the new answer
	public ArrayList<Integer> NandOperation(ArrayList<Integer> one,
			ArrayList<Integer> two) {
		ArrayList<Integer> ans = new ArrayList();
		for (int i = 0; i < one.size(); i++) {
			if (one.get(i) + two.get(i) == 2)
				ans.add(0);
			else
				ans.add(1);

		}
		return ans;

	}

}
