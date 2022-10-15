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
        Dealer dealer = new Dealer("Мэри Кей Эш");

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
        Goblin goblin = new Goblin("Дмитрий Пучков");
        Skeleton skeleton = new Skeleton("Маколей Калкин");

        return (new Random().nextInt(2) > 0) ? goblin : skeleton;
    }

    private static int getVal() {
        int val = 0;
        try {
            val = new Scanner(System.in).nextInt();
        } catch (Exception e) {
            System.out.println("Введите число: от 1 до 3");
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
        System.out.println("Герой: " + hero.getName()
                + "; жизнь: " + hero.getHealth()
                + "; ловкость: " + hero.getAgility()
                + "; сила: " + hero.getPower()
                + "; опыт: " + hero.getExperience()
                + "; золото: " + hero.getGold());
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
