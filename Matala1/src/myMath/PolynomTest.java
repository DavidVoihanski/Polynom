package myMath;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Iterator;

import org.junit.jupiter.api.Test;

/**
 * This is a JUnit testing case for the Polynom class, tests every public method
 * the class has
 * 
 * NOTICE:most of Polynoms methods are based on the Monom methods which were
 * already tested very seriously, so this JUnit is somewhat shorter then Monoms
 * JUnit
 * 
 * @author David
 *
 */
class PolynomTest {
	/**
	 * tests the String constructor of Polynom class
	 */
	@Test
	void testPolynomString() {
		Polynom testCon;
		boolean hasFailed = false;// this will indicate whether the constructor threw an exception
		String[] shouldNotWork = { "abc+7x^3", "abdjsa", "++^&^&#", "7^4", "x^-7" };// these Strings shouldn't work
		for (String str : shouldNotWork) {
			try {
				testCon = new Polynom(str);// trying to create the Polynom with this incorrect Strings
			} catch (RuntimeException e) {/// hoping to catch an runtime exception (which the constructor should throw)
				hasFailed = true;
			}
			if (!hasFailed) {// failing the test in case we haven't managed to catch the exception
				fail("String constructor WILL work for " + str);
			}
			hasFailed = false;
		}
		hasFailed = false;// resting the hasFailed boolean variable
		String[] shouldWork = { "x^5+7x^3+5-7", "0.5x^77+0.6x^34-66", "400-x", "7.77x^65+x-x^77", "6+7-3" };// these
																											// Strings
																											// should
																											// work
		for (String str : shouldWork) {
			try {
				testCon = new Polynom(str);
			} catch (Exception e) {
				hasFailed = true;
			}
			if (hasFailed) {// in case one of these String made the constructor throw an exception, we will
							// fail the test
				fail("String constructor WONT work for " + str);
				hasFailed = false;
			}
		}
	}

	/**
	 * Tests Polynom f(x) function
	 */
	@Test
	void testF() {
		Polynom p = new Polynom("1x^2+3x^1");
		double result = p.f(5);
		assertEquals(40, result);
	}

	/**
	 * Tests the isZero() function
	 */
	@Test
	void testIsZero() {

		Polynom zeroP = new Polynom("0x^1"); // creates 0 constant
		Polynom oneP = new Polynom("1x^0"); // creates 1 constant
		boolean positive = zeroP.isZero(); // should be positive
		boolean negative = oneP.isZero(); // should be negative
		if (negative || !positive) {
			fail("isZero() function failed"); // if positive or negative have the wrong value the function failed
		}

	}

	/**
	 * Tests equals() function
	 */
	@Test
	void testEquals() {

		Polynom equal1 = new Polynom("1x^2-5x^1");// 2 equal polynoms
		Polynom equal2 = new Polynom("1x^2-5x^1");
		Polynom diff = new Polynom("1x^2+5x^1"); // one different polynom
		boolean positive = equal1.equals(equal2); // should be positive
		boolean negative = equal1.equals(diff); // should be negative
		if (negative || !positive) {
			fail("Equals() function failed"); // if positive or negative have the wrong value the function failed
		}

	}

	/**
	 * tests the add(Polynom p) function that adds 2 polynoms together uses equals()
	 * that we tested before
	 */
	@Test
	void testAddPolynom() {

		Polynom expected = new Polynom("5x^4-4x^2");// this is the expected sum of both these Polynoms
		Polynom p = new Polynom("5x^4+3x^3-x^2");
		Polynom toAdd = new Polynom("-3x^3-3x^2");
		p.add(toAdd);

		if (!expected.equals(p))
			fail("add(Polynom p) function failed");
	}

	/**
	 * Tests the add(Monom m) function uses equals() function that we tested before
	 */
	@Test
	void testAddMonom() {

		Monom toAdd = new Monom("-3x^2");// creating a Monom which will be added
		Polynom p = new Polynom("4x^4-5x^2");// adding the Monom to this Polynom
		Polynom expected = new Polynom("4x^4-8x^2");// expected output, 4x^4-8x^2-3x^2= 4x^4-8x^2
		p.add(toAdd);
		if (!expected.equals(p)) {
			fail("add(Monom m) function failed");
		}
	}

	/**
	 * tests subtract() function uses equals() function that we tested before
	 */
	@Test
	void TestSubtract() {

		Polynom expected = new Polynom("x-5"); // expected result of subtracting next Polynoms is 1x-5
		Polynom result = new Polynom("2x+3");
		Polynom toBeSub = new Polynom("x+8");
		result.substract(toBeSub);// 2x+3-x-8 is expected =x-5
		if (!expected.equals(result)) {
			fail("subtract() function failed");
		}

	}

	/**
	 * test multiply(Polynom p) function uses equals() function that we tested
	 * before
	 */
	@Test
	void testMultiply() {

		Polynom expected = new Polynom("x^2-1"); // x^2-1, which is the product of both these polynoms
		Polynom result = new Polynom("x+1"); // x+1
		Polynom toMult = new Polynom("x-1"); // x-1
		result.multiply(toMult);// (x+1)*(x-1) is expected =>x^2-1
		if (!expected.equals(result))
			fail("multiply(Polynom p) failed");

	}

