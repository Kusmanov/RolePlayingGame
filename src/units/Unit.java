package units;

import config.UnitConfig;

public abstract class Unit {
    private final String NAME;
    private final String UNIT_TYPE;
    private int health;
    private int agility;
    private int power;
    private int experience;
    private boolean isAlive;


    public Unit(String name, int health, int agility, int power, int experience, String UNIT_TYPE) {
        final int MIN_NAME_LENGTH = Integer.parseInt(UnitConfig.unitConfig("MIN_NAME_LENGTH"));
        final int MAX_NAME_LENGTH = Integer.parseInt(UnitConfig.unitConfig("MAX_NAME_LENGTH"));

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
        final int MIN_HEALTH = Integer.parseInt(config.UnitConfig.unitConfig("MIN_HEALTH"));
        final int MAX_HEALTH = Integer.parseInt(config.UnitConfig.unitConfig("MAX_HEALTH"));

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
        final int MIN_AGILITY = Integer.parseInt(config.UnitConfig.unitConfig("MIN_AGILITY"));
        final int MAX_AGILITY = Integer.parseInt(config.UnitConfig.unitConfig("MAX_AGILITY"));

        this.agility += agility;
        this.agility = setLimits(MIN_AGILITY, MAX_AGILITY, this.agility);
    }

    public int getPower() {
        return power;
    }

    public void changePower(int power) {
        final int MIN_POWER = Integer.parseInt(config.UnitConfig.unitConfig("MIN_POWER"));
        final int MAX_POWER = Integer.parseInt(config.UnitConfig.unitConfig("MAX_POWER"));

        this.power += power;
        this.power = setLimits(MIN_POWER, MAX_POWER, this.power);
    }

    public int getExperience() {
        return experience;
    }

    public void changeExperience(int experience) {
        final int MIN_EXPERIENCE = Integer.parseInt(config.UnitConfig.unitConfig("MIN_EXPERIENCE"));
        final int MAX_EXPERIENCE = Integer.parseInt(config.UnitConfig.unitConfig("MAX_EXPERIENCE"));

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
        }
        return Math.min(val, max);
    }

}
