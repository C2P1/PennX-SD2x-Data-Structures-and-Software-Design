/*
 * SD2x Homework #8
 * This class represents the Presentation Tier in the three-tier architecture.
 */

import java.util.List;
import java.util.Scanner;

public class PresentationTier {
	
	private LogicTier logicTier; // link to the Logic Tier
	
	public PresentationTier(LogicTier logicTier) {
		this.logicTier = logicTier;
	}

	/**
	 * using the command-line (i.e., reading from System.in), ask the user to
     * enter part or all of an author’s name, then display (using System.out)
     * the titles of those books whose author name includes the input name.
	 */
	public void showBookTitlesByAuthor(String author) {
        List<String> booksByAuthor = logicTier.findBookTitlesByAuthor(author.toLowerCase());
        for (String title : booksByAuthor) {
            System.out.println(title);
        }
    }


    /**
     * using the command-line (i.e., reading from System.in), ask the user to
     * enter a year, then display (using System.out) the number of books
     * published in that year
	 */
    public void showNumberOfBooksInYear(int year) {
        System.out.println("There were " + logicTier.findNumberOfBooksInYear(year) + " books published in the year " + year);
    }

	public void start() {
		
		Scanner in = new Scanner(System.in);

        System.out.println("Press 0 to find all book titles by author.");
        System.out.println("Press 1 to find the number of books in a given year.");
        int input = in.nextInt();
        in.nextLine();
        if (input == 0) {
            System.out.println("Please enter the authors name: ");

            String author = in.nextLine();
            System.out.println(author);
            showBookTitlesByAuthor(author);
        } else {
            System.out.println("Please enter the year you would like to see the books of: ");
            int year = in.nextInt();
            showNumberOfBooksInYear(year);
        }
	}
}
