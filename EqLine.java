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
	
	public void init() {
		Scanner keyRead = new Scanner(System.in);
		char label;
		int i;
		for (i=0, label='a'; i<coeff.length; i++, label++){
			System.out.print(label + " = ");
			coeff[i] = (keyRead.hasNextDouble()? keyRead.nextDouble() : 0);
		}

		keyRead.close();
	}
	
	public void init(InputStream inStr)
	{
		Scanner keyRead = new Scanner(System.in);
		for (int i=0; (i<coeff.length) && keyRead.hasNextDouble(); i++){
			coeff[i] = keyRead.nextDouble();
		}
		keyRead.close();
	}
	
	public void calculate() {
		eqStr = coeff[0] + "x " + ( (Math.signum(coeff[1])<0)? "-":"+" ) + " " + Math.abs(coeff[1]) + " = 0";
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
	
	private String representRoot() {
		String res = "";
		for (int i=0; i<root.length; i++) res+= "x = " + root[i] + "\n";
		return res;
	}
	
	private String representSolution() {
		switch(solution) {
			case NONE:		return "No possible solution";
			case ALL:		return "All values are a valid solution";
			case NORMAL:	return "Root is possible";
			default:		return "Invalid solution";
		}
	}
	
	public String getSolution() {
		return (solution == solutionType.NORMAL) ? representRoot() : representSolution();
	}
	
	public void printResult() {
		System.out.println(eqStr);
		System.out.println(getSolution());
	}
}