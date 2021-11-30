import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.io.IOException;
import java.util.Scanner;
import java.lang.Thread;
import java.lang.String;

public class Driver {
    static String buffer = "----------------------------------------------------------------------";
    static Scanner scan = new Scanner(System.in);
    static String input;
    public static void main(String[] args) {
        Server s1 = new Server(); // create server on which myscribe will be running on

        Application myscribe = new Application(); // create the application myscribe for the connection

        myscribe.attach(s1); //  myscribe will update the connection to server

        myscribe.notifyUpdate(new Message("application has conected to server"));

        System.out.println("Observer Pattern here...");

        // pauses for 3s to simulate loading
        try {
            Thread.sleep(3000);
        } catch (Exception e) {
            System.out.println(e);
        }
        //in case the user opts to log in instead of sign up -- here are default credentials
        ArrayList<String> credentials = new ArrayList<>(Arrays.asList("user", "Default1%"));
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
                credentials = signUp();
            } else if (input.equals("L")) {
                logIn(credentials);
            } else if (input.equals("E")) {
                myscribe.detach(s1); // once we are done with mysribe it will now disconnect
                myscribe.attach(s1); // application has now updated the disconection from the server
                myscribe.notifyUpdate(new Message("application has disconnected from server"));
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

    static ArrayList<String> signUp() {
        boolean done = false;
        ArrayList<String> credentials = new ArrayList<>();
        System.out.println("Please enter a username:");
        System.out.print("scribe > ");
        input = scan.nextLine();
        String username = input;
        credentials.add(username);
        System.out.println(buffer);

        while (!done) {
            System.out.println("Password needs to have the following:");
            System.out.println("  - 8 or more characters");
            System.out.println("  - One or more lowercase letters");
            System.out.println("  - One or more uppercase letters");
            System.out.println("  - One or more numbers");
            System.out.println("  - One or more of these characters: !%&^$");
            System.out.println("  - No spaces");
            System.out.println("Please enter a password:");
            System.out.print("scribe > ");
            input = scan.nextLine();
            // -----INTERPRETER PATTERN HERE--------------------
            System.out.println("Interpreter Pattern here...");
            String potentialPassword = input;
            Password p = new Password();
            if (p.setPassword(potentialPassword) == false){
                while(p.getPassword() == null){
                    System.out.print("Try again: enter password: ");
                    potentialPassword = scan.nextLine();
                    p.setPassword(potentialPassword);
                }
            }
            credentials.add(potentialPassword);
            done = true;

            System.out.println(buffer);
            System.out.println("Account created!");

        }
        return credentials;
    }

    static void logIn(ArrayList<String> credentials) {
        boolean done = false;
        System.out.print("\nEnter username: ");
        String username = credentials.get(0);
        input = scan.nextLine();
        while(!username.equals(input)){
            System.out.print("\nTry again: Enter username: ");
            input = scan.nextLine();
        }
        String password = credentials.get(1);
        System.out.print("\nEnter password: ");
        input = scan.nextLine();

        while(!password.equals(input)){
            System.out.print("\nTry again: Enter password: ");
            input = scan.nextLine();
        }

        System.out.println("Welcome, " + username + "!");
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

            MyTranscriptions transcriptions = new MyTranscriptions();
            try {
                transcriptions.readText();
            } catch (IOException e) {
                return;
            }
            

            // filler
            // System.out.println("(1) audio.txt");
            // System.out.println("(2) audio2.txt");
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