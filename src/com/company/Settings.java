package com.company;

public class Settings {
    public final int xFrame;
    public final int yFrame;
    public final int unit;
    public final int numberOfPeople;
    public final int numberOfCovid;
    public final int moveStep;
    public int walkLife;
    public final int waitTimeMIN;
    public final int waitTimeMAX;
    public final int timeToFlag;
    public final int safeSocialDistance;


    public Settings(int xFrame,int yFrame,int unit,int numberOfPeople,int numberOfCovid,int moveStep, int walkLife,int waitTimeMIN,int waitTimeMAX, int timeToFlag, int safeSocialDistance){
        this.xFrame = xFrame;
        this.yFrame = yFrame;
        this.unit = unit;
        this.numberOfPeople = numberOfPeople;
        this.numberOfCovid = numberOfCovid;
        this.moveStep = moveStep;
        this.walkLife = walkLife;
        this.waitTimeMIN = waitTimeMIN;
        this.waitTimeMAX = waitTimeMAX;
        this.timeToFlag = timeToFlag;
        this.safeSocialDistance = safeSocialDistance;

    }

    public void printSettings(){
        System.out.println("xFrame: "+xFrame);
        System.out.println("yFrame: "+yFrame);
        System.out.println("numberOfPeople: "+numberOfPeople);
        System.out.println("numberOfCovid: "+numberOfCovid);
        System.out.println("walkLife: "+walkLife);
        System.out.println("waitTimeMIN: "+waitTimeMIN);
        System.out.println("waitTimeMAX: "+waitTimeMAX);
        System.out.println("timeToFlag: "+timeToFlag);
        System.out.println("safeSocialDistance: "+safeSocialDistance);
    }

}
