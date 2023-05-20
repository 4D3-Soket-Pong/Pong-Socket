import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.*;

public class PongPanel extends JPanel implements KeyListener {
    public static final int WIDTH = 800;
    public static final int HEIGHT = 400;

    private Paddle player1;
    private Paddle player2;
    private Ball ball;

    public PongPanel() {
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        setBackground(Color.BLACK);
        setFocusable(true);
        addKeyListener(this);

        player1 = new Paddle(20, HEIGHT / 2 - Paddle.HEIGHT / 2, HEIGHT);
        player2 = new Paddle(WIDTH - 20 - Paddle.WIDTH, HEIGHT / 2 - Paddle.HEIGHT / 2, HEIGHT);
        ball = new Ball(WIDTH / 2 - Ball.SIZE / 2, HEIGHT / 2 - Ball.SIZE / 2);
    }

    public void update() {
        player1.update();
        player2.update();
        ball.update();

        if (ball.intersects(player1.getBounds()) || ball.intersects(player2.getBounds())) {
            ball.bounceOffPaddle();
        }

        if (ball.getY() <= 0 || ball.getY() >= HEIGHT - Ball.SIZE) {
            ball.bounceOffWall();
        }

        if (ball.getX() < 0) {
            player2.incrementScore();
            ball.reset();
        } else if (ball.getX() > WIDTH - Ball.SIZE) {
            player1.incrementScore();
            ball.reset();
        }
    }

    public void draw(Graphics g) {
        super.paintComponent(g);

        player1.draw(g);
        player2.draw(g);
        ball.draw(g);

        g.setColor(Color.WHITE);
        g.setFont(new Font("Arial", Font.BOLD, 24));
        g.drawString("Player 1: " + player1.getScore(), 20, 30);
        g.drawString("Player 2: " + player2.getScore(), WIDTH - 140, 30);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        draw(g);
    }

    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_W) {
            player1.moveUp();
        } else if (e.getKeyCode() == KeyEvent.VK_S) {
            player1.moveDown();
        } else if (e.getKeyCode() == KeyEvent.VK_UP) {
            player2.moveUp();
        } else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
            player2.moveDown();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_W || e.getKeyCode() == KeyEvent.VK_S) {
            player1.stopMoving();
        } else if (e.getKeyCode() == KeyEvent.VK_UP || e.getKeyCode() == KeyEvent.VK_DOWN) {
            player2.stopMoving();
        }
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Pong");
        PongPanel pongPanel = new PongPanel();

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.add(pongPanel);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        Timer timer = new Timer(10, e -> {
            pongPanel.update();
            pongPanel.repaint();
        });
        timer.start();
    }

    public void startGame() {
        Timer timer = new Timer(10, e -> {
            update();
            repaint();
        });
        timer.start();
    }
}
