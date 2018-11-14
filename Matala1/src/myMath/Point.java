package myMath;
/**
 * This class is used to represent maxima and minima point on a given polynom
 * @author David
 *
 */
public class Point {
	
	private double x;			//x value of the point
	private double y;			//y value of the point
	private boolean max=false;	//is the point a min?
	private boolean min=false;	//is the point is max?
	private boolean globalMin=false;	//is the point a global min?
	private boolean globalMax=false;	//is the point a global max?

	public Point() {
		
	}
	/**
	 * creates a new point which is set to false for all min/max/global as default
	 * @param x	the x value of the point
	 * @param y	the y value of the point
	 */
	public Point(double x, double y) {
		this.x=x;
		this.y=y;
	}
	/**
	 * 
	 * @return returns the point's x value
	 */
	public double getX() {
		return this.x;
	}
	/**
	 * 
	 * @return returns the point's y value
	 */
	public double getY() {
		return this.y;
	}
	/**
	 * sets a new x value
	 * @param x given x 
	 */
	public void setX(double x) {
		this.x=x;
	}
	/**
	 * sets a new y value
	 * @param y given y
	 */
	public void setY(double y) {
		this.y=y;
	}
	/**
	 * 
	 * @return returns true if the point is max point
	 */
	public boolean isMax() {
		return this.max;
	}
	/**
	 * 
	 * @return returns true if the point is min point
	 */
	public boolean isMin() {
		return this.min;
	}
	/**
	 * 
	 * @return returns true if the point is global max point
	 */
	public boolean isGlobalMax() {
		return this.globalMax;
	}
	/**
	 * 
	 * @return returns true if the point is global min point
	 */
	public boolean isGlobalMin() {
		return this.globalMin;
	}
	/**
	 * sets the true/false for the poin't max 
	 * @param flag	given true/false
	 */
	public void setMax(boolean flag) {
		this.max=flag;
	}
	/**
	 * sets the true/false for the poin't min
	 * @param flag	given true/false
	 */
	public void setMin(boolean flag) {
		this.min=flag;
	}
	/**
	 * sets the true/false for the poin't global max 
	 * @param flag	given true/false
	 */
	public void setGlobalMax(boolean flag) {
		this.globalMax=flag;
	}
	/**
	 * sets the true/false for the poin't global min
	 * @param flag	given true/false
	 */
	public void setGlobalMin(boolean flag) {
		this.globalMin=flag;
	}
}
