import java.awt.event.KeyAdapter;
import java.util.Arrays;
import java.util.ArrayList;
import java.awt.event.KeyEvent;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JTextField;

public class ComboListener extends KeyAdapter
{
	JComboBox listener;
	Term[] array;
	
	@SuppressWarnings("rawtypes")
	public ComboListener(JComboBox listenerParam, Term[] arrayParam)
	{
		listener = listenerParam;
		array = arrayParam;
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void keyTyped(KeyEvent key)
	{
				//Get text from input
				String text = ((JTextField)key.getSource()).getText();
				//Set list as filtered list from other method
				listener.setModel(new DefaultComboBoxModel(getFilteredList(text)));
				//Set selected index as -1
				listener.setSelectedIndex(-1);
				((JTextField)listener.getEditor().getEditorComponent()).setText(text);
				listener.showPopup();
	}
	
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public String[] getFilteredList(String text)
	{
		int firstIndex = BinarySearchDeluxe.firstIndexOf(array, new Term(text, 0), Term.byPrefixOrder(text.length()));
		int lastIndex = BinarySearchDeluxe.lastIndexOf(array, new Term(text, 0), Term.byPrefixOrder(text.length()));
		Term[] matches = Arrays.copyOfRange(array, firstIndex, lastIndex);
		java.util.List<String> matchedQueries = new ArrayList<String>();
		for(Term match : matches){
			matchedQueries.add(match.getQuery());
		}
		String[] finalArray = new String[matchedQueries.size()];
		matchedQueries.toArray(finalArray);
		return finalArray;
	}
}
