import game.Deal;
import game.Fight;
import units.*;

import java.util.Random;
import java.util.Scanner;

public class Main {
    private static Dealer dealer = new Dealer("���� ��� ��");
    private static Goblin goblin;
    private static Skeleton skeleton;

    public static void main(String[] args) {
        boolean gameIsRun = true;

        showHelloMessage();

        Scanner sc = new Scanner(System.in);
        Hero hero = new Hero(sc.nextLine());

        showHeroParam(hero);
        showMainMenu();

        while (hero.isAlive() && gameIsRun) {
            Thread fight = new Thread(new Fight(hero, getMonster()));
            Thread deal = new Thread(new Deal());

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

            showHeroParam(hero);

            if (hero.isAlive()) {
                showMainMenu();
            }
        }
    }

    private static Unit getMonster() {
        goblin = new Goblin("������� ������");
        skeleton = new Skeleton("������� ������");

        return (new Random().nextInt(2) > 0) ? goblin : skeleton;
    }

    private static int getVal() {
        int val = 0;
        Scanner sc = new Scanner(System.in);
        try {
            val = sc.nextInt();
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

    private static void showHeroParam(Hero hero) {
        System.out.println("������ " + hero.getUnitType().toLowerCase() + ": " + hero.getName()
                + "; �����: " + hero.getHealth()
                + "; ��������: " + hero.getAgility()
                + "; ����: " + hero.getPower()
                + "; ����: " + hero.getExperience()
                + "; ������: " + hero.getMoney());
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
