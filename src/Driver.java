import java.io.File;
import java.util.Scanner;

public class Driver {
    public static void main(String[] args) {
        // Scanner scan = new Scanner(System.in);
        // System.out.print("Enter username: ");
        // String userName = scan.nextLine();
        // System.out.println("Username is: " + userName);
        system.out.println("howdy");
        Singleton x = Singleton.getInstance();
        File audio = new File("src\\audio\\steve_test.mp3");
        File toText = x.execute(audio);
    }

}