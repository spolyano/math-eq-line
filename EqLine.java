import java.util.Scanner;
import java.io.InputStream;
import java.util.ArrayList;

public static class Equation {
	
	private final int POWER;
	private double coeffs;	
	private String eqStr;
	
	private solutionType solType;
	private ArrayList<Double> roots = new ArrayList<Double>();
	
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
		
	public String toString() {
		if (solType == solutionType.NORMAL) {
			String res="";
			for (int i=0; i<roots.size(); i++) {
				res+= "x = " + roots.get(i) + "\n";
			}
			return res;
		}
		else return solType.toString();
	}
		
	public void solve(double params) {
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
				roots.add(-params.get(1)/params.get(0));
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
					roots.add((-params.get(1) + disSqrt)/(2*params.get(0)));
					if (dis > 0)
						roots.add((-params.get(1) - disSqrt)/(2*params.get(0)));
				}
				break;
			}
			default: {
				solType = solutionType.INVALID;
				break;
			}
		}
	}
	

	public void init(InputStream inStr)
	{
		Scanner keyRead = new Scanner(inStr);
		for (int i=0; (i<POWER+1) && keyRead.hasNextDouble(); i++) {
			double tmp = keyRead.nextDouble();
			if((tmp!=0) || (coeffs.size()>0)) coeffs.add(tmp);
		}
		keyRead.close();

		eqStr = " = 0";
		for (int i=coeffs.size()-1; i>=0; i--) {
			if(coeffs.get(i)==0) continue;
			for (int j=0; j<coeffs.size()-i-1; j++) eqStr = "*X" + eqStr;
			eqStr = ((Math.signum(coeffs.get(i))<0)? " - ":" + ") + Math.abs(coeffs.get(i)) + eqStr;
		}
	}

	public void printResult() {
		System.out.println(eqStr);
		System.out.println(solution);
	}
}
