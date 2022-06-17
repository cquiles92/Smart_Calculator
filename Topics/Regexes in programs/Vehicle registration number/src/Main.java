import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String regNum = scanner.nextLine(); // a valid or invalid registration number

        /* write your code here */
        String characterSet = "[ABEKMHOPCTYX]";
        String registrationRegex = String.format("\\s*%s\\d{3}%s{2}\\s*", characterSet, characterSet);

        System.out.println(regNum.matches(registrationRegex));
    }
}