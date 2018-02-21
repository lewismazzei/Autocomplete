import java.util.List;
import java.util.Arrays;
import java.lang.String;
import java.util.Collections;


public class Autocomplete {
	private final Term[] terms;
	// Initializes the data structure from the given array of terms.
	public Autocomplete(Term[] terms) {
		Collections.sort(Arrays.asList(terms));
		this.terms = terms;
	}
	// Returns all terms that start with the given prefix, in descending order of weight.
	public Term[] allMatches(String prefix) {
		//find the first and last indexes that match the prefix
		int firstIndex = BinarySearchDeluxe.firstIndexOf(this.terms, new Term(prefix, 0), Term.byPrefixOrder(prefix.length()));
		int lastIndex = BinarySearchDeluxe.lastIndexOf(this.terms, new Term(prefix, 0), Term.byPrefixOrder(prefix.length()));

		//intialise matches array to null, there might not be any matches
		Term[] matches = null;

		//if there are matches...
		if (firstIndex >= 0 && lastIndex >= 0) {
			//...create a subset of the terms array between the first and last match
			matches = Arrays.copyOfRange(this.terms, firstIndex, lastIndex+1);
			//convert array to list so it can be sorted
			List<Term> matchesList = Arrays.asList(matches);
			//sort matches by reverse weight order
			matchesList.sort(Term.byReverseWeightOrder());
			//convert list back to array before returning it
			matchesList.toArray(matches);
		}
		return matches;
	}

	// Returns the number of terms that start with the given prefix.
	public int numberOfMatches(String prefix) {
		int firstIndex = BinarySearchDeluxe.firstIndexOf(terms, new Term(prefix, 0), Term.byPrefixOrder(prefix.length()));
		int lastIndex = BinarySearchDeluxe.lastIndexOf(terms, new Term(prefix, 0), Term.byPrefixOrder(prefix.length()));

		if (firstIndex < 0 || lastIndex < 0) {
			return 0;
		} else {
			return (lastIndex - firstIndex);
		}
	}

	public Term[] getTerms() {
		return terms;
	}
}
//this took me ages to work out, realised i didn't need it but keeping it around in case it turns out we do need it for some reason
//lowerCaseCities = lowerCaseCities.stream().map(term -> new Term(term.getQuery().toLowerCase(), term.getWeight())).collect(Collectors.toList());
