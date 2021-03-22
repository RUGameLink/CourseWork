package sample.Items;


public class Heroes {
    public int rarity;
    public Names name;
    public String skill;

    public damageAmplification damAmpl;
    public healing heal;
    public String element;

    public enum Names {

        ABEL ("Абель"),
        ZELDA ("Зельда"),
        NINO ("Нино"),
        LUCIAN ("Лучан"),
        HILDA ("Хильда");

        private String name;

        Names(String name) {
            this.name = name;
        }
    /*    public Enum getName(String temp){
            switch (temp) {
                case 0:
                    temp = "Купол";
                    break;
                case 1:
                    temp = "Отражение";
                    break;
                case 2:
                    temp = "Разящий удар";
                    break;
                case 3:
                    temp = "Парирование";
                    break;
                case 4:
                    temp = "Отсутствует";
                    break;
            }
        }*/


        @Override
        public String toString() {
            return name;
        }
    }


    public enum damageAmplification {

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

    public enum healing {

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

    @Override
    public String toString() {

        var str = "Привет! Мое имя: " + this.name;
        str += "\nПовышенный урон: " + this.damAmpl;
        str += "\nИсцеление: " + this.heal;
        str += "\nМоя стихия: " + this.element;
        str += "\nРедкость героя: " + this.rarity;
        str += "\nСпособность героя: " + this.skill;
        return str;
    }
}
