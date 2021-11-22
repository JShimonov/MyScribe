// import classes and packages
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.lang.Exception;
import java.util.Arrays;
import java.io.File;


class CreateLoginForm extends JFrame implements ActionListener {

    // initialize variables
    JButton b1;
    JPanel newPanel;
    JLabel userLabel, passLabel;
    final JTextField textField1;
    final JPasswordField textField2;

    // create constructor
    CreateLoginForm() {
        userLabel = new JLabel();
        userLabel.setText("Username");

        // set length of text
        textField1 = new JTextField(15);

        // create label for password
        passLabel = new JLabel();
        passLabel.setText("Password");

        // set length for password
        textField2 = new JPasswordField(15);
        //textField2

        // create the submit button
        b1 = new JButton("Login");

        // create panel to put form elements
        newPanel = new JPanel(new GridLayout(3,1));
        newPanel.add(userLabel);    //set username label to panel
        newPanel.add(textField1);   //set text field to panel
        newPanel.add(passLabel);    //set password label to panel
        newPanel.add(textField2);   //set text field to panel
        newPanel.add(b1);           //set button to panel

        //set border to panel
        add(newPanel, BorderLayout.CENTER);

        //perform action on button click
        b1.addActionListener(this);         //add action listener to button
        setTitle("LOGIN FORM");               //set title to the login form

        this.setPreferredSize(new Dimension(500, 200));

        this.pack();
        this.setVisible(true);
    }

    public void actionPerformed(ActionEvent ae) {
        String userValue = textField1.getText();
        char[] passValue = textField2.getPassword();

        // check whether the credentials are authentic or not
        if (userValue.equals("test1@gmail.com") && isPasswordCorrect(passValue)) {
            // create instance of secondPage
            secondPage page = new secondPage();

            // make the page visible to the user
            page.setVisible(true);

            // dispose the login page
            this.dispose();

            // create a welcome label and set it to the new page
//            JLabel welcome_label = new JLabel("Welcome " + userValue);
//            page.getContentPane().add(welcome_label);
        } else {
            System.out.println("Please enter valid username and password");
        }
    }

    private static boolean isPasswordCorrect(char[] passValue) {
        boolean output = true;
        char[] correctValue = {'t', 'e', 's', 't'};

        if (passValue.length != correctValue.length) {
            output = false;
        } else {
            output = Arrays.equals(passValue, correctValue);
        }
        // zero out the password
        Arrays.fill(correctValue, '0');

        return output;
    }
}

// create the main class
public class LoginForm {
    public static void main (String[] args) {

        // Singleton x = Singleton.getInstance();
        // System.out.println(x.hashCode());

        // Singleton y = Singleton.getInstance();
        // System.out.println(y.hashCode());

        // Singleton z = Singleton.getInstance();
        // System.out.println(z.hashCode());

        // String file = "file_upload";
        // File upload = x.execute(file);
        // System.out.println(upload);
        // Singleton a = Singleton.getInstance();
        // System.out.println(a.execute(file));

    }
}
