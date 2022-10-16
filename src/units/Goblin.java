package units;

public class Goblin extends Unit {
    public Goblin(String name, int multiplier) {
        super(name, 30, 5, 10, 100, "������");
        this.changeHealth(-this.getHealth() + this.getHealth() * multiplier);
        this.changeAgility(-this.getAgility() + this.getAgility() * multiplier);
        this.changePower(-this.getPower() + this.getPower() * multiplier);
        this.changeExperience(-this.getExperience() + this.getExperience() * multiplier);
    }

}
