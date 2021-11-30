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

    public void jump(JumpStrategy j, String input) {
        j.jumpTo(input);
    }

    
}
