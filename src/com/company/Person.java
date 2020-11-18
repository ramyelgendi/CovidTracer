package com.company;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

public class Person extends Component implements Runnable {

    // Variables
    private boolean stop = false,hasCovid,isPotential;
    Thread thread;
    Settings settings;
    private final Image star,starOrange;
    private final Rectangle starRect;
    private final int height,width;
    private int x,y, angle,exposureTime=0;

    // Getters & Setters
    public boolean isStop() {
        return stop;
    }

    public void setStop(boolean stop) {
        this.stop = stop;
    }

    public boolean isHasCovid() {
        return hasCovid;
    }

    public void setHasCovid(boolean hasCovid) {
        this.hasCovid = hasCovid;
    }

    public boolean isPotential() {
        return isPotential;
    }

    public void setPotential(boolean potential) {
        isPotential = potential;
    }

    public Rectangle getStarRect() {
        return starRect;
    }

    public int getExposureTime() {
        return exposureTime;
    }

    public void setExposureTime(int exposureTime) {
        this.exposureTime = exposureTime;
    }

    @Override
    public int getHeight() {
        return height;
    }

    @Override
    public int getWidth() {
        return width;
    }

    @Override
    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    @Override
    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getAngle() {
        return angle;
    }

    public void setAngle(int angle) {
        this.angle = angle;
    }

    public Person(String path, String orangePath, int x, int y, int width, int height, Settings settings, boolean hasCovid){
        star = new ImageIcon(path).getImage();
        starOrange = new ImageIcon(orangePath).getImage();
        starRect = new Rectangle(x-settings.safeSocialDistance*10/2,y-settings.safeSocialDistance*10/2,width+settings.safeSocialDistance*10,height+settings.safeSocialDistance*10);
        angle = getRandomAngle();

        this.x = x;
        this.y = y;
        this.height = height;
        this.width = width;
        this.settings = settings;
        this.hasCovid = hasCovid;
        this.isPotential = false;

        // Threading
        thread = new Thread(this);
        thread.start();
    }

    public int getRandomAngle() {
        ArrayList<Integer> angles = new ArrayList<>();
        for(int i=0;i<360;i+=45)
            angles.add(i);

        Random rand = new Random();
        return angles.get(rand.nextInt(angles.size()));
    }

    public void Move(int Move, int RotationAngle,int xLimit,int yLimit){
        int temp_x=x,temp_y=y;

        switch (RotationAngle) {
            case 0 -> {
                x += Move;
                if (x < 0 || y < 0 || x > xLimit || y > yLimit) {
                    x = temp_x;
                    y = temp_y;
                    return;
                }
                starRect.translate(Move, 0);
            }
            case 45 -> {
                x += Move;
                y += Move;
                if (x < 0 || y < 0 || x > xLimit || y > yLimit) {
                    x = temp_x;
                    y = temp_y;
                    return;
                }
                starRect.translate(Move, Move);
            }
            case 90 -> {
                y += (Move);
                if (x < 0 || y < 0 || x > xLimit || y > yLimit) {
                    x = temp_x;
                    y = temp_y;
                    return;
                }
                starRect.translate(0, Move);
            }
            case 135 -> {
                x += -Move;
                y += Move;
                if (x < 0 || y < 0 || x > xLimit || y > yLimit) {
                    x = temp_x;
                    y = temp_y;
                    return;
                }
                starRect.translate(-Move, Move);
            }
            case 180 -> {
                x += -Move;
                if (x < 0 || y < 0 || x > xLimit || y > yLimit) {
                    x = temp_x;
                    y = temp_y;
                    return;
                }
                starRect.translate(-Move, 0);
            }
            case 225 -> {
                x += -Move;
                y += -Move;
                if (x < 0 || y < 0 || x > xLimit || y > yLimit) {
                    x = temp_x;
                    y = temp_y;
                    return;
                }
                starRect.translate(-Move, -Move);
            }
            case 270 -> {
                y += -Move;
                if (x < 0 || y < 0 || x > xLimit || y > yLimit) {
                    x = temp_x;
                    y = temp_y;
                    return;
                }
                starRect.translate(0, -Move);
            }
            case 315 -> {
                x += Move;
                y += -Move;
                if (x < 0 || y < 0 || x > xLimit || y > yLimit) {
                    x = temp_x;
                    y = temp_y;
                    return;
                }
                starRect.translate(Move, -Move);
            }
            default -> System.out.println("No Movement Detected");
        }
    }

    @Override
    public void paint(Graphics g){
        if(isPotential)
            g.drawImage(starOrange,x,y,width,height,null);
        else
            g.drawImage(star,x,y,width,height,null);
        //g.drawRect(starRect.x,starRect.y,starRect.width,starRect.height);

    }

    @Override
    public void run() {
        Random rand = new Random();
        try { Thread.sleep(rand.nextInt(settings.waitTimeMAX)+settings.waitTimeMIN); } catch (InterruptedException e) { e.printStackTrace(); }

        if(!stop) {
            Move(settings.moveStep, angle, settings.xFrame * settings.unit - 3, settings.yFrame * settings.unit - 28);
            thread.run();
        }

    }
}
