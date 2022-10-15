import game.Deal;
import game.Fight;
import units.*;

import java.util.Random;
import java.util.Scanner;

public class Main {
    private static Dealer dealer = new Dealer("Мэри Кей Эш");
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
        goblin = new Goblin("Дмитрий Пучков");
        skeleton = new Skeleton("Маколей Калкин");

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
                Добро пожаловать в игру "Shapalak beru"!
                Для начала создайте и назовите героя:
                """);
    }

    private static void showHeroParam(Hero hero) {
        System.out.println("Создан " + hero.getUnitType().toLowerCase() + ": " + hero.getName()
                + "; жизнь: " + hero.getHealth()
                + "; ловкость: " + hero.getAgility()
                + "; сила: " + hero.getPower()
                + "; опыт: " + hero.getExperience()
                + "; золото: " + hero.getMoney());
    }

    private static void showMainMenu() {
        System.out.print("""
                Куда вы хотите пойти?
                1 - к торговцу;
                2 - в тёмный лес;
                3 - на выход.
                """);
    }
}
