import java.util.Scanner;
import java.io.InputStream;
import java.util.ArrayList;

public static class Equation {
	
	private final int POWER;
	private double coeffs;	
	private String eqStr;
	
	private solutionType solType;
	private double roots;
	
	private enum solutionType {
		NONE	("No possible solution"),
		ALL	("All values are a valid solution"),
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
		
	public String printRoots() {
		if (solType == solutionType.NORMAL) {
			String res="";
			for (int i=0; i<roots.length; i++) {
				res+= "x = " + roots[i] + "\n";
			}
			return res;
		}
		else return solType.toString();
	}
		
	public static void solve(double params) {
		double a, b, c;
		switch (params.length) {
			case 0: {
				solType = solutionType.ALL;
				break;
			}
			case 1: {
				solType = solutionType.NONE;
				break;
			}
			case 2: {
				solType = solutionType.NORMAL;
				roots.add(-params[1]/params[0]);
				break;
			}
			case 3: {
				a = params[0];
				b = params[1];
				c = params[2];
				double dis = b*b - 4*a*c;
			
				if (dis < 0) {
					solType = solutionType.NONE;
				}
			
				else {
					solType = solutionType.NORMAL;
					double disSqrt = Math.sqrt(dis);
					roots[0] = (-b + disSqrt)/(2*a);
					if (dis > 0)
						roots[1] = (-b - disSqrt)/(2*a);
				}
				break;
			}
			default: {
				solType = solutionType.INVALID;
				break;
			}
		}
		
		//Equation string
		eqStr = " = 0";
		for (int i=coeffs.length-1; i>=0; i--) {
			if(coeffs[i]==0) continue;
			for (int j=0; j<coeffs.length-i-1; j++) eqStr = "*X" + eqStr;
			eqStr = ((Math.signum(coeffs[i])<0)? " - ":" + ") + Math.abs(coeffs[i]) + eqStr;
		}
		
		//roots
		printRoots();
	}
	

	public void init(InputStream inStr)
	{
		Scanner keyRead = new Scanner(inStr);
		for (int i=0; (i<POWER+1) && keyRead.hasNextDouble(); i++) {
			double tmp = keyRead.nextDouble();
			if((tmp!=0) || (coeffs.length>0)) coeffs[i] = tmp;
		}
		keyRead.close();

		eqStr = " = 0";
		for (int i=coeffs.length-1; i>=0; i--) {
			if(coeffs[i]==0) continue;
			for (int j=0; j<coeffs.length-i-1; j++) eqStr = "*X" + eqStr;
			eqStr = ((Math.signum(coeffs[i])<0)? " - ":" + ") + Math.abs(coeffs[i]) + eqStr;
		}
	}

	public void printResult() {
		System.out.println(eqStr);
		System.out.println(solution);
	}
}
