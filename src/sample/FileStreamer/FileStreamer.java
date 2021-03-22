package sample.FileStreamer;

import java.io.*;

public class FileStreamer {

    public static void OutputStream(String greetings) throws IOException {


        FileOutputStream fileOutputStream = new FileOutputStream("test.txt", false);


        fileOutputStream.write(greetings.getBytes());

        fileOutputStream.close();
    }

    public static String InputStream() throws IOException {

        FileInputStream fileInputStream = new FileInputStream("test.txt");

        int i;
        String temp = "";
        while((i=fileInputStream.read())!= -1){

            temp += ((char)i);
        }
        return temp;
    }
}
