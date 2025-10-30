package com.dealership;

import java.util.ArrayList;
import java.util.Scanner;

public class UserInterface {
    private Dealership dealership;
    private Scanner scanner = new Scanner(System.in);

    public void display() {
        init(); // INNIT?

        boolean running = true;
        while (running) {
            printMenu();
            System.out.print("Choose an option: ");
            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    processGetByPrice();
                    break;
                case "2":
                    processGetByMakeModel();
                    break;
                case "3":
                    processGetByYear();
                    break;
                case "4":
                    processGetByColor();
                    break;
                case "5":
                    processGetByMileage();
                    break;
                case "6":
                    processGetByType();
                    break;
                case "7":
                    processGetAllVehicles();
                    break;
                case "8":
                    processAddVehicle();
                    break;
                case "9":
                    processRemoveVehicle();
                    break;
                case "99":
                    System.out.println("Goodbye!");
                    running = false;
                    break;
                default:
                    System.out.println("Invalid option. Try again.");
            }
            System.out.println();
        }
    }

    // Loads the dealership data from the inventory.csv file
    private void init() {
        DealershipFileManager fm = new DealershipFileManager();
        this.dealership = fm.getDealership("inventory.csv");
        if (this.dealership == null) {
            System.out.println("Error loading dealership data. Check your inventory.csv file.");
        }
    }

    // MENU OPTIONS
    private void printMenu() {
        System.out.println("====================================");
        System.out.println("1 - Find vehicles within a price range");
        System.out.println("2 - Find vehicles by make / model");
        System.out.println("3 - Find vehicles by year range");
        System.out.println("4 - Find vehicles by color");
        System.out.println("5 - Find vehicles by mileage range");
        System.out.println("6 - Find vehicles by type (car, truck, SUV, van)");
        System.out.println("7 - List ALL vehicles");
        System.out.println("8 - Add a vehicle");
        System.out.println("9 - Remove a vehicle");
        System.out.println("99 - Quit");
        System.out.println("====================================");
    }

    // LIST OF VEHICLES
    private void displayVehicles(ArrayList<Vehicle> list) {
        if (list == null || list.isEmpty()) {
            System.out.println("No vehicles found.");
            return;
        }
        for (Vehicle v : list) {
            System.out.println(v);
        }
    }

    // MENU OPTION METHODS

    private void processGetByPrice() {
        System.out.print("Enter minimum price: ");
        double min = readDouble();
        System.out.print("Enter maximum price: ");
        double max = readDouble();
        displayVehicles(dealership.getVehiclesByPrice(min, max));
    }

    private void processGetByMakeModel() {
        System.out.print("Enter make: ");
        String make = scanner.nextLine();
        System.out.print("Enter model: ");
        String model = scanner.nextLine();
        displayVehicles(dealership.getVehiclesByMakeModel(make, model));
    }

    private void processGetByYear() {
        System.out.print("Enter minimum year: ");
        int min = readInt();
        System.out.print("Enter maximum year: ");
        int max = readInt();
        displayVehicles(dealership.getVehiclesByYear(min, max));
    }

    private void processGetByColor() {
        System.out.print("Enter color: ");
        String color = scanner.nextLine();
        displayVehicles(dealership.getVehiclesByColor(color));
    }

    private void processGetByMileage() {
        System.out.print("Enter minimum mileage: ");
        int min = readInt();
        System.out.print("Enter maximum mileage: ");
        int max = readInt();
        displayVehicles(dealership.getVehiclesByMileage(min, max));
    }

    private void processGetByType() {
        System.out.print("Enter type (car, truck, SUV, van): ");
        String type = scanner.nextLine();
        displayVehicles(dealership.getVehiclesByType(type));
    }

    private void processGetAllVehicles() {
        displayVehicles(dealership.getAllVehicles());
    }

    private void processAddVehicle() {
        System.out.print("VIN (number): ");
        int vin = readInt();
        System.out.print("Year: ");
        int year = readInt();
        System.out.print("Make: ");
        String make = scanner.nextLine();
        System.out.print("Model: ");
        String model = scanner.nextLine();
        System.out.print("Type: ");
        String type = scanner.nextLine();
        System.out.print("Color: ");
        String color = scanner.nextLine();
        System.out.print("Odometer: ");
        int miles = readInt();
        System.out.print("Price: ");
        double price = readDouble();

        Vehicle v = new Vehicle(vin, year, make, model, type, color, miles, price);
        dealership.addVehicle(v);
        new DealershipFileManager().saveDealership(dealership, "inventory.csv");
        System.out.println("Vehicle added successfully!");
    }

    private void processRemoveVehicle() {
        System.out.print("Enter VIN to remove: ");
        int vin = readInt();
        dealership.removeVehicleByVin(vin);
        new DealershipFileManager().saveDealership(dealership, "inventory.csv");
        System.out.println("Vehicle removed (if VIN existed).");
    }

    // INPUT HELPERS
    private int readInt() {
        while (true) {
            try {
                return Integer.parseInt(scanner.nextLine());
            } catch (Exception e) {
                System.out.print("Please enter a valid number: ");
            }
        }
    }

    private double readDouble() {
        while (true) {
            try {
                return Double.parseDouble(scanner.nextLine());
            } catch (Exception e) {
                System.out.print("Please enter a valid number: ");
            }
        }
    }
}