package calculator.operations;

public enum Operator {
    ADDITION(1), SUBTRACTION(1), MULTIPLICATION(2), DIVISION(2), MODULO(2),
    EXPONENTIATION(3), ASSIGNMENT(0);

    private final int precedence;

    Operator(int precedence) {
        this.precedence = precedence;
    }

    public int getPrecedence() {
        return precedence;
    }
}
