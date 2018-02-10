import java.util.Comparator;

public class Term implements Comparable<Term>{
	String query;
	long weight;

	public Term(String query, long weight) {
		this.query = query;
		this.weight = weight;
	}

	public static Comparator<Term> byReverseWeightOrder() {return null;}

	public static Comparator<Term> byPrefixOrder(int r) {return null;}

	public int compareTo(Term that) {
		String shortestTerm = "";

		if (this.query.length() < that.query.length()) {
			shortestTerm = this.query;
		} else {
			shortestTerm = that.query;
		}
		for (int i=0; i<shortestTerm.length(); i++) {
			if ((int)this.query.charAt(i) < (int)that.query.charAt(i)) {
				return -1;
			}
			if ((int)this.query.charAt(i) > (int)that.query.charAt(i)) {
				return 1;
			}
		}
		if (this.query.length() == that.query.length()) {
			return 0;
		} else {
			if (shortestTerm.equals(this.query)) {
				return -1;
			} else {
				return 1;
			}
		}
	}

	public String toString() {return null;}
}
