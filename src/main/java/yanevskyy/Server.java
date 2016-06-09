package yanevskyy;

import java.io.*;
import java.net.*;

/**
 * Takes and sends messages to client.
 */
public class Server extends CheckExit {
  private final int PORT;
  private ServerSocket serverSocket;
  String fileLog;
  private SendMessage sendMessage;
  private DataInputStream inputStream;
  private DataOutputStream outputStream;
  private Writer writer;

  public Server(int PORT, String txt, String fileLog) throws IOException {
    this.PORT = PORT;
    this.serverSocket = new ServerSocket(PORT);
    this.fileLog = fileLog;
    this.sendMessage = new SendMessage(txt);
    this.writer = new FileWriter(fileLog);

  }

  /**
   * Waiting for connecting with client.
   */
  public void connectionClient(){
    Socket socket;
    System.out.println("waiting for client connection.");
    try {
      socket = serverSocket.accept();
      System.out.println("United");
      inputStream = new DataInputStream(socket.getInputStream());
      outputStream = new DataOutputStream(socket.getOutputStream());
      sendMessage.fillStrings();
      changeMessageClient();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  /**
   * Changes messages with client.
   * @throws IOException
     */
  public void changeMessageClient() throws IOException {
    String lineIn;
      while (!exit){
        System.out.println("Waiting for client message.");
        lineIn = inputStream.readUTF();
        System.out.println("Client sent to server this message: " + lineIn);
        selectActions(lineIn);
      }
      inputStream.close();
      outputStream.close();
      writer.flush();
      writer.close();
  }

  /**
   * Gets word or sentence.
   * If gets "стоп", then continue sends the words to server but not returns.
   * If gets "продолжить", then continue again returns random sentence from file.
   * If gets "закончить", then closes program.
   * @param lineIn takes from client.
   * @throws IOException
     */
  public void selectActions(String lineIn) throws IOException {
    checkMessage(lineIn);
    if(exit){
      writer.write(lineIn + "\n");
      setWriter("Good By");
    } else if (stopSend){
      writer.write(lineIn + "\n");
      setWriter("Answers stopped" + "\n");
    } else {
      writer.write(lineIn + "\n");
      setWriter(sendMessage.generateMessage());
    }
    outputStream.flush();
  }

  /**
   * writer output message and writes file log.
   * @param lineOut output message.
   * @throws IOException
     */
  public void setWriter(String lineOut) throws IOException {
    outputStream.writeUTF(lineOut);
    writer.write(lineOut);
  }

}