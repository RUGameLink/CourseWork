package sample.Items;

import java.util.Random;

public class WaterHeroes extends Heroes{
    public WaterHeroes(int rarity, damageAmplification damageAmpl, healing heal, Names name, String  element, String skill){
        this.rarity = rarity;
        this.damAmpl = damageAmpl;
        this.heal = heal;
        this.name = name;
        this.element = element;
        this.skill = skill;
    }

    @Override
    public int getRarity() {
        return this.rarity;
    }


    @Override
    public damageAmplification getDam(){
        return this.damAmpl;
    }

    @Override
    public healing getHeal(){
        return this.heal;
    }

    @Override
    public Names getName(){
        return this.name;
    }

    @Override
    public String getElement(){
        return this.element;
    }

    @Override
    public String getSkill(){
        return this.skill;
    }

    public static WaterHeroes Generate(){
        int rarity = 1 + (int)(Math.random() * ((5 - 1) + 1));
        damageAmplification damageAmpl = damageAmplification.TRUE;
        healing heal = healing.TRUE;
        Random random = new Random();
        switch (random.nextInt() % 2){
            case 0:
                damageAmpl = damageAmplification.FALSE;
                break;
            case 1:
                damageAmpl = damageAmplification.TRUE;
                break;
        }
        switch (random.nextInt() % 2){
            case 0:
                heal = healing.FALSE;
                break;
            case 1:
                heal = healing.TRUE;
                break;
        }
        int pick = ( 0 + (int)(Math.random() * ((4 - 0) + 1)));
        Names name = Names.ABEL;
        switch (pick) {
            case 0:
                name = Names.ABEL;
                break;

            case 1:
                name = Names.ZELDA;
                break;
            case 2:
                name = Names.NINO;
                break;
            case 3:
                name = Names.LUCIAN;
                break;
            case 4:
                name = Names.HILDA;
                break;
        }
        String element = "Вода";
        String skill = "";
        switch (pick) {
            case 0:
                skill = "Заморозка";
                break;

            case 1:
                skill = "Водяной элементаль";
                break;
            case 2:
                skill = "Поток";
                break;
            case 3:
                skill = "Пузырь";
                break;
            case 4:
                skill = "Отсутствует";
                break;
        }

        return new WaterHeroes(rarity, damageAmpl, heal, name, element, skill);
    }

    @Override
    public String toString() {

        var str = super.toString();
        return str;
    }
}
