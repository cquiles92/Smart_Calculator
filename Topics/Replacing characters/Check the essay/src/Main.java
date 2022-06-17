import java.util.Map;
import java.util.Scanner;


class CheckTheEssay {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String text = scanner.nextLine();

        // write your code here
        text = spellCheck(text);
        System.out.println(text);

    }

    public static String spellCheck(String text) {

        Map<String, String> dictionary = Map.of(
                "Franse", "France",
                "Eifel tower", "Eiffel Tower",
                "19th", "XIXth",
                "20th", "XXth",
                "21st", "XXIst"
        );

        String correctedText = text;
        for (String key : dictionary.keySet()) {
            correctedText = correctedText.replaceAll(key, dictionary.get(key));
        }

        return correctedText;
    }
}
