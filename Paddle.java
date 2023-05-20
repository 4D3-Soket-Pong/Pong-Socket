import java.awt.*;

public class Paddle {
    public static final int WIDTH = 20;
    public static final int HEIGHT = 80;
    private static final int SPEED = 5;

    private int x;
    private int y;
    private int dy;
    private int screenHeight;
    private int score;
    private Rectangle bounds;

    public Paddle(int x, int y, int screenHeight) {
        this.x = x;
        this.y = y;
        this.dy = 0;
        this.screenHeight = screenHeight;
        this.score = 0;
        this.bounds = new Rectangle(x, y, WIDTH, HEIGHT);
    }

    public void update() {
        y += dy;

        if (y < 0) {
            y = 0;
        } else if (y > screenHeight - HEIGHT) {
            y = screenHeight - HEIGHT;
        }

        bounds.setLocation(x, y);
    }

    public void draw(Graphics g) {
        g.setColor(Color.WHITE);
        g.fillRect(x, y, WIDTH, HEIGHT);
    }

    public void moveUp() {
        dy = -SPEED;
    }

    public void moveDown() {
        dy = SPEED;
    }

    public void stopMoving() {
        dy = 0;
    }

    public Rectangle getBounds() {
        return bounds;
    }

    public void reset() {
        y = screenHeight / 2 - HEIGHT / 2;
    }

    public int getScore() {
        return score;
    }

    public void incrementScore() {
        score++;
    }
}