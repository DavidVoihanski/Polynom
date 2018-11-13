
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
	 * **NOTE: this constructor works only on logically correct type of expressions,
	 * where a is a double type variable, and b is a NON NEGATIVE integer type
	 * variable,this constructor WILL NOT work for any non logically correct string
	 * other then the empty one, in this case this constructor will create a zero
	 * value Monom, for more information please check the "readMe" file, example for
	 * correct input: "43","-33","3x","3x^5","33.3x^32"
	 * 
	 * @param str
	 *            String input which represents the soon to be Monom
	 * 
	 * 
	 * 
	 */
	public Monom(String str)throws RuntimeException {
		if (str.equals("")) {// in case the string is an empty one
			this.set_coefficient(0);
			this.set_power(0);
		}
		// in case the string isn't empty - turn it to a Monom type object
		str = str.toLowerCase();// to handle a case in which we got big letters
		Monom newM = initStringMonom(str);// sending the lower case string to a private method which will handle with
											// all cases
		// setting up new Monoms value's based on the Monom just created
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
	 *            used on NOTE: if both Monoms got different powers - the method will
	 *            print an error message and will do nothing
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
		if (this.get_coefficient() == 0)// in Monoms coefficient is zero, the form of its printing will be "0"
			return "0";
		if (this.get_power() == 1) {// in case the power is one, we don't need to print the part "..^.." because it
									// has no numeric value
			if (this.get_coefficient() == -1)
				return "-x";
			else if (this.get_coefficient() == 1)
				return "x";
			else
				return "" + this.get_coefficient() + "x";
		} else if (this.get_power() == 0)// in case Monos power is 0, the variable x has no meaning as any number^0=1
			return "" + this.get_coefficient();
		else {// in case the power isn't 0 or 1, well just print it all
			if (this.get_coefficient() == 1) {
				return "x^" + this.get_power();
			} else if (this.get_coefficient() == (-1)) {
				return "-x^" + this.get_power();
			}
			return "" + this.get_coefficient() + "x^" + this.get_power();
		}
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
		Monom OutPut = null;// setting up a null Monom for later use
		if (str.equals("")) {// in case we got an empty string, turning it to the zero Monom
			return OutPut = new Monom(0, 0);
		}
		if (howManyChar(str, 'x') == 1) {// in case there is one 'x' char in the input string we got
			if (str.indexOf('x') == 0 || (str.indexOf('x') == 1)) {// Checking whether the 'x' char is in the first or
																	// second place in the string
				if (str.indexOf('x') == str.length() - 1) {// if the 'x' char is in the last place of the string and
															// there is only one char before it
					if (str.charAt(0) == '-')// checking if the char before the 'x' is a '-'
						OutPut = new Monom(-1, 1);
					else if (str.charAt(0) == 'x') {// if the first char is 'x'
						OutPut = new Monom(1, 1);
					} else {// if the 'x' is the second char and the last one of this string we'll check if
							// the first char is a double type number - if it is we'll creat a Monom, if not
							// - we'll throw an exception
						boolean isNumber = true;
						try {
							Double.parseDouble("" + str.charAt(0));
						} catch (Exception e) {
							isNumber = false;
						}
						if (isNumber) {
							OutPut = creatMonomWithNoPower(str);
						} else {
							throw new RuntimeException("WRONG INPUT FOR " + "" + str.charAt(0));
						}
					}
				} else if (str.indexOf('^') != (str.indexOf('x') + 1)) {// in case the 'x' char isnt the last char in
																		// the string, we'll start looking for a '^'
																		// char and check whether its logically correct
																		// written
					throw new RuntimeException("POWER HAS TO BE RIGHT AFTER THE 'x' ");
				} else if (str.indexOf('^') == str.length()) {// in case the '^' char hasn't followed by chars
					throw new RuntimeException("POLYNOM CANT END WITH '^' ");
				} else {// in case the '^' char is logically right written - checking if the number
						// after it is an integer type number
					try {
						Integer.parseInt(str.substring(str.indexOf('^') + 1));
					} catch (Exception e) {
						throw new RuntimeException("WRONG VALUE FOR " + str.substring(str.indexOf('^') + 1));
					}
					OutPut = creatMonomWithPower(str);// if it is - creating a Monom with a power
				}
			} else {// in case the 'x' char isnt the first or second char in the string, checking
					// whether the char sequence before it represents a double type number
				try {
					Double.parseDouble(str.substring(0, str.indexOf('x')));
				} catch (Exception e) {
					throw new RuntimeException("WRONG VALUE FOR " + str.substring(0, str.indexOf('x')));
				}
				if (howManyChar(str, '^') == 1) {// checking if we got an '^' char in the string
					if (str.indexOf('^') != (str.indexOf('x') + 1)) {// if we do, checking if it is logically written
						throw new RuntimeException("POWER HAS TO BE RIGHT AFTER THE 'x' ");
					} else if (str.indexOf('^') == str.length()) {
						throw new RuntimeException("POLYNOM CANT END WITH '^' ");
					} else {// if it is - checking if the char sequence after it represents an integer
						try {
							Integer.parseInt(str.substring(str.indexOf('^') + 1));
						} catch (Exception e) {
							throw new RuntimeException("WRONG VALUE FOR " + str.substring(str.indexOf('^') + 1));
						}
						OutPut = creatMonomWithPower(str);// creating a Monom with a power
					}
				} else if (howManyChar(str, '^') == 0) {// if we got no '^' chars
					if (str.indexOf('x') == str.length() - 1)// checking whether the 'x' is the last char
						OutPut = creatMonomWithNoPower(str);// if it is creating an x with no power
				} else
					throw new RuntimeException("NUMBERS AFTER 'x' ARE FOUND ");
			}
		} else if (howManyChar(str, 'x') == 0) {// in case we got no 'x' chars in the string
			try {// checking whether the string represents a double type number
				Double.parseDouble(str);
			} catch (Exception e) {
				throw new RuntimeException("WRONG VALUE FOR " + str);
			}
			OutPut = creatMonomWithNoPower(str);// if it is - creating a Monom which is just a number with no variable
		} else {
			throw new RuntimeException("YOU HAVE TOO MANY 'x' IN THIS STRING TO REPRESENT A MONOM");// in case we got
																									// more than one 'x'
																									// char
		}
		return OutPut;
	}

	// private methods which supprot the Monom String constructor, building Monoms
	// from all kinds based on the logical state which the "innit" method is in
	private static Monom creatMonomWithNoPower(String str) {
		boolean hasVar = false;
		if (str.contains("x"))
			hasVar = true;
		String helpingString = "";
		char[] cArray = str.toCharArray();
		Monom OutPut;
		double coefficient = 0;
		int index = 0;
		char c = cArray[index];
		while (index < cArray.length && c != 'x') {
			helpingString += "" + c;
			index++;
			if (index < cArray.length)
				c = cArray[index];
		}
		coefficient = Double.parseDouble(helpingString);
		if (hasVar)
			OutPut = new Monom(coefficient, 1);
		else
			OutPut = new Monom(coefficient, 0);
		return OutPut;
	}

	private static Monom creatMonomWithPower(String str) {
		String helpingString = "";
		char[] cArray = str.toCharArray();
		Monom OutPut;
		boolean hasVar = true;
		int power = 0;
		double coefficient = 0;
		int index = 0;
		char c = cArray[index];
		while (c != 'x') {
			helpingString += "" + c;
			index++;
			c = cArray[index];
		}
		if (helpingString.equals("-")) {
			helpingString = "-1";
		}
		if (!helpingString.equals("")) {
			coefficient = Double.parseDouble(helpingString);
			helpingString = "";
		} else {
			hasVar = false;
		}
		index += 2;
		c = cArray[index];
		while (index < cArray.length) {
			helpingString += "" + c;
			index++;
			if (index < cArray.length)
				c = cArray[index];
		}
		try {
			power = Integer.parseInt(helpingString);
		} catch (Exception e) {
			throw new RuntimeException("POWER IS NOT INT");
		}
		if (hasVar)
			OutPut = new Monom(coefficient, power);
		else
			OutPut = new Monom(1, power);
		return OutPut;
	}

	// private method that calculates how many of certain char is in a string,
	// supporting the "innit" Monom method
	private static int howManyChar(String str, char c) {
		int sum = 0;
		for (int index = 0; index < str.length(); index++) {
			if (str.charAt(index) == c)
				sum++;
		}
		return sum;
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
