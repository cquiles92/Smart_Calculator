package calculator.operations;

import java.math.BigInteger;

public class DivisionOperation implements Operation {
    @Override
    public BigInteger calculate(BigInteger x, BigInteger y) {
        return y.divide(x);
    }
}
