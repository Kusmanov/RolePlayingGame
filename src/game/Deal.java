package game;

import units.Hero;

import java.util.Scanner;

public class Deal implements Runnable {
    Hero hero;

    public Deal(Hero hero) {
        this.hero = hero;
    }

    @Override
    public void run() {
        boolean inTheShop = true;

        showHelloMessage();

        while (inTheShop) {
            showMenu();

            switch (getVal()) {
                case 1 -> buyHealth();
                case 2 -> buyAgility();
                case 3 -> buyPower();
                case 4 -> showHeroInfo();
                case 5 -> inTheShop = false;
            }
        }
    }

    private void buyHealth() {
        if (hero.getGold() >= 10) {
            hero.changeGold(-10);
            hero.changeHealth(10);
            System.out.println("health: +10");
        } else {
            System.out.println("������ � ���� ����� ���������� � ����");
        }
    }

    private void buyAgility() {
        if (hero.getGold() >= 10) {
            hero.changeGold(-10);
            hero.changeAgility(1);
            System.out.println("agility: +10");
        } else {
            System.out.println("������ � ���� ����� ���������� � ����");
        }
    }

    private void buyPower() {
        if (hero.getGold() >= 10) {
            hero.changeGold(-10);
            hero.changePower(1);
            System.out.println("power: +10");
        } else {
            System.out.println("������ � ���� ����� ���������� � ����");
        }
    }

    private void showHelloMessage() {
        System.out.println("����� ���������� � ����� ���������� \"��� �� 10!\"");
    }

    private void showMenu() {
        System.out.print("""
                ��� �� ������ �������?
                1 - ������ ����� �����
                2 - ������ ����� ��������
                3 - ������ ����� ����
                4 - ���������� ���������� � �����
                5 - ����� �� �����
                """);
    }

    private int getVal() {
        int val = 0;
        try {
            val = new Scanner(System.in).nextInt();
        } catch (Exception ignored) {
        }
        return val;
    }

    private void showHeroInfo() {
        System.out.println("--> " + hero.getUnitType() + ": " + hero.getName()
                + "; �����: " + hero.getHealth()
                + "; ��������: " + hero.getAgility()
                + "; ����: " + hero.getPower()
                + "; ����: " + hero.getExperience()
                + "; ������: " + hero.getGold());
    }
}
