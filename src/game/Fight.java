package game;

import units.Hero;
import units.Unit;

import java.util.Random;

public class Fight implements Runnable {
    Hero hero;
    Unit monster;

    public Fight(Hero hero, Unit monster) {
        this.hero = hero;
        this.monster = monster;
    }

    @Override
    public void run() {
        if (new Random().nextInt(2) > 0) { //вероятность: кто первым нанесет урон?
            System.out.println(hero.getName() + " встретил " + monster.getUnitType().toLowerCase() + "а");
            fight(hero, monster);
        } else {
            System.out.println(monster.getUnitType() + " напал на " + hero.getName() + "а");
            fight(monster, hero);
        }

        if (hero.isAlive()) {
            int tempExp = hero.getExperience();
            int tempGold = hero.getGold();
            hero.changeExperience(monster.getExperience() / 10);
            hero.changeGold(monster.getExperience() / 10);
            System.out.println(hero.getName() + " победил!");
            System.out.println("Exp: +" + (hero.getExperience() - tempExp) + ", Gold: +" + (hero.getGold() - tempGold));
        } else {
            System.out.println(monster.getName() + " победил!");
        }
    }

    private void fight(Unit u1, Unit u2) {
        while (u1.isAlive() && u2.isAlive()) {
            tryToKick(u1, u2);
            if (u2.isAlive()) {
                tryToKick(u2, u1);
            }
        }
    }

    private void tryToKick(Unit u1, Unit u2) {
        if (u1.getAgility() * 3 > new Random().nextInt(100)) { //вероятность, что удар будет нанесен
            int kick;
            if (u1.getExperience() / 100 * 30 > new Random().nextInt(100)) { //вероятность, что будет двойной урон
                kick = u1.getPower() * -2;
                u2.changeHealth(kick);
                System.out.println(u1.getName() + ": Krit shapalaked " + kick);
            } else {
                kick = u1.getPower() * -1;
                u2.changeHealth(kick);
                System.out.println(u1.getName() + ": Shapalaked " + kick);
            }
        } else {
            System.out.println(u1.getName() + ": Missed");
        }
    }
}
