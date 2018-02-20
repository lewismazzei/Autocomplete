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

public class gui{
	
	static void initGui(){
		//Will use this later to customise the gui
		String xmlFile = "laf.xml";
		SynthLookAndFeel laf = new SynthLookAndFeel();
		try{
		laf.load(gui.class.getResourceAsStream(xmlFile), gui.class);
		}catch(Exception e){
			
		}
		try{
		UIManager.setLookAndFeel(laf);
		}catch(Exception e){
		}

		JFrame f = new JFrame();
		f.setLayout(new FlowLayout());
		JLabel statusLabel = new JLabel("", JLabel.CENTER);
		statusLabel.setSize(350,100);
		
		//Sort out the array
		Term[] cities = Parser.importData("cities.txt");
		java.util.List<Term> searchQueryList = Arrays.asList(cities);
		Collections.sort(searchQueryList);
		searchQueryList.toArray(cities);
		
		//Create combobox and add Key Listener
		final JComboBox searchCombo = new JComboBox();
		searchCombo.setEditable(true);
		JTextField editor = (JTextField) searchCombo.getEditor().getEditorComponent();
		editor.setFocusable(true);
		editor.setText("");
		editor.addKeyListener(new ComboListener(searchCombo, cities));

		JButton showButton = new JButton("Search");
		
		//Show everything
		f.add(searchCombo);
		f.add(showButton);
		f.setVisible(true);
	}
}
