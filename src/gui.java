import javax.swing.*;
import javax.swing.plaf.synth.SynthLookAndFeel;
import java.awt.*;

public class gui {
	final static boolean shouldFill = true;
	final static boolean shouldWeightX = true;
	static JLabel outOfText = new JLabel();

	static void initGui(Autocomplete autocomplete, int k, int file){
		//set stylesheet as default to begin with
		String xmlFile = "defaultlaf.xml";
		//check what stylesheet to use
		if (file == 0) {
			xmlFile = "satnavlaf.xml";
		} else if (file == 1) {
			xmlFile = "wiktionarylaf.xml";
		}

		SynthLookAndFeel laf = new SynthLookAndFeel();
		//load the new stylesheet
		try {
		    laf.load(gui.class.getResourceAsStream(xmlFile), gui.class);
		} catch(Exception e){
			System.out.println(e.getMessage());
		}

		try {
		    UIManager.setLookAndFeel(laf);
		} catch(Exception e) {
			System.out.println(e.getMessage());
		}
		//setup gui frame and layout
		JFrame f = new JFrame();
		f.setLayout(new GridBagLayout());
		f.setPreferredSize(new Dimension(600,400));
		f.setResizable(false);

		GridBagConstraints c = new GridBagConstraints();

		//make layout fill its container horizontally
		if (shouldFill) {
			c.fill = GridBagConstraints.HORIZONTAL;
		}
		//create insets so that components aren't touching
		//the frame
		c.insets = new Insets(20,20,20,20);
		
		//create label depending on file input
		String searchLabel = "";
		if(file == 0){searchLabel = "Destination:";}
		if(file == 1){searchLabel = "Search word:";}
		if(file == 3){searchLabel = "Search:";}
		JLabel destination = new JLabel(searchLabel);

		//create combobox for search
		final JComboBox searchCombo = new JComboBox();
		searchCombo.setPrototypeDisplayValue("xxxxxxxxxxxxxxx");
		searchCombo.setEditable(true);

		JTextField editor = (JTextField) searchCombo.getEditor().getEditorComponent();

		editor.setFocusable(true);
		editor.setText("");
		editor.addKeyListener(new ComboListener(searchCombo, autocomplete, k));

		if (shouldWeightX) {
			c.weightx = 0.5;
		}
		c.weighty = 0.0;
		c.gridx = 1;
		c.gridy = 0;


		//add Logo depending on file
		//add logo depending on file
		String logoText = "";
		switch (file) {
			case 0: logoText = "SatNav";
					break;
			case 1: logoText = "Wiktionary";
					break;
			case 2: logoText = "Search Engine";
					break;
		}

		JLabel logo = new JLabel(logoText);
		logo.setFont(new Font("Serif", Font.PLAIN, 52));
		//add search button
		JButton showButton = new JButton("Search");

		
		//add everything to frame
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

	public static void setResults(int numOfResults, int k){
		//if there are k or more search results update label to say we are only showing k results
		if (numOfResults >= k) {
			outOfText.setText("Showing " + k + " out of " + numOfResults);
		//if there are between 1 and k results update label to say we are showing all results
		} else if (numOfResults > 0 && numOfResults < k){
			outOfText.setText("Showing " + numOfResults + " out of " + numOfResults);
		//if there are no results update label to indicate this
		} else if (numOfResults == 0) {
			outOfText.setText("");
		//there cannot legally be a negative number of results
		} else {
			throw new IllegalArgumentException();
		}
	}
}
