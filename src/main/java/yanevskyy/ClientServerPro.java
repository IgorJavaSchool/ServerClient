package yanevskyy;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Provides the user a selection to be client or to be server.
 */
public class ClientServerPro {
  int port;
  BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

  /**
   * Starts selected actions.
   */
  public void selectFunction(){
    System.out.println("Select startup type");
    System.out.println("0 - Start Server,  1 - Start User");
    try {
      String selected = reader.readLine();
    switch (selected){
      case "0" : startServer();
        break;
      case "1" : startClient();
        break;
      default:
        System.out.println("Is not correct data");
    }
    } catch (IOException e) {
      e.printStackTrace();
    }
    finally {
      try {
        reader.close();
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
  }

  /**
   * Created Client.
   * @throws IOException
     */
  public void startClient() throws IOException {
    String address;
    System.out.println("Write port");
    port = Integer.parseInt(reader.readLine());
    System.out.println("IP - address");
    address = reader.readLine();
    new Client(port, address).sendToServer();
  }

  /**
   * Created Server
   * @throws IOException
     */
  public void startServer() throws IOException {
    System.out.println("Write port");
    port = Integer.parseInt(reader.readLine());
    new Server(port,"Text.txt","log.txt").connectionClient();
  }

  public static void main(String[] args) {
    ClientServerPro clientServerPro = new ClientServerPro();
    clientServerPro.selectFunction();
  }
}