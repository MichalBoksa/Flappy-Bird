import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class Bird {
    public static int xPos, yPos;
    public static Rectangle bird_collision;
    public static BufferedImage bird;

    public Bird(){
        try {
            bird = ImageIO.read(new FileInputStream("src\\bird.png"));
        }

        catch (IOException e){
            e.printStackTrace();
        }


        xPos = (Engine.getWIDTH() - bird.getWidth()) / 2;
        yPos = (Engine.getHEIGHT() - bird.getHeight()) / 2;
        bird_collision = new Rectangle(xPos, yPos, bird.getWidth(), bird.getHeight());
    }


    public static void jump(){

         if (!Engine.isGameOver())
        {
            if (Engine.getyMotion() > 0)
            {
                Engine.setyMotion(0);
            }

            Engine.setyMotion(Engine.getyMotion() - 10);
        }

         else{
             API.GameOver();
         }
    }



    public static int getxPos() {
        return xPos;
    }

    public static void setxPos(int xPos) {
        Bird.xPos = xPos;
    }

    public static int getyPos() {
        return yPos;
    }

    public static void setyPos(int yPos) {
        Bird.yPos = yPos;
    }

    public static Rectangle getBird_collision() {
        return bird_collision;
    }

    public static void setBird_collision(Rectangle bird_collision) {
        Bird.bird_collision = bird_collision;
    }

    public static BufferedImage getBird() {
        return bird;
    }

    public static void setBird(BufferedImage bird) {
        Bird.bird = bird;
    }
}
