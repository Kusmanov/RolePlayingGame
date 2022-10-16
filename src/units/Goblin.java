package units;

public class Goblin extends Unit {
    private final int multiplier;

    public Goblin(String name, int multiplier) {
        super(name, 30, 10, 15, 10, "Монстр");
        this.multiplier = multiplier;
        this.changeHealth(this.getHealth() * multiplier);
        this.changeAgility(this.getAgility() * multiplier);
        this.changePower(this.getPower() * multiplier);
        this.changeExperience(this.getExperience() * multiplier);
    }

}
