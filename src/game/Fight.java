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
        if (new Random().nextInt(2) > 0) { //вероятность: кто первым нападет?
            System.out.println(hero.getName() + " встретил " + unit.getUnitType().toLowerCase() + "а");

            while (hero.isAlive() && unit.isAlive()) {
                tryToKick(hero, unit);
                if (unit.isAlive()) {
                    tryToKick(unit, hero);
                }
            }
        } else {
            System.out.println(unit.getUnitType() + " напал на " + hero.getName() + "а");

            while (hero.isAlive() && unit.isAlive()) {
                tryToKick(unit, hero);
                if (hero.isAlive()) {
                    tryToKick(hero, unit);
                }
            }
        }

        if (hero.isAlive()) {
            System.out.println(hero.getName() + " победил!");
        } else {
            System.out.println(unit.getName() + " победил!");
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

            if (!u2.isAlive()) {
                int monsterExp = u2.getExperience();
                u1.changeExperience(monsterExp);
            }
        } else {
            System.out.println(u1.getName() + " Miss!");
        }
    }
}
