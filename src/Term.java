import java.util.Comparator;

public class Term implements Comparable<Term>{
	private String query;
	private long weight;

	//constructor
	public Term(String query, long weight) {
		//throw the relveant exceptions for invalid arguments
	    if (query == null) throw new NullPointerException();
	    if (weight < 0) throw new IllegalArgumentException();

	    this.query = query;
		this.weight = weight;
	}

	//compare two terms by weight: calculates the difference so the order can be determined by the sign of the result
	public static Comparator<Term> byReverseWeightOrder() {
		//returns a negative, positive or 0 value integer which indicates the order of the terms
	    return (term1, term2) -> (int)(term2.weight - term1.weight);
	}

	//compare two terms by prefix order: compares lowercase substrings of two term queries (the length the substring is determined by
	//the argument passed) lexicographically to determine their order
	public static Comparator<Term> byPrefixOrder(int r) {
			//any value less than 0 is invalid, throw an exception if this occurs
			if (r < 0) throw new IllegalArgumentException();
			//returns a negative, positive or 0 value integer which indicates the order of the terms
            return (term1, term2) -> (int)(term1.query.substring(0,r).toLowerCase().compareTo(term2.query.substring(0,r).toLowerCase()));
    }

    //compares two terms lexicographically to determine their order
	public int compareTo(Term that) {
		//returns a negative, positive or 0 value integer which indicates the order of the terms
		return this.query.toLowerCase().compareTo(that.query.toLowerCase());
	}

	//returns the weight and query of a term object seperated by a tab
	public String toString() {
	    return this.weight + "\t" + this.query;
	}

	//getter
    public String getQuery() {
        return query;
    }
}
