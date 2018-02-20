import java.awt.event.KeyAdapter;
import java.util.Arrays;
import java.util.ArrayList;
import java.awt.event.KeyEvent;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JTextField;

public class ComboListener extends KeyAdapter{
	JComboBox listener;
	Autocomplete autocomplete;
	int k;	
	@SuppressWarnings("rawtypes")
	public ComboListener(JComboBox listener, Autocomplete autocomplete, int k){
		this.autocomplete = autocomplete;
		this.listener = listener;
		this.k = k;
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void keyReleased(KeyEvent key){
				int i = 0;
				//Get text from input
				String text = ((JTextField)key.getSource()).getText();
				System.out.println(text);
				Term[] termMatches = autocomplete.allMatches(text);
				gui.setResults(termMatches.length + 1, k);
				//for (Term termmatch : termMatches) {
				//	System.out.print(termmatch);
				//}
				ArrayList<String> matches = new ArrayList<>();
				

				if (termMatches != null) {
					for (Term match : termMatches) {
						if(i < 5){
						matches.add(match.getQuery());
						i++;
						}
						//System.out.println(match);
					}
				}


				//Set list as filtered list from other method
				//get all queries from the term list
				listener.setModel(new DefaultComboBoxModel(matches.toArray()));
				//Set selected index as -1
				listener.setSelectedIndex(-1);
				((JTextField)listener.getEditor().getEditorComponent()).setText(text);
				if(text != ""){
					listener.showPopup();
				}else{
					listener.hidePopup();
				}
	}
}
