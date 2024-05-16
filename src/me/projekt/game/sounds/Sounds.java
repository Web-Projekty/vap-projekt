package me.projekt.game.sounds;

public class Sounds {

    public enum Songs {
        MENU_1(0),
        LEVEL_1(1),
        LEVEL_2(2);

        private int id;

        Songs(int id) {
            this.id = id;
        }

        public int getId() {
            return id;
        }

        @Override
        public String toString() {
            return super.toString();
        }
    }

    public enum SFX {
        JUMP(0),
        DIE(1),
        GAMEOVER(2),
        LVL_COMPLETED(3),
        ATTACK_ONE(4),
        ATTACK_TWO(5),
        ATTACK_THREE(6);

        private int id;

        SFX(int id) {
            this.id = id;
        }

        public int getId() {
            return id;
        }

        @Override
        public String toString() {
            return super.toString();
        }
    }

}
