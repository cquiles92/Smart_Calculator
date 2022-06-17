import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        // start coding here
        String password = scanner.nextLine();
        System.out.println(passwordTest(password) ? "YES" : "NO");
    }

    private static boolean passwordTest(String password) {
        String upperCaseRegex = ".*[A-Z]+.*";
        String lowerCaseRegex = ".*[a-z]+.*";
        String digitRegex = ".*\\d+.*";
        String lengthRegex = ".{12,}";

        return password.matches(upperCaseRegex) && password.matches(lowerCaseRegex)
               && password.matches(digitRegex) && password.matches(lengthRegex);
    }
}