	/**
	 * tests the root function
	 */
	@Test
	void testRoot() {

		Polynom zeroR = new Polynom("1x^3"); // Polynom with root 0
		double eps = 0.001; // allowed difference
		double result = zeroR.root(-1, 1, eps);
		if (result <= 0 - eps || result >= 0 + eps)
			fail("root() function failed"); // if the result is outside the 0+eps>x>0-eps
											// range, the function failed
	}

	/**
	 * tests the derivative() function uses equals() function that we tested before
	 */
	@Test
	void testDerivative() {

		Polynom expected = new Polynom("3x^2+1");
		Polynom toDer = new Polynom("x^3+x");// manually creating the expected derivative of the polynom function
		Polynom_able result = toDer.derivative();
		if (!expected.equals(result))// comparing the expected to the real output
			fail("the derivative() function failed");

	}

	/**
	 * tests the area() function
	 */
	@Test
	void testArea() {

		Polynom test = new Polynom("x^3");
		double expected = 0.25;// expected area which should be the output
		double eps = 0.001;
		double result = test.area(-1, 1, 0.0001);
		if (result > expected + eps || result < expected - eps)
			fail("The area() function failed");
		// checks if the result is in the expected+eps<=x<=expected-eps range

	}

	/**
	 * tests the negArea function, which is symmetric to the area function, only
	 * calculating the area below x- axis instead of above
	 */
	@Test
	void testNegArea() {
		Polynom test = new Polynom("x^3");
		double expected = 4;
		double eps = 0.001;
		double result=test.negArea(-2, 2, 0.0001);
		if (result > expected + eps || result < expected - eps)
			fail("the negArea method failed with " + test + "returned " + test.negArea(-2, 0, 0.01) + "insted of "
					+ expected);
	}


	/**
	 * tests the copy() function uses equals() function that we tested before
	 */
	@Test
	void testCopy() {

		Polynom origin = new Polynom("x^2-5x^3");
		Polynom_able copy = origin.copy();
		if (!origin.equals(copy))
			fail("copy() function failed");

	}

	/**
	 * tests the toString method
	 */
	@Test
	void testToString() {
		// creating different Polynoms and checking whether their toString output suits
		// out expectation
		Polynom test = new Polynom("34x^77-8x^6+3");
		if (!test.toString().equals("34.0x^77-8.0x^6+3.0"))
			fail("toString WONT work for " + test);
		test = new Polynom("23.6x^77-x^6");
		if (!test.toString().equals("23.6x^77-x^6"))
			fail("toString WONT work for " + test);
		test = new Polynom("23.6+0");
		if (!test.toString().equals("23.6"))
			fail("toString WONT work for " + test);

	}

	/**
	 * tests the findMinMax method
	 */
	@Test
	void testFindMinMax() {
		Polynom test = new Polynom("x^2");
		ArrayList<Point> points = test.findMinMax(-2, 2);// we will use the [-2,2] range for all tests
		Iterator<Point> it = points.iterator();
		while (it.hasNext()) {
			Point current = it.next();
			if (current.isMax() || current.isMin()) {// if this point is local max/min, checking if its derivative is
														// close enough to zero (it wont be actual zero as the point is
														// only approximately accurate)
				if (test.derivative().f(current.getX()) > 0.001)
					fail("the point " + current + " is set to be local extremum but isn't!");
			} // we use the ELSE here for the same reason, if the local extremum is a global
				// too, because the values are only approximately accurate we can't really check
				// it, but if a local extremum is a global one too - thats trivial that it is
				// the lowest/highest point
			else if (current.isGlobalMax()) {
				for (double x0 = -2; x0 < 2; x0 += 0.01) {// going through approximately all points to check whether
															// there is higher/ lower (respectively) point
					if (test.f(x0) > current.getY())
						fail("the point " + current + " is set to be global max but there is a point higher: " + x0);
				}
			} else if (current.isGlobalMin()) {
				for (double x0 = -2; x0 <= 2; x0 += 0.01) {
					if (test.f(x0) < current.getY())
						fail("the point " + current + " is set to be global min but there is a point lower: " + x0);
				}
			}
		}
		// doing all the same for "x^3" function, which has no extremum points
		test = new Polynom("x^3");
		points = test.findMinMax(-2, 2);
		it = points.iterator();
		while (it.hasNext()) {
			Point current = it.next();
			if (current.isMax() || current.isMin()) {
				if (test.derivative().f(current.getX()) > 0.001)
					fail("the point " + current + " is set to be local extremum but isn't!");
			} else if (current.isGlobalMax()) {
				for (double x0 = -2; x0 < 2; x0 += 0.01) {
					if (test.f(x0) > current.getY())
						fail("the point " + current + " is set to be global max but there is a point higher: " + x0);
				}
			} else if (current.isGlobalMin()) {
				for (double x0 = -2; x0 <= 2; x0 += 0.01) {
					if (test.f(x0) < current.getY())
						fail("the point " + current + " is set to be global min but there is a point lower: " + x0);
				}
			}
		}
	}
}
