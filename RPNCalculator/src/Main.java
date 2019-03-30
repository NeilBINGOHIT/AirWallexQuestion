import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        RPNCalculator calculator = new RPNCalculator();
        Scanner scan = new Scanner(System.in);
        boolean keepRunning = true;
        while (keepRunning) {
            String expr = scan.nextLine();
            if ("exit".equals(expr)) {
                keepRunning = false;
            } else {
                try {
                    calculator.calculate(expr);
                } catch (RPNCalculatorException e) {
                    System.out.println(e.getMessage());
                    keepRunning = false;
                }
                calculator.displayCalculateStack();
            }
        }
    }
}
