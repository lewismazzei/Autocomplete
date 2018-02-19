import java.util.ArrayList;
import java.util.Arrays;

public class Main {
	public static void main(String[] args) {
		Term[] cities = Parser.importData("cities.txt");

		String prefix = "Sha";

		for (Term city : cities) {
			//System.out.println(city.toString());
		}

		Arrays.sort(cities, Term.byPrefixOrder(3));

		//System.out.println("\nAFTER:\n");
		for (Term city : cities) {
			//System.out.println(city.toString());
		}

		int firstIndex = BinarySearchDeluxe.firstIndexOf(cities, new Term(prefix, 0), Term.byPrefixOrder(prefix.length()));

		ArrayList<Integer> keyIndices = BinarySearchDeluxe.findKeyIndices(cities, new Term(prefix, 0), Term.byPrefixOrder(prefix.length()));

		for (int index : keyIndices) {
			//System.out.println("\nAll matches:\n");
			//System.out.println(cities[index].toString());
		}

		//System.out.println("\nFirst index of \"" + prefix + "\" is: " + firstIndex);
		//System.out.println("Term: " + cities[firstIndex].toString());
	}
}
