import java.util.Comparator;

public class Term implements Comparable<Term>{
	private final String QUERY;
	private final long WEIGHT;

	public Term(String QUERY, long WEIGHT) {
		this.QUERY = QUERY;
		this.WEIGHT = WEIGHT;
	}

	public static Comparator<Term> byReverseWeightOrder() {
	    return (term1, term2) -> (int)(term2.WEIGHT - term1.WEIGHT);
	}

	public static Comparator<Term> byPrefixOrder(int r) {
	    return (term1, term2) -> (int)(term1.QUERY.substring(0,r+1).compareTo(term2.QUERY.substring(0,r+1)));
    }

	public int compareTo(Term that) {
        //find the shortest query to determine how many characters we need to loop through (if they're the same length this is arbitrary)
		String shortestTerm = this.QUERY.length() < that.QUERY.length() ? this.QUERY : that.QUERY;

		//loop through as many characters as the shortest term has, if at any point the characters are not the same then the lexicographic order
        // can be determined and a relevant integer value can be returned
		for (int i=0; i<shortestTerm.length(); i++) {
		    if ((int)this.QUERY.charAt(i) != (int)that.QUERY.charAt(i)) {
		        return (int)this.QUERY.charAt(i) - (int)that.QUERY.charAt(i);
            }
		}
		//if the characters have all matched and the queries are the same length then the words are equal so return 0
		if (this.QUERY.length() == that.QUERY.length()) {
			return 0;
        //if they are not the same length then determine the order based on the length of the queries (shortest query is lexicographically 'first')
		} else {
			return this.QUERY.length() - that.QUERY.length();
		}
	}

	public String toString() {
	    return this.WEIGHT + " : " + this.QUERY;
	}

    public String getQUERY() {
        return QUERY;
    }

    public long getWEIGHT() {
        return WEIGHT;
    }
}
