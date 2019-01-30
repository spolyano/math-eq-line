import java.util.InputMismatchException;
import java.util.Scanner;

public class EqLine {
	
	//ax + b = 0
	private double a = 0;
	private double b = 0;
	private double root = 0;
	
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
		root = (-b)/a;
	}
	
	public double getRoot() {
		return root;		
	}
}
