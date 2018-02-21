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
            return (term1, term2) -> (int)(term1.query.substring(0,r).toLowerCase().compareTo(term2.query.substring(0,r).toLowerCase()));

    }

	public int compareTo(Term that) {
		return this.query.toLowerCase().compareTo(that.query.toLowerCase());
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
