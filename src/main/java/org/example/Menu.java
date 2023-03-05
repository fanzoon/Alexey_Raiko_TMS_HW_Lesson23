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
                    Order order = service.formationOfAnOrderByTheUser();
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
                    System.out.println("Введите номер заказа который хотите удалить:");
                    service.deleteOrder(scanner.nextInt());
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