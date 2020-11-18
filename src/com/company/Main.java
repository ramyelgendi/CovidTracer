package com.company;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

public class Main extends Container implements Runnable {
    private boolean end = false,getnewDirec = true;
    private int random = 0;
    private final Thread thread;
    private final Settings settings;
    private static String mainPath;
    private final ArrayList<Person> people;
    public int time=0,totalCovid=0;

    public Main(Settings settings) {
        this.settings = settings;

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
                    if (!people.get(i).equals(person) && person.getStarRect().intersects(people.get(i).getStarRect())) {
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

        Menu menu = new Menu();

        menu.b.addActionListener(e -> {
            menu.jFrame.setVisible(false);
            menu.setSettings();


            Main main = new Main(menu.settings);
            main.settings.printSettings();
            // Creating GUI
            JFrame jFrame = new JFrame("Covid Tracker");
            jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            jFrame.setSize(main.settings.xFrame*main.settings.unit,main.settings.yFrame*main.settings.unit);
            jFrame.setResizable(false);


            jFrame.setContentPane(main);
            jFrame.repaint();
            jFrame.setVisible(true);


            jFrame.repaint();
        });
    }

    void endProg(){
        System.out.println("------------------------- Program ended at "+time);
        System.out.println(" Out of "+settings.numberOfPeople+" and "+settings.numberOfCovid+" have covid, now "+totalCovid+" are potential!");
        end = true;
        for(Person person : people){
            person.setStop(true);

        }
    }

    void checkExposure(){
        for (Person covidPerson : people) {
            if(covidPerson.isHasCovid())
                for (Person noncovidPerson : people) {
                    if (covidPerson.getStarRect().intersects(noncovidPerson.getStarRect()) && (covidPerson != noncovidPerson) && !noncovidPerson.isPotential() && !noncovidPerson.isHasCovid()) {
                       noncovidPerson.setExposureTime( noncovidPerson.getExposureTime() + 1);
//                        System.out.println(noncovidPerson.thread.getName()+" is exposed for "+noncovidPerson.exposureTime+" seconds.");
                    }
                }
        }
    }

    void checkPotentialOrSafe(){
        for (Person person : people) {
            if(person.getExposureTime() > settings.timeToFlag && !person.isPotential()) {
                person.setPotential(true);
                System.out.println(person.thread.getName()+" is now potential!");
                totalCovid++;
            }
            boolean covidFlag = false;
            if(!person.isHasCovid() && person.getExposureTime()>0 && !person.isPotential()){
                for(Person covid : people) {
                    if(covid.isHasCovid() && covid.getStarRect().intersects(person.getStarRect()))
                        covidFlag = true;
                }
                if(!covidFlag){
                    person.setExposureTime(0);
//                    System.out.println(person.thread.getName()+" is now safe!");
                }
            }
        }
    }

    void changeDirection(){
//        System.out.println("-------------------------------------- People changing direction at time "+time);
//        pause = false;
        for(Person person : people){
//            person.pause = false;
            person.setAngle(person.getRandomAngle());
        }
//        counter = 0;
    }

    @Override
    public void run() {
        time++;
        Random rand = new Random();
        try { Thread.sleep(1000); } catch (InterruptedException e) { e.printStackTrace(); }

        repaint();

        if(getnewDirec){
            random = (rand.nextInt(settings.waitTimeMAX));
            while (random>settings.waitTimeMAX || random<settings.waitTimeMIN)
                random = (rand.nextInt(settings.waitTimeMAX));

            getnewDirec = false;
            if(random<1000) {
                changeDirection();
//                System.out.println("** Change Direction at less than a second.");
            }
        } else {
            if (random > 1000) {
                int randomInSeconds = Integer.parseInt(Integer.toString(random).substring(0, 1));
                if(time % randomInSeconds == 0) {
                    changeDirection();
                    getnewDirec = true;
//                    System.out.println("** Change Direction at more than a second.");
                }
            } else {
                changeDirection();
                getnewDirec = true;
//                System.out.println("** Change Direction at less than a second 2.");

            }
        }
//        System.out.println(time+" is the time, and "+random+" is the random");



//        System.out.println(random);

//        if(counter% (random) == 0)
//            changeDirection();

        checkExposure();
        checkPotentialOrSafe();

        if(time == settings.walkLife)
            endProg();

//        if(!pause)
//        if(counter == settings.walkLife+5){
//        }

        if(end){
            try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("\n Program Terminated.\n");
            System.exit(0);
        }else {
            thread.run();
        }

    }
}
