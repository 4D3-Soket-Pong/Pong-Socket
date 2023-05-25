import java.net.*;

public class PongSocket {
  private DatagramSocket s;
  private DatagramPacket p;

  private int port;
  private String ip;

  private static PongSocket instance;
  public static PongSocket getInstance() {
    if (instance == null) {
      instance = new PongSocket();
    }

    return instance;
  }

  private PongSocket() {}
  
  // Setter di IP e porta
  public void setIP(String ip) {
    this.ip = ip;
  }
  public void setPort(int port) {
    this.port = port;
  }

  // Crea la connessione se non esiste
  public void createConnection() {
    if (this.s != null) return;

    this.s = new DatagramSocket(this.ip, this.port)
  }

  public int getY() {
    s.receive(p);

    // estrai y dal pacchetto
  }
  public int getX() {
    s.receive(p);

    // estrai x dal pacchetto
  }

  public void sendCoords(x, y) {
    byte[] payload = ByteBuffer.allocate(8).putInt(x).putInt(y).array();

    p = new DatagramPacket(payload, 8);
    p.setPort(this.port);
    
    s.send(p);
  }
}
