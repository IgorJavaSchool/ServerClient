package yanevskyy;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Random;

/**
 * Generates random answers from file.
 */
public class SendMessage {
    String[] text;
    String fileName;
    Random random;
    int count;

    public SendMessage(String fileName) {
        this.fileName = fileName;
        random = new Random();
    }

    /**
     * Selects random element from string array.
     * @return
     */
    public String generateMessage() {
        count = random.nextInt(text.length - 1);
        System.out.println(text[count]);
        return text[count];
    }

    /**
     * Fills string array with random strings.
     * @throws IOException
     */
    public void fillStrings() throws IOException {
        FileReader stream = new FileReader(new File(fileName));
        int readByte;
        StringBuilder stringBuilder = new StringBuilder();
        while (stream.ready()){
            readByte = stream.read();
            stringBuilder.append((char) readByte);
        }
        this.text = stringBuilder.toString().split("\n");
        stream.close();
    }
}