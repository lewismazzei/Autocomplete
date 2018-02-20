import java.util.List;
import java.util.Arrays;
import java.lang.String;
import java.util.Collections;


public class Autocomplete {
	private final Term[] terms;
	// Initializes the data structure from the given array of terms.
	public Autocomplete(Term[] terms) {
		this.terms = terms;
	}
	// Returns all terms that start with the given prefix, in descending order of weight.
	public Term[] allMatches(String prefix) {
		Collections.sort(Arrays.asList(terms));
		//find the first
		int firstIndex = BinarySearchDeluxe.firstIndexOf(terms, new Term(prefix, 0), Term.byPrefixOrder(prefix.length()));
		int lastIndex = BinarySearchDeluxe.lastIndexOf(terms, new Term(prefix, 0), Term.byPrefixOrder(prefix.length()));

		System.out.println(firstIndex + " : " + lastIndex);
		Term[] matches = null;

		if (firstIndex >= 0 && lastIndex >= 0) {
			System.out.println("hi");
			matches = Arrays.copyOfRange(terms, firstIndex, lastIndex);

			List<Term> matchesList = Arrays.asList(matches);

			matchesList.sort(Term.byReverseWeightOrder());

			matchesList.toArray(matches);
		}
		return matches;
	}

	// Returns the number of terms that start with the given prefix.
	public int numberOfMatches(String prefix) {
		Collections.sort(Arrays.asList(terms));

		int firstIndex = BinarySearchDeluxe.firstIndexOf(terms, new Term(prefix, 0), Term.byPrefixOrder(prefix.length()));
		int lastIndex = BinarySearchDeluxe.lastIndexOf(terms, new Term(prefix, 0), Term.byPrefixOrder(prefix.length()));

		if (firstIndex < 0 || lastIndex < 0) {
			return 0;
		} else {
			return lastIndex - firstIndex;
		}
	}

	public Term[] getTerms() {
		return terms;
	}
}
//this took me ages to work out, realised i didn't need it but keeping it around in case it turns out we do need it for some reason
//lowerCaseCities = lowerCaseCities.stream().map(term -> new Term(term.getQuery().toLowerCase(), term.getWeight())).collect(Collectors.toList());
