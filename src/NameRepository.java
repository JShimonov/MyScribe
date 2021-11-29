/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project_cs370;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;

/**
 *
 * @author zarif
 */
public class NameRepository {

    File directory = new File("C:\\Users\\zarif\\OneDrive\\Desktop\\ProjectFolder");
    ArrayList<File> files1 = new ArrayList<File>(Arrays.asList(directory.listFiles()));
    int fileLength = files1.size();

    public Iterator getIterator() {
        return new NameIterator();
    }

    private class NameIterator implements Iterator {

        int index;

        @Override

        public boolean hasNext() {

            if (index < files1.size()) {
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
