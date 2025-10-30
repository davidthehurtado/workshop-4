package com.dealership;

import java.io.*;
import java.util.ArrayList;

public class DealershipFileManager {

    public Dealership getDealership(String fileName) {
        Dealership dealership = null;

        try {
            BufferedReader reader = new BufferedReader(new FileReader(fileName));

            // Read first line — dealership info
            String firstLine = reader.readLine();
            if (firstLine != null) {
                String[] parts = firstLine.split("\\|");
                String name = parts[0];
                String address = parts[1];
                String phone = parts[2];

                // Create dealership object
                dealership = new Dealership(name, address, phone);

                // Read vehicle lines
                String line;
                while ((line = reader.readLine()) != null) {
                    String[] t = line.split("\\|");

                    int vin = Integer.parseInt(t[0]);
                    int year = Integer.parseInt(t[1]);
                    String make = t[2];
                    String model = t[3];
                    String type = t[4];
                    String color = t[5];
                    int odometer = Integer.parseInt(t[6]);
                    double price = Double.parseDouble(t[7]);

                    Vehicle v = new Vehicle(vin, year, make, model, type, color, odometer, price);
                    dealership.addVehicle(v);
                }
            }

            reader.close();

        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
            e.printStackTrace();
        }

        return dealership;
    }

    public void saveDealership(Dealership d, String fileName) {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(fileName, false));

            // Write dealership header line using the new getters
            writer.write(d.getName() + "|" + d.getAddress() + "|" + d.getPhone());
            writer.newLine();

            // Write each vehicle
            ArrayList<Vehicle> all = d.getAllVehicles();
            for (Vehicle v : all) {
                String row = v.getVin() + "|" +
                        v.getYear() + "|" +
                        v.getMake() + "|" +
                        v.getModel() + "|" +
                        v.getType() + "|" +
                        v.getColor() + "|" +
                        v.getOdometer() + "|" +
                        String.format("%.2f", v.getPrice());
                writer.write(row);
                writer.newLine();
            }

            writer.close();
        } catch (IOException e) {
            System.out.println("Error writing file: " + e.getMessage());
            e.printStackTrace();
        }
    }
}