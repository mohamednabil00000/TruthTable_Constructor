package Operations;

import java.util.ArrayList;

public class Nor {
	// take two arraylist and make nor operation and return arraylist
		// have the new answer
	public ArrayList<Integer> NorOperation(ArrayList<Integer> one,
			ArrayList<Integer> two) {
		ArrayList<Integer> ans = new ArrayList();
		for (int i = 0; i < one.size(); i++) {
			if (one.get(i) + two.get(i) == 0)
				ans.add(1);
			else
				ans.add(0);

		}
		return ans;

	}

}
