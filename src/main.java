import java.util.ArrayList;

public class Main {

	public static void main(String[] args) {
		ArrayList<Term> cities = Parser.importData("cities.txt");

		for (Term city : cities) {
			System.out.println(city.toString());
		}
	}
}
