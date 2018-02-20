import java.awt.event.KeyAdapter;
import java.util.Arrays;
import java.util.ArrayList;
import java.awt.event.KeyEvent;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JTextField;

public class ComboListener extends KeyAdapter{
	JComboBox listener;
	Term[] array;
	Autocomplete autocomplete = new Autocomplete(Parser.importData("cities.txt"));
	
	@SuppressWarnings("rawtypes")
	public ComboListener(JComboBox listenerParam, Term[] arrayParam){
		listener = listenerParam;
		array = arrayParam;
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void keyTyped(KeyEvent key){
				//Get text from input
				String text = ((JTextField)key.getSource()).getText();
				//Set list as filtered list from other method
				listener.setModel(new DefaultComboBoxModel(getFilteredList(text)));
				//Set selected index as -1
				listener.setSelectedIndex(-1);
				((JTextField)listener.getEditor().getEditorComponent()).setText(text);
				if(text != ""){
					listener.showPopup();
				}else{
					listener.hidePopup();
				}
	}
	
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public String[] getFilteredList(String text){
		long startTime = System.nanoTime();
		int firstIndex = BinarySearchDeluxe.firstIndexOf(array, new Term(text, 0), Term.byPrefixOrder(text.length()));
		int lastIndex = BinarySearchDeluxe.lastIndexOf(array, new Term(text, 0), Term.byPrefixOrder(text.length()));
		//System.out.println(firstIndex + " " + lastIndex);	
		Term[] matches = Arrays.copyOfRange(array, firstIndex, lastIndex);
		java.util.List<String> matchedQueries = new ArrayList<String>();
		for(Term match : matches){
			matchedQueries.add(match.getQuery());
		}
		String[] finalArray = new String[matchedQueries.size()];
		matchedQueries.toArray(finalArray);
		long endTime = System.nanoTime();
		long duration = ((endTime - startTime)/1000000);
		//System.out.println(duration);
		return finalArray;

		//java.util.List<String> matchedQueries = new ArrayList<String>();
		//Term[] terms = autocomplete.allMatches(text);
		//for(Term term : terms ){
		//	matchedQueries.add(term.getQuery());
		//}
		//String[] finalArray = new String[matchedQueries.size()];
		//matchedQueries.toArray(finalArray);
		//return finalArray;
	}
}
