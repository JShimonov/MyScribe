import java.io.File;
import java.util.regex.*;


public class TranscriptionView {
    String fileName;

    public TranscriptionView(String fn) {
        fileName = fn;
    }

    public boolean isTime(String input) {
        Pattern p = Pattern.compile("\\d\\d:\\d\\d");

        if (p.matcher(input).matches()) return true;
        else return false;
    }

    public Thread play(PlayStrategy p, File input) {
        return p.playFrom(input);
    }

    // public void printTranscript() {
    //     PrintTranscript print = new PrintTranscript(p);
    // }

    
}
