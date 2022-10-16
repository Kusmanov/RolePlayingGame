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
            System.out.println("Золото и опыт можно заработать в лесу");
        }
    }

    private void buyAgility() {
        if (hero.getGold() >= 10) {
            hero.changeGold(-10);
            hero.changeAgility(1);
            System.out.println("agility: +10");
        } else {
            System.out.println("Золото и опыт можно заработать в лесу");
        }
    }

    private void buyPower() {
        if (hero.getGold() >= 10) {
            hero.changeGold(-10);
            hero.changePower(1);
            System.out.println("power: +10");
        } else {
            System.out.println("Золото и опыт можно заработать в лесу");
        }
    }

    private void showHelloMessage() {
        System.out.println("Добро пожаловать в лавку волшебника \"Все по 10!\"");
    }

    private void showMenu() {
        System.out.print("""
                Что вы хотите сделать?
                1 - купить зелье ЖИЗНИ
                2 - купить зелье ЛОВКОСТИ
                3 - купить зелье СИЛЫ
                4 - посмотреть информацию о герое
                5 - выйти из лавки
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
                + "; жизнь: " + hero.getHealth()
                + "; ловкость: " + hero.getAgility()
                + "; сила: " + hero.getPower()
                + "; опыт: " + hero.getExperience()
                + "; золото: " + hero.getGold());
    }
}
