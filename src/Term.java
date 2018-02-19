import java.util.Comparator;

public class Term implements Comparable<Term>{
	private String query;
	private long weight;

	public Term(String query, long weight) {
	    if (query == null) throw new NullPointerException();
	    if (weight < 0) throw new IllegalArgumentException();

	    this.query = query;
		this.weight = weight;
	}

	public static Comparator<Term> byReverseWeightOrder() {
	    return (term1, term2) -> (int)(term2.weight - term1.weight);
	}

	public static Comparator<Term> byPrefixOrder(int r) {
	    return (term1, term2) -> (int)(term1.query.substring(0,r).compareTo(term2.query.substring(0,r)));
    }

	public int compareTo(Term that) {

        //convert query strings to lowercase so that capitilised characters are treated the same as non-capitalised ones
        String query1 = this.query.toLowerCase();
        String query2 = that.query.toLowerCase();

		System.out.println(query1 + " " + query2);

        //find the shortest query to determine how many characters we need to loop through (if they're the same length this is arbitrary)
		String shortestTerm = query1.length() < query2.length() ? query1 : query2;

		System.out.println(shortestTerm);

		//loop through as many characters as the shortest term has, if at any point the characters are not the same then the lexicographic order
        // can be determined and a relevant integer value can be returned
		for (int i=0; i<shortestTerm.length(); i++) {
		    if ((int)query1.charAt(i) != (int)query2.charAt(i)) {
				System.out.println("Char order: " + ((int)query1.charAt(i) - (int)query2.charAt(i)));
				return (int)query1.charAt(i) - (int)query2.charAt(i);
            }
		}
		//if the characters have all matched and the queries are the same length then the words are equal so return 0
		if (query1.length() == query2.length()) {
			System.out.println(0);
			return 0;
        //if they are not the same length then determine the order based on the length of the queries (shortest query is lexicographically 'first')
		} else {
			System.out.println("Length order: " + (query1.length() - query2.length()));
			return query1.length() - query2.length();
		}
	}

	public String toString() {
	    return this.weight + "\t" + this.query;
	}

    public String getQuery() {
        return query;
    }

    public long getWeight() {
        return weight;
    }
}
