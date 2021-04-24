package sample.Items;


import java.util.Random;

public class FireHeroes extends Heroes{
    public FireHeroes(int rarity, damageAmplification damAmpl, healing heal, Names name, String element, String skill, int price){
        this.rarity = rarity;
        this.damAmpl = damAmpl;
        this.heal = heal;
        this.name = name;
        this.element = element;
        this.skill = skill;
        this.price = price;

    }

    public FireHeroes(String owner, int rarity, damageAmplification damAmpl, healing heal, Names name, String element, String skill, int price){
        this.rarity = rarity;
        this.damAmpl = damAmpl;
        this.heal = heal;
        this.name = name;
        this.element = element;
        this.skill = skill;
        this.owner = owner;
        this.price = price;
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
    public String getSkill(){
        return this.skill;
    }

    public String getOwner(){
        return this.owner;
    }

    @Override
    public String getElement(){
        return this.element;
    }

    @Override
    public int getPrice(){return this.price;}

    @Override
    public void setPrice(int price){this.price = price;}

    public static FireHeroes Generate(){
        int rarity = 1 + (int)(Math.random() * ((5 - 1) + 1));
        damageAmplification damageAmpl = damageAmplification.TRUE;
        healing heal = healing.TRUE;

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

        String skill = "";
        switch (pick) {
            case 0:
                skill = "Огненный шар";
                break;

            case 1:
                skill = "Огненный вихрь";
                break;
            case 2:
                skill = "Пожар";
                break;
            case 3:
                skill = "Свеча";
                break;
            case 4:
                skill = "Стена огня";
                break;
            case 5:
                skill = "Отсутствует";
                break;
        }
        String element = "Огонь";

        int price = 125 + (int)(Math.random() * ((250 - 1) + 125));

        return new FireHeroes(rarity, damageAmpl, heal, name, element, skill, price);
    }

    @Override
    public String toString() {
        var str = super.toString();
        return str;
    }
}
