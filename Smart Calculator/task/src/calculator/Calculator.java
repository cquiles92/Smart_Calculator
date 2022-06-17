package calculator;

import calculator.operations.*;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.Stack;

public class Calculator {
    private final Scanner scanner;
    private final Map<String, BigInteger> variables;
    private final InputHandler inputHandler = new InputHandler();
    private Operation operation;
    private boolean quitProgram;

    Calculator() {
        scanner = new Scanner(System.in);
        variables = new HashMap<>();
    }

    public void mainProgram() {
        quitProgram = false;
        while (!quitProgram) {
            String input = scanner.nextLine();
            processInput(input);
        }
    }

    private void processInput(String input) {
        if (input.trim().startsWith("/")) {
            if (input.toLowerCase().contains("/exit")) {
                quitProgram();
            } else if (input.toLowerCase().contains("/help")) {
                helpReadMe();
            } else {
                System.out.println("Unknown command");
            }
        } else if (!input.trim().isEmpty()) {
            try {
                String reversePolishInput = inputHandler.shuntingYardAlgorithm(InputHandler.cleanInput(input));
                reversePolishInterpreter(reversePolishInput);

            } catch (Exception e) {
                System.out.println("Invalid expression");
            }
        }
    }

    private void quitProgram() {
        System.out.println("Bye!");
        quitProgram = true;
    }

    private void helpReadMe() {
        System.out.println("The program can perform Assignment, use Parenthesis and evaluate the following:\n" +
                           "Multiplication, Division, Addition, Subtraction, Modulus, And Exponents.\n To quit the program enter \\quit");
    }

    private void reversePolishInterpreter(String input) {
        //formatted line divided by spaces
        String[] tokens = input.split(" ");
        Stack<String> stack = new Stack<>();
        BigInteger x;
        BigInteger y;

        if (tokens.length == 1 && inputHandler.isOperand(tokens[0])) {
            if (inputHandler.isVariable(tokens[0])) {
                if (variables.containsKey(tokens[0])) {
                    tokens[0] = variables.get(tokens[0]).toString();
                } else {
                    System.out.println("Unknown variable");
                    return;
                }
            }
            System.out.println(tokens[0]);
            return;
        }

        for (String token : tokens) {
            if (inputHandler.isOperand(token)) {
                if (inputHandler.isVariable(token) || inputHandler.isDigit(token)) {
                    stack.push(token);
                } //illegal sequence e.g. abc1 as variable name
                else {
                    System.out.println("Invalid identifier");
                    return;
                }
            } else if (!inputHandler.isOperator(token)) {
                System.out.println("Invalid expression");
                return;
            } else {
                //pick correct strategy based off operator
                Operator currentOperation = selectOperator(token);
                if (inputHandler.isDigit(stack.peek())) {
                    x = new BigInteger(stack.pop());
                } else {
                    if (variables.containsKey(stack.peek())) {
                        x = variables.get(stack.pop());
                    } else {
                        System.out.println("Unknown variable");
                        return;
                    }
                }
                if (currentOperation == Operator.ASSIGNMENT) {
                    //y = x
                    String variable = stack.pop();
                    variables.put(variable, x);
                } else {
                    setOperation(currentOperation);

                    if (inputHandler.isDigit(stack.peek())) {
                        y = new BigInteger(stack.pop());
                    } else {
                        if (variables.containsKey(stack.peek())) {
                            y = variables.get(stack.pop());
                        } else {
                            System.out.println("Unknown variable");
                            return;
                        }
                    }
                    stack.push(String.valueOf(operation.calculate(x, y)));
                }
            }
        }
        //if stack is empty, this implies assignment so do nothing
        //if stack has 1 argument, we want the result
        //otherwise there is an error somewhere
        if (stack.size() == 1) {
            if (inputHandler.isVariable(stack.peek())) {
                System.out.println(variables.get(stack.pop()));
            } else {
                System.out.println(stack.pop());
            }
        } else if (stack.size() > 1) {
            System.out.println("Invalid expression");
        }
    }


    private Operator selectOperator(String selection) {
        return inputHandler.selectOperator(selection);
    }

    private void setOperation(Operator operator) {
        if (operator != null) {
            switch (operator) {
                case ADDITION:
                    operation = new AdditionOperation();
                    break;
                case SUBTRACTION:
                    operation = new SubtractionOperation();
                    break;
                case MULTIPLICATION:
                    operation = new MultiplicationOperation();
                    break;
                case DIVISION:
                    operation = new DivisionOperation();
                    break;
                case MODULO:
                    operation = new ModuloOperation();
                    break;
                case EXPONENTIATION:
                    operation = new ExponentiationOperation();
                    break;
            }
        }
    }
}
