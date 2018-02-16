import java.util.Collections;
import java.util.List;
public class main{
	public static void main(String[] args){
		Term london = new Term("London", 100);
		Term glasgow = new Term("Glasgow", 50);
		
		//int val = london.compareTo(glasgow);
		//System.out.println(val);
		//Parser.importData("cities.txt");
		List<Term> terms = new ArrayList<Term>();
		terms.add(london);
		terms.add(glasgow);
		Collections.sort(terms, Term.byReverseWeightOrder());
	}
}
