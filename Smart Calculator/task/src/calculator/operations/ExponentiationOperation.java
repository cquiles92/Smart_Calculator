package calculator.operations;

import java.math.BigInteger;

public class ExponentiationOperation implements Operation {
    @Override
    public BigInteger calculate(BigInteger x, BigInteger y) {
        return y.pow(x.intValue());
    }
}
