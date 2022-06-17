package calculator;

import calculator.operations.Operator;

import java.util.Stack;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class InputHandler {
    public InputHandler() {
    }

    static String cleanInput(String input) {
        //left
        input = input.replaceAll("[(]", " ( ");

        //right
        input = input.replaceAll("[)]", " ) ");

        //equals
        input = input.replaceAll("=", " = ");

        //clean plus symbol in front of operand
        input = input.replaceAll("(?=[+]\\d+)[+]", "");

        //clean extra spaces
        input = input.replaceAll("\\s+", " ");

        //clean extra operators for + and - exclusively
        StringBuilder copy = new StringBuilder(input);
        Pattern pattern = Pattern.compile("[-+]{2,}");
//        Pattern pattern = Pattern.compile("[-+*/%^=]{2,}"); this cleans all inputs
        Matcher matcher = pattern.matcher(input);

        while (matcher.find()) {
            String token = input.substring(matcher.start(), matcher.end());
            String singleChar;

            if (input.charAt(matcher.start()) == '-') {
                if (token.length() % 2 == 0) {
                    singleChar = "+";
                } else {
                    singleChar = "-";
                }
            } else {
                singleChar = token.substring(0, 1);
            }
            int indexStart = copy.indexOf(token);
            int indexEnd = indexStart + token.length();
            copy.replace(indexStart, indexEnd, singleChar);
        }
        return copy.toString();
    }

    boolean isOperator(String input) {
        return input.matches("[+/%*^=-]");
    }

    boolean isOperand(String input) {
        return input.matches("[-+]?[\\da-zA-Z]+");
    }

    boolean isDigit(String input) {
        return input.matches("[-+]?\\d+");
    }

    boolean isVariable(String input) {
        return input.matches("[-+]?[a-zA-Z]+");
    }

    String shuntingYardAlgorithm(String input) {
        StringBuilder rpnInput = new StringBuilder();
        Stack<String> operatorStack = new Stack<>();

        String[] tokens = input.split(" ");
        for (String token : tokens) {
            if (isOperand(token)) {
                rpnInput.append(token).append(" ");
            } else if (token.equals("(")) {
                operatorStack.push(token);
            } else if (token.equals(")")) {
                while (!operatorStack.peek().equals("(")) {
                    rpnInput.append(operatorStack.pop()).append(" ");
                }
                //pop left parenthesis out
                operatorStack.pop();
            } else {
                //operators below
                //if stack is not empty or there is no left parenthesis at top of stack
                //check precedence and pop if necessary
                while (!operatorStack.isEmpty() && isOperator(operatorStack.peek())
                       && selectOperator(token).getPrecedence() <= selectOperator(operatorStack.peek()).getPrecedence()) {
                    rpnInput.append(operatorStack.pop()).append(" ");
                }
                operatorStack.push(token);
            }
        }

        while (!operatorStack.isEmpty()) {
            rpnInput.append(operatorStack.pop()).append(" ");
        }

        return rpnInput.toString();
    }

    Operator selectOperator(String selection) {
        selection = selection.toUpperCase().trim();

        switch (selection) {
            case "+":
                return Operator.ADDITION;
            case "-":
                return Operator.SUBTRACTION;
            case "*":
                return Operator.MULTIPLICATION;
            case "/":
                return Operator.DIVISION;
            case "%":
                return Operator.MODULO;
            case "^":
                return Operator.EXPONENTIATION;
            case "=":
                return Operator.ASSIGNMENT;
        }

        return null;
    }
}