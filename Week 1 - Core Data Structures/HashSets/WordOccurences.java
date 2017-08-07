import java.io.*;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/*
 * Using a HashMap to store the number of times a word occurs in a passage of text
 */
public class WordOccurences {
    public static void main(String[] args) {
        HashMap<String, Integer> wordCount = new HashMap<>();

        String line;
        try (
            InputStream fis = new FileInputStream("demo.txt");
            InputStreamReader isr = new InputStreamReader(fis, Charset.forName("UTF-8"));
            BufferedReader br = new BufferedReader(isr);
            ) {
                while ((line = br.readLine()) != null) {
                    System.out.println(line);
                    String[] words = line.split(" ");
                    for (String word : words) {
                        if (wordCount.containsKey(word)) {
                            int count = wordCount.get(word);
                            wordCount.put(word, count + 1);
                        } else {
                            wordCount.put(word, 1);
                        }
                    }
                }
        } catch (IOException e) {
            e.printStackTrace();
        }

        Scanner in = new Scanner(System.in);
        String input;

        while (true) {
            System.out.println("Enter a word: ");
            input = in.nextLine();
            if (input.equals("q")) {
                break;
            }
            if (wordCount.containsKey(input)) {
                int count = wordCount.get(input);
                System.out.println(input + " appears " + count + " times");
            } else {
                System.out.println(input + " is not in the text");
            }
        }

        System.out.println("======================");
        // EntrySet way of iterating over all elements
        for (Map.Entry<String, Integer> entry : wordCount.entrySet()) {
            String word = entry.getKey();
            int count = entry.getValue();
            System.out.println(word + " appears " + count + " times.");
        }
    }
}
