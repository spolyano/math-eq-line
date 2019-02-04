import java.util.Scanner;
import java.io.InputStream;
import java.util.ArrayList;

public class EqLine {
	
	private final int POWER;
	public EqLine(int power) {
		POWER = power;
	}
	
	private ArrayList<Double> coeffs = new ArrayList<Double>();	
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
		private ArrayList<Double> roots = new ArrayList<Double>();
		
		public String toString() {
			if (solType == solutionType.NORMAL) {
				String res="";
				for (int i=0; i<roots.size(); i++) res+= "x = " + roots.get(i) + "\n";
				return res;
			}
			else return solType.toString();
		}
		
		public void solve(ArrayList<Double> params) {
			if (params.size() == 0) {
				solType = solutionType.ALL;
			}
			else if (params.size() == 1) {
				solType = solutionType.NONE;
			}
			else if (params.size() == 2) {
				solType = solutionType.NORMAL;
				roots.add(-params.get(1)/params.get(0));
			}
			else if (params.size() == 3) {
				double dis = params.get(1)*params.get(1) - 4*params.get(0)*params.get(2);
				
				if (dis < 0) {
					solType = solutionType.NONE;
				}
				else {
					solType = solutionType.NORMAL;
					double disSqrt = Math.sqrt(dis);
					roots.add((-params.get(1) + disSqrt)/(2*params.get(0)));
					if (dis > 0) roots.add((-params.get(1) - disSqrt)/(2*params.get(0)));
				}
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
	
	public void calculate() {
		solution.solve(coeffs);
	}

	public void printResult() {
		System.out.println(eqStr);
		System.out.println(solution);
	}
}