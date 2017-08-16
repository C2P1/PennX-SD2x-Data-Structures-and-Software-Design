import java.util.List;

/*
 * SD2x Homework #10
 * This is the empty implementation of the StudentsDataSource.
 */

public interface StudentsDataSource {

	/*
	 * Returns a List of students who are taking the specified class.
	 */
	public List<Student> getStudents(String className) ;
	

}
