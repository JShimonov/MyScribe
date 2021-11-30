import java.io.*;
import java.lang.Process;
import java.lang.ProcessBuilder;
import java.util.ArrayList;
import java.util.Arrays;

public class Singleton {
    private static Singleton singleInstance;

    private Singleton() {
    }

    public static Singleton getInstance() {
        if (singleInstance == null) {
            singleInstance = new Singleton();
        }
        return singleInstance;
    }

    public File execute(File file) {
        String[] cmd = {"python", 
                        "src\\main.py",
                        file.getAbsolutePath()};
        ArrayList<String> l = new ArrayList<String>(Arrays.asList(cmd));
        try {
            ProcessBuilder processBuilder = new ProcessBuilder(l);
            processBuilder.redirectErrorStream(true);

            Process process = processBuilder.start();

            String fileName = file.getName();
            String writeFile = "src\\transcribed\\" + fileName.split("\\.")[0] + ".txt";

            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            BufferedWriter writer = new BufferedWriter(new FileWriter(writeFile));

            int i = 0;
            String line;
            while ((line = reader.readLine()) != null) {
                // Option 1
                writer.write("[" + i + "] " + line + "\n");

                // Option 2
                System.out.println("[" + i++ + "] Read: " + line);
            }
            reader.close();
            writer.close();
            return new File(writeFile);
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        return file;
    }

}


