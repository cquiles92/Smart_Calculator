package calculator.operations;

import java.math.BigInteger;

public class ModuloOperation implements Operation {
    @Override
    public BigInteger calculate(BigInteger x, BigInteger y) {
        return y.mod(x);
    }
}
