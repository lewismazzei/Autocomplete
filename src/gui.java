import javax.swing.*;
import java.text.ParseException;
import java.net.URL;
import java.net.MalformedURLException;
import javax.swing.plaf.synth.SynthLookAndFeel;
import java.io.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.Vector;
import java.util.Collections;

public class gui {

    static void initGui() {
        //Will use this later to customise the gui
        String xmlFile = "laf.xml";
        SynthLookAndFeel laf = new SynthLookAndFeel();

        try {
            laf.load(gui.class.getResourceAsStream(xmlFile), gui.class);
        } catch (Exception e) {
            System.out.println(e);
        }

        try {
            UIManager.setLookAndFeel(laf);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

		JFrame f = new JFrame();
		f.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		if(shouldFill){
			c.fill = GridBagConstraints.HORIZONTAL;
		}
		
		//Sort out the array
		Term[] cities = Parser.importData("cities.txt");
		java.util.List<Term> searchQueryList = Arrays.asList(cities);
		Collections.sort(searchQueryList);
		searchQueryList.toArray(cities);
		
		//Create combobox and add Key Listener
		JLabel destination = new JLabel("Destination:");
		final JComboBox searchCombo = new JComboBox();
		searchCombo.setPrototypeDisplayValue("xxxxxxxxxxxxxxx");
		searchCombo.setEditable(true);
		JTextField editor = (JTextField) searchCombo.getEditor().getEditorComponent();
		editor.setFocusable(true);
		editor.setText("");
		editor.addKeyListener(new ComboListener(searchCombo, cities));
		if(shouldWeightX){
			c.weightx = 0.5;
		}
		c.gridx = 0;
		c.gridy = 0;


		JButton showButton = new JButton("Search");
		
		//Show everything
		f.add(destination, c);
		c.gridx = 0;
		c.gridy = 1;
		f.add(searchCombo, c);
		c.gridx = 1;
		c.gridy = 1;
		f.add(showButton, c);
		f.setVisible(true);
	}
}
