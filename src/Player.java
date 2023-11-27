import java.awt.*;
import java.awt.event.*;

public class Player extends Rectangle {
    int id;
    int yVelocity;
    int speed = 10;

    Player(int x, int y, int PADDLE_WIDTH, int PADDLE_HEIGHT, int id) {
        super(x, y, PADDLE_WIDTH, PADDLE_HEIGHT);
        this.id = id;
    }

    public void keyPressed(KeyEvent e) {
        System.out.println("id: " + id);
        switch (id) {
            case 1:
//                System.out.println("Je suis le 1er ");
                if (e.getKeyCode() == KeyEvent.VK_Z) {
                    setyDirection(-speed);
                    move();
                }
                if (e.getKeyCode() == KeyEvent.VK_S) {
                    setyDirection(speed);
                    move();
                }
                break;
            case 2:
//                System.out.println("Je suis le 2er ");
                if (e.getKeyCode() == KeyEvent.VK_UP) {
                    setyDirection(-speed);
                    move();
                }
                if (e.getKeyCode() == KeyEvent.VK_DOWN) {
                    setyDirection(speed);
                    move();
                }
                break;
        }
    }

    public void keyReleased(KeyEvent e) {
        switch (id) {
            case 1:
                if (e.getKeyCode() == KeyEvent.VK_Z) {
                    setyDirection(0);
                    move();
                }
                if (e.getKeyCode() == KeyEvent.VK_S) {
                    setyDirection(0);
                    move();
                    break;
                }
            case 2:
                if (e.getKeyCode() == KeyEvent.VK_UP) {
                    setyDirection(0);
                    move();
                }
                if (e.getKeyCode() == KeyEvent.VK_DOWN) {
                    setyDirection(0);
                    move();
                }
                break;
        }
    }

    public void setyDirection(int yDirection) {
        yVelocity = yDirection;
    }
    public void move() {
        y= y + yVelocity;
    }

    public void draw(Graphics g) {
        if (id == 1)
            g.setColor(Color.blue);
        else
            g.setColor(Color.red);
        g.fillRect(x, y, width, height);
    }
}
