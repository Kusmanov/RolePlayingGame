package units;

public class Skeleton extends Unit {
    public Skeleton(String name, int multiplier) {
        super(name, 10, 10, 15, 100, "Монстр");
        this.changeHealth(-this.getHealth() + this.getHealth() * multiplier);
        this.changeAgility(-this.getAgility() + this.getAgility() * multiplier);
        this.changePower(-this.getPower() + this.getPower() * multiplier);
        this.changeExperience(-this.getExperience() + this.getExperience() * multiplier);
    }

}
