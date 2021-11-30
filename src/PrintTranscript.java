import java.io.File;
import java.io.FileReader;
import java.io.BufferedReader;

public class PrintTranscript extends Thread {
    String path;
    String time = "(00:05)";

    // public PrintTranscript(String p, String t) {
    public PrintTranscript(String p) {
        path = p;
        // time = t;
    }

    public void run() {
        File file = new File("steve_test.txt");
        FileReader fr = null;
        try {
            fr = new FileReader(file);
        } catch (Exception e) {
            System.out.println("File not found!");
        }
        BufferedReader br = new BufferedReader(fr);

        // String path = "src\\transcribed\\steve_test.txt";
        // BufferedReader br = new BufferedReader(new FileReader(path));
        // StringBuilder sb = new StringBuilder();
        // String line = br.readLine();

        String line;
        try {
            line = br.readLine();
        } catch (Exception e) {
            return;
        }

        int seconds = -1;
        int spaceLength = 30;
        boolean printing = true;
        boolean atTime = false;
        boolean first = true;

        while (printing) {
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
                        System.out.println(e);
                    }
    
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
        
        if (seconds != -1) {
            for (int i = 0; i < spaceLength; i++) 
                System.out.print(" ");
            System.out.print("scribe > ");
        }
    }
}
