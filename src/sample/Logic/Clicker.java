package sample.Logic;

import java.util.Random;

public class Clicker {
    private static int passivBustPrice;
    private static int passivBustNumber;


    private static int bustPrice;
    private static int bustNumber;
    private static int bustEffect;
    private static int oreCount;
    private static int min;
    private static int max;


    private static Random r = new Random();
    private static int clickerCount;

    private static double hpCount;
    //Пользовательский конструктор для задания "нулевого" состояния игры
    public Clicker(int busteffect, int bustprice, int bustnumber, int passivbustNumber, int passivbustPrice, int count, double hpcount, int murCount, int Min, int Max){
        bustPrice = bustprice;
        bustEffect = busteffect;
        bustNumber = bustnumber;

        passivBustPrice = passivbustPrice;

        passivBustNumber = passivbustNumber;

        clickerCount = count;
        hpCount = hpcount;
        oreCount = murCount;
        min = Min;
        max = Max;
    }

    public static boolean actBuster(){ // Активное усиление, влияющее на скорость "добычи" руды
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

    public static boolean passBuster(){ // Пассивное училение, влияющее на значение получаемой валюты за уничтожение руды
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
    //Геттеры и Сеттеры
    public static int getBustEffect(){
        return bustEffect;
    }

    public static void setClickerCount(int clickercount){
        clickerCount = clickercount;
    }

    public static int getClickerCount(){
        return clickerCount;
    }

    public static int getBustNumber(){
        return bustNumber;
    }

    public static int getBustPrice(){
        return bustPrice;
    }

    public static void setBustEffect(int busteffect){bustEffect = busteffect;}

    public static void setBustPrice(int bustprice){bustPrice = bustprice;}

    public static void setBustNumber(int bustnumber){bustNumber = bustnumber;}

    public static int getPassivBustPrice(){return passivBustPrice;}

    public static int getPassivBustNumber() {
        return passivBustNumber;
    }

    public static void setPassivBustPrice(int PassivBustPrice){passivBustPrice = PassivBustPrice;}

    public static void setPassivBustNumber(int PassivBustNumber){passivBustNumber = PassivBustNumber;}

    public static void  setHpCount(double hp){hpCount = hp;}

    public static double getHpCount(){return hpCount;}

    public static int getOreCount(){return oreCount;}

    public static void setOreCount(int temp){oreCount = temp;}

    public static int getMin(){
        return min;
    }

    public static void setMax(int Max){max = Max;}

    public static int getMax(){
        return max;
    }

    public static void setMin(int Min){min = Min;}

    public static int getPower(){ //Получение игровой валюты в регулируемых пределах
        int temp = clickerCount +  r.nextInt(max - min + 1) + min;
        return temp;
    }
}
