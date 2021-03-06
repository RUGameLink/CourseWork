package sample.Items;

import java.util.Random;

public class Knights extends Heroes{
    public Knights(int rarity, damageAmplification damageAmpl, healing heal, Names name, String element, String skill, int price){ //Пользовательский конструктор
        this.rarity = rarity;
        this.damAmpl = damageAmpl;
        this.heal = heal;
        this.name = name;
        this.element = element;
        this.skill = skill;
        this.price = price;
    }

    public Knights(String owner, int rarity, damageAmplification damageAmpl, healing heal, Names name, String element, String skill, int price){ //Альтернативный пользовательский конструктор
        this.rarity = rarity;
        this.damAmpl = damageAmpl;
        this.heal = heal;
        this.name = name;
        this.element = element;
        this.skill = skill;
        this.owner = owner;
        this.price = price;
    }
    //Геттеры и сеттеры
    @Override
    public int getRarity() {
        return this.rarity;
    }

    @Override
    public String getSkill(){
        return this.skill;
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
    public int getPrice(){return this.price;}

    @Override
    public void setPrice(int price){this.price = price;}

    public String getOwner(){
        return this.owner;
    }

    ///////

    public static Knights Generate(){ //Метод генерации предмета
        int rarity = 1 + (int)(Math.random() * ((5 - 1) + 1));
        damageAmplification damageAmpl = damageAmplification.TRUE;
        healing heal = healing.TRUE;

        Random random = new Random();
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
        String skill = "";
        switch (pick) {
            case 0:
                skill = "Купол";
                break;
            case 1:
                skill = "Отражение";
                break;
            case 2:
                skill = "Разящий удар";
                break;
            case 3:
                skill = "Парирование";
                break;
            case 4:
                skill = "Отсутствует";
                break;
        }
        String element = "Нет";
        int price = 125 + (int)(Math.random() * ((250 - 1) + 125));
        return new Knights(rarity, damageAmpl, heal, name, element, skill, price);
    }

    @Override
    public String toString() { //Вывод информации о предметах
        var str = super.toString();
        return str;
    }
}
