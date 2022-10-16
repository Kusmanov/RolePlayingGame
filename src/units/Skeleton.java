package units;

public class Skeleton extends Unit {
    private final int multiplier;

    public Skeleton(String name, int multiplier) {
        super(name, 30, 15, 10, 10, "Монстр");
        this.multiplier = multiplier;
        this.changeHealth(this.getHealth() * multiplier);
        this.changeAgility(this.getAgility() * multiplier);
        this.changePower(this.getPower() * multiplier);
        this.changeExperience(this.getExperience() * multiplier);
    }

}
