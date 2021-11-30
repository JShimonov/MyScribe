/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

/**
 *
 * @author zarif
 */
public class MyTranscriptions extends NameRepository {

   // NameRepository namesRepository = new NameRepository();
    

    public MyTranscriptions() {
        System.out.println("My Transcriptions <Iterator>");
    }
    Iterator iter = getIterator();
    String text = " ";
     int i = 0;
    public void  readText() throws IOException {
       
        
        while (iter.hasNext()) {//it will check if there are any file in the directory.

            try {
                File file1 = (File) iter.next();//It will return the index of the file to file1
                // System.out.println("Name : " + file1);
                if (file1.getName().contains(".txt")) {// if the file is a .txt file 
                    text = text + file1.getName() + "\n ";// will put the file name into the text
                    System.out.println(++i + " " + file1.getName());

                    System.out.print("Scribe >");
                    Scanner sc = new Scanner(System.in);

                    int j = sc.nextInt();
                    if (i == j) {
                        
                        Scanner input = new Scanner(file1);
                        while (input.hasNext()) {// this section will iterate through the transcribed files.
                            String a = input.next();
                            System.out.println(a);

                        }
                    } else {
                        break;
                    }

                }
            } catch (Exception e) {
                return;
            }

        }
    }
}
