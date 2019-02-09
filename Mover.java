package CursorMover;

import java.awt.*;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class Mover{

    private int x, y;
    private Robot robot;
    private Timer timer;

    public Mover(){
        try {
            robot = new Robot();
        } catch (AWTException e) {
            e.printStackTrace();
        }
    }

    private void getCurrentPosition(){
        PointerInfo pInfo = MouseInfo.getPointerInfo();
        x = (int) pInfo.getLocation().getX();
        y = (int) pInfo.getLocation().getY();
    }

    private void move(){
        getCurrentPosition();
        int randomMovement = getRandomMovement();
        robot.mouseMove(x+ randomMovement, y + randomMovement);
    }

    private int getRandomMovement(){
        int randomMovement = new Random().nextInt(10)-5;
        return (randomMovement == 0) ? getRandomMovement() : randomMovement;
    }

    private void start(){
        timer = new Timer();
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                move();
            }
        };
        timer.scheduleAtFixedRate(task, 0, 5000);
    }

    public void startMoving(){
        start();
    }

    public void stopMoving(){
        timer.cancel();
    }

}