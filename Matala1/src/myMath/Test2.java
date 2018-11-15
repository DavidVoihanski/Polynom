package myMath;

public class Test2 {
	public static void main(String[] args) {
		Polynom p = new Polynom("x^3");
		System.out.println(p.negArea(-2, 0, 0.001));
		System.out.println(4-0.001);
	}
}