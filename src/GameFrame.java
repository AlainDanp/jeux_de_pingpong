import java.awt.*;
import javax.swing.*;

public class GameFrame extends JFrame{
     Gamepanel panel;

    GameFrame(){
     panel = new Gamepanel();
     this.add(panel);
     this.setTitle("PING PONG GAME");
     this.setResizable(false);
     this.setBackground(Color.black);
     this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
     this.pack();
     this.setVisible(true);
     this.setLocationRelativeTo(null);
    }
}
