package myMath;

import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;

/**
 * This class represents a Polynom with add, multiply functionality, it also
 * should support the following: 1. Riemann's Integral:
 * https://en.wikipedia.org/wiki/Riemann_integral 2. Finding a numerical value
 * between two values (currently support root only f(x)=0). 3. Derivative
 * 
 * @author Evgeny&David
 *
 */
public class Polynom implements Polynom_able {

	// ********** add your code below ***********
	//////////////////////////// public Polynom class methods:
	/**
	 * the data structure used in this class to represent the Monom list is the
	 * ArrayList
	 */
	private ArrayList<Monom> polynom;

	/**
	 * empty constructor which only allocates LinkedList type memory to the Polynom
	 * pointer
	 */
	public Polynom() {
		this.polynom = new ArrayList<>();
	}

	/**
	 * a constructor which input is a String, this method creates a new Polynom
	 * which values determined by the String input
	 * 
	 * 
	 *** NOTE: this constructor works only on "ax^b+/-...." type of expression, where
	 * a is a double type variable, and b is a NON NEGATIVE integer,this constructor
	 * WILL NOT work for any non logically correct string other then the empty one,
	 * in this case this constructor will create a zero value Polynom, for more
	 * information please check the "readMe" file
	 * 
	 * @param str
	 *            String input which represents the soon to be Polynom
	 */
	public Polynom(String str) throws RuntimeException {// string Ploynom constructor supported by the Monom class
														// string constructor
		if (str.equals("0") || str.equals("")) {// in case the string is just "0" or the empty one
			this.polynom = new ArrayList<>();
			return;
		}
		this.polynom = new ArrayList<>();
		str = str.toLowerCase();// making sure the string is in all small letters
		Polynom newPolynom = initStringPolynom(str);// sending the string to a supporting private method which will //
		// create a new Polynom
		Iterator<Monom> it = newPolynom.iteretor();
		while (it.hasNext()) {
			Monom current = it.next();
			this.polynom.add(current);
		}
		Comparator<Monom> cmp = new Monom_Comperator();
		this.polynom.sort(cmp);// sorting it
		this.fixUp();// in case one of these Monoms is zero we'll remove it, as it
		// has no value
	}

	/**
	 * calculates Polynoms y value for a certain given x
	 * 
	 * @param x
	 *            a double type variable which represents the point on the x - axis
	 *            we want to calculate the y value for
	 * @return summed values of all the Monoms at this x value this Polynom contains
	 */
	@Override
	public double f(double x) {
		double returnedValue = 0;
		Iterator<Monom> it = this.iteretor();
		while (it.hasNext()) {
			Monom runningMonom = it.next();
			returnedValue += runningMonom.f(x);// summing every Monom value in this given point on the x axis
		}
		return (returnedValue);
	}

	/**
	 * sums two Polynoms, actually it changes the Polynom which the method is used
	 * on to the values of the sum
	 * 
	 * @param p1
	 *            input Polynom_able type object which should be be summed with the
	 *            Polynom this method is used on
	 */
	@Override
	public void add(Polynom_able p1) {
		Iterator<Monom> it = p1.iteretor();
		while (it.hasNext()) {
			Monom runningMonom = it.next();
			this.add(runningMonom);// using the private "add" method on every monom in the list
		}
		this.fixUp();// in case there are zero Monoms in the list
	}

	/**
	 * sums a Polynom and a Monom, actually it changes the Polynom which the method
	 * is used on to the values of the sum
	 * 
	 * @param m1
	 *            Monom type object which should be summed with the Polynom
	 */
	@Override
	public void add(Monom m1) {
		Iterator<Monom> it = this.iteretor();
		boolean isAdded = false;
		while (it.hasNext() && !isAdded) {// as long as the Monom isn't added yet, and there are more Monoms in the list
			// to check with
			Monom runningMonom = it.next();
			if (runningMonom.get_power() == m1.get_power()) {// we will sum both Monoms only if their powers are equal
				runningMonom.add(m1);// using Monoms "add" method
				isAdded = true;// this will make the while stop
			}
		}
		if (!isAdded) {// in case we haven't found similar power as the input, we'll just add it as
			// another element and sort the arrayList as asked
			polynom.add(m1);
			Comparator<Monom> cmpMonoms = new Monom_Comperator();
			polynom.sort(cmpMonoms);
		}
		this.fixUp();// in case this method somehow made some Monom zero valued, we remove them
	}

