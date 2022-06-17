import java.util.*;

class Date {

    public static void main(String[] args) {
        Scanner scn = new Scanner(System.in);
        String date = scn.nextLine();

        System.out.println(dateChecker(date) ? "Yes" : "No");
    }

    public static boolean dateChecker(String date) {
        String yearFormatRegex = "(19\\d{2}||20\\d{2})";
        String monthFormatRegex = "([0]\\d|[1][0-2])";
        String dayFormatRegex = "([0-2]\\d||[3][0-1])";
        String yearMonthDayRegex = String.format("%s[-/.]%s[-/.]%s", yearFormatRegex, monthFormatRegex, dayFormatRegex);
        String dayMonthYearRegex = String.format("%s[-/.]%s[-/.]%s", dayFormatRegex, monthFormatRegex, yearFormatRegex);
        String dateRegex = String.format("%s|%s", yearMonthDayRegex, dayMonthYearRegex);

        return date.matches(dateRegex);
    }
}