package calculator.operations;

import java.math.BigInteger;

public interface Operation {
    BigInteger calculate(BigInteger x, BigInteger y);
}
