import java.util.InputMismatchException;
import java.util.Scanner;

public class EqLine {
	
	private double a = 0;
	private double b = 0;
	private double root = 0;
	private String eqStr = "ax + b = 0";
	
	private enum solutionType {NONE, ALL, NORMAL};
	private solutionType solution;
	
	public EqLine() {};
	public EqLine(double a, double b) {
		this.a = a;
		this.b = b;
	}
	
	public void setCoeffFromKey() {
		Scanner keyRead = new Scanner(System.in);
		
		try {
			System.out.print("a = ");
			a = keyRead.nextDouble();
			
			System.out.print("b = ");
			b = keyRead.nextDouble();
		}
		catch (InputMismatchException ex) {
			System.out.println("Please type double variables.");
		}
		
		keyRead.close();
	}
	
	public void calculate() {
		eqStr = a + "x " + ( (Math.signum(b)<0)? "-":"+" ) + " " + Math.abs(b) + " = 0";
		if (a!=0) {
			solution = solutionType.NORMAL;
			root = (-b)/a;
		}
		else if (b==0) {
			solution = solutionType.ALL;
		}
		else {
			solution = solutionType.NONE;
		}
	}
	
	public double getRoot() {
		return root;		
	}
	
	public Object getSolution() {
		return (solution == solutionType.NORMAL) ? root : solution;
	}
	
	public void printResult() {
		System.out.println(eqStr);
		System.out.println("x = " + getSolution());
	}
}
