public class Main {
    public static void main(String[] args) {
        String fileName = "";
        String k = "";
		if(args.length == 2){
        	try {
        	    fileName = args[0];
        	    k = args[1];
        	} catch (ArrayIndexOutOfBoundsException e) {
        	    e.getMessage();
        	}
		}else{
			System.out.println("Incorrect usage, please enter arguments" +
							" [filename] [k]");
			System.exit(0);
		}

        //initialise gui
		if(fileName.equals("cities.txt")){
			gui.initGui(new Autocomplete(Parser.importData(fileName)), Integer.parseInt(k), 0);
		}else if(fileName.equals("wiktionary.txt")){
			gui.initGui(new Autocomplete(Parser.importData(fileName)), Integer.parseInt(k), 1);
		}else{
			gui.initGui(new Autocomplete(Parser.importData(fileName)), Integer.parseInt(k), 2);
		}
    }
}
