public class Main {
    public static void main(String[] args) {
        String fileName = "";
        String k = "";

        try {
            fileName = args[0];
            k = args[1];
        } catch (ArrayIndexOutOfBoundsException e) {
            e.getMessage();
        }


        gui.initGui();
    }
}