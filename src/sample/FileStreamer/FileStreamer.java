package sample.FileStreamer;

import com.sun.webkit.SharedBuffer;

import java.io.*;

public class FileStreamer {
    
    public static void OutputStream(String greetings, String fileName) throws IOException {


        FileOutputStream fileOutputStream = new FileOutputStream(fileName, false);


        fileOutputStream.write(greetings.getBytes());

        fileOutputStream.close();
    }

    public static String InputStream(String fileName) throws IOException {

        FileInputStream fileInputStream = new FileInputStream(fileName);

        int i;
        String temp = "";
        while((i=fileInputStream.read())!= -1){

            temp += ((char)i);
        }
        return temp;
    }
}
