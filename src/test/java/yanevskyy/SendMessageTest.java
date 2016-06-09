package yanevskyy;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by MM on 07.06.2016.
 */
public class SendMessageTest {
    @Test
    public void generationMessage() throws Exception {
        SendMessage sendMessage = new SendMessage("Text.txt");
        sendMessage.text = new String[]{"How are you", "How are you"};

        assertEquals("How are you", sendMessage.generateMessage());
    }

}