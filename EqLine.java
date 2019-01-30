import java.util.InputMismatchException;
import java.util.Scanner;

public class EqLine {
	
	enum solutionType {NONE, ALL, NORMAL};
	
	//ax + b = 0
	
	private double a = 0;
	private double b = 0;
	private double root = 0;
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
	
	public void printResult() {
		if (solution == solutionType.NORMAL) System.out.println(root);
		else System.out.println(solution);
	}
}
