package Main;


public class Screen {

    public static String frame = "------------------------------------------------\n" +
                "-------------------Almami Games ----------------\n" +
                "------------------------------------------------\n";

    public static void printMenu() {
        System.out.println(frame +
                "[1]New Game                    [2]Set Stock units \n" +
                "[3]View all games available    [4]Search by name\n" +
                "[5]Delete Game                 [6]filter games by\n" +
                "[7]Exit");

    }



}
