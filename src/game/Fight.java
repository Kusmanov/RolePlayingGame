package game;

import units.Hero;
import units.Unit;

import java.util.Random;

public class Fight implements Runnable {
    Hero hero;
    Unit monster;
    public static int killedMonsterCount = 0;

    public Fight(Hero hero, Unit monster) {
        this.hero = hero;
        this.monster = monster;
    }

    @Override
    public void run() {
        if (new Random().nextInt(2) > 0) { //�����������: ��� ������ ������� ����?
            System.out.println(hero.getUnitType() + " �������� " + monster.getUnitType().toLowerCase() + "�!");
            showMonsterInfo();
            fight(hero, monster);
        } else {
            System.out.println(monster.getUnitType() + " ����� �� " + hero.getName() + "�!");
            showMonsterInfo();
            fight(monster, hero);
        }

        if (hero.isAlive()) {
            int tempExp = hero.getExperience();
            int tempGold = hero.getGold();
            hero.changeExperience(monster.getExperience() / 10);
            hero.changeGold(monster.getExperience() / 10);
            System.out.println(hero.getUnitType() + " �������!");
            System.out.println("exp: +" + (hero.getExperience() - tempExp) + ", gold: +" + (hero.getGold() - tempGold));
            killedMonsterCount++;
        } else {
            System.out.println(monster.getUnitType() + " �������!");
        }
    }

    private void showMonsterInfo() {
        System.out.println("--> " + monster.getUnitType() + ": " + monster.getName()
                + "; �����: " + monster.getHealth()
                + "; ��������: " + monster.getAgility()
                + "; ����: " + monster.getPower()
                + "; ����: " + monster.getExperience());
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
        if (u1.getAgility() * 3 > new Random().nextInt(100)) { //�����������, ��� ���� ����� �������
            int kick;
            if (u1.getExperience() / 100 * 30 > new Random().nextInt(100)) { //�����������, ��� ����� ������� ����
                kick = u1.getPower() * -2;
                u2.changeHealth(kick);
                System.out.println(u1.getUnitType() + ": Krit shapalaked " + kick);
            } else {
                kick = u1.getPower() * -1;
                u2.changeHealth(kick);
                System.out.println(u1.getUnitType() + ": Shapalaked " + kick);
            }
        } else {
            System.out.println(u1.getUnitType() + ": Missed");
        }
    }
}
