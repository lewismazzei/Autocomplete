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
		Term[] cities = Parser.importData("cities.txt");

		String prefix = "glas";

		List<Term> sortedCities = Arrays.asList(cities);

		Collections.sort(sortedCities);

		sortedCities.toArray(cities);

		int firstIndex = BinarySearchDeluxe.firstIndexOf(cities, new Term(prefix, 0), Term.byPrefixOrder(prefix.length()));
		int lastIndex = BinarySearchDeluxe.lastIndexOf(cities, new Term(prefix, 0), Term.byPrefixOrder(prefix.length()));

		Term[] matches = Arrays.copyOfRange(cities, firstIndex, lastIndex);

		for (Term match : matches) {
			System.out.println(match.getQuery());
		}
	}
}
//this took me ages to work out, realised i didn't need it but keeping it around in case it turns out we do need it for some reason
//lowerCaseCities = lowerCaseCities.stream().map(term -> new Term(term.getQuery().toLowerCase(), term.getWeight())).collect(Collectors.toList());