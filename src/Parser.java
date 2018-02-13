import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.regex.Pattern;
import java.util.regex.Matcher;
import java.util.List;
import java.util.ArrayList;

public class Parser{
	static void importData(String fileName){
		Pattern weightPattern = Pattern.compile("[^\\s](\\d*)\t");
		Pattern namePattern = Pattern.compile("(?:\t)(.*)");
		List<Term> cityList = new ArrayList<Term>();
		try{
			BufferedReader in = new BufferedReader(new FileReader(fileName));
			System.out.println("File opened successfully");
			int line = 0;
			for(String x = in.readLine(); x != null; x = in.readLine()){
				line++;
				Matcher weightMatcher = weightPattern.matcher(x);
				Matcher nameMatcher = namePattern.matcher(x);
				if(!weightMatcher.find()){
					System.out.println("No matches");
				}else{
					System.out.println(weightMatcher.group(0));
				}
				if(!nameMatcher.find()){
					System.out.println("No matched names");
				}
				if(weightMatcher.find() && nameMatcher.find()){
					long weight = Long.parseLong(weightMatcher.group(0), 10);
					Term city = new Term(nameMatcher.group(0), weight);
					cityList.add(city);
					System.out.println(city.query);
				}
			}
			for(int i = 0; i < cityList.size(); i++){
				System.out.println(cityList.get(i).query);
			}
		}catch(IOException e){
			System.out.println("File IO Error!");
		}
	}
}
