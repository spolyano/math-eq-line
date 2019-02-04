import java.util.ArrayList;

final class Equation {

    private static String eqStr;
    private static solutionType solType;
    private static ArrayList<Double> roots = new ArrayList<Double>();

    private static enum solutionType {
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

    private static void printResults() {
        System.out.println(eqStr);
        if (solType == solutionType.NORMAL) {
            String res="";
            for (int i=0; i<roots.size(); i++) {
                res+= "x = " + roots.get(i) + "\n";
            }
            System.out.println(res);
        }
        else System.out.println(solType.toString());
    }

    public static void solve(double[] params) {
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
                if (params[0] != 0) {
                    roots.add(-params[1] / params[0]);
                }
                else solType = solutionType.NONE;
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
                    roots.add((-b + disSqrt) / (2*a));
                    if (dis > 0)
                        roots.add((-b - disSqrt) / (2*a));
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
        for (int i = params.length-1; i>=0; i--) {
            if(params[i]==0) continue;
            for (int j = 0; j < params.length-i-1; j++) eqStr = "*X" + eqStr;
            if (i > 0){
                eqStr = ((Math.signum(params[i])<0)? " - ":" + ") + Math.abs(params[i]) + eqStr;
            } else {
                eqStr = ((Math.signum(params[i])<0)? " - " : "") + Math.abs(params[i]) + eqStr;
            }
        }

        printResults();
    }
}
