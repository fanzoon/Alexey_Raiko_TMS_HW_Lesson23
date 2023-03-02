package org.example;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Service {

    private static final Logger logger = LogManager.getLogger(Menu.class);

    public void addOrder(Order order) {
        List<Order> listOrder = new ArrayList<>();
        listOrder.add(order);
        File file = new File("src/main/java/org/example/orders_txt");
        try (FileWriter writer = new FileWriter(file, true)) {
            for (Order x : listOrder) {
                String text = x.getNumber() + ";" + x.getName() + ";" + x.getQuality() + ";" + x.getAmount() + ";"
                        + x.getPrice() + ";" + x.getDeleted();
                writer.write(text);
                writer.append('\n');
                writer.flush();
            }
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
                order.setDeleted(Boolean.parseBoolean(stringsOrder[5]));
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

    public void deleteOrder() {
        File file = new File("src/main/java/org/example/orders_txt");
        try (FileWriter writer = new FileWriter(file)) {
            writer.write("");
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
