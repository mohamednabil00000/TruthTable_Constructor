package Operations;

import java.util.ArrayList;

public class Not {
	
	// take one arraylist and make not operation and return arraylist
		// have the new answer
	public ArrayList NotOperation(ArrayList<Integer> one) {
		ArrayList<Integer> ans = new ArrayList();
		for (int i = 0; i < one.size(); i++) {
			if (one.get(i) == 0)
				ans.add(1);
			else
				ans.add(0);
		}
		return ans;
	}
}
