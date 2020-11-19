// Ramy ElGendi
// 900170269

package com.company;

public class Settings {
    private final int xFrame;
    private final int yFrame;
    private final int unit;
    private final int numberOfPeople;
    private final int numberOfCovid;
    private final int moveStep;
    private final int walkLife;
    private final int waitTimeMIN;
    private final int waitTimeMAX;
    private final int timeToFlag;
    private final int safeSocialDistance;

    public int getxFrame() {
        return xFrame;
    }
    public int getyFrame() {
        return yFrame;
    }
    public int getUnit() {
        return unit;
    }
    public int getNumberOfPeople() {
        return numberOfPeople;
    }
    public int getNumberOfCovid() {
        return numberOfCovid;
    }
    public int getMoveStep() {
        return moveStep;
    }
    public int getWalkLife() {
        return walkLife;
    }
    public int getWaitTimeMIN() {
        return waitTimeMIN;
    }
    public int getWaitTimeMAX() {
        return waitTimeMAX;
    }
    public int getTimeToFlag() {
        return timeToFlag;
    }
    public int getSafeSocialDistance() {
        return safeSocialDistance;
    }

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
        System.out.println("Simulatiom beingging with the following settings:-");
        System.out.println("- xFrame: "+xFrame);
        System.out.println("- yFrame: "+yFrame);
        System.out.println("- numberOfPeople: "+numberOfPeople);
        System.out.println("- numberOfCovid: "+numberOfCovid);
        System.out.println("- walkLife: "+walkLife);
        System.out.println("- waitTimeMIN: "+waitTimeMIN);
        System.out.println("- waitTimeMAX: "+waitTimeMAX);
        System.out.println("- timeToFlag: "+timeToFlag);
        System.out.println("- safeSocialDistance: "+safeSocialDistance);
        System.out.println("\n");
    }

}
