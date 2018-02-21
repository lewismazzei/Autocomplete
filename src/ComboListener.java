import java.awt.event.KeyAdapter;
import java.util.ArrayList;
import java.util.Arrays;
import java.awt.event.KeyEvent;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JTextField;

public class ComboListener extends KeyAdapter {
    //combobox component
    JComboBox comboBox;
    //autocomplete object
    Autocomplete autocomplete;
    //number of results to display
    int k;

    //constructor
    @SuppressWarnings("rawtypes")
    public ComboListener(JComboBox comboBox, Autocomplete autocomplete, int k) {
        this.autocomplete = autocomplete;
        this.comboBox = comboBox;
        this.k = k;
    }

    //action to take when a key is released
    @SuppressWarnings({"unchecked", "rawtypes"})
    public void keyReleased(KeyEvent key) {
        //get text from the search bar
        String text = ((JTextField) key.getSource()).getText();
        //array to hold all terms that match the text that is currently sitting in search bar
        Term[] matches = autocomplete.allMatches(text);

        //if the search bar is empty or there are no matches then hide the combo box and exit method
        if (text.equals("") || matches == null) {
            comboBox.hidePopup();
            return;
        }
        //array to hold the queries of all the matched term objects
        ArrayList<String> matchQueries = new ArrayList<>();
        //make sure no items in the combo box are selected by default
        comboBox.setSelectedIndex(-1);
        //try to create a list of matched query strings and place them into a combo box underneath the search bar
        try {
            //create list of matched query strings
            for (Term match : matches) {
                matchQueries.add(match.getQuery());
            }
            //generate combobox with a subset of the matched query strings (size of subset depends on k)
            comboBox.setModel(new DefaultComboBoxModel(Arrays.copyOfRange(matchQueries.toArray(), 0, k)));
            //display popup
            comboBox.showPopup();
            gui.setResults(autocomplete.numberOfMatches(text), k);
            //catch exception if there are no matches
        } catch (NullPointerException e) {
            //print message to console
            System.out.println(e.getMessage());
        }
        //update search bar
        ((JTextField) comboBox.getEditor().getEditorComponent()).setText(text);
    }
}
