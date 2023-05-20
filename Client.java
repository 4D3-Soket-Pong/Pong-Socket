import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    private static final String SERVER_IP = "localhost";
    private static final int SERVER_PORT = 5000;
    private static boolean isRunning = true;

    public static void main(String[] args) {
        try {
            Socket socket = new Socket(SERVER_IP, SERVER_PORT);
            System.out.println("Connessione al server effettuata.");

            Scanner in = new Scanner(socket.getInputStream());
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);

            Thread receivingThread = new Thread(() -> {
                while (isRunning) {
                    String message = in.nextLine();
                    System.out.println("Server: " + message);

                    if (message.equalsIgnoreCase("exit")) {
                        isRunning = false;
                    }
                }
            });

            Thread sendingThread = new Thread(() -> {
                Scanner consoleInput = new Scanner(System.in);

                while (isRunning) {
                    String input = consoleInput.nextLine();

                    out.println("Player 2: " + input);

                    if (input.equalsIgnoreCase("exit")) {
                        isRunning = false;
                    }
                }
            });

            receivingThread.start();
            sendingThread.start();

            receivingThread.join();
            sendingThread.join();

            in.close();
            out.close();
            socket.close();
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}
