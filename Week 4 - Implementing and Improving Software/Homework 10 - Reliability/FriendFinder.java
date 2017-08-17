import java.util.List;
import java.util.HashSet;
import java.util.Set;

/*
 * SD2x Homework #10
 * Modify the method below so that it uses defensive programming.
 */

public class FriendFinder {

	protected ClassesDataSource classesDataSource;
	protected StudentsDataSource studentsDataSource;

	public FriendFinder(ClassesDataSource cds, StudentsDataSource sds) {
		classesDataSource = cds;
		studentsDataSource = sds;
	}


	public Set<String> findClassmates(Student theStudent) {

		if (theStudent == null) {
			System.out.println("null student");
			throw new IllegalArgumentException("Student is null");
		}

		String name = theStudent.getName();

		if (name == null) {
			System.out.println("null name");
			throw new IllegalArgumentException();
		}

		if (classesDataSource == null) {
			System.out.println("no data source class");
			throw new IllegalStateException();
		}

		if (studentsDataSource == null) {
			System.out.println("no data source class");
			throw new IllegalStateException();
		}
		// find the classes that this student is taking
		List<String> myClasses = classesDataSource.getClasses(name);

		if (myClasses == null) {
			System.out.println("null class");
			return null;
		}
		if (myClasses.isEmpty()) {
			System.out.println("empty class");
			return null;
		}

		Set<String> classmates = new HashSet<String>();

		// use the classes to find the names of the students
		for (String myClass : myClasses) {
			if (myClass == null) {
				continue;
			}

			// list all the students in the class
			List<Student> students = studentsDataSource.getStudents(myClass);

			if (students == null) {
				return null;
			}
			if (students.size() == 1) {
				return null;
			}

			for (Student student : students) {
				if (student == null) {
					continue;
				}
				if (student.getName() == null) {
					continue;
				}
				// find the other classes that they're taking
				List<String> theirClasses = classesDataSource.getClasses(student.getName());
				if (theirClasses == null) {
					throw new IllegalStateException();
				}

				// see if all of the classes that they're taking are the same as the ones this student is taking
				boolean same = true;
				for (String c : myClasses) {
					if (c == null) {
						continue;
					}
					if (!theirClasses.contains(c)) {
						same = false;
						break;
					}
				}
				if (same) {
					if (!student.getName().equals(name) && !classmates.contains(student.getName()))
						classmates.add(student.getName());
				}
			}

		}

		if (classmates.isEmpty()) {
			return null;
		}
		else return classmates;
	}


}
