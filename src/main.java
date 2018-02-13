
public class main{
	public static void main(String[] args){
		Term london = new Term("London", 100);
		Term glasgow = new Term("Glasgow", 50);
		
		int val = london.compareTo(glasgow);
		System.out.println(val);
		Parser.importData("cities.txt");
	}
}
