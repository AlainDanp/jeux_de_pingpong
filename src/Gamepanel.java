import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;
import javax.sound.sampled.*;
import javax.sound.sampled.AudioInputStream;
import java.io.File;
import javax.sound.sampled.AudioSystem;

public class Gamepanel extends JPanel implements Runnable{
    static final int GAME_WIDTH = 1000;
    static final int GAME_HEIGHT = (int) (GAME_WIDTH * (0.5555));
    static final Dimension SCREEN_SIZE = new Dimension(GAME_WIDTH,GAME_HEIGHT);
    static final int BALL_DIAMETER = 20;
    static final int PADDLE_WIDTH = 24;
    static final int PADDLE_HEIGHT = 100;
    // Game State
    public int gameState ;
    public final int pauseState = 2;
    public final int playState = 1;
    Thread gameThread;
    Image image;
    Graphics graphics;
    Random random;
    Player paddle1;
    Player paddle2;
    Ball ball;
    score score;
    private Clip paddleCollisionSound;
    private Clip paddleCollisionSounds;

    Gamepanel(){
        newPaddles();
        newBall();
        score = new score(GAME_WIDTH,GAME_HEIGHT);
        this.setFocusable(true);
        this.addKeyListener(new AL());
        this.setPreferredSize(SCREEN_SIZE);

        gameThread = new Thread(this);
        gameThread.start();

    try {
        paddleCollisionSound = AudioSystem.getClip();
        paddleCollisionSounds = AudioSystem.getClip();
        AudioInputStream inputStream = AudioSystem.getAudioInputStream(new File("C:\\Users\\danpj\\projetjava\\src\\son du jeu\\Son22.wav"));
        AudioInputStream inputStreams = AudioSystem.getAudioInputStream(new File("C:\\Users\\danpj\\projetjava\\src\\son du jeu\\Son23.wav"));
        paddleCollisionSound.open(inputStream);
        paddleCollisionSounds.open(inputStreams);
    }catch (Exception e){
        e.printStackTrace();
    }
    }
    public void newBall(){
    random = new Random();
    ball = new Ball((GAME_WIDTH/2)-(BALL_DIAMETER/2),random.nextInt(GAME_HEIGHT-BALL_DIAMETER),BALL_DIAMETER,BALL_DIAMETER);

    }
    public void  newPaddles(){
        paddle1 = new Player(0,(GAME_HEIGHT/2)-(PADDLE_HEIGHT/2),PADDLE_WIDTH,PADDLE_HEIGHT,1);
        paddle2 = new Player(GAME_WIDTH-PADDLE_WIDTH,(GAME_HEIGHT/2)-(PADDLE_HEIGHT/2),PADDLE_WIDTH,PADDLE_HEIGHT,2);
    }
    public void paint(Graphics g){
        image = createImage(getWidth(),getHeight());
        graphics = image.getGraphics();
        draw(graphics);
        g.drawImage(image,0,0,this);
    }
    public void draw(Graphics g){
        paddle1.draw(g);
        paddle2.draw(g);
        ball.draw(g);
        score.draw(g);
    }
    public void move(){
        paddle1.move();
        paddle2.move();
        ball.move();
    }
    public void checkcollision(){

        if(ball.y <=0){
            ball.setYDirection(-ball.yVelocity);
            playPaddleCollisionSounds();
        }
        if(ball.y >= GAME_HEIGHT-BALL_DIAMETER){
            ball.setYDirection(-ball.yVelocity);
            playPaddleCollisionSounds();
        }
        if(ball.intersects(paddle1)) {
            ball.xVelocity = Math.abs(ball.xVelocity);
            ball.xVelocity++;
            if (ball.yVelocity > 0)
                ball.yVelocity++;
            else
                ball.yVelocity--;
            ball.setXDirection(ball.xVelocity);
            ball.setYDirection(ball.yVelocity);
            playPaddleCollisionSound();
        }
        if(ball.intersects(paddle2)) {
            ball.xVelocity = Math.abs(ball.xVelocity);
            ball.xVelocity++;
            if (ball.yVelocity > 0)
                ball.yVelocity++;
            else
                ball.yVelocity--;
            ball.setXDirection(-ball.xVelocity);
            ball.setYDirection(ball.yVelocity);
            playPaddleCollisionSound();
        }

    // arrête le Player de l'ecran //
        if(paddle1.y<=0)
            paddle1.y=0;
        if(paddle1.y >= (GAME_HEIGHT-PADDLE_HEIGHT))
            paddle1.y = GAME_HEIGHT-PADDLE_HEIGHT;
        if(paddle2.y<=0)
            paddle2.y=0;
        if(paddle2.y >= (GAME_HEIGHT-PADDLE_HEIGHT))
            paddle2.y = GAME_HEIGHT-PADDLE_HEIGHT;
        // pour avoir des points
        if(ball.x <= 0) {
            score.player2++;
            newPaddles();
            newBall();
            System.out.println("Player 2:"+score.player2);
        }
        if(ball.x >= GAME_WIDTH-BALL_DIAMETER) {
            score.player1++;
            newPaddles();
            newBall();
            System.out.println("Player1"+score.player1);
        }
    }
    //son du jeu
    private void playPaddleCollisionSound(){
        if(paddleCollisionSound.isRunning()){
            paddleCollisionSound.stop();
        }
        paddleCollisionSound.setFramePosition(0);
        paddleCollisionSound.start();
    }
    private void playPaddleCollisionSounds(){
        if(paddleCollisionSounds.isRunning()){
            paddleCollisionSounds.stop();
        }
        paddleCollisionSounds.setFramePosition(0);
        paddleCollisionSounds.start();
    }

    public void run(){
        // game loop
        long lasTime = System.nanoTime();
        double amountOfTicks = 60.0;
        double ns = 1000000000 / amountOfTicks;
        double delta = 0;
        while(true) {
            long now = System.nanoTime();
           delta += (now -lasTime)/ns;
           lasTime = now;
           if(delta >=1) {
               move();
               checkcollision();
               repaint();
               delta--;
           }
        }
    }
    public class AL extends KeyAdapter{
        public void keyPressed (KeyEvent e){
            paddle1.keyPressed(e);
            paddle2.keyPressed(e);
        }
        public void keyReleased(KeyEvent e){
            paddle1.keyReleased(e);
            paddle2.keyReleased(e);
        }
    }
}
