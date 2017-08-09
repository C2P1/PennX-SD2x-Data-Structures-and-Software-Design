import java.util.Map;
import java.util.TreeMap;

public class BookReport implements Comparable<BookReport> {
    protected String bookTitle;
    protected String studentName;
    protected int numPages;

    public BookReport(String bookTitle, String studentName, int numPages) {
        this.bookTitle = bookTitle;
        this.studentName = studentName;
        this.numPages = numPages;
    }

    @Override
    public String toString() {
        return (studentName + " wrote " + numPages + " pages on " + bookTitle + ".");
    }

    @Override
    public int compareTo(BookReport otherBookReport) {
        return numPages - otherBookReport.numPages;
    }

    public static void main(String[] args) {
        BookReport chris = new BookReport("The Cathedral and the Bazaar", "Chris", 50);
        BookReport ada = new BookReport("The Idea Factory", "Ada", 2);
        BookReport toby = new BookReport("Toby Tries a Taco", "Toby", 100);

        // where integer key value is the score of the tree map
        TreeMap<BookReport, Integer> reportScores = new TreeMap<>();



        reportScores.put(chris, 87);
        reportScores.put(ada, 30);
        reportScores.put(toby, 30);


        // print out sorted by number of pages in book report
        for (Map.Entry<BookReport, Integer> entry : reportScores.entrySet()) {
            BookReport reportInfo = entry.getKey();
            int score = entry.getValue();
            System.out.println(reportInfo + " " + score + " pts.");
        }
    }
}
