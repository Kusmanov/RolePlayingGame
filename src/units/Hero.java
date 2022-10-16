package units;

public class Hero extends Unit {
    private int gold = 0;

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
