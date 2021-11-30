import java.io.File;
import java.util.Scanner;
import java.lang.Thread;
import java.lang.String;
import java.net.URL;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.BufferedReader;

public class Driver {
    static String buffer = "----------------------------------------------------------------------";
    static Scanner scan = new Scanner(System.in);
    static String input;
    public static void main(String[] args) {
        // -----OBSERVER PATTERN HERE--------------------
        System.out.println("Observer Pattern here...");

        // pauses for 3s to simulate loading
        // try {
        //     Thread.sleep(3000);
        // } catch (Exception e) {
        //     System.out.println(e);
        // }

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

    }

    static void signUp() {
        boolean done = false;

        System.out.println("Please enter a username:");
        System.out.print("scribe > ");
        input = scan.nextLine();
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
            // Singleton x = Singleton.getInstance();
            // File audio = new File(input);
            // File toText = x.execute(audio);
            // -----SINGLETON PATTERN HERE--------------------
            System.out.println("Singleton Pattern here...");

            done = true;
        } 

        System.out.println("Transcribing...");
        System.out.println(buffer);

        // pauses for 3s to simulate loading
        // try {
        //     Thread.sleep(3000);
        // } catch (Exception e) {
        //     System.out.println(e);
        // }

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
        
            TranscriptionView tView = new TranscriptionView(input);
            printTranscript();
            done = true;

            if (input.equals("B")) {
                tView.jump(new JumpToBeginning(), input);
            } else if (tView.isTime(input)) {
                tView.jump(new JumpToTime(), input);
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

    static void printTranscript() {
        File file = new File("steve_test.txt");

        FileReader fr = null;
        try {
            fr = new FileReader(file);
        } catch (FileNotFoundException e) {
            System.out.println("File not found!");
        }
        
        BufferedReader br = new BufferedReader(fr);

        // String path = "src\\transcribed\\steve_test.txt";
        // BufferedReader br = new BufferedReader(new FileReader(path));
        // StringBuilder sb = new StringBuilder();
        // String line = br.readLine();

        Thread thread = new Thread() {

            public void run() {
                String line;
                try {
                    line = br.readLine();
                } catch (IOException e) {
                    return;
                }

                int seconds = -1;
                int spaceLength = 30;
                boolean printing = true;

                while (printing) {
                    String[] lineContents = line.split(" ", 4);
                    String[] lineTime = lineContents[3].split(":", 3);
                    int lineSeconds = Character.getNumericValue(lineTime[2].charAt(1));
                    String displaySeconds = "[" + lineTime[1] + ":" + lineTime[2].substring(0,2) + "]";
                    // String displayString = "";
                    
                    if (lineSeconds == seconds%10) {
                        System.out.print(" " + lineContents[1]);
                        spaceLength = (spaceLength - lineContents[1].length() - 1);
                    } else {
                        // displayString = "\n" + displaySeconds + " " + lineContents[1];
                        if (seconds != -1) {
                            for (int i = 0; i < spaceLength; i++) 
                                System.out.print(" ");
                            System.out.print("scribe > ");
                        }

                        spaceLength = 30;
                        seconds++;

                        try {
                            sleep(1000);
                        } catch (Exception e) {
                            System.out.println(e);
                        }

                        System.out.print("\n" + displaySeconds + " " + lineContents[1]);
                        spaceLength = (spaceLength - lineContents[1].length() - 1);
                    }

                    // displayString = "\n" + displaySeconds + " " + lineContents[1];
                    // for (int i = 0; i < 45-displayString.length(); i++) 
                    //     displayString = displayString + " ";
                    // System.out.print(displayString + "scribe >");

                    try {
                        line = br.readLine();
                    } catch (IOException e) {
                        return;
                    }
                    if (line.split(" ", 4).length != 4) printing = false;
                    
                }
                if (seconds != -1) {
                    for (int i = 0; i < spaceLength; i++) 
                        System.out.print(" ");
                    System.out.print("scribe > ");
                }
            }
        };
        
        thread.start();
        // thread.interrupt();
    }

}