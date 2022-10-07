package units;

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

    public boolean isAlive() {
        return isAlive;
    }

    public int getExperience() {
        return experience;
    }

    public void changeExperience(int experience) {
        int MIN_EXPERIENCE = 1;
        int MAX_EXPERIENCE = 9;

        this.experience += experience;

        if (this.experience < MIN_EXPERIENCE) {
            this.experience = MIN_EXPERIENCE;
        } else if (this.experience > MAX_EXPERIENCE) {
            this.experience = MAX_EXPERIENCE;
        }
    }

    public int getAgility() {
        return agility;
    }

    public void changeAgility(int agility) {
        int MIN_AGILITY = 1;
        int MAX_AGILITY = 9;

        this.agility += agility;

        if (this.agility < MIN_AGILITY) {
            this.agility = MIN_AGILITY;
        } else if (this.agility > MAX_AGILITY) {
            this.agility = MAX_AGILITY;
        }
    }

    public int getPower() {
        return power;
    }

    public void changePower(int power) {
        int MIN_POWER = 1;
        int MAX_POWER = 9;

        this.power += power;

        if (this.power < MIN_POWER) {
            this.power = MIN_POWER;
        } else if (this.power > MAX_POWER) {
            this.power = MAX_POWER;
        }
    }

    public int getHealth() {
        return health;
    }

    public void changeHealth(int health) {
        int MIN_HEALTH = 1;
        int MAX_HEALTH = 9;

        this.health += health;

        if (this.health < MIN_HEALTH) {
            isAlive = false;
        } else if (this.health > MAX_HEALTH) {
            this.health = MAX_HEALTH;
        }
    }

    public void attack(Unit unit) {
        if (isAlive) {
            unit.changeHealth(-power);
        }
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