	/**
	 * Multiples two Monoms,actually it changes the Polynom which the method is used
	 * on to the values of the product
	 * 
	 * @param p1
	 *            input Polynom_able type object which sould be multiplied with the
	 *            Polynom which the method is used on
	 */
	@Override
	public void multiply(Polynom_able p1) {
		Polynom outPut = new Polynom();// creating new Polynom which will "host" the multiply between pairs of Monoms,
		// we do this to prevent double multiplying
		Iterator<Monom> it = this.iteretor();
		Iterator<Monom> it2 = p1.iteretor();
		while (it2.hasNext()) {
			Monom multiplier2 = it2.next();
			while (it.hasNext()) {
				Monom multiplier = it.next();
				outPut.add(Monom.multiply(multiplier, multiplier2));// adding to this helping Polynom the products of
				// all Monoms multiplied by every Monom in the input
				// Polynom
			}
			it = this.iteretor();
		}
		this.polynom.clear();// using the "clear" method to make the Polynom which the method was used on
		// empty, now we'll add all the products which we got in outPut to this Polynom,
		// as we want this Polynom values to be the product between all Monoms
		Iterator<Monom> it3 = outPut.iteretor();
		while (it3.hasNext()) {
			Monom result = it3.next();
			this.polynom.add(result);
		}
		this.fixUp();// in case this method somehow made some Monom zero valued, we remove them
	}

	/**
	 * checks whether two Polynoms are logically equal
	 * 
	 * @param p1
	 *            input Polynom_able type object which should be compared to the
	 *            Polynom which the method is used on
	 * @return true if both objects are equal, false if not
	 */
	@Override
	public boolean equals(Polynom_able p1) {
		boolean isEqual = true;// this boolean variable starts as "true" but in case we'll find two not similar
		// Monoms we'll change it to false, this is our output value
		Iterator<Monom> it = this.iteretor();
		Iterator<Monom> it2 = p1.iteretor();
		while (it.hasNext() && it2.hasNext() && isEqual) {// as long as there are Monoms to keep comparing, and as long
			// we haven't found any Monoms which are not similar
			Monom firstPoly = it.next();
			Monom secPoly = it2.next();
			if (firstPoly.get_coefficient() != secPoly.get_coefficient()
					|| firstPoly.get_power() != secPoly.get_power())
				isEqual = false;
		}
		if ((it.hasNext() || it2.hasNext()) && isEqual)// if we havent found any different Monoms in both lists, but one
			// of the lists has more elements, these Polynoms isn't equal
			isEqual = false;
		return isEqual;
	}

	/**
	 * checks whether given Polynom is the zero Polynom
	 * 
	 * @return the boolean value of whether the list is empty or not
	 */
	@Override
	public boolean isZero() {
		if (this.polynom.isEmpty()) // an empty Polynom is a zero one, as we use the "fixUp" method this is the only
			// option to get a zero Polynom
			return true;
		return false;
	}

	/**
	 * returns the root of given Polynom in a given range (to the left of x0 and to
	 * the right of x1) using Bisection method
	 * 
	 * NOTE: if (x0>=x1)===> this will throw an exception as logically the method
	 * CANT HANDLE such a range
	 * 
	 * @param x0
	 *            double type variable, the start of the range in which the root
	 *            should be found
	 * @param x1
	 *            double type variable, the end of the range in which the root
	 *            should be found
	 * @param eps
	 *            double type variable, the amount of deviation allowed,ASSUMED to
	 *            be 0.01 or smaller, PLEASE NOTICE that a large eps will return not
	 *            accurate area
	 * 
	 * @return the double value of the point on the x axis which returns zero y
	 * 
	 *         NOTE: Took the algorithm from
	 *         https://en.wikipedia.org/wiki/Bisection_method
	 */
	@Override
	public double root(double x0, double x1, double eps) throws RuntimeException {
		double middle = 0;

		if (x0 > x1) { // swaps
			System.out.println("x0 has bigger vaule than x1, values SWAPPED");
			double temp = x0;
			x0 = x1;
			x1 = temp;

		}
		if (this.f(x0) * this.f(x1) > 0)
			throw new RuntimeException("ERR: NO ROOT IN GIVEN RANGE!");// in this case we don't know how to handle the
																		// situation
		while (x1 - x0 > eps) { // if the difference is bigger than eps
			middle = (x0 + x1) / 2.0; // calculates mid point
			if (f(x0) * f(middle) > 0)
				x0 = middle; // ignore the left side
			else
				x1 = middle; // ignore the right side
		}
		return x1; // returns the root in the given range
	}

