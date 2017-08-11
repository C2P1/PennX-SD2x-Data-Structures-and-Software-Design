/*
 * SD2x Homework #5
 */

import java.util.*;

public class MovieRatingsParser {

	public static TreeMap<String, PriorityQueue<Integer>> parseMovieRatings(List<UserMovieRating> allUsersRatings) {

		TreeMap<String, PriorityQueue<Integer>> map = new TreeMap<String, PriorityQueue<Integer>>();

		if (allUsersRatings == null || allUsersRatings.isEmpty()) {
            System.out.println("List is null or empty");
            return map;
		}

		for (UserMovieRating rating : allUsersRatings) {
		    if (rating == null) {
                System.out.println("Null rating found");
                continue;
            }
		    if (rating.getMovie() == null || rating.getMovie().equals("")) {
                System.out.println("Movie with no name found");
                continue;
            }
		    if (rating.getUserRating() < 0) {
                System.out.println("Invalid user rating found for movie: " + rating.getMovie());
                continue;
            }
            if (map.containsKey(rating.getMovie().toLowerCase())) {
                PriorityQueue<Integer> moviesRatings = map.get(rating.getMovie().toLowerCase());
                moviesRatings.add(rating.getUserRating());
                map.put(rating.getMovie().toLowerCase(), moviesRatings);
            } else {
                PriorityQueue<Integer> moviesRatings = new PriorityQueue<>();
                moviesRatings.add(rating.getUserRating());
                map.put(rating.getMovie().toLowerCase(), moviesRatings);
            }
        }
		return map;
	}

    public static void main(String[] args) {
        List<UserMovieRating> ratings = new ArrayList<>();
        ratings.add(new UserMovieRating("Deadpool", 5));
        ratings.add(new UserMovieRating("deadpool", 5));
        ratings.add(new UserMovieRating("Inglorious Basterds", 4));
        ratings.add(new UserMovieRating(null, 1));
        ratings.add(new UserMovieRating("mice", -5));
        ratings.add(new UserMovieRating("abba", 30));

        TreeMap<String, PriorityQueue<Integer>> mapOfRatings = parseMovieRatings(ratings);

        if (mapOfRatings == null) return;

        for (Map.Entry<String,PriorityQueue<Integer>> entry : mapOfRatings.entrySet()) {
            String key = entry.getKey();
            PriorityQueue<Integer> heap = entry.getValue();
            System.out.print(key + " ");
            for (Integer val : heap) {
                System.out.print(val + " ");
            }
            System.out.println("\n");
        }
    }
}
