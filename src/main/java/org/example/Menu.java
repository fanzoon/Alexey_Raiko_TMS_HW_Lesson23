package org.example;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Scanner;

public class Menu {
    private static final Logger logger = LogManager.getLogger(Menu.class);
    public static void menu (){
        int menu;
        Scanner scanner = new Scanner(System.in);
        Service service = new Service();
        startMenu();
        do {
            menu = scanner.nextInt();
            switch (menu) {
                case 0 -> startMenu();
                case 1 -> {
                    System.out.println("Введите наименование товара:");
                    String name = scanner.next();
                    System.out.println("Ведите количество товара:");
                    double quality = scanner.nextDouble();
                    System.out.println("Ведите цену товара товара:");
                    double price = scanner.nextDouble();
                    Order order = new Order(service.getMaxNumberOfOrder() + 1, name, quality, price);
                    service.addOrder(order);
                    logger.info("Создан новый заказ " + order);
                    startMenu();
                }
                case 2 -> {
                    System.out.println("Ваш заказ:");
                    for (Order order : service.getAllOrders()) {
                        System.out.println(order);
                    }
                    System.out.println(" ");
                    startMenu();
                }
                case 3 -> {
                    service.deleteOrder();
                    logger.info("Заказ удален.");
                    System.out.println(" ");
                    startMenu();
                }
                case 9 -> System.out.println("Выход из программы");
                default -> logger.info("Выбрали несуществующий пункт меню");
            }
        } while (menu != 9);
    }

    private static void startMenu() {
        System.out.println("Меню программы:");
        System.out.println("Нажми 0, чтобы Вернуться в главное меню:");
        System.out.println("Нажми 1, чтобы создать заказ");
        System.out.println("Нажми 2, чтобы посмотреть заказ");
        System.out.println("Нажми 3, чтобы удалить заказ");
        System.out.println("Нажми 9, чтобы выйти из приложения");
    }
}
