package yanevskyy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class GetMessage {
    BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    /**
     * Reads data from console.
     * @return
     */

    public String readerMessage() {
        System.out.println("Write your message");
        try {
            return reader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }

    /**
     * Closes stream.
     */
    public void closeStream(){
        try {
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}