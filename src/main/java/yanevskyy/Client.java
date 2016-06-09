package yanevskyy;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;

/**
 * Makes connect and send message to server.
 */
public class Client extends CheckExit {
  private GetMessage getMessage;
  private final int SERVERPORT;
  private final String ADDRESS;
  private Socket socket;

  public Client(int serverPort, String address) {
    this.getMessage = new GetMessage();
    this.SERVERPORT = serverPort;
    this.ADDRESS = address;
  }

  /**
   * Makes connect to server
   * @throws IOException creates when no connect.
     */
  private void connectToServer() throws IOException {
    InetAddress inetAddress = InetAddress.getByName(this.ADDRESS);
    this.socket = new Socket(inetAddress, this.SERVERPORT);
  }

  /**
   * Sends messages to server and takes messages from server.
   */
  public void sendToServer() {
    String lineIn;
    String lineOut;
    try {
      connectToServer();
      DataInputStream inputStream = new DataInputStream(socket.getInputStream());
      DataOutputStream outputStream = new DataOutputStream(socket.getOutputStream());
      while (!exit) {
        lineIn = getMessage.readerMessage();
        checkMessage(lineIn);
        outputStream.writeUTF(lineIn);
        outputStream.flush();
        lineOut = inputStream.readUTF();
        System.out.println(lineOut);
      }
      inputStream.close();
      outputStream.close();
      getMessage.closeStream();

    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}