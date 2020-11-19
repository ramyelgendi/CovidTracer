// Ramy ElGendi
// 900170269

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
    private int time=0,totalCovid=0;

    public Main(Settings settings) {
        this.settings = settings;

        this.people = new ArrayList<>();

        Random rand = new Random();
        for (int i = 0; i<settings.getNumberOfPeople(); i++) {
            boolean flag = true;

            while(flag) {
                flag = false;

                int x = settings.getxFrame() * settings.getUnit(), y = settings.getyFrame() * settings.getUnit();
                while (x > settings.getxFrame() * settings.getUnit() - 20 || y > settings.getyFrame() * settings.getUnit() - 40) {
                    x = rand.nextInt(settings.getxFrame() * settings.getUnit()) + settings.getUnit();
                    y = rand.nextInt(settings.getxFrame() * settings.getUnit()) + settings.getUnit();
                }
                if(i<settings.getNumberOfCovid())
                    people.add(i, new Person(mainPath + "red_star.png",mainPath + "orange_star.png", x, y, 5, 5,settings,true));
                else
                    people.add(i, new Person(mainPath + "blue_star.png",mainPath + "orange_star.png", x, y, 5, 5,settings,false));

                for (Person person : people) {
                    if (!people.get(i).equals(person) && person.getStarRect().intersects(people.get(i).getStarRect())) {
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
        for (int i = 0; i<settings.getNumberOfPeople(); i++) {
            people.get(i).paint(g);
        }

        // Draw Grid
        g.setFont(new Font("TimesRoman", Font.PLAIN, 10));
        for(int j=0;j<=settings.getxFrame()*settings.getUnit();j+=2*settings.getUnit()){
            if(j!=0)
                g.drawString(j/settings.getUnit()+"",j,8);
        }
        for(int j=0;j<=settings.getyFrame()*settings.getUnit();j+=2*settings.getUnit()){
           g.drawString(j/settings.getUnit()+"",5,j);
        }
    }
    public static void main(String[] args) {
        mainPath = "/Users/ramyelgendi/Downloads/CovidTracer/src/com/company/img/";

        Menu menu = new Menu();

        menu.getB().addActionListener(e -> {
            menu.getjFrame().setVisible(false);
            menu.setSettings();


            Main main = new Main(menu.getSettings());
            main.settings.printSettings();

            // Creating GUI
            JFrame jFrame = new JFrame("Covid Tracker");
            jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            jFrame.setSize(main.settings.getxFrame()*main.settings.getUnit(),main.settings.getyFrame()*main.settings.getUnit());
            jFrame.setResizable(false);
            jFrame.setContentPane(main);
            jFrame.repaint();
            jFrame.setVisible(true);
            jFrame.repaint();
        });
    }

    void endProg(){
        System.out.println("Program ended at "+time);
        System.out.println("Out of "+settings.getNumberOfPeople()+" and "+settings.getNumberOfCovid()+" have covid, now "+totalCovid+" are potential!");
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
            if(person.getExposureTime() > settings.getTimeToFlag() && !person.isPotential()) {
                person.setPotential(true);
                System.out.println(person.getThread().getName()+" is now potential!");
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
        for(Person person : people){
            person.setAngle(person.getRandomAngle());
        }
    }

    @Override
    public void run() {
        time++;
        Random rand = new Random();
        try { Thread.sleep(1000); } catch (InterruptedException e) { e.printStackTrace(); }

        repaint();

        if(getnewDirec){
            random = (rand.nextInt(settings.getWaitTimeMAX()));
            while (random>settings.getWaitTimeMAX() || random<settings.getWaitTimeMIN())
                random = (rand.nextInt(settings.getWaitTimeMAX()));

            getnewDirec = false;
            if(random<1000) {
                changeDirection();
            }
        } else {
            if (random > 1000) {
                int randomInSeconds = Integer.parseInt(Integer.toString(random).substring(0, 1));
                if(time % randomInSeconds == 0) {
                    changeDirection();
                    getnewDirec = true;
                }
            } else {
                changeDirection();
                getnewDirec = true;
            }
        }
        checkExposure();
        checkPotentialOrSafe();

        if(time == settings.getWalkLife())
            endProg();

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
