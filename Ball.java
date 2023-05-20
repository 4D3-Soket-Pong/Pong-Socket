import java.awt.*;

public class Ball {
    public static final int SIZE = 20;
    private static final int SPEED = 5;

    private int x;
    private int y;
    private int dx;
    private int dy;
    private Rectangle bounds;

    public Ball(int x, int y) {
        this.x = x;
        this.y = y;
        this.dx = SPEED;
        this.dy = SPEED;
        this.bounds = new Rectangle(x, y, SIZE, SIZE);
    }

    public void update() {
        x += dx;
        y += dy;
        bounds.setLocation(x, y);
    }

    public void draw(Graphics g) {
        g.setColor(Color.WHITE);
        g.fillRect(x, y, SIZE, SIZE);
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void reset() {
        x = PongPanel.WIDTH / 2 - SIZE / 2;
        y = PongPanel.HEIGHT / 2 - SIZE / 2;
        dx = SPEED;
        dy = SPEED;
    }

    public void bounceOffWall() {
        dy = -dy;
    }

    public void bounceOffPaddle() {
        dx = -dx;
    }

    public boolean intersects(Rectangle rectangle) {
        return bounds.intersects(rectangle.getBounds());
    }
}