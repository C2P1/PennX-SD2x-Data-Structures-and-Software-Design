/*
 * SD2x Homework #5
 */
import java.util.*;


public class MovieRatingsProcessor {

  /*
   * return a List of movie titles in alphabetical order
   */
	public static List<String> getAlphabeticalMovies(TreeMap<String, PriorityQueue<Integer>> movieRatings) {

	    List<String> movies = new ArrayList<>();

	    if (movieRatings == null || movieRatings.isEmpty()) return movies;

        for (Map.Entry<String,PriorityQueue<Integer>> entry : movieRatings.entrySet()) {
            movies.add(entry.getKey());
        }
		return movies;
	}

	/*
	 * given an input int rating, return a List of movie titles in alphabetical order, where
	 * all movies in the List do not have any ratings less than or equal to rating
	 * (hint: the PriorityQueue is a min-heap, meaning that the smallest rating is at the front of the queue!)
	 */

	public static List<String> getAlphabeticalMoviesAboveRating(TreeMap<String, PriorityQueue<Integer>> movieRatings, int rating) {

        List<String> movies = new ArrayList<>();

        if (movieRatings == null || movieRatings.isEmpty() || rating < 0) return movies;

        for (Map.Entry<String,PriorityQueue<Integer>> entry : movieRatings.entrySet()) {
            String name = entry.getKey();
            int movieRating = entry.getValue().peek(); // get first rating as that is smallest
            if (movieRating > rating) {
                movies.add(name);
            }
        }
		return movies;
	}

	/*
	 * given an input int rating, modify the TreeMap object that was passed as an argument so
	 * that you remove all ratings in the PriorityQueue that are below (but not equal to)
	 * rating for every movie in the Map. If all ratings are removed from a movie’s PriorityQueue,
	 * then remove the mapping from the TreeMap. Additionally, this method should return a new
	 * TreeMap that maps a movie title to the number of ratings that were removed from its
	 * corresponding PriorityQueue; the TreeMap that is returned should only contain movies
	 * that had ratings removed from its PriorityQueue.
	 */
	public static TreeMap<String, Integer> removeAllRatingsBelow(TreeMap<String, PriorityQueue<Integer>> movieRatings, int rating) {

		TreeMap<String, Integer> moviesRemovedRatings = new TreeMap<>();

        if (movieRatings == null || movieRatings.isEmpty()) return moviesRemovedRatings;

        for (Iterator<Map.Entry<String, PriorityQueue<Integer>>> iterator = movieRatings.entrySet().iterator();
             iterator.hasNext();) {

            // x is each movie and all it's ratings
            Map.Entry<String, PriorityQueue<Integer>> x = iterator.next();
            String movieName = x.getKey();
            PriorityQueue<Integer> ratings = x.getValue();
            int ratingsRemoved;

            // store all the ratings of the current movie which need to be removed
            List<Integer> toRemove = new ArrayList<>();
            for (Integer movieRating : ratings) {
                if (movieRating < rating) {
                    toRemove.add(movieRating);
                }
            }

            // remove all discarded ratings from the list
            ratings.removeAll(toRemove);
            ratingsRemoved = toRemove.size();

            // if all ratings are removed, remove the movie from the list of movies
            if (ratings.size() == 0) {
                iterator.remove();
            }

            // store any movies which have had ratings removed
            if (ratingsRemoved != 0) {
                moviesRemovedRatings.put(movieName, ratingsRemoved);
            }
        }
        return moviesRemovedRatings;
	}
}
