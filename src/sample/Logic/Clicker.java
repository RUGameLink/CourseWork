package sample.Logic;

import java.util.Random;

public class Clicker {
    private int passivBustPrice;
    private int passivBustNumber;


    private int bustPrice;
    private int bustNumber;
    private int bustEffect;
    private int oreCount;
    private int min;
    private int max;


    private Random r = new Random();
    private int clickerCount;

    private double hpCount;

    public Clicker(int bustEffect, int bustPrice, int bustNumber, int passivBustNumber, int passivBustPrice, int count, double hpCount, int murCount, int min, int max){
        this.bustPrice = bustPrice;
        this.bustEffect = bustEffect;
        this.bustNumber = bustNumber;

        this.passivBustPrice = passivBustPrice;

        this.passivBustNumber = passivBustNumber;

        this.clickerCount = count;
        this.hpCount = hpCount;
        this.oreCount = murCount;
        this.min = min;
        this.max = max;
    }

    public boolean actBuster(){
        if (clickerCount >= bustPrice) {
            clickerCount = clickerCount - bustPrice;
            bustPrice += bustPrice * 0.3;
            if(bustEffect == 0){
                bustEffect ++;
            }
            else {
                bustEffect += bustEffect + r.nextInt((2 - 1 + 1) + 1);
            }
            bustNumber ++;
            return true;
        }
        else {
            return false;
        }
    }

    public boolean passBuster(){
        if (clickerCount >= passivBustPrice) {
            clickerCount = clickerCount - passivBustPrice;
            passivBustPrice += passivBustPrice * 0.5;
            passivBustNumber ++;
            min += r.nextInt((2 - 1 + 1) + 1);
            max += r.nextInt((2 - 1 + 1) + 1);
            return true;
        }
        else {
            return false;
        }
    }

    public int getBustEffect(){
        return bustEffect;
    }

    public void setClickerCount(int clickerCount){
        this.clickerCount = clickerCount;
    }

    public int getClickerCount(){
        return clickerCount;
    }

    public int getBustNumber(){
        return bustNumber;
    }

    public int getBustPrice(){
        return bustPrice;
    }

    public void setBustEffect(int bustEffect){this.bustEffect = bustEffect;}

    public void setBustPrice(int bustPrice){this.bustPrice = bustPrice;}

    public void setBustNumber(int bustNumber){this.bustNumber = bustNumber;}

    public int getPassivBustPrice(){return this.passivBustPrice;}

    public int getPassivBustNumber() {
        return this.passivBustNumber;
    }

    public void setPassivBustPrice(int PassivBustPrice){this.passivBustPrice = PassivBustPrice;}

    public void setPassivBustNumber(int PassivBustNumber){this.passivBustNumber = PassivBustNumber;}

    public void  setHpCount(double hp){this.hpCount = hp;}

    public double getHpCount(){return this.hpCount;}

    public int getOreCount(){return this.oreCount;}

    public void setOreCount(int temp){this.oreCount = temp;}

    public int getMin(){
        return this.min;
    }

    public void setMax(int max){this.max = max;}

    public int getMax(){
        return this.max;
    }

    public void setMin(int min){this.min = min;}

    public int getPower(){
        int temp = clickerCount +  r.nextInt(max - min + 1) + min;
        return temp;
    }
}
