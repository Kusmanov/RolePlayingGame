package units;

import java.util.Random;

abstract class Unit {
    private final String NAME;
    private int health;
    private int agility;
    private int power;
    private int experience;
    private boolean isAlive;

    public Unit(String name, int health, int agility, int power, int experience) {
        int MIN_NAME_LENGTH = 1;
        int MAX_NAME_LENGTH = 50;

        if (name.length() >= MIN_NAME_LENGTH && name.length() <= MAX_NAME_LENGTH) {
            NAME = name;
        } else {
            throw new IllegalArgumentException();
        }

        this.health = health;
        this.agility = agility;
        this.power = power;
        this.experience = experience;
        isAlive = true;
    }

    public String getName() {
        return NAME;
    }

    public int getHealth() {
        return health;
    }

    public void changeHealth(int health) {
        int MIN_HEALTH = 0;
        int MAX_HEALTH = 100;

        this.health += health;

        if (this.health <= MIN_HEALTH) {
            isAlive = false;
        }

        this.health = setLimits(MIN_HEALTH, MAX_HEALTH, this.health);
    }

    public int getAgility() {
        return agility;
    }

    public void changeAgility(int agility) {
        int MIN_AGILITY = 10;
        int MAX_AGILITY = 30;

        this.agility += agility;
        this.agility = setLimits(MIN_AGILITY, MAX_AGILITY, this.agility);
    }

    public int getPower() {
        return power;
    }

    public void changePower(int power) {
        int MIN_POWER = 10;
        int MAX_POWER = 30;

        this.power += power;
        this.power = setLimits(MIN_POWER, MAX_POWER, this.power);
    }

    public int getExperience() {
        return experience;
    }

    public void changeExperience(int experience) {
        int MIN_EXPERIENCE = 10;
        int MAX_EXPERIENCE = 300;

        this.experience += experience;
        this.experience = setLimits(MIN_EXPERIENCE, MAX_EXPERIENCE, this.experience);
    }

    public boolean isAlive() {
        return isAlive;
    }

    public void attack(Unit unit) {
        if (isAlive && unit.isAlive()) {
            int random = new Random().nextInt(100);

            //System.out.println(random + "\n");

            if (agility * 3 > random) {
                unit.changeHealth(-power);

                if (!unit.isAlive()) {
                    experience += unit.getExperience() / 10;
                }
            }
        }
    }

    private int setLimits(int min, int max, int val) {
        if (val < min) {
            return min;
        } else if (val > max) {
            return max;
        }
        return val;
    }

    @Override
    public String toString() {
        return "Name='" + NAME + "'\n" +
                "health=" + health + '\n' +
                "agility=" + agility + '\n' +
                "power=" + power + '\n' +
                "experience=" + experience + '\n' +
                "isAlive=" + isAlive + '\n';
    }
}
