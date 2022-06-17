import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        // start coding here
        System.out.println(isValidIPAddress(scanner.nextLine()) ? "YES" : "NO");
    }

    public static boolean isValidIPAddress(String ipAddress) {
        String addressRegex = "((10\\d|1[1-9]\\d|2[0-4]\\d|25[0-5])|(1\\d|[2-9]\\d)|\\d)";
        String ipv4Regex = String.format("%s[.]%s[.]%s[.]%s", addressRegex, addressRegex, addressRegex, addressRegex);

        return ipAddress.matches(ipv4Regex);
    }
}