import java.io.*;
import java.util.*;
import java.util.HashSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/*
 * SD2x Homework #3
 */
public class Analyzer {

    /*
     * This method takes the name of the file to read and reads it one line at a time,
     * creating Sentence objects and putting them into the List.
     * Note that the method returns a List containing Sentence objects.
     */
	public static List<Sentence> readFile(String filename) {
		ArrayList<Sentence> list = new ArrayList<>();
        ArrayList<String> sentenceList = new ArrayList<>();
		String[] sentences = null;
		if (filename == null) {
		    return list;
        }
        try {
            Scanner sentence = new Scanner(new File(filename));

            while (sentence.hasNextLine()) {
                sentenceList.add(sentence.nextLine());
            }

            sentence.close();
        } catch (IOException x) {
		    return list;
        }


        String[] sentenceArray = sentenceList.toArray(new String[sentenceList.size()]);

        for (int r = 0; r < sentenceArray.length; r++) {
            sentenceArray[r] = sentenceArray[r].replaceAll("\\.{3,}", "");
            sentences = sentenceArray[r].split("(?<=[.!?])");
            for (int i = 0; i < sentences.length; i++) {
                if (sentences[i].matches("-?[0-2] .*")) {
                    Pattern pattern = Pattern.compile("-?[0-2] ");
                    Matcher matcher = pattern.matcher(sentences[i]);
                    if (matcher.find())
                    {
                        int score = Integer.parseInt(matcher.group(0).split(" ")[0]);
                        String text = sentences[i].replaceAll("-?[0-2] ", "");
                        Sentence newSentence = new Sentence(score, text);
                        list.add(newSentence);
                    }
                }
            }
        }

        if (list.isEmpty()) {
            return null;
        }

		return list; // this line is here only so this code will compile if you don't modify it

	}

	/*
	 * This method should find all of the individual tokens/words in the text field of each Sentence in the List and
	 * create Word objects for each distinct word. The Word objects should keep track of the number of occurrences of
	 * that word in all Sentences, and the total cumulative score of all Sentences in which it appears.
	 * This method should then return a Set of those Word objects.
     * If the input List of Sentences is null or is empty, the allWords method should return an empty Set.
     * If a Sentence object in the input List is null, this method should ignore it and process the non-null Sentences.
	 */
	public static Set<Word> allWords(List<Sentence> sentences) {

        HashSet<Word> allWords = new HashSet<>();

	    if (sentences != null) {
            for (Sentence sentence : sentences) {
                if (sentence == null) {
                    continue;
                }
                String[] words = sentence.getText().split(" ");
                for (String word : words) {
                    word = word.toLowerCase();
                    if (isAlpha(word) && !word.equals("")) {
                        Word newWord = new Word(word);
                        boolean flag = false; // flag to check if added
                        for (Word obj : allWords) {
                            if (obj.equals(newWord)) {
                                obj.increaseTotal(sentence.getScore());
                                flag = true;
                                break;
                            }
                        }
                        if (!flag) {
                            allWords.add(newWord);
                            newWord.increaseTotal(sentence.getScore());
                        }
                    }
                }
            }
        }
		return allWords;
	}

	/*
	 * This method should iterate over each Word in the input Set, use the Word’s calculateScore method to
	 * get the average sentiment score for that Word, and then place the text of the Word (as key) and
	 * calculated score (as value) in a Map.
	 */
	public static Map<String, Double> calculateScores(Set<Word> words) {
        HashMap<String, Double> scores = new HashMap<>();
        if (words != null) {
            for (Word word : words) {
                if (word != null) {
                    double score = word.calculateScore();
                    scores.put(word.getText(), score);
                }
            }
        }
        return scores;
	}

	/*
	 * This method should use the Map of scores to calculate and return the average
	 * score of all the words in the input sentence.
	 */
	public static double calculateSentenceScore(Map<String, Double> wordScores, String sentence) {

	    double sentenceScore = 0;
	    int wordCount = 0;

        if (wordScores == null) {
            return 0;
        }

        if (sentence == null) {
            return 0;
        }

        if (sentence.equals("")) {
            return 0;
        }


        String[] words = sentence.split("\\s");
        for (String word : words) {
            word = word.toLowerCase();
            if (isAlpha(word)) {
                if (wordScores.containsKey(word)) {
                    if (!word.equals("")) {
                        sentenceScore += wordScores.get(word);
                    }
                }
                wordCount++;
            }
        }

        if (wordCount != 0) {
            return sentenceScore / wordCount;
        } else {
	        return sentenceScore;
        }
	}

	public static void main(String[] args) {
		if (args.length == 0) {
			System.out.println("Please specify the name of the input file");
			System.exit(0);
		}
		String filename = args[0];
		System.out.print("Please enter a sentence: ");
		Scanner in = new Scanner(System.in);
		String sentence = in.nextLine();
		in.close();
		List<Sentence> sentences = Analyzer.readFile(filename);
		Set<Word> words = Analyzer.allWords(sentences);
        Map<String, Double> wordScores = Analyzer.calculateScores(words);
		double score = Analyzer.calculateSentenceScore(wordScores, sentence);
		System.out.println("The sentiment score is " + score);
	}

    public static boolean isAlpha(String name) {
        char[] chars = name.toCharArray();
        for (char c : chars) {
            if(!Character.isLetter(c)) {
                return false;
            }
        }
        return true;
    }
}
