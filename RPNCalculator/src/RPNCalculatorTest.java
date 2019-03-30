import org.junit.Test;

import java.util.Stack;

import static org.junit.Assert.assertEquals;


public class RPNCalculatorTest {

    @Test
    public void example1() throws Exception {
        Stack<Double> stack = new Stack<>();
        RPNCalculator calculator = new RPNCalculator();
        calculator.calculate("5 2");
        stack.push(5.0);
        stack.push(2.0);
        assertEquals(stack, calculator.getCalculateStack());
    }

    @Test
    public void example2() throws Exception {
        Stack<Double> stack = new Stack<>();
        RPNCalculator calculator = new RPNCalculator();

        calculator.calculate("2 sqrt");
        stack.push(Math.sqrt(2));
        assertEquals(stack, calculator.getCalculateStack());
        stack.clear();

        calculator.calculate("clear 9 sqrt");
        stack.push(3.0);
        assertEquals(stack, calculator.getCalculateStack());
        stack.clear();
    }

    @Test
    public void example3() throws Exception {
        Stack<Double> stack = new Stack<>();
        RPNCalculator calculator = new RPNCalculator();

        calculator.calculate("5 2 -");
        stack.push(3.0);
        assertEquals(stack, calculator.getCalculateStack());
        stack.clear();

        calculator.calculate("3 -");
        stack.push(0.0);
        assertEquals(stack, calculator.getCalculateStack());
        stack.clear();

        calculator.calculate("clear");
        assertEquals(stack, calculator.getCalculateStack());
        stack.clear();
    }

    @Test
    public void example4() throws Exception {
        Stack<Double> stack = new Stack<>();
        RPNCalculator calculator = new RPNCalculator();

        calculator.calculate("5 4 3 2");
        stack.push(5.0);
        stack.push(4.0);
        stack.push(3.0);
        stack.push(2.0);
        assertEquals(stack, calculator.getCalculateStack());
        stack.clear();

        calculator.calculate("undo undo *");
        stack.push(20.0);
        assertEquals(stack, calculator.getCalculateStack());
        stack.clear();

        calculator.calculate("5 *");
        stack.push(100.0);
        assertEquals(stack, calculator.getCalculateStack());
        stack.clear();

        calculator.calculate("undo");
        stack.push(20.0);
        stack.push(5.0);
        assertEquals(stack, calculator.getCalculateStack());
        stack.clear();
    }

    @Test
    public void example5() throws Exception {
        Stack<Double> stack = new Stack<>();
        RPNCalculator calculator = new RPNCalculator();

        calculator.calculate("7 12 2 /");
        stack.push(7.0);
        stack.push(6.0);
        assertEquals(stack, calculator.getCalculateStack());
        stack.clear();

        calculator.calculate("*");
        stack.push(42.0);
        assertEquals(stack, calculator.getCalculateStack());
        stack.clear();

        calculator.calculate("4 /");
        stack.push(10.5);
        assertEquals(stack, calculator.getCalculateStack());
        stack.clear();
    }

    @Test
    public void example6() throws Exception {
        Stack<Double> stack = new Stack<>();
        RPNCalculator calculator = new RPNCalculator();

        calculator.calculate("1 2 3 4 5");
        stack.push(1.0);
        stack.push(2.0);
        stack.push(3.0);
        stack.push(4.0);
        stack.push(5.0);
        assertEquals(stack, calculator.getCalculateStack());
        stack.clear();

        calculator.calculate("*");
        stack.push(1.0);
        stack.push(2.0);
        stack.push(3.0);
        stack.push(20.0);
        assertEquals(stack, calculator.getCalculateStack());
        stack.clear();

        calculator.calculate("clear 3 4 -");
        stack.push(-1.0);
        assertEquals(stack, calculator.getCalculateStack());
        stack.clear();
    }

    @Test
    public void example7() throws Exception {
        Stack<Double> stack = new Stack<>();
        RPNCalculator calculator = new RPNCalculator();

        calculator.calculate("1 2 3 4 5");
        stack.push(1.0);
        stack.push(2.0);
        stack.push(3.0);
        stack.push(4.0);
        stack.push(5.0);
        assertEquals(stack, calculator.getCalculateStack());
        stack.clear();

        calculator.calculate("* * * *");
        stack.push(120.0);
        assertEquals(stack, calculator.getCalculateStack());
        stack.clear();
    }
}