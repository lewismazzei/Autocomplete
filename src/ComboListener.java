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
				//Get text from input
				String text = ((JTextField)key.getSource()).getText();
				Term[] matches = autocomplete.allMatches(text);


                if (matches.length != 0) {
					gui.setResults(matches.length + 1, k);
                    listener.setModel(new DefaultComboBoxModel(Arrays.copyOfRange(matches, 0, k)));
                } else {
                    listener.hidePopup();
                }
				//Set selected index as -1
				listener.setSelectedIndex(-1);
				((JTextField)listener.getEditor().getEditorComponent()).setText(text);
				if(!text.equals("")){
					listener.showPopup();
				}else{
					listener.hidePopup();
					gui.setResults(0, k);
				}
	}
}
