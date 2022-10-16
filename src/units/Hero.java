package units;

public class Hero extends Unit {
    private int gold = 100;

    public Hero(String name) {
        super(name, 60, 10, 10, 100, "Герой");
    }

    public int getGold() {
        return gold;
    }

    public void changeGold(int gold) {
        this.gold += gold;
    }
}
