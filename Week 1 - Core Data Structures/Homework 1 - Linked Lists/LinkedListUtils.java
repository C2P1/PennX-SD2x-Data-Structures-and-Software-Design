import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;

/*
 * SD2x Homework #1
 */
public class LinkedListUtils {

    public static void main(String[] args) {
//        LinkedList<Integer> list = new LinkedList<>();
//        list.add(1);
//        list.add(3);
//        list.add(4);
//        list.add(7);
//        list.add(9);
//        list.add(10);
//        System.out.println(list.toString());
//        insertSorted(list, 5);
//        insertSorted(list, 20);
//        System.out.println(list.toString());

//        LinkedList<String> stringList = new LinkedList<>();
//        stringList.add("hello");
//        stringList.add("meow");
//        stringList.add("cat");
//        stringList.add("add");
//        stringList.add("marvelous");
//        stringList.add("marvelous");
//        stringList.add("woods");
//        System.out.println(stringList.toString());
//        removeMaximumValues(stringList, 9);
//        System.out.println(stringList.toString());

        LinkedList<Integer> one = new LinkedList<>();
        one.add(5);
        one.add(3);
        one.add(7);
        one.add(9);
        one.add(18);
        one.add(27);
        System.out.println("one: " + one.toString());

        LinkedList<Integer> two = new LinkedList<>();
        two.add(27);
        System.out.println("two: " + two.toString());

        System.out.println(containsSubsequence(one, two));

    }

    public static void insertSorted(LinkedList<Integer> list, int value) {

        if (list != null) {
            if (list.isEmpty()) {
                list.add(value);
                return;
            } else if (list.get(list.size() - 1) < value) {
                list.add(list.size(), value);
                return;
            }
            for (Integer i : list) {
                if (value < i) {
                    list.add(list.indexOf(i), value);
                    return;
                }
            }
        }
    }


    public static void removeMaximumValues(LinkedList<String> list, int N) {

        if (list == null || N < 0) {
            return;
        } else if (N > list.size()) {
            list.removeAll(list);
        } else {

            // find the longest word(s)
            // remove them
            // find the 2nd longest word(s)
            // remove them
            // ...
            // find the Nth longest word(s)
            // remove them

            for (int j = 0; j < N; j++) {
                int largestString = list.get(0).length();

                ArrayList<String> indexes = new ArrayList<>();

                // get the longest string
                for (int i = 0; i < list.size(); i++) {
                    if (list.get(i).length() > largestString) {
                        largestString = list.get(i).length();
                    }
                }

                // get all strings equal to longest string
                for (int i = 0; i < list.size(); i++) {
                    if (list.get(i).length() == largestString) {
                        indexes.add(list.get(i));
                    }
                }

                // remove all strings equal to longest string
                list.removeAll(indexes);
            }
        }
    }

    public static boolean containsSubsequence(LinkedList<Integer> one, LinkedList<Integer> two) {
        if (one != null && two != null) {
            if (one.isEmpty() || two.isEmpty()) {
                return false;
            }
            for (int i = 0; i < one.size(); i++) {
                for (int j = i; j <= one.size(); j++) {
                    if (one.subList(i, j).equals(two)) {
                        return true;
                    }
                }
            }
            return false;
        }
        return false;
    }
}
