import game.Deal;
import game.Fight;
import units.Hero;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        boolean gameIsRun = true;

        showHelloMessage();

        Scanner sc = new Scanner(System.in);
        Hero hero = new Hero(sc.nextLine());

        showUnitParam(hero);
        showMainMenu();

        while (hero.isAlive() && gameIsRun) {
            Thread fight = new Thread(new Fight());
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

            showMainMenu();
        }
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
                ����� ���������� � ���� "����������"!
                ��� ������ �������� � �������� �����:
                """);
    }

    private static void showUnitParam(Hero hero) {
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
