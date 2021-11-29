import java.io.File;
import java.util.Scanner;
import java.lang.Thread;
import java.lang.String;

public class Driver {
    static String buffer = "----------------------------------------------------------------------";
    static Scanner scan = new Scanner(System.in);
    static String input;
    public static void main(String[] args) {
        // -----OBSERVER PATTERN HERE--------------------
        System.out.println("Observer Pattern here...");

        // pauses for 3s to simulate loading
        try {
            Thread.sleep(3000);
        } catch (Exception e) {
            System.out.println(e);
        }

        boolean done = false;

        while (!done) {
            System.out.println("Welcome to Scribe!");
            System.out.println("(S)ign up");
            System.out.println("(L)og in");
            System.out.println("(E)xit Scribe");
            System.out.print("scribe > ");
            input = scan.nextLine();
            System.out.println(buffer);

            if (input.equals("S")) {
                signUp();
            } else if (input.equals("L")) {
                logIn();
            } else if (input.equals("E")) {
                done = true;
            } else {
                System.out.println("Please enter a valid response!");
            }
        } 

        scan.close();
        System.out.println("Scribe says goodbye!");
        System.out.println(buffer);



        // System.out.print("Enter username: ");
        // System.out.println("Username is: " + userName);

        // Singleton x = Singleton.getInstance();
        // File audio = new File("src\\audio\\steve_test.mp3");
        // File toText = x.execute(audio);

        // MediaView mv = new MediaView();
        // mv.jump(new JumpToTime());
    }

    static void signUp() {
        boolean done = false;

        System.out.println("Please enter a username:");
        System.out.print("scribe > ");
        input = scan.nextLine();
        // should check if username exists but nah
        System.out.println(buffer);

        while (!done) {
            System.out.println("Password needs to have the following:");
            System.out.println("  - One lowercase letter");
            System.out.println("  - One uppercase letter");
            System.out.println("  - One number");
            System.out.println("  - etc");
            System.out.println("Please enter a password:");
            System.out.print("scribe > ");
            input = scan.nextLine();

            // -----INTERPRETER PATTERN HERE--------------------
            System.out.println("Interpreter Pattern here...");
            done = true;

            System.out.println(buffer);
            System.out.println("Account created!");
        } 

    }

    static void logIn() {
        boolean done = false;

        while (!done) {
            System.out.println("Scribe Home");
            System.out.println("(T)ranscribe");
            System.out.println("(S)how transcriptions");
            System.out.println("Log (O)ut");
            System.out.print("scribe > ");
            input = scan.nextLine();
            System.out.println(buffer);

            if (input.equals("T")) {
                transcribe();
            } else if (input.equals("S")) {
                list();
            } else if (input.equals("O")) {
                done = true;
            } else {
                System.out.println("Please enter a valid response!");
            }
        } 

    }

    static void transcribe() {
        boolean done = false;

        while (!done) {
            System.out.println("Please choose audio/video file to transcribe:");
            System.out.println("(G)o back");
            System.out.print("scribe > ");
            input = scan.nextLine();
            System.out.println(buffer);

            // Add file validation
            Singleton x = Singleton.getInstance();
            File audio = new File(input);
            File toText = x.execute(audio);
            // -----SINGLETON PATTERN HERE--------------------
            System.out.println("Singleton Pattern here...");

            done = true;
        } 

        System.out.println("Transcribing...");
        System.out.println(buffer);

        // pauses for 3s to simulate loading
        try {
            Thread.sleep(3000);
        } catch (Exception e) {
            System.out.println(e);
        }

        System.out.println("Transcription done!");

        load();
    }

    static void list() {
        boolean done = false;

        while (!done) {
            // -----ITERATOR PATTERN HERE--------------------
            System.out.println("Iterator Pattern here...");

            // filler
            System.out.println("(1) audio.txt");
            System.out.println("(2) audio2.txt");
            System.out.println("(G)o back");
            System.out.print("scribe > ");
            input = scan.nextLine();
            System.out.println(buffer);
        
            if (input.equals("1")) {
                load();
            } else if (input.equals("G")) {
                done = true;
            } else {
                System.out.println("Please enter a valid response!");
            }
        } 

    }

    // file parameter?
    static void load() {
        boolean done = false;

        while (!done) {
            // -----STRATEGY PATTERN HERE--------------------
            System.out.println("Strategy Pattern here...");

            // filler
            System.out.println("Audio playing...");
            // System.out.println("00:00 - vksnerg");
            // System.out.println("00:10 - egndskvn");
            // System.out.println("00:22 - ksdgnskn");
            // System.out.println("etc");
            System.out.println(buffer);

            System.out.println("Enter time to skip to:");
            System.out.println("(B)eginning");
            System.out.println("(MM:SS) Where M is minute and S is second");
            System.out.println("File settings:");
            System.out.println("(M)odify file");
            System.out.println("(S)ave and Exit");
            System.out.println("(E)xit");
            System.out.print("scribe > ");
            input = scan.nextLine();
            System.out.println(buffer);
        
            if (input.equals("B")) {
                
            } else if (input.equals("XX:XX")) {
                
            } else if (input.equals("M")) {
                modify();
            } else if (input.equals("S")) {
                save();
                done = true;
            } else if (input.equals("E")) {
                done = true;
            } else {
                System.out.println("Please enter a valid response!");
            }
        } 

    } 

    static void modify() {
        // -----MEMENTO PATTERN HERE--------------------
        System.out.println("Memento Pattern here...");
        System.out.println(buffer);
    } 

    static void save() {
        boolean done = false;

        while (!done) {
            System.out.println("Please name your transcription:");
            System.out.print("scribe > ");
            input = scan.nextLine();
            // should check if username exists but nah
            System.out.println(buffer);

            done = true;
        } 
    } 

}