	/**
	 * creates a deep copy of the Polynom on which been used on
	 *
	 * @return a Polynom_able type object which is a deep copy of the Polynom the
	 *         method was used on
	 */
	@Override
	public Polynom_able copy() {
		Iterator<Monom> it = this.iteretor();
		Polynom outPut = new Polynom();// as we want to create a DEEP copy, we should create a new Polynom_able
		// type object to return
		while (it.hasNext()) {// copying all Monoms from the Polynom which should be copied to the outPut
			Monom runningMonom = it.next();
			Monom deepCopyMonom = new Monom(runningMonom);// creating a NEW Monom object, as the request is a DEEP copy
			outPut.add(deepCopyMonom);
		}
		return outPut;
	}

	/**
	 * returns the derivative of the Polynom on which was used on
	 * 
	 * @return new Polynom_able type object which represents original Polynoms
	 *         derivative
	 */
	@Override
	public Polynom_able derivative() {
		Iterator<Monom> it = this.iteretor();
		Polynom outPut = new Polynom();
		while (it.hasNext()) {
			Monom runningMonom = it.next();
			outPut.add(runningMonom.derivative());// using the Monom derivative method which returns a derivative for
			// every Monom
			// in the list, adding them all - thats the Polynoms derivative
		}
		return outPut;
	}

	/**
	 * sets up a basic iterator for the Polynom class
	 * 
	 * @return new Iterator for the Polynom class
	 */
	@Override
	public Iterator<Monom> iteretor() {
		Iterator<Monom> it = polynom.iterator();
		return it;
	}

	/**
	 * subtract one Polynom from the other
	 * 
	 * @param p1
	 *            input Polynom_able type object which should be subtract from the
	 *            one which the method is used on
	 * @see this method is supported by the private subtract method
	 */
	@Override
	public void substract(Polynom_able p1) {
		Iterator<Monom> it = p1.iteretor();
		while (it.hasNext()) {
			Monom runningMonom = it.next();
			this.subtract((runningMonom));// using the private helping method we created on every Monom from the p1 list
		}
		this.fixUp();// in case this method somehow made some Monom zero valued, we remove them

	}

	/**
	 * toString method which used to print the value of the Polynom which is used on
	 * 
	 * @see if the Polynom is a zero Polynom - will print it as "0"
	 * 
	 * @return String representation of the Polynom
	 */
	@Override
	public String toString() {
		if (this.isZero()) {// if the polynom is empty, thats just zero..
			return "0";
		}
		String outPut = "";// we are going to add all the string values to this variable which will be
		// returned
		Iterator<Monom> it = this.iteretor();
		boolean first = true;
		while (it.hasNext()) {
			Monom runningMonom = it.next();
			if (first) {
				outPut = "" + runningMonom.toString();// adding first monom to the output string, we dont want it to
				// get a "+", so we have to use the boolean variable
				first = false;
			} else {
				if (runningMonom.get_coefficient() > 0)// if this Monom is positive - add a "+" before it
					outPut = outPut + "+" + runningMonom.toString();
				else if (runningMonom.get_coefficient() != 0)// if it is a negative one, the "-" before it is already
					// there
					outPut += runningMonom.toString();
			}
		}
		return outPut;
	}

