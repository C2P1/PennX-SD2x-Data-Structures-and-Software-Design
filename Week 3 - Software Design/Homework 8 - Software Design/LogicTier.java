/*
 * SD2x Homework #8
 * This class represents the Logic Tier in the three-tier architecture.
 */

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.Comparator;

public class LogicTier {
	
	private DataTier dataTier; // link to the Data Tier
	
	public LogicTier(DataTier dataTier) {
		this.dataTier = dataTier;
	}

    /**
     * for a given name, search through all of the books and return the
     * titles of those books whose author name includes the input name.
     *
     * The titles of the books that are returned/displayed should be
     * sorted based on the year they were published in non-descending order;
     * if two or more books have the same publication year, those books
     * should be sorted alphabetically.
     */
	public List<String> findBookTitlesByAuthor(String author) {
	    if (author == null) {
	        return null;
        }
        if (author.equals("")) {
	        return null;
        }
	    List<Book> books = dataTier.getAllBooks();

        List<Book> booksByAuthor = new ArrayList<>();
        for (Book book : books) {
            if (book.getAuthor().contains(author.toLowerCase())) {
                booksByAuthor.add(book);
            }
        }

        Map<Integer, List<Book>> booksByYear = new TreeMap<>();

        for (Book book : booksByAuthor) {
            List<Book> booksInYear = booksByYear.get(book.getPublicationYear());
            if (booksInYear != null) {
                booksInYear.add(book);
                booksByYear.put(book.getPublicationYear(), booksInYear);
            } else {
                booksInYear = new ArrayList<>();
                booksInYear.add(book);
                booksByYear.put(book.getPublicationYear(), booksInYear);
            }
        }

        List<List<Book>> testList = new ArrayList<>();
        for (Map.Entry<Integer, List<Book>> entry : booksByYear.entrySet()) {
            if (entry.getValue().size() > 1) {
                SortedSet<Book> orderedBooks = new TreeSet<Book>(new SortByTitleAscending());
                orderedBooks.addAll(entry.getValue());
                List<Book> ordered = new ArrayList<>(orderedBooks);
                booksByYear.put(entry.getKey(), ordered);
            }
            testList.add(entry.getValue());
        }

        List<String> listToReturn = new ArrayList<>();
	    for (List<Book> list : testList) {
            for (Book book : list) {
                listToReturn.add(book.getTitle());
            }
        }
        return listToReturn;
    }

    /**
     * for a given year, search through all of the books and
     * return the number of books published in that year
     */
    public int findNumberOfBooksInYear(int year) {
        List<Book> books = dataTier.getAllBooks();
        int count = 0;
        for (Book book : books) {
            if (book.getPublicationYear() == year) {
                count++;
            }
        }
        return count;
    }
}

class SortByTitleAscending implements Comparator<Book> {
    public int compare(Book b1, Book b2) {
        return b1.getTitle().compareTo(b2.getTitle());
    }
}
