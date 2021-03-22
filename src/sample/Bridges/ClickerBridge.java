package sample.Bridges;

import sample.FileStreamer.FileStreamer;

import java.io.IOException;

public class ClickerBridge {
    private static int passivBustPrice;
    private static int passivBustNumber;
    private static int bustPrice;
    private static int bustNumber;
    private static int bustEffect;
    private static int clickerCount;
    private static double perSecond;
    private static int boxPrice;
    private static boolean timerOn;

    private static FileStreamer fileStreamer = new FileStreamer();

    public static int getPassivBustPrice() {
        return passivBustPrice;
    }

    public static int getBoxPrice() {
        return boxPrice;
    }

    public static int getPassivBustNumber() {
        return passivBustNumber;
    }

    public static int getBustPrice() {
        return bustPrice;
    }

    public static int getBustNumber() {
        return bustNumber;
    }

    public static int getBustEffect() {
        return bustEffect;
    }

    public static int getClickerCount() {
        return clickerCount;
    }

    public static double getPerSecond() {
        return perSecond;
    }

    public static boolean getTimerOne(){
        return timerOn;
    }

    public static void setPassivBustPrice(int passivBust) {
        passivBustPrice = passivBust;
    }

    public static void setBoxPrice(int bPrice) {
        boxPrice = bPrice;
    }

    public static void setPassivBustNumber(int passivBust) {
        passivBustNumber = passivBust;
    }

    public static void setBustPrice(int bust) {
        bustPrice = bust;
    }

    public static void setBustNumber(int Number) {
        bustNumber = Number;
    }

    public static void setBustEffect(int Effect) {
        bustEffect = Effect;
    }

    public static void setClickerCount(int Count) {
        clickerCount = Count;
    }

    public static void setPerSecond(double Second) {
        perSecond = Second;
    }

    public static void setTimerOn(boolean timer){
        timerOn = timer;
    }

    public static void Output() throws IOException {
        String res = "";
        res = clickerCount + " " + bustEffect + " " + bustNumber + " " + bustPrice + " "
                + passivBustNumber + " " + passivBustPrice + " " + perSecond + " " + boxPrice + " " + timerOn;
        fileStreamer.OutputStream(res);
    }

    public static void Input() throws IOException {
        String temp = fileStreamer.InputStream();
            if (temp.length() == 0){
                clickerCount = 0;
                bustEffect = 0;
                bustNumber = 0;
                bustPrice = 25;
                passivBustNumber = 0;
                passivBustPrice = 10;
                perSecond = 0;
                boxPrice = 250;
                timerOn = false;

                Output();
            }
            else {
                String[] words = split(temp);
                clickerCount = Integer.parseInt(words[0]);
                bustEffect = Integer.parseInt(words[1]);
                bustNumber = Integer.parseInt(words[2]);
                bustPrice = Integer.parseInt(words[3]);
                passivBustNumber = Integer.parseInt(words[4]);
                passivBustPrice = Integer.parseInt(words[5]);
                perSecond = Double.parseDouble(words[6]);
                boxPrice = Integer.parseInt(words[7]);
                timerOn = Boolean.parseBoolean(words[8]);
            }


    }
    private static String[] split(String temp){

        String[] words = temp.split(" ");
        return words;
    }
}
