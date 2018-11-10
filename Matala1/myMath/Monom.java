
package myMath;

/**
 * This class represents a simple "Monom" of shape a*x^b, where a is a real
 * number and a is an integer (summed a none negative), see:
 * https://en.wikipedia.org/wiki/Monomial The class implements function and
 * support simple operations as: construction, value at x, derivative, add and
 * multiply.
 * 
 * @author Boaz
 *
 */
public class Monom implements function {

	public Monom(double a, int b) {
		this.set_coefficient(a);
		this.set_power(b);
	}

	public Monom(Monom ot) {
		this(ot.get_coefficient(), ot.get_power());
	}

	// ***************** add your code below **********************
	//////////////////////////// public Monom class methods:

	/**
	 * a constructor which input is a String, this method created to support the
	 * Polynom class String constructor, this method creates a new Monom which
	 * values determined by the String input
	 * 
	 * **NOTE: this constructor works only on "ax^b" type of expression, where a is
	 * a double type variable, and b is a NON NEGATIVE integer type variable,this
	 * constructor WILL NOT work for any non logically correct string other then the
	 * empty one, in this case this constructor will create a zero value Monom, for
	 * more information please check the "readMe" file
	 * 
	 * @param str
	 *            String input which represents the soon to be Monom
	 * 
	 * 
	 * 
	 */
	public Monom(String str) {
		if (str.equals("")) {// in case the string is an empty one
			this.set_coefficient(0);
			this.set_power(0);
		}
		// in case the string isn't empty - turn it to a Monom type object
		Monom newM = initStringMonom(str);
		this.set_coefficient(newM.get_coefficient());
		this.set_power(newM.get_power());

	}

	/**
	 * calculates Monoms y value for a certain given x value
	 * 
	 * @param x
	 *            a double type variable which represents the point on the x - axis
	 *            we want to calculate the y value for
	 * @return the y - axis point which the Monom equals to in this certain given
	 *         point x
	 */
	@Override
	public double f(double x) {
		return (this._coefficient * (Math.pow(x, this._power)));
	}

	/**
	 * returns the power of the Monom used on
	 * 
	 * @return the integer value of the Monoms power
	 */
	public int get_power() {
		return this._power;
	}

	/**
	 * returns the coefficient of the Monom used on
	 * 
	 * @return the double value of the Monoms coefficient
	 */
	public double get_coefficient() {
		return this._coefficient;
	}

	/**
	 * returns a Monom which represents the derivative of the Monom which is used on
	 * 
	 * @return a Monom type object which represents the derivative
	 */
	public Monom derivative() {
		Monom outPut;
		if (this.get_power() == 0) {// in case the Monom is only a coefficient (as x^0 =1 for every x), the
									// derivative is just a zero
			outPut = new Monom(0, 0);
			return outPut;
		}
		outPut = new Monom(this._coefficient * this._power, this._power - 1);// this is the formula for derivative
																				// in Monom functions
		return (outPut);
	}

	/**
	 * sums two Monoms into the value of the Monom which used on
	 * 
	 * @param addend
	 *            a input Monom which should be summed with the one the method is
	 *            used on NOTE: if both Monoms got diffrent powers - the method will
	 *            print an eror message and will do nothing
	 */
	public void add(Monom addend) {
		if (this.get_power() != addend.get_power() && addend._coefficient != 0) {// checking whether you can add these
																					// both
			// Monoms
			System.out.println(" ERROR: YOU CANT ADD TWO MONOMS WITH DIFFRENT POWER!");
			return;
		} else if (addend.get_coefficient() != 0) {// if Monoms coefficient is zero, no point in adding it as it a zero
													// valued Monom
			this.set_coefficient(this.get_coefficient() + addend.get_coefficient());
		}
	}

	/**
	 * static method which supports the multiply method in the Polynom class
	 * 
	 * @param m1
	 *            is the first Monom in this product
	 * @param m2
	 *            is the second Monom in the product
	 * @return a new Monom which represents the product between two given Monoms m1
	 *         & m2
	 */
	public static Monom multiply(Monom m1, Monom m2) {
		return (new Monom(m1._coefficient * m2._coefficient, m1._power + m2._power));
	}

	/**
	 * subtract one Monom from the other
	 * 
	 * @param minuend
	 *            a input Monom which should be subtract from the one which the
	 *            method is used on
	 * @see if both Monoms got diffrent powers - the method will print an eror
	 *      message and will do nothing
	 */
	public void subtract(Monom minuend) {
		if (this.get_power() != minuend.get_power() && minuend._coefficient != 0) {// checking whether you can subtract
																					// these two Monoms
			System.out.println(" ERROR: YOU CANT SUBSTRACT TWO MONOMS WITH DIFFRENT POWER!");
			return;
		} else if (minuend._coefficient == 0) {

		} else
			this.set_coefficient(this._coefficient - (minuend._coefficient));
	}

	/**
	 * toString method which prints the value of the Monom which is used on, also
	 * supports the Polynom toString method
	 *
	 * @see if Monoms power is zero - will print only the coefficient as x^0=1 for
	 *      every x
	 * @see if Monoms coefficient is zero - will return a zero as x*0 = 0 as for
	 *      every x
	 * @return String representation of the Monom
	 */
	@Override
	public String toString() {
		if (this.get_coefficient() == 0) {
			return "0";
		} else if (this._coefficient == 1 && this._power != 0)// if this is Monoms coefficient is 1, it has no meaning,
																// we'll
			// print only the variable and the power;

			return "x" + "^" + _power;
		else if (this._power == 0) {// if this Monoms power is zero, we only interested in the coefficient, as any
			// x^0=1
			return "" + _coefficient;
		}
		return _coefficient + "x" + "^" + _power;
	}
	//////////////////////////// private supporting methods:

	/**
	 * private static method which supports Monom String constructor
	 * 
	 * @param str
	 *            String type variable which represents the soon to be Monom
	 * @return a new Monom type object which will be used in the String constructor
	 */
	private static Monom initStringMonom(String str) {
		str.toLowerCase();
		char cArray[] = str.toCharArray();
		int index = 0;
		String coefficient = "";
		String power = "";
		while (cArray[index] != 'x') {// getting the coefficient of this certain Monom as a String
			coefficient += "" + cArray[index];
			index++;
		}
		double coefficientD = Double.parseDouble(coefficient);// turning it to a double value
		while (index < cArray.length) {// getting the power of this certain Monom as a String
			if (cArray[index] != '^' && cArray[index] != 'x') {
				power += cArray[index];
			}
			index++;
		}
		int powerI = Integer.parseInt(power);// turning it to a integer value
		Monom outPut = new Monom(coefficientD, powerI);
		return outPut;
	}
	// ****************** Private Methods and Data *****************

	private void set_coefficient(double a) {
		this._coefficient = a;
	}

	private void set_power(int p) {
		if (p < 0) {
			throw new RuntimeException("WRONG POWER VALUE!");
		}
		this._power = p;
	}

	private double _coefficient; //
	private int _power;

}
