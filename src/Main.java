import game.*;
import units.*;

import java.util.Random;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        boolean gameIsRun = true;

        showHelloMessage();

        Scanner sc = new Scanner(System.in);
        Hero hero = new Hero(sc.nextLine());
        Dealer dealer = new Dealer("���� ��� ��");

        showUnitParam(hero);
        showMainMenu();

        while (hero.isAlive() && gameIsRun) {
            Unit monster = getMonster(hero);

            Thread deal = new Thread(new Deal());
            Thread fight = new Thread(new Fight(hero, monster));

            switch (getVal()) {
                case 1 -> deal.start();
                case 2 -> fight.start();
                case 3 -> gameIsRun = false;
            }

            try {
                deal.join();
                fight.join();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            if (gameIsRun) {
                showUnitParam(hero);
                showUnitParam(monster);

                if (hero.isAlive()) {
                    showMainMenu();
                } else {
                    System.out.println("Game over");
                }
            }
        }
    }

    private static Unit getMonster(Hero hero) {
        int multiplier = 0;
        if (hero.getExperience() / 100 == 1) {
            multiplier = 1;
        } else if (hero.getExperience() / 100 == 2) {
            multiplier = 2;
        } else if (hero.getExperience() / 100 == 3) {
            multiplier = 3;
        }
        return (new Random().nextInt(2) > 0)
                ? new Goblin("������� ������", multiplier)
                : new Skeleton("������� ������", multiplier);
    }

    private static int getVal() {
        int val = 0;
        try {
            val = new Scanner(System.in).nextInt();
        } catch (Exception ignored) {
        }
        return val;
    }

    private static void showHelloMessage() {
        System.out.print("""
                ����� ���������� � ���� "Shapalak beru"!
                ��� ������ �������� � �������� �����:
                """);
    }

    private static void showUnitParam(Unit unit) {
        System.out.print("�����: " + unit.getName()
                + "; �����: " + unit.getHealth()
                + "; ��������: " + unit.getAgility()
                + "; ����: " + unit.getPower()
                + "; ����: " + unit.getExperience());
        try {
            Hero hero = (Hero) unit;
            System.out.println("; ������: " + hero.getGold());
        } catch (Exception e) {
            System.out.println();
        }
    }

    private static void showMainMenu() {
        System.out.print("""
                ���� �� ������ �����?
                1 - � ��������;
                2 - � ����� ���;
                3 - �� �����.
                """);
    }
}
