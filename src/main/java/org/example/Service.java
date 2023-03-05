package org.example;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Service {

    private static final Logger logger = LogManager.getLogger(Menu.class);

    public Order formationOfAnOrderByTheUser() {
        Scanner scanner = new Scanner(System.in) ;
        Service service = new Service();
        System.out.println("Введите наименование товара:");
        String name = scanner.next();
        System.out.println("Ведите количество товара:");
        double quality = scanner.nextDouble();
        System.out.println("Ведите цену товара товара:");
        double price = scanner.nextDouble();
        Order order = new Order();
        order.setNumber(service.getMaxNumberOfOrder() + 1);
        order.setName(name);
        order.setQuality(quality);
        order.setPrice(price);
        order.setAmount(order.getQuality() * order.getPrice());
        return order;
    }

    public void addOrder(Order order) {
        File file = new File("src/main/java/org/example/orders_txt");
        try (FileWriter writer = new FileWriter(file, true)) {
                String text = order.getNumber() + ";"
                            + order.getName() + ";"
                            + order.getQuality() + ";"
                            + order.getAmount() + ";"
                            + order.getPrice();
                writer.write(text);
                writer.append("\n");
                writer.flush();
        } catch (IOException e) {
            logger.error("Файл c именем: " + "orders_txt" + " не найден");
        }
    }

    public List<Order> getAllOrders() {
        List<Order> listOrder = new ArrayList<>();
        File file = new File("src/main/java/org/example/orders_txt");
        FileReader fr = null;
        BufferedReader reader = null;
        try {
            fr = new FileReader(file);
            reader = new BufferedReader(fr);
            String line = reader.readLine();
            while (line != null) {
                String[] stringsOrder = line.split(";");
                Order order = new Order();
                order.setNumber(Integer.parseInt(stringsOrder[0]));
                order.setName(stringsOrder[1]);
                order.setQuality(Double.parseDouble(stringsOrder[2]));
                order.setAmount(Double.parseDouble(stringsOrder[3]));
                order.setPrice(Double.parseDouble(stringsOrder[4]));
                listOrder.add(order);
                line = reader.readLine();
            }
        } catch (IOException e) {
            logger.error("Файл c именем: " + "orders_txt" + " не найден");
        } finally {
            try {
                if (fr != null) {
                    fr.close();
                }
                if (reader != null) {
                    reader.close();
                }
            } catch (IOException e) {
                logger.error("FileReader fr или BufferedReader reader не закрыт");
            }
        }
        return listOrder;
    }

    public void deleteOrder(int numberOrder) {
        Service service = new Service();
        List<Order> allOrders = service.getAllOrders();
        File file = new File("src/main/java/org/example/orders_txt");
        try (FileWriter writer = new FileWriter(file)) {
            for (Order x : allOrders) {
                if (x.getNumber() != numberOrder) {
                    String text = x.getNumber() + ";"
                            + x.getName() + ";"
                            + x.getQuality() + ";"
                            + x.getPrice() + ";"
                            + x.getAmount();
                    writer.write(text);
                    writer.append('\n');
                    writer.flush();
                }
            }
        } catch (IOException e) {
            logger.error("Файл c именем: " + "orders_txt" + " не найден");
        }
    }

    public int getMaxNumberOfOrder() {
        List<Order> allOrders = getAllOrders();
        return allOrders
                .stream()
                .map(Order::getNumber)
                .max(Integer::compareTo)
                .orElse(0);
    }
}