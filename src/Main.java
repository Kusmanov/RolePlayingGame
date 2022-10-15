import game.Deal;
import game.Fight;
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

        showHeroParam(hero);
        showMainMenu();

        while (hero.isAlive() && gameIsRun) {
            Thread deal = new Thread(new Deal());
            Thread fight = new Thread(new Fight(hero, getMonster()));

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
                showHeroParam(hero);

                if (hero.isAlive()) {
                    showMainMenu();
                }
            }
        }
    }

    private static Unit getMonster() {
        Goblin goblin = new Goblin("������� ������");
        Skeleton skeleton = new Skeleton("������� ������");

        return (new Random().nextInt(2) > 0) ? goblin : skeleton;
    }

    private static int getVal() {
        int val = 0;
        try {
            val = new Scanner(System.in).nextInt();
        } catch (Exception e) {
            System.out.println("������� �����: �� 1 �� 3");
        }
        return val;
    }

    private static void showHelloMessage() {
        System.out.print("""
                ����� ���������� � ���� "Shapalak beru"!
                ��� ������ �������� � �������� �����:
                """);
    }

    private static void showHeroParam(Hero hero) {
        System.out.println("�����: " + hero.getName()
                + "; �����: " + hero.getHealth()
                + "; ��������: " + hero.getAgility()
                + "; ����: " + hero.getPower()
                + "; ����: " + hero.getExperience()
                + "; ������: " + hero.getGold());
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
