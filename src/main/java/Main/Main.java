package Main;

import Models.Shippers;
import db.DataManager;
import org.apache.commons.dbcp2.BasicDataSource;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        String username = args[0];
        String password = args[1];

        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setUrl("jdbc:mysql://localhost:3306/northwind");
        dataSource.setUsername(username);
        dataSource.setPassword(password);

        DataManager dataManager = new DataManager(dataSource);

        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter shipping company:");
        String company = scanner.nextLine();

        System.out.println("Enter phone number: ");
        String number = scanner.nextLine();

        Shippers shipper = new Shippers(company, number);
        dataManager.insertNewShipper(shipper);

        dataManager.getAllShippers();
    }
}
