import java.awt.*;

public abstract class Engine {
    private static int WIDTH = 800, HEIGHT = 800;
    private static int pressed, yMotion, score;
    private static Obstacle obstacle;
    private static Bird bird;
    private static int speed = 10;
    private static boolean gameOver, started;
    private static Renderer renderer;

    public abstract void View();


    public Engine(){
        Init();
    }

    public void Init(){
        obstacle = new Obstacle();
        bird = new Bird();
        renderer = new Renderer();

        obstacle.addColumn(true);
        obstacle.addColumn(false);
        obstacle.addColumn(false);

    }

    public static int getWIDTH() {
        return WIDTH;
    }

    public static int getHEIGHT() {
        return HEIGHT;
    }

    public static boolean isGameOver() {
        return gameOver;
    }

    public static void setGameOver(boolean gameOver) {
        Engine.gameOver = gameOver;
    }

    public static void setStarted(boolean started) {
        Engine.started = started;
    }

    public static boolean isStarted() {
        return started;
    }

    public static Renderer getRenderer() {
        return renderer;
    }

    public static int getPressed() {
        return pressed;
    }

    public static void setPressed(int pressed) {
        Engine.pressed = pressed;
    }

    public static int getyMotion() {
        return yMotion;
    }

    public static void setyMotion(int yMotion) {
        Engine.yMotion = yMotion;
    }

    public static int getScore() {
        return score;
    }

    public static void setScore(int score) {
        Engine.score = score;
    }

    public static int getSpeed() {
        return speed;
    }

    public static void setSpeed(int speed) {
        Engine.speed = speed;
    }

    public static void setWIDTH(int WIDTH) {
        Engine.WIDTH = WIDTH;
    }

    public static void setHEIGHT(int HEIGHT) {
        Engine.HEIGHT = HEIGHT;
    }

    public static Obstacle getObstacle() {
        return obstacle;
    }

    public static void setObstacle(Obstacle obstacle) {
        Engine.obstacle = obstacle;
    }

    public static Bird getBird() {
        return bird;
    }

    public static void setBird(Bird bird) {
        Engine.bird = bird;
    }

    public static void setRenderer(Renderer renderer) {
        Engine.renderer = renderer;
    }
}
