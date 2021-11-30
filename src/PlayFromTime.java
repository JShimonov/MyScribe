import java.io.File;
import java.io.FileReader;
import java.io.BufferedReader;

public class PlayFromTime implements PlayStrategy {
    static Thread thread;
    static BufferedReader br;
    
    public PlayFromTime() {

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
        String time = "(" + input + ")";

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
                boolean atTime = false;
                boolean first = true;

                while (printing && running) {
                    String[] lineContents = line.split(" ", 4);
                    String[] lineTime = lineContents[3].split(":", 3);
                    int lineSeconds = Character.getNumericValue(lineTime[2].charAt(1));
                    String displaySeconds = "(" + lineTime[1] + ":" + lineTime[2].substring(0,2) + ")";

                    if (displaySeconds.equals(time)) {
                        atTime = true;
                    }

                    if (!atTime) {
                        if (lineSeconds != seconds%10) seconds++;
                    } else {
                        if (lineSeconds == seconds%10) {
                            System.out.print(" " + lineContents[1]);
                            spaceLength = (spaceLength - lineContents[1].length() - 1);
                        } else {
                            if (!first) {
                                for (int i = 0; i < spaceLength; i++) 
                                    System.out.print(" ");
                                System.out.print("scribe > ");
                            }
            
                            first = false;
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
                    System.out.print("scribe > ");
                }
            }
        };

        return thread;
    }

}