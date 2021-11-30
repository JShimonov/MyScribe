import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.io.IOException;
import java.util.Scanner;
import java.lang.Thread;
import java.lang.String;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.List;
import java.net.URL;
import java.io.FileNotFoundException;


public class Driver {
    static String buffer = "----------------------------------------------------------------------";
    static Scanner scan = new Scanner(System.in);
    static String input;
    // create a File variable that wil store transcription
    static File toText = new File("src\\transcribed\\test.txt");
    public static void main(String[] args) {
        Server s1 = new Server(); // create server on which myscribe will be running on

        Application myscribe = new Application(); // create the application myscribe for the connection

        myscribe.attach(s1); //  myscribe will update the connection to server

        myscribe.notifyUpdate(new Message("application has conected to server"));

        System.out.println("Observer Pattern here...");

        

        // pauses for 3s to simulate loading
        // try {
        //     Thread.sleep(3000);
        // } catch (Exception e) {
        //     System.out.println(e);
        // }
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

            // -----SINGLETON PATTERN HERE--------------------
            System.out.println("Singleton Pattern here...");
            Singleton x = Singleton.getInstance();
            File audio = new File(input);
            toText = x.execute(audio);

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
        boolean first = true;
        TranscriptionView tView = new TranscriptionView(input);
        Thread player = tView.play(new PlayFromBeginning(), toText);

        while (!done) {
            System.out.println("Enter time to skip to:");
            System.out.println("(B)eginning");
            System.out.println("(MM:SS) Where M is minute and S is second");
            System.out.println("File settings:");
            System.out.println("(M)odify file");
            System.out.println("(S)ave and Exit");
            System.out.println("(E)xit");
            System.out.println(buffer);
            

            if (first) {
                System.out.println("\nStrategy Pattern here...");
                System.out.println("Audio playing...");
                System.out.print(buffer);

                player.start();
            }
            
            System.out.print("scribe > ");
            input = scan.nextLine();
            // PrintTranscript printer = new PrintTranscript(input);
            // printer.start();

            if (input.equals("B")) {
                // -----STRATEGY PATTERN HERE--------------------
                System.out.println("\nStrategy Pattern here...");
                System.out.println("Audio playing...");
                System.out.print(buffer);

                player.interrupt();
                player = tView.play(new PlayFromBeginning(), toText);
                player.start();
            } else if (tView.isTime(input)) {
                System.out.println("\nStrategy Pattern here...");
                System.out.println("Audio playing...");
                System.out.print(buffer);

                player.interrupt();
                player = tView.play(new PlayFromTime(), toText);
                player.start();
            } else if (input.equals("M")) {
                player.interrupt();
                modify();
            } else if (input.equals("S")) {
                player.interrupt();
                save();
                done = true;
            } else if (input.equals("E")) {
                player.interrupt();
                done = true;
            } else {
                System.out.println("Please enter a valid response!");
            }

            first = false;
            System.out.println(buffer);

            try {
                Thread.sleep(500);
            } catch (Exception e) {
                System.out.println(e);
            }

        } 

    } 

    static void modify() {
        // -----MEMENTO PATTERN HERE--------------------
        System.out.println("Memento Pattern here...");
        System.out.println(buffer);
        List<Memento.ScribeMemento> savedStateList = new ArrayList<Memento.ScribeMemento>();
		String transcriptString = "";
		Memento originator = new Memento();
	    boolean done = false;
        // URL url = getClass().getResource("steve_test.txt");
        // String path = url.getPath();
        // String path = "src/transcribed/steve_test.txt";
        String path = "src\\transcribed\\steve_test.txt";
	    while(!done) {
	    	System.out.println("Memento Pattern here...");
	    	System.out.println("(1) Save- Create a Memento State.");
	    	System.out.println("(2) Load- Restore a Memento State.");
	    	System.out.println("(3) Edit- Edit Transcript.");
	    	System.out.println("(4) Exit.");
            System.out.print("scribe > ");
	    	input = scan.nextLine();
	    	
            // takes text from transcript file and puts into a string, then adds it to arraylist as memento state
	    	if (input.equals("1")) {
	    	    try {
                    
	    	        BufferedReader br = new BufferedReader(new FileReader(path));
	    	        StringBuilder sb = new StringBuilder();
	    	        String line = br.readLine();
	    	        while (line != null) {
	    	            sb.append(line);
	    	            sb.append("\n");
	    	            line = br.readLine();
	    	        }
	    	        transcriptString = sb.toString();
	    	        br.close();
	    	    } catch (Exception e) {
	    	        e.printStackTrace();
	    	    }
	    	    originator.set(transcriptString);
	    		savedStateList.add(originator.createScribeMemento());
	    	}
	    	
            // takes string from a saved memento state and saves it onto the transcript text file
	    	else if (input.equals("2")) {
	    		transcriptString = originator.restoreScribeMemento(savedStateList.get(savedStateList.size()-1));
	    		try {
	    		FileWriter transcriptEditor = new FileWriter(path); 
	    		transcriptEditor.write(transcriptString);
	    		transcriptEditor.close();
	    		}
	    		catch(IOException e){
	    			System.out.println("an error occured while restoring memento");
	    			e.printStackTrace();
	    		}
	    	}
	    	
            // placeholder code to "edit" the transcript text file
	    	else if (input.equals("3")) {
	    		System.out.println("\nPlaceholder to edit the transcript text file");
	    		try {
	    		FileWriter transcriptEditor = new FileWriter(path); 
	    		transcriptEditor.write("Placeholder to edit the transcript text file");
	    		transcriptEditor.close();
	    		}
	    		catch(IOException e){
	    			System.out.println("an error occured while restoring memento");
	    			e.printStackTrace();
	    		}
	    	}
	    	
	    	else if (input.equals("4")) {
	    		done = true;
	    	}
	    	
	    	else {
	    		System.out.println("Please enter a valid response!");
	    	}
	    }
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