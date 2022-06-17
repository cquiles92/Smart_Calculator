package calculator.operations;

import java.math.BigInteger;

public class SubtractionOperation implements Operation {

    @Override
    public BigInteger calculate(BigInteger x, BigInteger y) {
        return y.subtract(x);
    }
}
