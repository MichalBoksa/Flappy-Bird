import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

public class Obstacle {

    private static ArrayList<Rectangle> columns;
    private static Random rand;
    private static int hole, width, height;

    public Obstacle(){
        columns = new ArrayList<Rectangle>();
        rand = new Random();
    }

    public static void addColumn(boolean start)
    {

         width = 50;
         height = 50 + rand.nextInt(300);
         hole = 150;

        if (start)
        {
            columns.add(new Rectangle(Engine.getWIDTH() + width + columns.size() * 150, Engine.getHEIGHT() - height , width, height));
            columns.add(new Rectangle(Engine.getWIDTH() + width + (columns.size() - 1) * 150, 0, width, Engine.getHEIGHT() - height - hole));
        }
        else
        {
            columns.add(new Rectangle(columns.get(columns.size() - 1).x + 320, Engine.getHEIGHT() - height  , width, height));
            columns.add(new Rectangle(columns.get(columns.size() - 1).x , 0, width, Engine.getHEIGHT() - height - hole));
        }
    }

    public static void paintColumn(Graphics g, Rectangle column)
    {
        g.setColor(Color.green.darker());
        g.fillRect(column.x, column.y, column.width, column.height);


    }

    public static ArrayList<Rectangle> getColumns() {
        return columns;
    }

    public static void setColumns(ArrayList<Rectangle> columns) {
        Obstacle.columns = columns;
    }

    public static Random getRand() {
        return rand;
    }

    public static void setRand(Random rand) {
        Obstacle.rand = rand;
    }

    public static int getHole() {
        return hole;
    }

    public static void setHole(int hole) {
        Obstacle.hole = hole;
    }

    public static int getWidth() {
        return width;
    }

    public static void setWidth(int width) {
        Obstacle.width = width;
    }

    public static int getHeight() {
        return height;
    }

    public static void setHeight(int height) {
        Obstacle.height = height;
    }
}
