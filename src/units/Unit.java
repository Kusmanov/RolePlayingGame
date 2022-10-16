package units;

public abstract class Unit {
    private final String NAME;
    private final String UNIT_TYPE;
    private final int MIN_NAME_LENGTH = 1;
    private final int MAX_NAME_LENGTH = 50;
    private final int MIN_HEALTH = 0;
    private final int MAX_HEALTH = 100;
    private final int MIN_AGILITY = 10;
    private final int MAX_AGILITY = 30;
    private final int MIN_POWER = 10;
    private final int MAX_POWER = 30;
    private final int MIN_EXPERIENCE = 10;
    private final int MAX_EXPERIENCE = 300;
    private int health;
    private int agility;
    private int power;
    private int experience;
    private boolean isAlive;


    public Unit(String name, int health, int agility, int power, int experience, String UNIT_TYPE) {
        if (name.length() >= MIN_NAME_LENGTH && name.length() <= MAX_NAME_LENGTH) {
            NAME = name;
        } else {
            throw new IllegalArgumentException();
        }
        this.UNIT_TYPE = UNIT_TYPE;

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
        this.agility += agility;
        this.agility = setLimits(MIN_AGILITY, MAX_AGILITY, this.agility);
    }

    public int getPower() {
        return power;
    }

    public void changePower(int power) {
        this.power += power;
        this.power = setLimits(MIN_POWER, MAX_POWER, this.power);
    }

    public int getExperience() {
        return experience;
    }

    public void changeExperience(int experience) {
        this.experience += experience;
        this.experience = setLimits(MIN_EXPERIENCE, MAX_EXPERIENCE, this.experience);
    }

    public String getUnitType() {
        return UNIT_TYPE;
    }

    public boolean isAlive() {
        return isAlive;
    }

    private int setLimits(int min, int max, int val) {
        if (val < min) {
            return min;
        } else if (val > max) {
            return max;
        }
        return val;
    }

//    @Override
//    public String toString() {
//        return "Name='" + NAME + "'\n" +
//                "health=" + health + '\n' +
//                "agility=" + agility + '\n' +
//                "power=" + power + '\n' +
//                "experience=" + experience + '\n' +
//                "isAlive=" + isAlive + '\n';
//    }
}
