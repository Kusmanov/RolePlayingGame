package units;

public class Hero extends Unit {
    private int money = 100;

    public Hero(String name) {
        super(name, 60, 10, 10, 200, "�����");
    }

    public int getMoney() {
        return money;
    }

}
