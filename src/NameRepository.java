/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;

/**
 *
 * @author zarif
 */
public class NameRepository {

    //URL url = getClass().getResource("transcribed");
    //File directory = new File(url.getPath());
    File directory = new File("src\\transcribed");
    // File directory = new File("C:\\Users\\zarif\\OneDrive\\Desktop\\ProjectFolder");
    ArrayList<File> files1 = new ArrayList<File>(Arrays.asList(directory.listFiles()));
    int fileLength = files1.size();

    public Iterator getIterator() {
        return new NameIterator();
    }

    private class NameIterator implements Iterator {

        int index;

        @Override

        public boolean hasNext() {

            if (index <= files1.size()) {
                return true;
            }
            return false;
        }

     

        @Override

       

        public Object next() {

            if (hasNext()) {
                return files1.get(index++);
            }
            return null;
        }

    }
}
