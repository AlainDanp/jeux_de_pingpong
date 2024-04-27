import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;
public class  score extends Rectangle{
    static int GAME_WIDHT;
    static int GAME_HEIGHT;
    int player1;
    int player2;
    score(int GAME_WIDHT, int GAME_HEIGHT){
        score.GAME_WIDHT = GAME_WIDHT;
        score.GAME_HEIGHT = GAME_HEIGHT;
    }
    public void draw(Graphics g){
        g.setColor(Color.white);
        g.setFont(new Font("Consolas",Font.PLAIN,60));
        g.drawLine(GAME_WIDHT/2,0,GAME_WIDHT/2,GAME_HEIGHT);
        g.drawString(String.valueOf(player1/10)+String.valueOf(player1%10),(GAME_HEIGHT/2)-85,50);
        g.drawString(String.valueOf(player2/10)+String.valueOf(player2%10),(GAME_WIDHT/2)+20,50);
    }
    public void reset() {
        player1 = 0;
        player2 = 0;
    }
}
