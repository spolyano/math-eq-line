import java.util.Scanner;
import java.io.InputStream;
import java.util.ArrayList;

class EqLine {

	private final int POWER;
	public EqLine(int power) {
		POWER = power;
	}

	private double[] coeffs;	
	private String eqStr = "ax + b = 0";

	private enum solutionType {
		NONE	("No possible solution"),
		ALL		("All values are a valid solution"),
		NORMAL	("Solution should be there"),
		INVALID	("Invalid state");
		private final String textForm;
		solutionType(String txt) {
			textForm = txt;
		}
		public String toString() {
			return textForm;
		}

	};

	public class Solution {

		private solutionType solType;
		private double[] roots;

		public String toString() {
			if (solType == solutionType.NORMAL) {
				String res="";
				for (int i=0; i<roots.length; i++) res+= "x = " + roots[i] + "\n";
				return res;
			}
			else return solType.toString();
		}

		public void solve(double[] params) {
		    double a, b, c;
			if (params.length == 0) {
				solType = solutionType.ALL;
			}
			else if (params.length == 1) {
				solType = solutionType.NONE;
			}
			else if (params.length == 2) {
				solType = solutionType.NORMAL;
				roots.push(-params[1]/params[0]);
			}
			else if (params.length == 3) {
			    a = params[0];
			    b = params[1];
			    c = params[2];
				double dis = b*b - 4*a*c;
			
				if (dis > 0) {
					double disSqrt = Math.sqrt(dis);
					roots.push((-b + disSqrt)/(2*a));
					roots.push((-b - disSqrt)/(2*a));
					solType = solutionType.NORMAL;
				}
				else if (dis == 0){
					roots.push(-b/(2*a));
					solType = solutionType.NORMAL;
				}
				else solType = solutionType.NONE;
			}
			else {
				solType = solutionType.INVALID;
			}
		}
	}

	private Solution solution = new Solution();

	public void init(InputStream inStr)
	{
		Scanner keyRead = new Scanner(inStr);
		for (int i=0; (i<POWER+1) && keyRead.hasNextDouble(); i++) {
			double tmp = keyRead.nextDouble();
			if((tmp!=0) || (coeffs.length>0)) coeffs.push(tmp);
		}
		keyRead.close();

		eqStr = " = 0";
		for (int i=coeffs.length-1; i>=0; i--) {
			for (int j=0; j<coeffs.length-i-1; j++) eqStr = "*X" + eqStr;
			eqStr = ((Math.signum(coeffs[i])<0)? " - ":" + ") + Math.abs(coeffs[i]) + eqStr;
		}
	}

	public void calculate() {
		solution.solve(coeffs);
	}

	public void printResult() {
		System.out.println(eqStr);
		System.out.println(solution);
	}
}

class Tester {

	public static void main(String args[]) {

		EqLine eq1 = new EqLine(1);
		eq1.init(System.in);
		eq1.calculate();
		//System.out.println(eq1.getRoot());
		eq1.printResult();

	}

}
