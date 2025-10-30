package com.dealership;

import java.util.ArrayList;

public class Dealership {
    private String name;
    private String address;
    private String phone;
    private ArrayList<Vehicle> inventory;

    public Dealership(String name, String address, String phone) {
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.inventory = new ArrayList<>(); // instantiate the list here
    }

    // Basic add/remove
    public void addVehicle(Vehicle v) {
        inventory.add(v);
    }

    public void removeVehicleByVin(int vin) {
        for (int i = 0; i < inventory.size(); i++) {
            if (inventory.get(i).getVin() == vin) {
                inventory.remove(i);
                break;
            }
        }
    }

    // Return all (used by menu option 7)
    public ArrayList<Vehicle> getAllVehicles() {
        return inventory;
    }

    // Finders — each makes NEW list by scanning the inventory
    public ArrayList<Vehicle> getVehiclesByPrice(double min, double max) {
        ArrayList<Vehicle> results = new ArrayList<>();
        for (Vehicle v : inventory) {
            if (v.getPrice() >= min && v.getPrice() <= max) {
                results.add(v);
            }
        }
        return results;
    }

    public ArrayList<Vehicle> getVehiclesByMakeModel(String make, String model) {
        ArrayList<Vehicle> results = new ArrayList<>();
        for (Vehicle v : inventory) {
            boolean matchMake = v.getMake().equalsIgnoreCase(make);
            boolean matchModel = v.getModel().equalsIgnoreCase(model);
            if (matchMake && matchModel) {
                results.add(v);
            }
        }
        return results;
    }

    public ArrayList<Vehicle> getVehiclesByYear(int minYear, int maxYear) {
        ArrayList<Vehicle> results = new ArrayList<>();
        for (Vehicle v : inventory) {
            if (v.getYear() >= minYear && v.getYear() <= maxYear) {
                results.add(v);
            }
        }
        return results;
    }

    public ArrayList<Vehicle> getVehiclesByColor(String color) {
        ArrayList<Vehicle> results = new ArrayList<>();
        for (Vehicle v : inventory) {
            if (v.getColor().equalsIgnoreCase(color)) {
                results.add(v);
            }
        }
        return results;
    }

    public ArrayList<Vehicle> getVehiclesByMileage(int minMiles, int maxMiles) {
        ArrayList<Vehicle> results = new ArrayList<>();
        for (Vehicle v : inventory) {
            if (v.getOdometer() >= minMiles && v.getOdometer() <= maxMiles) {
                results.add(v);
            }
        }
        return results;
    }

    public ArrayList<Vehicle> getVehiclesByType(String type) {
        ArrayList<Vehicle> results = new ArrayList<>();
        for (Vehicle v : inventory) {
            if (v.getType().equalsIgnoreCase(type)) {
                results.add(v);
            }
        }
        return results;
    }

    // --- newly added getters for file saving ---
    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public String getPhone() {
        return phone;
    }
}