import java.util.List;
import java.util.Arrays;
import java.lang.String;
import java.util.Collections;


public class Autocomplete {
	public Term[] queries;
	// Initializes the data structure from the given array of terms.
	public Autocomplete(Term[] terms) {

	}
	// Returns all terms that start with the given prefix, in descending order of weight.
	public Term[] allMatches(String prefix) {
		//PLACEHOLDER
		return new Term[]{new Term("X", 0), new Term("Y", 0)};
	}

	// Returns the number of terms that start with the given prefix.
	public int numberOfMatches(String prefix) {
		//PLACEHOLDER
		return 0;
	}

	public static void main(String[] args) {
		//initialise array, import data
		Term[] cities = Parser.importData("cities.txt");
		//cast array to list
		List<Term> sortedCities = Arrays.asList(cities);
		//sort the list lexicographically
		Collections.sort(sortedCities);
		//cast list back to array
		sortedCities.toArray(cities);
		System.out.println(cities[cities.length-1]);
		//input string
		String prefix = "진";
		//find first and last occurence of queries matching the input string
		int firstIndex = BinarySearchDeluxe.firstIndexOf(cities, new Term(prefix, 0), Term.byPrefixOrder(prefix.length()));
		int lastIndex = BinarySearchDeluxe.lastIndexOf(cities, new Term(prefix, 0), Term.byPrefixOrder(prefix.length()));
		System.out.println(firstIndex + " : " + lastIndex);
		System.out.println(cities[firstIndex].getQuery() + " : " + cities[lastIndex].getQuery());
		//create an array of matches by taking the subset of the full array between the first and last occurence
		Term[] matches = null;
		try {
			matches = Arrays.copyOfRange(cities, firstIndex, lastIndex);
		} catch (ArrayIndexOutOfBoundsException e) {
			System.out.println("First Index: " + firstIndex + "\nLast Index: " + lastIndex);
		}

		//print out matches
		//for (Term match : matches) {
		//	System.out.println(match.getQuery());
		//}
		//for (Term match : matches) {
		//	System.out.println(match.getQuery());
		//}
		gui.initGui();
	}
}
//this took me ages to work out, realised i didn't need it but keeping it around in case it turns out we do need it for some reason
//lowerCaseCities = lowerCaseCities.stream().map(term -> new Term(term.getQuery().toLowerCase(), term.getWeight())).collect(Collectors.toList());