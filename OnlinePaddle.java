public class OnlinePaddle extends Paddle {
    public OnlinePaddle(int x, int y, int scrH) {
        super(x, y, scrH);
    }

    @Override
    public void update() {
        // y = getFromServer()

        bounds.setLocation(x, y);
    }
}
