package myMath;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class PolynomTest {
	/**
	 * Tests Polynom f(x) function
	 */
	@Test
	void testF() {
		Polynom p=new Polynom("1x^2+3x^1");
		double result=p.f(5);
		assertEquals(40, result);
	}
	/**
	 * Tests the isZero() function
	 */
	@Test
	void testIsZero() {

		Polynom zeroP=new Polynom("0x^1");  //creates 0 constant
		Polynom oneP=new Polynom("1x^0");   //creates 1 constant
		boolean positive=zeroP.isZero();   //should be positive   
		boolean negative=oneP.isZero();     //should be negative
		if(negative||!positive) {
			fail("isZero() function failed");	//if positive or negative have the wrong value the function failed
		}

	}
	/**
	 * Tests equals() function
	 */
	@Test
	void testEquals() {

		Polynom equal1=new Polynom("1x^2-5x^1");// 2 equal polynoms
		Polynom equal2=new Polynom("1x^2-5x^1");
		Polynom diff=new Polynom("1x^2+5x^1");  //one different polynom
		boolean positive=equal1.equals(equal2);	//should be positive
		boolean negative=equal1.equals(diff);  //should be negative
		if(negative||!positive) {
			fail("Equals() function failed");	//if positive or negative have the wrong value the function failed
		}

	}
	/**
	 * tests the add(Polynom p) function that adds 2 polynoms together
	 * uses equals() that we tested before 
	 */
	@Test
	void testAddPolynom(){

		Polynom expected=new Polynom("1x^2+3x^3");
		Polynom result=new Polynom("1x^2");
		Polynom toAdd=new Polynom("3x^3");
		result.add(toAdd);

		if(!expected.equals(result))fail("add(Polynom p) function failed");
	}
	/**
	 * Tests the add(Monom m) function
	 * uses equals() function that we tested before
	 */
	@Test
	void testAddMonom() {
		
		Monom toAdd=new Monom("-3x^2");
		Polynom result=new Polynom("4x^4-5x^2");
		Polynom expected=new Polynom("4x^4-8x^2");
		result.add(toAdd);
		if(!expected.equals(result)) {
			fail("add(Monom m) function failed");
		}
	}
	
	/**
	 * tests subtract() function
	 * uses equals() function that we tested before
	 */
	@Test
	void TestSubtract() {
		
		Polynom expected=new Polynom("1x^1-5x^0"); //expected result is 1x-5
		Polynom result=new Polynom("2x^1+3x^0");
		Polynom toBeSub=new Polynom("1x^1+8x^0");
		result.substract(toBeSub);
		if(!expected.equals(result)) {				
			fail("subtract() function failed");
		}
		
	}
	/**
	 * test multiply(Polynom p) function
	 * uses equals() function that we tested before
	 */
	@Test
	void testMultiply() {
		
		Polynom expected=new Polynom("1x^2-1x^0");  //x^2-1
		Polynom result=new Polynom("1x^1+1x^0");  	//x+1
		Polynom toMult=new Polynom("1x^1-1x^0");	//x-1
		result.multiply(toMult);
		if(!expected.equals(result))fail("multiply(Polynom p) failed");
		
	}
	/**
	 * tests the 
	 */
	@Test
	void testRoot() {
		
	Polynom zeroR=new Polynom("1x^3");    //Polynom with root 0
	double eps=0.001;					  //allowed difference
	double result=zeroR.root(-1, 1, eps);
	if(result<=0-eps||result>=0+eps)fail("root() function failed"); //if the result is outside the 0+eps>x>0-eps 
																	//range, the function failed 
	}
	
	/**
	 * tests the derivative() function
	 * uses equals() function that we tested before
	 */
	@Test
	void testDerivative() { 
		
		Polynom expected=new Polynom("3x^2+1x^0");
		Polynom toDer=new Polynom("1x^3+1x^1");
		Polynom_able result=toDer.derivative();
		if(!expected.equals(result))fail("the derivative() function failed");
		
	}
	
	/**
	 * tests the area() function
	 */
	@Test
	void testArea() {
		
		Polynom test=new Polynom("1x^3");
		double expected=0.25;
		double eps=0.001;
		double result=test.area(-1, 1,eps);
		if(result>=expected+eps||result<=expected-eps)fail("The area() function failed");
		//checks if the result is in the expected+eps<=x<=expected-eps range
		
	}
	/**
	 * tests the copy() function
	 * uses equals() function that we tested before
	 */
	@Test
	void testCopy() {
		
		Polynom origin=new Polynom("1x^2-5x^3");
		Polynom_able copy=origin.copy();
		if(!origin.equals(copy))fail("copy() function failed");
		
	}
	
}
