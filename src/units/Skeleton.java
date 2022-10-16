package units;

public class Skeleton extends Unit {
    public Skeleton(String name, int multiplier) {
        super(name, 40, 10, 10, 60, "������");
        this.changeHealth(-this.getHealth() + this.getHealth() * multiplier);
        this.changeAgility(-this.getAgility() + this.getAgility() * multiplier);
        this.changePower(-this.getPower() + this.getPower() * multiplier);
        this.changeExperience(-this.getExperience() + this.getExperience() * multiplier);
    }

}
