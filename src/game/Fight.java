package game;

import units.Hero;
import units.Unit;

import java.util.Random;

public class Fight implements Runnable {
    Hero hero;
    Unit unit;

    public Fight(Hero hero, Unit unit) {
        this.hero = hero;
        this.unit = unit;
    }

    @Override
    public void run() {
        if (new Random().nextInt(2) > 0) { //вероятность: кто первым нанесет урон?
            System.out.println(hero.getName() + " встретил " + unit.getUnitType().toLowerCase() + "а");
            fight(hero, unit);
        } else {
            System.out.println(unit.getUnitType() + " напал на " + hero.getName() + "а");
            fight(unit, hero);
        }

        if (hero.isAlive()) {
            System.out.println(hero.getName() + " победил!");
            hero.changeExperience(unit.getExperience());
            hero.changeGold(unit.getExperience());
        } else {
            System.out.println(unit.getName() + " победил!");
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
            } else {
                kick = u1.getPower() * -1;
                u2.changeHealth(kick);
            }

            System.out.println(u1.getName() + " Shapalak! " + kick);
        } else {
            System.out.println(u1.getName() + " Miss!");
        }
    }
}
