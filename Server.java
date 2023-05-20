import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Server {
    private static final int PORT = 5000;
    private static boolean isRunning = true;

    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(PORT);
            System.out.println("Server avviato. In attesa di connessioni...");

            Socket clientSocket = serverSocket.accept();
            System.out.println("Connessione accettata da Player 2.");

            Scanner in = new Scanner(clientSocket.getInputStream());
            PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);

            Thread receivingThread = new Thread(() -> {
                while (isRunning) {
                    String message = in.nextLine();
                    System.out.println("Player 2: " + message);

                    if (message.equalsIgnoreCase("exit")) {
                        isRunning = false;
                    }
                }
            });

            Thread sendingThread = new Thread(() -> {
                Scanner consoleInput = new Scanner(System.in);

                while (isRunning) {
                    String input = consoleInput.nextLine();

                    out.println("Player 1: " + input);

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
            clientSocket.close();
            serverSocket.close();
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}
