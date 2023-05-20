import javax.swing.*;

public class PongGame {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Pong Game");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            PongPanel pongPanel = new PongPanel();
            frame.getContentPane().add(pongPanel);

            frame.pack();
            frame.setResizable(false);
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);

            pongPanel.startGame();
        });
    }
}
