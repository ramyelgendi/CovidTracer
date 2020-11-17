package com.company;

public class Settings {
    public final int xFrame,yFrame,unit,numberOfPeople,numberOfCovid,moveStep,walkLife,waitTimeMIN,waitTimeMAX,timeToFlag,safeSocialDistance;


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

}
