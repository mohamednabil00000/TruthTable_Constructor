package Operations;

import java.util.ArrayList;

public class Implicant {
	// take two arraylist and make implication operation and return arraylist
		// have the new answer

	public ArrayList ImplicantOperation(ArrayList<Integer> one,ArrayList<Integer> two) {
		ArrayList<Integer> ans = new ArrayList();
		for (int i = 0; i < one.size(); i++) {
			if (one.get(i) == 1 && two.get(i) == 0)
				ans.add(0);

			else
				ans.add(1);

		}
		return ans;
	}

}
