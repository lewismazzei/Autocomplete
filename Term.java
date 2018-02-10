public class Term implements Comparable<Term>{
	String query;
	long weight;

	public Term(String query, long weight) {
		this.query = query;
		this.weight = weight;
	}

	public static Comparator<Term> byReverseWeightOrder() {}

	public static Comparator<Term> byPrefixOrder(int r) {}

	public int compareTo(Term that) {
		String shortestTerm = "";

		if (this.length() < that.length()) {
			shortestTerm = this;
		} else {
			shortestTerm = that;
		}
		for (int i=0; i<shortestTerm.length(); i++) {
			if ((int)this[i] < (int)that[i]) {
				return -1;
			}
			else if ((int)this[i] == (int)that[i]) {
				continue
			}
			else {
				return 1
			}
		}
		if (this.length() == that.length()) {
			return 0
		} else {
			if (shortestTerm == this) {
				return -1
			} else {
				return 1
			}
		}
	}

	public String toString() {}
}
