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

        System.out.println("������ ����� " + hero.getName() + "!");

        while (hero.isAlive() && gameIsRun) {
            showMainMenu();

            Unit monster = getMonster(hero);

            Thread deal = new Thread(new Deal(hero));
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
        }
        System.out.println("����� ��������: " + Fight.killedMonsterCount);
        System.out.println("GAME OVER");
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
                ? new Goblin("������", multiplier)
                : new Skeleton("������", multiplier);
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

    private static void showMainMenu() {
        System.out.print("""
                ���� �� ������ �����?
                1 - � ��������
                2 - � ����� ���
                3 - �� �����
                """);
    }
}
