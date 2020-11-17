package com.company;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

public class Main extends Container implements Runnable {
    private boolean pause = false;
    Thread thread;
    Settings settings = new Settings(30,32,10,30,3,5,10,500,1000,2,1);
    private static String mainPath;
    private final ArrayList<Person> people;

    public Main() {
        this.people = new ArrayList<>();

        Random rand = new Random();
        for (int i = 0; i<settings.numberOfPeople; i++) {
            boolean flag = true;

            while(flag) {
                flag = false;

                int x = settings.xFrame * settings.unit, y = settings.yFrame * settings.unit;
                while (x > settings.xFrame * settings.unit - 20 || y > settings.yFrame * settings.unit - 40) {
                    x = rand.nextInt(settings.xFrame * settings.unit) + settings.unit;
                    y = rand.nextInt(settings.xFrame * settings.unit) + settings.unit;
                }
                if(i<settings.numberOfCovid)
                    people.add(i, new Person(mainPath + "red_star.png",mainPath + "orange_star.png", x, y, 5, 5,settings,true));
                else
                    people.add(i, new Person(mainPath + "blue_star.png",mainPath + "orange_star.png", x, y, 5, 5,settings,false));

//                System.out.println(x + "," + y);

                for (Person person : people) {
                    if (!people.get(i).equals(person) && person.starRect.intersects(people.get(i).starRect)) {
//                        System.out.println("Collusion");
                        people.remove(i);
                        flag = true;
                        break;
                    }
                }
            }
        }
        thread = new Thread(this);
        thread.start();
    }

    @Override
    public void paint(Graphics g){
        for (int i = 0; i<settings.numberOfPeople; i++) {
            people.get(i).paint(g);
        }

        // Draw Grid
        g.setFont(new Font("TimesRoman", Font.PLAIN, 10));
        for(int j=0;j<=settings.xFrame*settings.unit;j+=2*settings.unit){
            if(j!=0)
                g.drawString(j/settings.unit+"",j,8);
        }
        for(int j=0;j<=settings.yFrame*settings.unit;j+=2*settings.unit){
           g.drawString(j/settings.unit+"",5,j);
        }
    }
    public static void main(String[] args) {
        mainPath = "/Users/ramyelgendi/Downloads/CovidTracer/src/com/company/img/";
        Main main = new Main();

        // Creating GUI
        JFrame jFrame = new JFrame("Covid Tracker");
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.setSize(main.settings.xFrame*main.settings.unit,main.settings.yFrame*main.settings.unit);
        jFrame.setResizable(false);


        jFrame.setContentPane(main);
        jFrame.repaint();
        jFrame.setVisible(true);


        jFrame.repaint();
        // write your code here
    }

    public int counter = 0,time=0;

    void pauseProg(){
        System.out.println("------------------------- Program paused at "+time);
        pause = true;
        for(Person person : people){
            person.pause = true;

        }
    }

    void checkExposure(){
        for (Person covidPerson : people) {
            if(covidPerson.hasCovid)
                for (Person noncovidPerson : people) {
                    if (covidPerson.starRect.intersects(noncovidPerson.starRect) && (covidPerson != noncovidPerson) && !noncovidPerson.isPotential && !noncovidPerson.hasCovid) {
                        noncovidPerson.exposureTime++;
                        System.out.println(noncovidPerson.thread.getName()+" is exposed for "+noncovidPerson.exposureTime+" seconds.");
                    }
                }
        }
    }

    void checkPotentialOrSafe(){
        for (Person person : people) {
            if(person.exposureTime >= settings.timeToFlag && !person.isPotential) {
                person.isPotential = true;
                System.out.println(person.thread.getName()+" is now potential!");
            }
            boolean covidFlag = false;
            if(!person.hasCovid && person.exposureTime>0 && !person.isPotential){
                for(Person covid : people) {
                    if(covid.hasCovid && covid.starRect.intersects(person.starRect))
                        covidFlag = true;
                }
                if(!covidFlag){
                    person.exposureTime=0;
                    System.out.println(person.thread.getName()+" is now safe!");
                }
            }
        }
    }

    void contProgram(){
        System.out.println("------------------------- Program continued at "+time);
        pause = false;
        for(Person person : people){
            person.pause = false;
            person.angle = person.getRandomAngle();
        }
        counter = 0;
    }


    @Override
    public void run() {
        counter++;
        time++;
        try { Thread.sleep(1000); } catch (InterruptedException e) { e.printStackTrace(); }
        repaint();
//        System.out.println(counter);

        if(counter == settings.walkLife)
            pauseProg();

        if(!pause)
            checkExposure();

        checkPotentialOrSafe();

        if(counter == settings.walkLife+5){
           contProgram();

        }

        thread.run();

    }
}
