package Operations;

import java.util.ArrayList;

public class Or {
	// take two arraylist and make Or operation and return arraylist
		// have the new answer
	public ArrayList OrOperation(ArrayList<Integer> one,ArrayList<Integer> two) {
		ArrayList<Integer> ans = new ArrayList();
		for (int i = 0; i < one.size(); i++) {
			if (one.get(i) == 1 || two.get(i) == 1)
				ans.add(1);

			else
				ans.add(0);

		}
		return ans;
	}
}
