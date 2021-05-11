package sample.Items;


public class Heroes {
    protected int rarity;
    protected Names name;
    protected String skill;

    protected damageAmplification damAmpl;
    protected healing heal;
    protected String element;

    protected String owner;

    protected int price;

    public enum Names { //Перечисление имен персонажей

        ABEL ("Абель"),
        ZELDA ("Зельда"),
        NINO ("Нино"),
        LUCIAN ("Лучан"),
        HILDA ("Хильда");

        private String name;

        Names(String name) {
            this.name = name;
        }


        @Override
        public String toString() {
            return name;
        }
    }


    public enum damageAmplification { //Перечисление присутствия показателя получения урона

        TRUE ("Есть"),
        FALSE ("Нет");

        private String dam;

        damageAmplification(String dam) {
            this.dam = dam;
        }

        public String getTitle() {
            return dam;
        }

        @Override
        public String toString() {
            return dam;
        }
    }

    public enum healing { //Перечисление присутствия показателя исцеления

        TRUE ("Есть"),
        FALSE ("Нет");

        private String heal;

        healing(String name) {
            this.heal = name;
        }

        public String getTitle() {
            return heal;
        }

        @Override
        public String toString() {
            return heal;
        }
    }

    //Геттеры и сеттеры

    public int getRarity() {
        return this.rarity;
    }

    public damageAmplification getDam(){
        return this.damAmpl;
    }

    public healing getHeal(){
        return this.heal;
    }

    public Names getName(){
        return this.name;
    }

    public String getElement(){
        return this.element;
    }

    public String getSkill(){
        return this.skill;
    }

    public String getOwner(){return this.owner;}

    public int getPrice(){return this.price;}

    public void setPrice(int price){this.price = price;}

    @Override
    public String toString() { //Вывод информации о предметах

        var str = "Привет! Мое имя: " + this.name;
        str += "\nПовышенный урон: " + this.damAmpl;
        str += "\nИсцеление: " + this.heal;
        str += "\nМоя стихия: " + this.element;
        str += "\nРедкость героя: " + this.rarity;
        str += "\nСпособность героя: " + this.skill;
        str +="\nСтоимость продажи: " + this.price;
        return str;
    }
}
