import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int numberOfVariables = Integer.parseInt(scanner.nextLine());

        String underscoreRegex = "^[_]([a-zA-Z0-9])+[a-zA-Z0-9_]*";
        String upperCaseRegex = "^[A-Z]+[a-zA-Z0-9_]*";
        String lowerCaseRegex = "^[a-z]+[a-zA-Z0-9_]*";


        String regex = String.format("%s|%s|%s", underscoreRegex, upperCaseRegex, lowerCaseRegex);

        for (int i = 0; i < numberOfVariables; i++) {
            String identifier = scanner.nextLine();
            if (!identifier.matches(regex)) {
                System.out.println(identifier);
            }
        }
    }
}