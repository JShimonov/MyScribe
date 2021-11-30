import static org.junit.jupiter.api.Assertions.*;
import java.io.File;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class SingletonTest {

    @org.junit.jupiter.api.Test
    void getInstance() {
        assertSame(Singleton.getInstance(), Singleton.getInstance());
    }

    @org.junit.jupiter.api.Test
    void execute() throws IOException {
        Singleton x = Singleton.getInstance();
        File testFile = new File("src/audio/test.mp3");
        File testAudio = x.execute(testFile);
        List<String> correctList = new ArrayList<>(Arrays.asList("My", "name", "is", "Joseph.", "None"));

        BufferedReader br = new BufferedReader(new FileReader(testAudio));
        String st;
        List<String> list = new ArrayList<>();
        while ((st = br.readLine()) != null) {
            int firstSpace = st.indexOf(" ");
            String word = st.substring(firstSpace+1);
            if (word.equals("None")) {
                list.add(word);
            } else {
                int secondSpace = word.indexOf(" ");
                word = word.substring(0, secondSpace);
                list.add(word);
            }
        }
        assertEquals(correctList, list);
    }
}