	/**
	 * calculates the area above x-axis of a polynom in a certain given borders, to
	 * the right of x0 and to the left of x1 as asked, using the numeric method of
	 * splitting the area to eps based rectangle and summing their surface
	 * 
	 * NOTE:if (x0>x1) ===> the method will swap them
	 * 
	 * NOTE2:this method will ignore rectangles for which the area is negative, as
	 * this method calculates only the area above x-axis
	 * 
	 * @param x0
	 *            double type variable, the start of the range in which we calculate
	 *            the area above x - axis
	 * @param x1
	 *            double type variable, the end of the range in which we calculate
	 *            the area above x - axis
	 * @param eps
	 *            double type variable, the base of the rectangle calculated,
	 *            ASSUMED to be 0.01 or smaller, PLEASE NOTICE that a large eps will
	 *            return not accurate area
	 * 
	 * @return the approximate area above the x axis under the Polynom function
	 */
	@Override
	public double area(double x0, double x1, double eps) {

		double area = 0; // temp for holding each area
		double sumOfArea = 0; // sums of all the rectangles
		double h = 0; // height

		if (x0 > x1) { // swaps
			System.out.println("x0 has bigger vaule than x1, values SWAPPED");
			double temp = x0;
			x0 = x1;
			x1 = temp;

		}
		while (x0 + eps <= x1) {
			h = f(x0);
			area = eps * h;
			if (area > 0)
				sumOfArea = sumOfArea + area; // if above x axis
			x0 = x0 + eps;
		}
		if (x0 < x1) { // calculates the remaining distance between x0-x1 when it's smaller than eps
			double width = x1 - x0;
			area = width * f(x0);
			if (area > 0)
				sumOfArea = sumOfArea + area; // if above x axis
		}
		return sumOfArea;
	}

	/**
	 * calculates the area below x-axis of a polynom in a certain given borders, to
	 * the right of x0 and to the left of x1 as asked, using the numeric method of
	 * splitting the area to eps based rectangle and summing their surface
	 * 
	 * NOTE:if (x0>x1) ===> the method will swap them
	 * 
	 * NOTE2:this method will ignore rectangles for which the area is positive, as
	 * this method calculates only the area below x-axis
	 * 
	 * @param x0
	 *            double type variable, the start of the range in which we calculate
	 *            the area above x - axis
	 * @param x1
	 *            double type variable, the end of the range in which we calculate
	 *            the area above x - axis
	 * @param eps
	 *            double type variable, the base of the rectangle calculated,
	 *            ASSUMED to be 0.01 or smaller, PLEASE NOTICE that a large eps will
	 *            return not accurate area
	 * 
	 * @return the approximate area below the x axis above the Polynom function
	 */
	public double negArea(double x0, double x1, double eps) {

		double area = 0; // temp for holding each area
		double sumOfArea = 0; // sums of all the rectangles
		double h = 0; // height

		if (x0 > x1) { // swaps
			System.out.println("x0 has bigger vaule than x1, values SWAPPED");
			double temp = x0;
			x0 = x1;
			x1 = temp;
		}
		while (x0 + eps <= x1) {
			h = f(x0);
			area = eps * h;
			if (area < 0)
				sumOfArea = sumOfArea + area; // if below x axis
			x0 = x0 + eps;
		}
		if (x0 < x1) { // calculates the remaining distance between x0-x1 when it's smaller than eps
			double width = x1 - x0;
			area = width * f(x0);
			if (area < 0)
				sumOfArea = sumOfArea + area; // if below x axis
		}
		return -sumOfArea; // makes the answer positive because area can't be negative
	}
<<<<<<< HEAD
	/**
	 * 
	 */
	public ArrayList<Point> minMax(double x0,double x1) {
		
		ArrayList<Double>suspected=localExtremum(x0, x1);
		if(suspected.size()==0)return null;
		ArrayList<Point>points=sortPoints(suspected);
		findGlobalP(x0,x1,points);
		return points;
		
	}
=======

>>>>>>> 22fa05fd66aa30b81832f80a61e75ff9119b0f96
	//////////////////////////// private supporting methods:
	/**
	 * private static method which supports Polynom String constructor
	 * 
	 * @param str
	 *            String type variable which represents the soon to be Polynom
	 * 
	 * @return a new ArrayList type object which will be used in the String
	 *         constructor
	 */
	private static Polynom initStringPolynom(String str) {
		Polynom outPut = new Polynom();// this will be the output of this method
		String helpingStr = "";
		for (int i = 0; i < str.length(); i++) {// replacing every '-' char with "-*" to represent the '-' in further
			// use
			if (str.charAt(i) == '-') {
				helpingStr = helpingStr + "" + str.charAt(i) + "*";
			} else
				helpingStr = helpingStr + str.charAt(i);
		}
		String strArr[] = helpingStr.split("\\+");// splitting around '+'
		String strArr_2[];// helping array we might need in case we got '-'
		for (String runningStr : strArr) {
			if (runningStr.contains("-")) {// if the string contains "-" we will want to split it again around "-"
				strArr_2 = runningStr.split("-");
				for (int i = 0; i < strArr_2.length; i++) {
					if (strArr_2[i].contains("*")) {// this way we know if there was a '-' before the split
						Monom m = new Monom(strArr_2[i].replaceAll("\\*", "-"));// replacing all '*' we got with '-' as
						// we already stated
						outPut.add(m);
					} else if (!runningStr.equals("")) {// in case we havn't got a '-'
						Monom m = new Monom(strArr_2[i]);
						outPut.add(m);
					}
				}
			} else
				outPut.add(new Monom(runningStr));
		}
		return outPut;
	}

