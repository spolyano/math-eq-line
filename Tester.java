
public class Tester {

	public static void main(String args[]) {
		
		EqLine eq1 = new EqLine();
		eq1.setCoeffFromKey();
		eq1.calculate();
		System.out.println(eq1.getRoot());
		
	}
	
}
