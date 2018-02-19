import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.regex.Pattern;
import java.util.regex.Matcher;
import java.util.ArrayList;

public class Parser{
	public static Term[] importData(String fileName) {
		Pattern weightPattern = Pattern.compile("[^\\s](\\d*)\t");
		Pattern namePattern = Pattern.compile("(?:\t)(.*)");
		ArrayList<Term> terms = new ArrayList<>();

		try (BufferedReader in = new BufferedReader(new FileReader(fileName))) {

			String line; in.readLine();

			while ((line = in.readLine()) != null) {
				Matcher weightMatcher = weightPattern.matcher(line);
				Matcher nameMatcher = namePattern.matcher(line);
				
				if (!weightMatcher.find()) {
					System.out.println("No match for weight");
					continue;
				}
				if (!nameMatcher.find()) {
					System.out.println("No match for name");
					continue;
				}
				
				terms.add(new Term(nameMatcher.group(0).trim(), Long.parseLong(weightMatcher.group(0).trim())));
			}
		} catch (FileNotFoundException e) {
			System.out.println("File could not be found " + e.getMessage());
		} catch (IOException e){
			System.out.println("IO Error: " + e.getMessage());
		}
		return terms.toArray(new Term[terms.size()]);
	}
}
