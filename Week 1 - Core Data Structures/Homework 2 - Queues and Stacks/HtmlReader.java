import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Queue;

/*
 * SD2x Homework #2
 * This class contains a method that will read an HTML file and convert it to a Queue of HtmlTags.
 */

public class HtmlReader {
	
	public static Queue<HtmlTag> getTagsFromHtmlFile(String filename) throws IOException {
	     InputStream stream = new FileInputStream(filename);
	     StringBuffer buffer = new StringBuffer();
	     int ch;
	     while ((ch = stream.read()) > 0) {
	         buffer.append((char) ch);
	     }
	     stream.close();
	     String htmlFileString = buffer.toString();
	     return HtmlTag.tokenize(htmlFileString);
	}

}
