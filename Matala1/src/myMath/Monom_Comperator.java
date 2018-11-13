package myMath;

import java.util.Comparator;

public class Monom_Comperator implements Comparator<Monom> {

	// ******** add your code below *********
	@Override
	public int compare(Monom firstMonom, Monom secondMonom) {
		int returnedValuve = secondMonom.get_power() - firstMonom.get_power();// sorting by the largest power of all
																				// monoms
		return (returnedValuve);
	}

}
