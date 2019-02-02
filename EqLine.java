import java.util.Scanner;
import java.io.InputStream;

public class EqLine {
	
	private static final int DEGREE = 1;
	
	private double[] coeff = new double[DEGREE + 1];
	private double[] root = new double[DEGREE];

	private String eqStr = "ax + b = 0";
	
	private enum solutionType {NONE, ALL, NORMAL};
	private solutionType solution;

	public EqLine() {};

	public void init(InputStream inStr)
	{
		Scanner keyRead = new Scanner(inStr);
		for (int i=0; (i<coeff.length) && keyRead.hasNextDouble(); i++) coeff[i] = keyRead.nextDouble();
		keyRead.close();

		eqStr = coeff[0] + "x " + ( (Math.signum(coeff[1])<0)? "-":"+" ) + " " + Math.abs(coeff[1]) + " = 0";
	}
	
	public void calculate() {
		if (coeff[0]!=0) {
			solution = solutionType.NORMAL;
			root[0] = (-coeff[1])/coeff[0];
		}
		else if (coeff[1]==0) {
			solution = solutionType.ALL;
		}
		else {
			solution = solutionType.NONE;
		}
	}
	
	public String printRoot() {
		String res = "";
		for (int i=0; i<root.length; i++) res+= "x = " + root[i] + "\n";
		return res;
	}
	
	public Object getSolution() {
		return (solution == solutionType.NORMAL) ? printRoot() : solution;
	}
	
	public void printResult() {
		System.out.println(eqStr);
		System.out.println(getSolution());
	}
}