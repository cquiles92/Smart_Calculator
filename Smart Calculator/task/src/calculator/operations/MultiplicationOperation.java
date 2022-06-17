package calculator.operations;

import java.math.BigInteger;

public class MultiplicationOperation implements Operation {
    @Override
    public BigInteger calculate(BigInteger x, BigInteger y) {
        return x.multiply(y);
    }
}
