/*
 * SD2x Homework #8
 * This class represents the Data Tier in the three-tier architecture.
 */

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DataTier {
	
	private String fileName; // the name of the file to read
	
	public DataTier(String inputSource) {
		fileName = inputSource;
	}

    /**
     *  read the data file containing information about the books,
     *  create Book objects for each, and then return the Book objects.
     *
     *  it is okay for getAllBooks to assume that the input file exists and
     *  is well-formatted.
     */
	public List<Book> getAllBooks() {
        ArrayList<Book> books = new ArrayList<>();
	    try {
            File file = new File(fileName);
            FileReader fileReader = new FileReader(file);
            BufferedReader reader = new BufferedReader(fileReader);

            String currentLine;

            while ((currentLine = reader.readLine()) != null) {
                String[] split = currentLine.split("\t");
                if (split.length == 3) {
                    String title = split[0];
                    String author = split[1].toLowerCase();
                    int year = Integer.valueOf(split[2]);

                    Book newBook = new Book(title, author, year);
                    books.add(newBook);

                } else {
                    System.out.println("Incorrectly formatted line");
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return books;
	}
}
