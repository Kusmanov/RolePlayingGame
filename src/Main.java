import units.Goblin;
import units.Hero;
import units.Skeleton;

public class Main {
    public static void main(String[] args) {
        Hero hero = new Hero("Hero");
        Goblin goblin = new Goblin("Goblin");
        Skeleton skeleton = new Skeleton("Skeleton");

        System.out.println(hero);
        System.out.println(goblin);

        hero.attack(goblin);
        hero.attack(goblin);
        hero.attack(goblin);

        System.out.println(goblin);
        System.out.println(skeleton);

        goblin.attack(skeleton);

        System.out.println(skeleton);

    }
}
