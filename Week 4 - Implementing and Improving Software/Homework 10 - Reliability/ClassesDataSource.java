import java.util.List;

/*
 * SD2x Homework #10
 * This is the empty implementation of the ClassesDataSource.
 */

public interface ClassesDataSource {

	/*
	 * Returns a List of the names of the classes 
	 * that are being taken by the specified student.
	 */
	public List<String> getClasses(String studentName) ;

}
