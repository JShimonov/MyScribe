import java.io.File;
import java.io.FileReader;
import java.io.BufferedReader;

public class PlayFromBeginning implements PlayStrategy {
    static Thread thread;
    static BufferedReader br;

    public PlayFromBeginning() {
        
    }

    private void readFile(File f) {
        //File file = new File(f);

        FileReader fr = null;
        try {
            fr = new FileReader(f);
        } catch (Exception e) {
            System.out.println("File not found!");
        }
        
        br = new BufferedReader(fr);

        // String path = "src\\transcribed\\steve_test.txt";
        // BufferedReader br = new BufferedReader(new FileReader(path));
        // StringBuilder sb = new StringBuilder();
        // String line = br.readLine();
    }

    public Thread playFrom(File input) {
        readFile(input);
        
        thread = new Thread() {
            public void run() {
                try {
                    sleep(1000);
                } catch (Exception e) {
                    System.out.println(e);
                }

                String line;
                try {
                    line = br.readLine();
                } catch (Exception e) {
                    return;
                }

                int seconds = -1;
                int spaceLength = 30;
                boolean printing = true;
                boolean running = true;

                while (printing && running) {
                    String[] lineContents = line.split(" ", 4);
                    String[] lineTime = lineContents[3].split(":", 3);
                    // for (String l : lineTime) {
                    //     System.out.println(l);
                    // }

                    int lineSeconds = Character.getNumericValue(lineTime[2].charAt(1));
                    String displaySeconds = "(" + lineTime[1] + ":" + lineTime[2].substring(0,2) + ")";
                    
                    if (lineSeconds == seconds%10) {
                        System.out.print(" " + lineContents[1]);
                        spaceLength = (spaceLength - lineContents[1].length() - 1);
                    } else {
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
                            running = false;
                        }

                        if (running) {
                            System.out.print("\n" + displaySeconds + " " + lineContents[1]);
                            spaceLength = (spaceLength - lineContents[1].length() - 1);
                        }
                    }

                    try {
                        line = br.readLine();
                    } catch (Exception e) {
                        return;
                    }
                    if (line.split(" ", 4).length != 4) printing = false;
                    
                }
                if (seconds != -1 && running) {
                    for (int i = 0; i < spaceLength; i++) 
                        System.out.print(" ");
                    System.out.println("scribe > ");
                    System.out.println("End of Transcription");
                    System.out.println("----------------------------------------------------------------------");
                }
            }
        };

        return thread;
    }
}
