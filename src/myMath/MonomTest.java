package myMath;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

/**
 * This is a JUnit testing case for the Monom class, tests every public method the
 * class has
 * 
 * @author Evgeny
 *
 */
class MonomTest {
	private Monom tester;// setting private Monom to use in all tests

	/**
	 * testing Monoms (double,int) constructor
	 */
	@Test
	void testMonomDoubleInt() {// testing basic Monom constructor
		tester = new Monom(3, 4);
		if (tester.get_power() != 4 || tester.get_coefficient() != 3) {// testing it with non zero positive integers
			fail("Monoms basic constructor with non zero integers DOSENT WORK!");
		}
		// testing the constructor on negative power
		boolean didThrew = false;// this will indicate whether the constructor threw an RuntimeException
		try {
			tester = new Monom(0, -3);// setting up new Monom with a negative power
		} catch (RuntimeException e) {// catching the RuntimeException which should be thrown
			didThrew = true;// if the constructor did threw an exception - it means it works well
		}
		if (!didThrew) {// if the constructor hasn't thrown an exception - we have a problem so we use
						// "fail"
			fail("HAVENT THROWN AN EXCEPTION WITH NEGATIVE POWER OF MONOM!");
		}
	}
/**
 * testing Monoms copy constructor
 */
	@Test
	void testMonomMonom() {
		Monom tester2 = new Monom(3, 3);// setting up a new Monom
		tester = new Monom(tester2);// using the copy constructor to creat a copy of it
		if (tester.get_power() != tester2.get_power() || tester.get_coefficient() != tester2.get_coefficient()) {
			fail("A PROBLEM IN THE COPY CONSTRUCTOR WITH NON NULL MONOM");
		}
		// testing the copy constructor in case of trying to copy a null Monom
		tester2 = null;// setting a null Monom
		boolean didThrew = false;// this will indicate whether the constructor threw an RuntimeException
		try {// trying to copy construct it
			tester = new Monom(tester2);
		} catch (RuntimeException e) {
			didThrew = true;// if the constructor did threw an exception - it means it works well
		}
		if (!didThrew) {// if the constructor hasn't thrown an exception - we have a problem so we use
			// "fail"
			fail("HAVENT THROWN AN EXCEPTION WITH NULL MONOM!");
		}
	}
/**
 * testing Monoms String constructor
 */
	@Test
	void testMonomString() {
		tester = null;
		boolean didThrew = false;// this will indicate whether these strings threw an exception
		// these strings SHOULD all throw an exception
		String[] testIfThorws = { "asfadfe", "4234jkj", "7xx^4", "xyyz", "3xx", "3x^-3", "2x^avc", "#$%#$FDSFDFSD",
				"3x^3.5", "3x^5+7" };
		for (String str : testIfThorws) {
			try {
				tester = new Monom(str);
			} catch (RuntimeException e) {
				didThrew = true;// in case this string indeed threw an exception
			}
			if (!didThrew)// if it didn't - the test failed
				fail("STRING CONTRUCTOR WILL WORK WITH " + str + " INPUT");
			didThrew = false;
		}
		// these strings should become a Monom as they logically correct
		String[] testIfWorks = { "33x^3", "43", "-56.43", "34.67x", "0", "+4x^2", "-34.67x", "x^3", "x", "7X",
				"7X^23" };
		for (String str : testIfWorks) {
			try {
				tester = new Monom(str);
			} catch (Exception e) {
				fail("STRING CONTRUCTOR WONT WORK WITH " + str + " " + "err: " + e);
			}
		}
		// testing whether the constructor created correct valued Monoms
		tester = new Monom("-44.5x^23");
		if (tester.get_coefficient() != (-44.5) || tester.get_power() != 23)
			fail("VALUES ARE NOT RIGHT IN THE STRING CONSTURCTOR " + tester);
		tester = new Monom("-44.5");
		if (tester.get_coefficient() != (-44.5) || tester.get_power() != 0)
			fail("VALUES ARE NOT RIGHT IN THE STRING CONSTURCTOR" + tester);
		tester = new Monom("x^76");
		if (tester.get_coefficient() != 1 || tester.get_power() != 76)
			fail("VALUES ARE NOT RIGHT IN THE STRING CONSTURCTOR" + tester);
		tester = new Monom("-x^56");
		if (tester.get_coefficient() != (-1) || tester.get_power() != 56)
			fail("VALUES ARE NOT RIGHT IN THE STRING CONSTURCTOR" + tester);
	}
/**
 * Testing Monoms f method
 */
	@Test
	void testF() {
		// testing this method by comparing the value of the output of the method to the
		// expected numeric value of the expression
		tester = new Monom("3x^4");
		if (tester.f(-34) != (3 * Math.pow((-34), 4)))
			fail("f function dosent return right value, " + tester + " with (-34) x value");
		if (tester.f(115) != (3 * Math.pow((115), 4)))
			fail("f function dosent return right value, " + tester + " with (115) x value");
		tester = new Monom("x^42");
		if (tester.f(-34) != Math.pow((-34), 42))
			fail("f function dosent return right value, " + tester + " with (-34) x value");
		tester = new Monom("x");
		if (tester.f(-34) != Math.pow((-34), 1))
			fail("f function dosent return right value, " + tester + " with (-34) x value");

	}
/**
 * testing Monoms get_power method
 */
	@Test
	void testGet_power() {
		// testing getPower method with different kinds of Monoms
		tester = new Monom("-45x^4");
		if (tester.get_power() != 4)
			fail("wrong value for " + tester + " using getPower method ");
		tester = new Monom("x^66");
		if (tester.get_power() != 66)
			fail("wrong value for " + tester + " using getPower method ");
		tester = new Monom("-55");
		if (tester.get_power() != 0)
			fail("wrong value for " + tester + " using getPower method ");
		tester = new Monom("-3x");
		if (tester.get_power() != 1)
			fail("wrong value for " + tester + " using getPower method ");
	}
/**
 * testing Monoms get_coefficietn method
 */
	@Test
	void testGet_coefficient() {
		// testing getCoefficient method with different kinds of Monoms
		tester = new Monom("-45x^4");
		if (tester.get_coefficient() != -45)
			fail("wrong value for " + tester + " using getCoefficient method ");
		tester = new Monom("x^66");
		if (tester.get_coefficient() != 1)
			fail("wrong value for " + tester + " using getCoefficient method ");
		tester = new Monom("-55");
		if (tester.get_coefficient() != -55)
			fail("wrong value for " + tester + " using getCoefficient method ");
		tester = new Monom("-3x");
		if (tester.get_coefficient() != -3)
			fail("wrong value for " + tester + " using getCoefficient method ");
	}
/**
 * testing Monoms derivative method
 */
	@Test
	void testDerivative() {
		// testing the Derivative method by comparing the output of the method to the
		// expected numeric value of the output Monom
		tester = new Monom("-45x^4");
		if (tester.derivative().get_coefficient() != -180 || tester.derivative().get_power() != 3)
			fail("wrong value for " + tester + " using Derivative method ");
		tester = new Monom("x^66");
		if (tester.derivative().get_coefficient() != 66 || tester.derivative().get_power() != 65)
			fail("wrong value for " + tester + " using Derivative method ");
		tester = new Monom("-55");
		if (tester.derivative().get_coefficient() != 0 || tester.derivative().get_power() != 0)
			fail("wrong value for " + tester + " using Derivative method ");
		tester = new Monom("-3x");
		if (tester.derivative().get_coefficient() != -3 || tester.derivative().get_power() != 0)
			fail("wrong value for " + tester + " using Derivative method ");
	}
/**
 * testing Monoms add method
 */
	@Test
	void testAdd() {
		// testing negative Monom with positive Monom
		tester = new Monom("-45x^4");
		Monom tester2 = new Monom("45x^4");
		tester.add(tester2);
		if (tester.get_coefficient() != 0)
			fail("wrong value for " + tester + " " + tester2 + " using add method ");
		// testing both negative Monom
		tester = new Monom("-45x^4");
		tester2 = new Monom("-45x^4");
		tester.add(tester2);
		if (tester.get_coefficient() != -90 || tester.get_power() != 4)
			fail("wrong value for " + tester + " " + tester2 + " using add method ");
		// testing both positive
		tester = new Monom("45x^4");
		tester2 = new Monom("45x^4");
		tester.add(tester2);
		if (tester.get_coefficient() != 90 || tester.get_power() != 4)
			fail("wrong value for " + tester + " " + tester2 + " using add method ");
		// testing with zero
		tester = new Monom("45x^4");
		tester2 = new Monom("0");
		tester.add(tester2);
		if (tester.get_coefficient() != 45 || tester.get_power() != 4)
			fail("wrong value for " + tester + " " + tester2 + " using add method ");
	}
/**
 * testing Monoms multiply static method
 */
	@Test
	void testMultiply() {
		// testing negative Monom with positive Monom
		tester = new Monom("-3x^4");
		Monom tester2 = new Monom("3x^4");
		if (Monom.multiply(tester, tester2).get_coefficient() != -9 || Monom.multiply(tester, tester2).get_power() != 8)
			fail("wrong value for " + tester + " " + tester2 + " using multiply method ");
		// testing both negative Monom
		tester = new Monom("-4x^4");
		tester2 = new Monom("-5x^4");
		if (Monom.multiply(tester, tester2).get_coefficient() != 20 || Monom.multiply(tester, tester2).get_power() != 8)
			fail("wrong value for " + tester + " " + tester2 + " using multiply method ");
		// testing both positive
		tester = new Monom("5x^4");
		tester2 = new Monom("4x^4");
		if (Monom.multiply(tester, tester2).get_coefficient() != 20 || Monom.multiply(tester, tester2).get_power() != 8)
			fail("wrong value for " + tester + " " + tester2 + " using multiply method ");
		// testing with zero
		tester = new Monom("45x^4");
		tester2 = new Monom("0");
		if (Monom.multiply(tester, tester2).get_coefficient() != 0)
			fail("wrong value for " + tester + " " + tester2 + " using multiply method ");
		// testing with one
		tester = new Monom("45x^4");
		tester2 = new Monom("1");
		if (Monom.multiply(tester, tester2).get_coefficient() != 45 || Monom.multiply(tester, tester2).get_power() != 4)
			fail("wrong value for " + tester + " " + tester2 + " using multiply method ");
	}
/**
 * testong Monoms subtract method
 */
	@Test
	void testSubtract() {
		// testing negative Monom with positive Monom
		tester = new Monom("-45x^4");
		Monom tester2 = new Monom("45x^4");
		tester.subtract(tester2);
		if (tester.get_coefficient() != -90)
			fail("wrong value for " + tester + " " + tester2 + " using subtract method");
		// testing both negative Monom
		tester = new Monom("-45x^4");
		tester2 = new Monom("-45x^4");
		tester.subtract(tester2);
		if (tester.get_coefficient() != 0)
			fail("wrong value for " + tester + " " + tester2 + " using subtract method ");
		// testing both positive
		tester = new Monom("45x^4");
		tester2 = new Monom("45x^4");
		tester.subtract(tester2);
		if (tester.get_coefficient() != 0)
			fail("wrong value for " + tester + " " + tester2 + " using subtract method ");
		// testing with zero
		tester = new Monom("45x^4");
		tester2 = new Monom("0");
		tester.subtract(tester2);
		if (tester.get_coefficient() != 45)
			fail("wrong value for " + tester + " " + tester2 + " using subtract method ");
	}
/**
 * testing Monoms toString method
 */
	@Test
	void testToString() {
		// testing the toString method by comparing the String output to the expected
		// string representation of the Monom
		tester = new Monom("-45x^4");
		if (!tester.toString().equals("-45.0x^4"))
			fail("wrong value for " + tester + " using toString method ");
		tester = new Monom("-45x");
		if (!tester.toString().equals("-45.0x"))
			fail("wrong value for " + tester + " using toString method ");
		tester = new Monom("-45x^1");
		if (!tester.toString().equals("-45.0x"))
			fail("wrong value for " + tester + " using toString method ");
		tester = new Monom("-45");
		if (!tester.toString().equals("-45.0"))
			fail("wrong value for " + tester + " using toString method ");
		tester = new Monom("-0x^773");
		if (!tester.toString().equals("0"))
			fail("wrong value for " + tester + " using toString method ");
	}

}
