package Operations;

import java.util.ArrayList;

public class And {
	//take two arraylist and make and operation
	public ArrayList AndOperation(ArrayList<Integer> one,ArrayList<Integer> two) {
		ArrayList<Integer> ans = new ArrayList();
		for (int i = 0; i < one.size(); i++) {
			if (one.get(i) == two.get(i) && one.get(i) == 1)
				ans.add(1);

			else
				ans.add(0);

		}
		return ans;
	}
}
