package vehiclemanagement;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class CaseMethod {

    public static void displayVehicleDetails(ArrayList<Vehicle> vList) {
        if (vList.isEmpty()) {
            System.out.println("No vehicles added yet.");
        }
        
        else {
            for (Vehicle vehicle : vList) {
                System.out.println(vehicle);
            }
        }
    }
    
    public static void addVehicle(ArrayList<Vehicle> vList, Scanner kazu) {
        System.out.println("Enter Vehicle Details:");
        System.out.print("Registration Number: ");
            String regNo = kazu.nextLine();
        System.out.print("Make: ");
            String make = kazu.nextLine();
        System.out.print("Year of Manufacture: ");
            int YearOfManufacture = kazu.nextInt();
        System.out.print("Value: ");
            double value = kazu.nextDouble();
        kazu.nextLine();
        
        Vehicle vehicle = new Vehicle(regNo, make, YearOfManufacture, value);
        vList.add(vehicle);
        
        System.out.println("Vehicle added successfully.");
    }
    
    public static void deleteVehicle(ArrayList<Vehicle> vList, Scanner kazu) {
        System.out.print("Enter the index of the vehicle to delete: ");
        int VehicleToDelete = kazu.nextInt();
        
            if (VehicleToDelete < 1 || VehicleToDelete > vList.size()) {
                System.out.println("Invalid index, Please enter a valid index.");
                return;
            }
            
            int ModifiedIndex = VehicleToDelete - 1;
            vList.remove(ModifiedIndex);
            System.out.println("Vehicle deleted successfully.");
    }
    
    public static void sortVehicleListByAge(ArrayList<Vehicle> vList, Scanner kazu) {
        boolean sorting = true;
        while (sorting) {
        if (vList.isEmpty()) {
            System.out.println("No vehicle added yet.");
            return;
        }
        
        System.out.println("Sort Menu:");
        System.out.println("1. Sort by age (ascending)");
        System.out.println("2. Sort by age (descending)");
        System.out.println("3. Back to main menu");
        
        System.out.print("Enter your choice: ");
        int choice = kazu.nextInt();
        
        kazu.nextLine();
        
        switch (choice) {
            
            case 1:
                Ascending(vList);
                System.out.println("List of vehicle details sorted by age (ascending):");
                displayVehicleDetails(vList);
                break;
            case 2:
                Descending(vList);
                System.out.println("List of vehicle details sorted by age (descending):");
                displayVehicleDetails(vList);
                break;
            case 3:
                System.out.println("RETURNING TO MAIN MENU...");
                sorting = false;
                break;
                
            default:
                System.out.println("Invalid choice. Please Try Again.");
        }
        }
    }
    
    public static void Ascending(ArrayList<Vehicle> vList) {
        int n = vList.size();
            for (int i = 0; i < n - 1; i++) {
                for (int j = 0; j < n - i - 1; j++) {
                    if (vList.get(j).calculateAge(2004) < vList.get(j + 1).calculateAge(2004)) {
                        Vehicle temp = vList.get(j);
                        vList.set(j, vList.get(j + 1));
                        vList.set(j + 1, temp);
                    }
                }
            }
    }
        
    public static void Descending(ArrayList<Vehicle> vList) {
        int n = vList.size();
            for (int i = 0; i < n - 1; i++) {
                for (int j = 0; j < n - i - 1; j++) {
                    if (vList.get(j).calculateAge(2004) > vList.get(j + 1).calculateAge(2004)) {
                        Vehicle temp = vList.get(j);
                        vList.set(j, vList.get(j + 1));
                        vList.set(j + 1, temp);
                    }
                }
            }
    }
}
