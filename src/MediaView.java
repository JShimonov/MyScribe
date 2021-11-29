import java.util.Map;
import java.util.Scanner;
import java.util.regex.*;


public class MediaView {
    private String file;
    private Map<Integer, String> transcription;

    private void exit() {

    }

    private void save() {

    }

    public void jump(JumpStrategy j) {
        Pattern p = Pattern.compile("\\d\\d:\\d\\d");
        int time = 0;

        Scanner scan = new Scanner(System.in);
        System.out.println("Enter time: ");
        String textTime = scan.nextLine();

        while (!p.matcher(textTime).matches()) {
            if (textTime == "exit") return;

            System.out.println("Enter time: ");
            textTime = scan.nextLine();
        }

        scan.close();

        time = Integer.parseInt(textTime.replace(":", ""));
        j.jumpTo(time);

    }
}
