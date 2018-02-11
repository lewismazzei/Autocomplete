import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class Parser{
	static void importData(String fileName){
		Pattern weightPattern = Pattern.compile("[^\\s](.*)\t");
		Pattern namePattern = Pattern.compile("(?:\t)(.*)");
		try{
			BufferedReader in = new BufferedReader(new FileReader(fileName));
			System.out.println("File opened successfully");
			int line = 0;
			for(String x = in.readLine(); x != null; x = in.readLine()){
				line++;
				Matcher weightMatcher = weightPattern.matcher(x);
				Matcher nameMatcher = namePattern.matcher(x);
				if(weightMatcher.find()){
					System.out.println(weightMatcher.group(0));
				}else{
					System.out.println("No matches");
				}
				if(nameMatcher.find()){
					System.out.println(nameMatcher.group(0));
				}else{
					System.out.println("No matched names");
				}
			}
		}catch(IOException e){
			System.out.println("File IO Error!");
		}
	}
}
