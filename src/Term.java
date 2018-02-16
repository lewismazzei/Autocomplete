import java.util.Comparator;
import java.util.Collections;

public class Term implements Comparable<Term>{
	String query;
	long weight;

	public Term(String query, long weight) {
		this.query = query;
		this.weight = weight;
	}

	public static Comparator<Term> byReverseWeightOrder() {
		@Override
		public int compare(Term term1, Term term2){
			long weight1 = term1.weight;
			long weight2 = term2.weight;
			if(weight1 > weight2){
					return 1;
			}else if(weight1 < weight2){
					return -1;
			}
			return 0;
		}
	}

	public static Comparator<Term> byPrefixOrder(int r) {return 0;}

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
