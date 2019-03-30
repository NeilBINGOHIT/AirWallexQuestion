import java.util.Stack;

public class RPNCalculator {

    // track the whole inputs
    private Stack<String> trackStack = new Stack<>();

    // used for calculate
    private Stack<Double> calculateStack = new Stack<>();

    public void calculate(String expr) throws RPNCalculatorException {
        for (String token : expr.split("\\s+")) {
            switch (token) {
                case "+":
                    add();
                    break;
                case "-":
                    subtract();
                    break;
                case "*":
                    multiply();
                    break;
                case "/":
                    divide();
                    break;
                case "sqrt":
                    sqrt();
                    break;
                case "undo":
                    undo();
                    break;
                case "clear":
                    clearExpr();
                    break;
                default:
                    try {
                        double number = Double.parseDouble(token);
                        trackStack.push(String.valueOf(number));
                        calculateStack.push(number);
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid input of number");
                    }
                    break;
            }
        }
    }

    private double[] arithmeticPreProcess() {
        if (calculateStack.empty() || calculateStack.size() < 2) {
            return null;
        }
        double first = 0, second = 0;
        first = calculateStack.pop();
        second = calculateStack.pop();
        return new double[]{first, second} ;
    }

    private void add() throws RPNCalculatorException {
        trackStack.push("+");
        double[] temp = arithmeticPreProcess();
        if (temp == null) {
            throw new RPNCalculatorException("operator + (position: " + (trackStack.size() * 2 - 1) + "): insufficient parameters");
        }
        calculateStack.push(temp[1] + temp[0]);
    }

    private void subtract() throws RPNCalculatorException {
        trackStack.push("-");
        double[] temp = arithmeticPreProcess();
        if (temp == null) {
            throw new RPNCalculatorException("operator - (position: " + (trackStack.size()* 2 - 1) + "): insufficient parameters");
        }
        calculateStack.push(temp[1] - temp[0]);

    }

    private void multiply() throws RPNCalculatorException {
        trackStack.push("*");
        double[] temp = arithmeticPreProcess();
        if (temp == null) {
            throw new RPNCalculatorException("operator * (position: " + (trackStack.size()* 2 - 1) + "): insufficient parameters");
        }
        calculateStack.push(temp[1] * temp[0]);
    }

    private void divide() throws RPNCalculatorException {
        trackStack.push("/");
        double[] temp = arithmeticPreProcess();
        if (temp == null) {
            throw new RPNCalculatorException("operator * (position: " + (trackStack.size()* 2 - 1) + "): insufficient parameters");
        }
        if (temp[0] == 0) {
            throw new RPNCalculatorException("divisor cannot be zero");
        }
        calculateStack.push(temp[1] / temp[0]);
    }

    private void sqrt() throws RPNCalculatorException {
        trackStack.push("sqrt");
        if (calculateStack.empty() || calculateStack.size() < 1) {
            throw new RPNCalculatorException("operator sqrt (position: " + (trackStack.size() * 2 - 1) + "): insufficient parameters");
        }
        double number = 0;
        number = calculateStack.pop();
        if (number < 0) {
            throw new RPNCalculatorException("negative number sqrt not supported");
        }
        calculateStack.push(Math.sqrt(number));
    }

    private void undo() throws RPNCalculatorException {
        if (trackStack.isEmpty()) {
            throw new RPNCalculatorException("nothing to undo");
        }
        trackStack.pop();
        calculateStack.clear();
        StringBuilder sBuilder = new StringBuilder();
        for (String s : trackStack) {
            sBuilder.append(s);
            sBuilder.append(" ");
        }
        trackStack.clear();
        calculate(sBuilder.toString());
    }

    private void clearExpr() {
        trackStack.clear();
        calculateStack.clear();
    }

    public void displayCalculateStack() {
        System.out.println(calculateStack);
    }

    public Stack<Double> getCalculateStack() {
        return calculateStack;
    }
}
