import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.*;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;
import java.util.List;

public class API extends Engine implements ActionListener, KeyListener {
    private static BufferedImage background;
    public static File records;
    public static ArrayList list = new ArrayList();
    public static JFrame jframe;



    public API(){
        super();
        View();

        records  = new File("records.txt");

    }

    @Override
    public void View() {

        try {
            background = ImageIO.read(new FileInputStream("src\\back1.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        jframe = new JFrame();
        jframe.setTitle("Flappy Bird");
        Timer timer = new Timer(20, this);
        jframe.add(getRenderer());
        jframe.setSize(Engine.getWIDTH(), Engine.getHEIGHT());
        jframe.addKeyListener(this);
      //  jframe.setDefaultCloseOperation(3);
        jframe.setResizable(false);
        jframe.setVisible(true);

        timer.start();
    }


    public static void GameOver() {


        if (Engine.isGameOver())
        {
            Bird.xPos = (Engine.getWIDTH() - Bird.bird.getWidth()) / 2;
            Bird.yPos = (Engine.getHEIGHT() - Bird.bird.getHeight()) / 2;
            Bird.bird_collision = new Rectangle(Bird.xPos, Bird.yPos, Bird.bird.getWidth(), Bird.bird.getHeight());
            Obstacle.getColumns().clear();
            Engine.setyMotion(0);
            Engine.setScore(0);


            Obstacle.addColumn(true);
            Obstacle.addColumn(true);
            Obstacle.addColumn(true);

            Engine.setGameOver(false);
        }

        if (!Engine.isStarted())
        {
            Engine.setStarted(true);
            records  = new File("records.txt");
        }
    }

    public static void write(ArrayList list, File f) throws IOException{
        FileWriter fw =  new FileWriter(f);
        for(int i = 0; i < list.size();i++){
            fw.write(String.valueOf(list.get(i)) + "\n");
        }

        fw.close();
        jframe.dispose();
    }




    @Override
    public void actionPerformed(ActionEvent e) {

        setPressed(getPressed() + 1);

        if (isStarted() && !isGameOver())
        {
            for (int i = 0; i < Obstacle.getColumns().size(); i++)
            {
                Rectangle column = Obstacle.getColumns().get(i);

                column.x -= getSpeed();
            }

            if (getPressed() % 2 == 0 && getyMotion() < 15 && !isGameOver())
            {
                setyMotion( getyMotion() + 2);
            }

            for (int i = 0; i < Obstacle.getColumns().size(); i++)
            {
                Rectangle column = Obstacle.getColumns().get(i);

                if (column.x + column.width < 0)
                {
                    Obstacle.getColumns().remove(column);

                    if (column.y == 0)
                    {
                        Obstacle.addColumn(false);
                    }
                }
            }

                Bird.yPos += getyMotion();
                Bird.bird_collision.y += getyMotion();

            for (Rectangle column : Obstacle.getColumns())
            {
                if (column.y == 0 && Bird.bird_collision.x + Bird.bird_collision.width / 2 > column.x + column.width / 2 - 10 && Bird.bird_collision.x + Bird.bird_collision.width / 2 < column.x + column.width / 2 + 10)
                {
                    setScore(getScore() + 1);
                }


                if (column.intersects(Bird.bird_collision))
                {
                    list.add(getScore() / 2);
                    setGameOver(true);

                    if (Bird.bird_collision.x <= column.x)
                    {
                        Bird.bird_collision.x = column.x - Bird.bird_collision.width;

                    }

                    else
                    {
                        if (column.y != 0)
                        {
                            Bird.bird_collision.y = column.y - Bird.bird_collision.height;
                        }
                        else if (Bird.bird_collision.y < column.height)
                        {
                            Bird.bird_collision.y = column.height;
                        }
                    }

                }
            }



            if (Bird.bird_collision.y > Engine.getHEIGHT()  || Bird.yPos < 0)
            {
                setGameOver(true);
            }

            if (Bird.bird_collision.y + getyMotion() >= Engine.getHEIGHT() )
            {
                Bird.bird_collision.y = getHEIGHT() - Bird.bird_collision.height;
                setGameOver(true);

            }
        }


        getRenderer().repaint();

    }



    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_SPACE)
        {

            Bird.jump();
            GameOver();

        }

        if (e.getKeyCode() == KeyEvent.VK_ENTER)
        {

            try {
                write(list,records);
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        }
    }

    public static void repaint(Graphics g)
    {
        g.drawImage(background,0,0, null);
        g.fillRect(Bird.xPos, Bird.yPos, Bird.bird.getWidth(), Bird.bird.getHeight());
        g.drawImage(Bird.bird,Bird.xPos, Bird.yPos,null);

        for (Rectangle column : Obstacle.getColumns())
        {
            Obstacle.paintColumn(g, column);
        }

        g.setColor(Color.white);
        g.setFont(new Font("Arial", 1, 50));


        if (!isStarted())
        {
            g.drawString("Click space to start!", 75, getHEIGHT() / 2 - 50);
        }

        if (isGameOver())
        {

            g.setFont(new Font("Arial", 1, 50));
            g.drawString("Game Over!" , 100, getHEIGHT() / 2 - 200);
            g.drawString("Your score: " + getScore() / 2 , 100, getHEIGHT() / 2 - 100);
            g.drawString( "Press space to start again!", 100, getHEIGHT() / 2 + 75);
            g.drawString( "Press enter to close game!", 100, getHEIGHT() / 2 + 150);

        }

        if (!isGameOver() && isStarted())
        {
            g.drawString(String.valueOf(getScore() / 2), getWIDTH() / 2 - 25, 100);
        }


    }



}
