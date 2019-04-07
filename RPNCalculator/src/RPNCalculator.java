import org.apache.commons.collections4.CollectionUtils;

import java.util.Arrays;
import java.util.List;
import java.util.Stack;

public class RPNCalculator {

    private static final List<String> operators = Arrays.asList("+", "-", "*", "/", "sqrt");
    public static final String add = "+";
    public static final String subtract = "-";
    public static final String multiply = "*";
    public static final String divide = "/";
    public static final String sqrt = "sqrt";
    public static final String undo = "undo";
    public static final String clear = "clear";

    // track the whole inputs
    private Stack<String> trackStack = new Stack<>();

    // used for calculate
    private Stack<Double> calculateStack = new Stack<>();

    public void calculate(String expr) throws RPNCalculatorException {
        String[] input = expr.split("\\s+");
        for (String token : input) {
            if (token.equals(undo)) {
                undo();
                continue;
            }
            if (token.equals(clear)) {
                clearExpr();
                continue;
            }

            if (isOperator(token)) {
                boolean isSqrt = token.equals(sqrt);
                double[] nums = arithmeticPreProcess(isSqrt, token);
                ResultEntity resultEntity = null;
                if (isSqrt) {
                    resultEntity = new ResultEntity(nums[0]);
                    resultEntity.sqrt();
                } else {
                    resultEntity = new ResultEntity((nums[0]));
                    if (token.equals(add)) {
                        resultEntity.add(new ResultEntity(nums[1]));
                    } else if (token.equals(subtract)) {
                        resultEntity.sub(new ResultEntity(nums[1]));
                    } else if (token.equals(multiply)) {
                        resultEntity.multiply(new ResultEntity(nums[1]));
                    } else {
                        resultEntity.divide(new ResultEntity(nums[1]));
                    }
                }
                trackStack.push(token);
                calculateStack.push(resultEntity.getResult());
                continue;
            }

            try {
                double number = Double.parseDouble(token);
                trackStack.push(String.valueOf(number));
                calculateStack.push(number);
            } catch (NumberFormatException e) {
                System.out.println("Invalid input of number");
            }
        }
    }

    private boolean isOperator(String s) {
        return operators.contains(s);
    }
    private double[] arithmeticPreProcess(boolean isSqrt, String token) throws RPNCalculatorException {
        if (CollectionUtils.isEmpty(calculateStack)) {
            throw new RPNCalculatorException("operator " + token + "(position: " + locateInsuffParams() + "): insufficient parameters");
        }
        double first = 0, second = 0;
        if (isSqrt) {
            first = calculateStack.pop();
            return new double[]{first};
        }
        if (calculateStack.size() < 2) {
            throw new RPNCalculatorException("operator " + token + "(position: " + locateInsuffParams() + "): insufficient parameters");
        }
        first = calculateStack.pop();
        second = calculateStack.pop();
        return new double[]{first, second} ;
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

    private String locateInsuffParams() {
        return String.valueOf(trackStack.size() * 2);
    }

    public void displayCalculateStack() {
        System.out.println(calculateStack);
    }

    public Stack<Double> getCalculateStack() {
        return calculateStack;
    }
}
