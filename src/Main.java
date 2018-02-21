public class Main {
    public static void main(String[] args) {
        //intitialise filename and k (number of results to display)
        String fileName = "";
        String k = "";
		//if there are 2 arguments set filename and k to the strings passed through the terminal
        if (args.length == 2) {
            fileName = args[0];
            k = args[1];
        //if there are either less or more than 2 arguments notify user and exit program
		} else {
			System.out.println("Incorrect usage, please enter arguments [filename] [k]");
			System.exit(0);
		}

        //initialise gui with respect to the filename that has been passed in through the terminal
        //there are custom designs for the two expected files however if an unexpected filename is given there is a default design
		if (fileName.equals("cities.txt")) {
			gui.initGui(new Autocomplete(Parser.importData(fileName)), Integer.parseInt(k), 0);
		} else if (fileName.equals("wiktionary.txt")) {
			gui.initGui(new Autocomplete(Parser.importData(fileName)), Integer.parseInt(k), 1);
		} else {
			gui.initGui(new Autocomplete(Parser.importData(fileName)), Integer.parseInt(k), 2);
		}
    }
}
