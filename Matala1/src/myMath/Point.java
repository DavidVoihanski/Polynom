package myMath;
/**
 * This call is used to represent maxima and minima point on a given polynom
 * @author David
 *
 */
public class Point {
	
	private double x;
	private double y;
	private boolean max=false;
	private boolean min=false;
	private boolean globalMin=false;
	private boolean globalMax=false;

	public Point() {
		
	}
	
	public Point(double x, double y) {
		this.x=x;
		this.y=y;
	}
	public double getX() {
		return this.x;
	}
	public double getY() {
		return this.y;
	}
	public void setX(double x) {
		this.x=x;
	}
	public void setY(double y) {
		this.y=y;
	}
	public boolean isMax() {
		return this.max;
	}
	public boolean isMin() {
		return this.min;
	}
	public boolean isGlobalMax() {
		return this.globalMax;
	}
	public boolean isGlobalMin() {
		return this.globalMin;
	}
	public void setMax(boolean flag) {
		this.max=flag;
	}
	public void setMin(boolean flag) {
		this.min=flag;
	}
	public void setGlobalMax(boolean flag) {
		this.globalMax=flag;
	}
	public void setGlobalMin(boolean flag) {
		this.globalMin=flag;
	}
}
