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
	final static boolean shouldFill = true;
	final static boolean shouldWeightX = true;
	static int results;
	static JLabel outOfText = new JLabel();

	static void initGui(Autocomplete autocomplete, int k){
		
		//Will use this later to customise the gui
		String xmlFile = "laf.xml";
		SynthLookAndFeel laf = new SynthLookAndFeel();
		try{
		laf.load(gui.class.getResourceAsStream(xmlFile), gui.class);
		}catch(Exception e){
			System.out.println(e);			
		}
		try{
		UIManager.setLookAndFeel(laf);
		}catch(Exception e){
			System.out.println(e);
		}

		JFrame f = new JFrame();
		f.setLayout(new GridBagLayout());
		f.setPreferredSize(new Dimension(400,300));
		f.setResizable(false);
		GridBagConstraints c = new GridBagConstraints();
		if(shouldFill){
			c.fill = GridBagConstraints.HORIZONTAL;
		}
		c.insets = new Insets(20,20,20,20);
				
		c.fill = GridBagConstraints.HORIZONTAL;

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
		editor.addKeyListener(new ComboListener(searchCombo, autocomplete, k));
		if(shouldWeightX){
			c.weightx = 0.5;
		}
		c.weighty = 0.0;
		c.gridx = 1;
		c.gridy = 0;


		//Add Logo
		JLabel logo = new JLabel("SatNav");
		logo.setFont(new Font("Serif", Font.PLAIN, 52));
		//Add search button
		JButton showButton = new JButton("Search");

		
		//Add everything
		f.add(logo, c);
		c.gridx = 1;
		c.gridy = 1;
		f.add(destination, c);
		c.gridx = 1;
		c.gridy = 2;
		f.add(searchCombo, c);
		c.gridx = 2;
		c.gridy = 2;
		f.add(showButton, c);
		c.weighty = 1.0;

		c.gridy=3;
		c.fill = GridBagConstraints.BOTH;
		c.anchor = GridBagConstraints.LINE_END;
		c.gridheight = GridBagConstraints.REMAINDER;
		f.add(Box.createGlue(), c);
		c.gridy=4;
		c.anchor = GridBagConstraints.EAST;
		f.add(outOfText,c);
		f.pack();
		f.setVisible(true);
	}
	public static void setResults(int resultsIn, int k){
		outOfText.setText("Showing " + k + " out of " + resultsIn);
	}
}
