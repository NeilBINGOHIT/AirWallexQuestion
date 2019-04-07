import java.math.BigDecimal;

public class ResultEntity {
    private double result;

    public ResultEntity(double result) {
        this.result = result;
    }

    public void add(ResultEntity val) {
        result = val.result + result;
    }

    public void sub(ResultEntity val) {
        result = val.result - result;
    }

    public void multiply(ResultEntity val) {
        result = val.result * result;
    }

    public void divide(ResultEntity val) throws RPNCalculatorException {
        if (BigDecimal.valueOf(val.result).compareTo(BigDecimal.ZERO) == 0) {
            throw new RPNCalculatorException("divisor cannot be zero");
        }
        result = val.result / result;
    }

    public void sqrt() {
        result = Math.sqrt(result);
    }

    public double getResult() {
        return result;
    }
}