	/**
	 * helping private method which subtract one Monom from the Polynom, this method
	 * is created to support the subtract between Polynoms which we had to implement
	 * from the interface
	 * 
	 * @param m1
	 *            Monom type object which should be subtracted from the Polynom the
	 *            method is used on
	 * 
	 */
	private void subtract(Monom m1) {
		Iterator<Monom> it = this.iteretor();
		boolean isSubtracted = false;
		while (it.hasNext() && !isSubtracted) {// as long as the Monom isn't subtracted yet, and there are more Monoms
			// in the list to check with
			Monom runningMonom = it.next();
			if (runningMonom.get_power() == m1.get_power()) {// only in a case where both powers are equal we'll
				// subtract
				runningMonom.subtract(m1);
				isSubtracted = true;
			}
		}
		if (!isSubtracted) {// in case we couldn't find a Monom with same power in the list, we'll just add
			// it as a negative Monom and we'll sort the list
			Monom negative = new Monom(m1.get_coefficient() * (-1), m1.get_power());
			this.add(negative);
			Comparator<Monom> cmpMonoms = new Monom_Comperator();
			polynom.sort(cmpMonoms);
		}
		this.fixUp();// in case this method somehow made some Monom zero valued, we remove them

	}

	/**
	 * Helping method which is used to remove zero value Monoms from the list
	 * 
	 */
	private void fixUp() {
		Iterator<Monom> it = this.iteretor();
		Monom runningMonom;// we'll use this runningMonom for all method long so we'll declare it here
		while (it.hasNext()) {// removing all zero values might be in the list
			runningMonom = it.next();
			if (runningMonom.get_coefficient() == 0)
				it.remove();
		}
		for (int index = 0; index < this.polynom.size() - 1; index++) {// if we got two Monoms with the same power -
																		// we'll sum them
			if (this.polynom.get(index).get_power() == this.polynom.get(index + 1).get_power()) {
				this.polynom.get(index).add(this.polynom.get(index + 1));
				this.polynom.remove(index + 1);
			}
		}
		it = this.iteretor();//resting the iterator
		while (it.hasNext()) {//checking whether there are zero valued Monoms
			runningMonom = it.next();
			if (runningMonom.get_coefficient() == 0)
				it.remove();
		}
	}
<<<<<<< HEAD

=======
<<<<<<< HEAD

=======
<<<<<<< HEAD
>>>>>>> 22fa05fd66aa30b81832f80a61e75ff9119b0f96
	//identifies if a point is a minima or a maxima
	private ArrayList<Point> sortPoints(ArrayList<Double> arr) {

		ArrayList<Point>points=new ArrayList<Point>();		//holds our points
		Iterator<Double>input=arr.iterator();		
		double eps=0.1;								//difference checked
		double temp=0;
		Polynom der=(Polynom)this.derivative();		

		while(input.hasNext()) {   
			temp=input.next();
			Point p=new Point(temp,this.f(temp));	//converts all of the x values given to points on the function
			if(der.f(p.getX()-eps)>0&&der.f(p.getX()+eps)<0) p.setMax(true);	//sort if min or max
			else p.setMin(true);
			points.add(p);							//adds them to the array list
		}

		return points;
	}
	//takes arraylist of points, finds the global min and the global max in it and checks if
	//the borders are the global min||max
	private void findGlobalP(double x0, double x1,ArrayList<Point> points) {

		Iterator<Point>it=points.iterator();
		findMin(points);
		findMax(points);
		double y0=this.f(x0);
		double y1=this.f(x1);
		Point newMin;
		Point newMax;
		boolean areEqual=false;
		Point temp;
		if(y0<y1) {
			newMin=new Point(x0,y0);
			newMax=new Point(x1,y1);
			
		}
		else if(y0>y1) {
			newMin=new Point(x1,y1);
			newMax=new Point(x0,y0);
		}
		else {
			areEqual=true;
			newMin=new Point(x0,y0);
			newMax=new Point(x1,y1);
		}
		while(it.hasNext()) {
			
			temp=it.next();
			if(temp.isGlobalMin()&&y0<temp.getY()) {
				temp.setGlobalMin(false);
				if(!areEqual) {
				newMin.setGlobalMin(true);
				//points.add(newMin);
				}
				else {
					newMin.setGlobalMin(true);
					newMax.setGlobalMin(true);
//					points.add(newMin);
//					points.add(newMax);
				}
			}
			if(temp.isGlobalMax()&&y0>temp.getY()) {
				temp.setGlobalMax(false);
				if(!areEqual) {
				newMax.setGlobalMax(true);
				//points.add(newMin);
				}
				else {
					newMin.setGlobalMax(true);
					newMax.setGlobalMax(true);
//					points.add(newMin);
//					points.add(newMax);
				}
			}
		}
		if(newMin.isGlobalMin()||newMin.isGlobalMax())points.add(newMin);
		if(newMax.isGlobalMax()||newMax.isGlobalMin())points.add(newMax);


	}
	//find global min in arraylist of points
	private void findMin(ArrayList<Point> points) { //assumes has atleast one point

		Iterator<Point>it=points.iterator();
		Point minP=it.next();
		minP.setGlobalMin(true);
		Point temp;

		while(it.hasNext()) {
			temp=it.next();
			if(minP.getY()>temp.getY()) {
				minP=temp;
				Iterator<Point>it2=points.iterator();
				while(it2.hasNext())it2.next().setGlobalMin(false);		//resets all previous Global mins
				minP.setGlobalMin(true);
			}
			else if(minP.getY()==temp.getY())temp.setGlobalMin(true);
		}
	}
	//find global max in arraylist of points
	private void findMax(ArrayList<Point> points) { //assumes has atleast one point

		Iterator<Point>it=points.iterator();
		Point maxP=it.next();
		maxP.setGlobalMax(true);
		Point temp;

		while(it.hasNext()) {
			temp=it.next();
			if(maxP.getY()<temp.getY()) {
				maxP=temp;
				Iterator<Point>it2=points.iterator();
				while(it2.hasNext())it2.next().setGlobalMax(false);		//resets all previous Global mins
				maxP.setGlobalMax(true);
			}
			else if(maxP.getY()==temp.getY())temp.setGlobalMax(true);
		}
	}
<<<<<<< HEAD

=======
=======
>>>>>>> da0b7efabb148216bd65b187459ed011aa7e1ebe
>>>>>>> 22fa05fd66aa30b81832f80a61e75ff9119b0f96
	/**
	 * returns the local extremum points in the Polynom used on in a certain given
	 * borders NOTE: if (x0>x1) ===> the method will swap them
	 * 
	 * @param x0
	 *            is the start of the given border
	 * @param x1
	 *            is the end of the given border
	 * @return an ArrayList which contains all x values which represents the local
	 *         extremum points, NOTE: if there are no local extremum points in the
	 *         given border will return empty ArrayList
	 */
	private ArrayList<Double> localExtremum(double x0, double x1) {
		if (x0 > x1) { // swaps
			System.out.println("x0 has bigger vaule than x1, values SWAPPED");
			double temp = x0;
			x0 = x1;
			x1 = temp;
		}
		ArrayList<Double> pArray = new ArrayList<>();
		Polynom dev = (Polynom) this.derivative();// creating a Polynom which represents this Polynoms derivative
		double extremumX;// this will be our x value if the point is suspected to be extremum point
		double eps = 0.1;// eps with which we'll "travel" on the function
		double currentX = x0;// "running" x
		while (currentX <= x1) {// as long as we're in the given borders
			if (dev.f(currentX) * dev.f(currentX + eps) < 0) {// in case one point is negative while the other is
																// positive
				if (dev.derivative().f(currentX) < eps && dev.derivative().f(currentX) > -eps) {// checking whether this
																								// isn't a inflection
																								// point

					extremumX = dev.root(currentX, (currentX + eps), eps);// determine where the derivative is zero
																			// valued
					pArray.add(extremumX);// adding the x value which we found to the ArrayList
				}
			}
			currentX += eps;
		}
		return pArray;
	}


}
