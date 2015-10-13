package Operations;

import java.util.ArrayList;

public class Xor {
	// take two arraylist and make Xor operation and return arraylist
		// have the new answer
	public ArrayList XorOperation(ArrayList<Integer> one, ArrayList<Integer> two) {
		ArrayList<Integer> ans = new ArrayList();
		for (int i = 0; i < one.size(); i++) {
			if (one.get(i) + two.get(i) == 1)
				ans.add(1);
			else
				ans.add(0);
		}
		return ans;
	}

}
