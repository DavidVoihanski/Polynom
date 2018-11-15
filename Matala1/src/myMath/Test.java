package myMath;

//public class Test {
//	/**
//	 * This is a testing Main class for all methods from the Monom & Polynom Classes
//	 * 
//	 * @see belongs originaly to "Matala 0"
//	 * 
//	 * @author Evgeny
//	 *
//	 */
//	public static void main(String[] args) {
//		// Monom class test:
//
//		// testing the String constructor
//		Monom stringTest = new Monom("-5x^2");
//		// testing the toString method
//		System.out.println(stringTest);
//		// testing the f method - will print if the output value is wrong
//		if (stringTest.f(-5.324) != (-5 * (Math.pow(-5.324, 2))))
//			System.out.println("f Monom method is wrong");
//		// testing the f function with zero input
//		System.out.println(stringTest.f(0));
//		// testing the String constructor with zero value
//		Monom stringTestZero = new Monom("0x^88");
//		System.out.println(stringTestZero);
//		// testing the basic number values constructor
//		Monom basicTest = new Monom(4.5, 5);
//		System.out.println(basicTest);// just to make sure this constructor works as it should
//		System.out.print(basicTest + " + " + stringTest + " = ");// testing whether i can do this, and will it change
//																	// Monoms value
//		basicTest.add(stringTest);
//		System.out.println(basicTest);
//		System.out.print(basicTest + " + " + stringTest + " = ");// same test for the subtract method
//		basicTest.subtract(stringTest);
//		System.out.println(basicTest);
//
//		// testing the derivative method:
//		System.out.println("the derivative of " + basicTest + " is: " + basicTest.derivative());
//		System.out.println("the derivative of (zero Monom) " + stringTestZero + " is: " + stringTestZero.derivative());
//
//		// add method tests:
//
//		System.out.print(basicTest + " + " + stringTestZero + " = ");// testing the add method with zero value Monom
//		basicTest.add(stringTestZero);
//		System.out.println(basicTest);
//
//		Monom testNegative = new Monom("-4x^5");
//		System.out.print(basicTest + " + " + testNegative + " = ");// testing the add method with negative value Monom
//		basicTest.add(testNegative);
//		System.out.println(basicTest);
//
//		Monom testNegative2 = new Monom("-8.5x^5");
//		System.out.print(testNegative2 + " + " + testNegative + " = ");// testing the add method with BOTH negative
//																		// value
//																		// Monom
//		testNegative2.add(testNegative);
//		System.out.println(testNegative2);
//
//		Monom testPositive = new Monom("23.33x^5");
//		System.out.print(basicTest + " + " + testPositive + " = ");// testing the add method with positive value Monom
//		basicTest.add(testPositive);
//		System.out.println(basicTest);
//
//		// subtract method tests:
//
//		Monom subTest = new Monom("5.7x^3");
//		System.out.print(subTest + " - " + stringTestZero + " = ");// testing the subtract method with zero value Monom
//		subTest.subtract(stringTestZero);
//		System.out.println(subTest);
//
//		Monom testSubNegative = new Monom("-4x^3");
//		System.out.print(subTest + " - " + testSubNegative + " = ");// testing the subtract method with negative value
//																	// Monom
//		subTest.subtract(testSubNegative);
//		System.out.println(subTest);
//
//		Monom testSubNeative2 = new Monom("-18.333x^3");
//		System.out.print(testSubNeative2 + " - " + testSubNegative + " = ");// testing the subtract method with BOTH
//																			// negative value Monom
//		testSubNeative2.subtract(testSubNegative);
//		System.out.println(testSubNeative2);
//
//		Monom subTest2 = new Monom("3.45x^3");
//		System.out.print(subTest + " - " + subTest2 + " = ");// testing the subtract method with positive value
//																// Monom
//		subTest.subtract(subTest2);
//		System.out.println(subTest);
//
//		// multiply method test:
//
//		System.out.print(subTest + " * " + stringTestZero + " = ");// testing the multiply method with zero value Monom
//		System.out.println(Monom.multiply(subTest, stringTestZero));
//
//		System.out.print(subTest + " * " + testPositive + " = ");// testing the multiply method with positive value
//																	// Monom
//		System.out.println(Monom.multiply(subTest, testPositive));
//
//		System.out.print(subTest + " * " + testNegative + " = ");// testing the multiply method with negative value
//		// Monom
//		System.out.println(Monom.multiply(subTest, testNegative));
//
//		System.out.print(testNegative + " * " + testNegative2 + " = ");// testing the multiply method with BOTH negative
//																		// value
//		// Monom
//		System.out.println(Monom.multiply(testNegative, testNegative2));
//
//		// testing the method on a one value Monom, as you can see the output is indeed
//		// the value of "testPositive"
//		Monom ZeroPowerTest = new Monom("1x^0");
//		System.out.print(ZeroPowerTest + " * " + testPositive + " = ");
//		System.out.println(Monom.multiply(ZeroPowerTest, testPositive));
//
//		// *******************
//		// Polynom class test:
//
//		// testing the string constructor with negative
//		// first element
//		Polynom polynomStringTest = new Polynom("-4x^3-3x^7+9x^3+0x^5");
//		System.out.println(polynomStringTest);
//		// testing the f method on the Polynom
//		if (polynomStringTest.f(-5.6) != (-3 * (Math.pow(-5.6, 7)) + 5 * (Math.pow(-5.6, 3)))) {
//			System.out.println("WRONG CALCULATION");
//		}
//		// add method test
//
//		// testing a polynom with single monom
//		System.out.print(polynomStringTest + " + " + subTest2 + " = ");
//		polynomStringTest.add(subTest2);
//		System.out.println(polynomStringTest);
//
//		// a test with all different powers of all elements
//		Polynom polynomAddTest = new Polynom("-7x^9+4x^6");
//		System.out.print(polynomAddTest + " + " + polynomStringTest + " = ");
//		polynomAddTest.add(polynomStringTest);
//		System.out.println(polynomAddTest);
//
//		// test with similar powers between both Monoms
//		polynomAddTest = new Polynom("-3x^7+8.45x^3");
//		Polynom polynomAddTest2 = new Polynom("-5x^7+2.55x^3");
//		System.out.print(polynomAddTest + " + " + polynomAddTest2 + " = ");
//		polynomAddTest.add(polynomAddTest2);
//		System.out.println(polynomAddTest);
//
//		// subtract method test
//
//		// test with similar Polynom power
//		Polynom polynomSubTest = new Polynom("-13.5x^4+8x^3-4x^2+3x^0");
//		Polynom polynomSubTest2 = new Polynom("-6.5x^4+2x^3-3x^2+1x^0");
//		System.out.print(polynomSubTest + " - (" + polynomSubTest2 + ") = ");
//		polynomSubTest.substract(polynomSubTest2);
//		System.out.println(polynomSubTest);
//
//		// test with all different power
//		polynomSubTest = new Polynom("-2.5x^3+4x^2");
//		polynomSubTest2 = new Polynom("1x^8+5x^4");
//		System.out.print(polynomSubTest + " - (" + polynomSubTest2 + ") = ");
//		polynomSubTest.substract(polynomSubTest2);
//		System.out.println(polynomSubTest);
//
//		// testing the multiply method
//		Polynom polynomMultiTest = new Polynom("1x^0+4.3x^4-2.5x^3");
//		Polynom polynomMultiTest2 = new Polynom("7x^5-3.4x^2+2x^0");
//		System.out.print(polynomMultiTest + " * (" + polynomMultiTest2 + ") = ");
//		polynomMultiTest.multiply(polynomMultiTest2);
//		System.out.println(polynomMultiTest);
//
//		// testing the isZero method
//
//		// classic example, empty polynom
//		Polynom testZero = new Polynom();
//		System.out.println("the polynom " + testZero + " is zero: " + testZero.isZero());
//		// adding a Monom to make it false
//		testZero.add(new Monom("3x^5"));
//		System.out.println("the polynom " + testZero + " is zero: " + testZero.isZero());
//		// subtracting to make it zero again
//		testZero.substract(new Polynom("3x^5"));
//		System.out.println("the polynom " + testZero + " is zero: " + testZero.isZero());
//
//		// testing the equal method & the copy method
//
//		// creating two similar Polynoms
//		Polynom polynomEqualTest = new Polynom("14x^6-3x^7");
//		Polynom polynomEqualTest2 = (Polynom) polynomEqualTest.copy();// we had to cast because the method will return a
//																		// Polynom_able type object
//		System.out.println("dose " + polynomEqualTest + " is equal to " + polynomEqualTest2 + " "
//				+ polynomEqualTest.equals(polynomEqualTest2));
//		// now we'll change one of them using the subtract method
//		polynomEqualTest2.substract(new Polynom("-3x^7"));
//		// and use the method again
//		System.out.println("dose " + polynomEqualTest + " is equal to " + polynomEqualTest2 + " "
//				+ polynomEqualTest.equals(polynomEqualTest2));
//
//		// testing the method on both zero value Polynoms
//		polynomEqualTest = new Polynom("0");
//		polynomEqualTest2 = new Polynom("0");
//		System.out.println("dose " + polynomEqualTest + " is equal to " + polynomEqualTest2 + " "
//				+ polynomEqualTest.equals(polynomEqualTest2));
//
//		// testing the area & root methods
//
//		Polynom rootTest = new Polynom("3x^6-2x^4+3x^2-3x^0");
//		System.out.println(
//				"the root of " + rootTest + "in the interval between -2 to -0.5 is:" + rootTest.root(-2, -0.5, 0.001));
//		// notice that the precise point based on "DESMOS" graphs is -0.927
//		// test on the positive interval
//		System.out.println(
//				"the root of " + rootTest + "in the interval between 0.5 to 2 is:" + rootTest.root(0.5, 2, 0.001));
//		// notice that these points are symmetric so the precise point is 0.927
//
//		// testing the area method on the same function
//		System.out.println("the area above x- axis of " + rootTest + "in the interval between 2 to 4 is:"
//				+ rootTest.area(2, 4, 0.001));
//		// the exact area based on the "wolframalpha" calculation is 6620.1 }
//
//	}
//}
